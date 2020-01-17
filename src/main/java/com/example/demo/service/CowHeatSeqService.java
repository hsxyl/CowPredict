package com.example.demo.service;
import com.example.demo.model.TimeSequence;
import com.example.demo.model.bo.CowHeatPredictBO;
import com.example.demo.model.vo.ForecastSeqVO;
import com.example.demo.mapper.CowHeatSeqMapper;
import com.example.demo.model.CowHeatSeq;
import com.example.demo.model.CowHeatSeqPO;
import com.example.demo.model.param.CreateHeatSeqParam;
import com.example.demo.model.param.ForecastCowHeatSeqParam;
import com.example.demo.model.param.SaveHeatSeqParam;
import com.example.demo.model.vo.CowHeatVO;
import com.example.demo.model.vo.ForecastCowHeatSeqVO;
import com.example.demo.algo.Predict;
import com.example.demo.constant.Global;
import com.example.demo.convert.CowHeatSeqConvert;
import com.example.demo.sql.entity.TrueCowTemperature;
import com.example.demo.sql.entity.TrueCowTemperatureExample;
import com.example.demo.sql.mapper.TrueCowTemperatureMapper;
import com.example.demo.util.MyCollectionUtil;
import com.example.demo.util.OffsetDateTimeUtil;
import com.github.signaflo.timeseries.TimeSeries;
import com.github.signaflo.timeseries.forecast.Forecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/11/2
 */
@Service
public class CowHeatSeqService {

    @Autowired
    CowHeatSeqMapper cowHeatSeqMapper;
    @Autowired
    TrueCowTemperatureMapper trueCowTemperatureMapper;

    public CowHeatVO createCowHeatSeq(CreateHeatSeqParam param) {
        double range = param.getUpper()-param.getLower();

        List<Double> heats = new ArrayList<>();
        for(int i=0;i<param.getSum();i++) {
            heats.add(param.getLower()+Math.random()*range);
        }

        CowHeatVO cowHeatSeq = new CowHeatVO();
        cowHeatSeq.setHeats(heats);
        cowHeatSeq.setStartTime(param.getStartTime());
        return cowHeatSeq;
    }

    public int saveCowHeatSeq(SaveHeatSeqParam param) {

        CowHeatSeq cowHeatSeq = new CowHeatSeq();
        cowHeatSeq.setCowId(param.getCowId());

        TimeSeries timeSeries =
                TimeSeries.from(Global.TIME_UNIT,
                        OffsetDateTimeUtil.fromLong(param.getStartTime()),
                        MyCollectionUtil.listToArray(param.getHeats()));
        cowHeatSeq.setTimeSeries(timeSeries);
        CowHeatSeqPO cowHeatSeqPO = CowHeatSeqConvert.seqToPO(cowHeatSeq);
        return cowHeatSeqMapper.insert(cowHeatSeqPO);
    }


    public ForecastSeqVO forecastSeq(ForecastCowHeatSeqParam param) {
        Forecast forecast = Predict.predict(param);
        ForecastSeqVO forecastSeqVO = new ForecastSeqVO();
        forecastSeqVO.setResult(forecast.pointEstimates().asList());
        return forecastSeqVO;
    }

    public ForecastCowHeatSeqVO forecastCowHeatSeq(ForecastCowHeatSeqParam param){

        int bound = (int)(param.getHeats().size()*param.getRatio());
        List right = param.getHeats().subList(bound,param.getHeats().size());
        ForecastCowHeatSeqVO vo = Predict.predict(param,bound);

        vo.setMse(Predict.esm(right, vo.getForecast()));
        vo.setL2(Predict.l2(right, vo.getForecast()));
        return vo;
    }

    /**
     * 获取预测用的原始数据
     * @param cowId 奶牛id
     * @param predictStartTime 预测开始时间
     * @return
     */
    public TimeSequence getOriginValueForPredict(Integer cowId, LocalDateTime predictStartTime) {
        TrueCowTemperatureExample example = new TrueCowTemperatureExample();
        LocalDateTime beginTime = predictStartTime.minusSeconds(Global.DURATION.getSeconds()*(Global.PREDICT_BASE_SUM));
        LocalDateTime endTime = predictStartTime.minusSeconds(Global.DURATION.getSeconds());
        example.or()
                .andCowIdEqualTo(cowId)
                .andTimeBetween(
                        beginTime,
                        endTime);
        List<TrueCowTemperature> trueCowTemperatureList = trueCowTemperatureMapper
                .selectByExample(example)
                .stream()
                .sorted(Comparator.comparing(TrueCowTemperature::getTime))
                .collect(Collectors.toList());
        return new TimeSequence(
                trueCowTemperatureList.get(0).getTime(),
                trueCowTemperatureList.get(trueCowTemperatureList.size()-1).getTime(),
                Global.DURATION,
                trueCowTemperatureList.stream()
                        .map(TrueCowTemperature::getValue)
                        .collect(Collectors.toList()));
    }

    /**
     * 预测奶牛体温
     * @param originValue 原始数据
     * @param predictNum 预测个数
     * @param beginTime 开始时间
     * @return
     */
    public CowHeatPredictBO predictCowHeat(List<Double> originValue, int predictNum, LocalDateTime beginTime) {
        beginTime = LocalDateTime.of(
                beginTime.getYear(),
                beginTime.getMonth(),
                beginTime.getDayOfMonth(),
                beginTime.getHour(),0,0);
        TimeSequence timeSequence  = Predict.predict(beginTime,predictNum,originValue);
        return new CowHeatPredictBO(
                timeSequence.getBeginDateTime(),
                timeSequence.getEndDateTime(),
                timeSequence.getTimeDataList());
    }
}
