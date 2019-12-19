package com.example.demo.Service;

import com.example.demo.Model.vo.ForecastSeqVO;
import com.example.demo.mapper.CowHeatSeqMapper;
import com.example.demo.Model.CowHeatSeq;
import com.example.demo.Model.CowHeatSeqPO;
import com.example.demo.Model.param.CreateHeatSeqParam;
import com.example.demo.Model.param.ForecastCowHeatSeqParam;
import com.example.demo.Model.param.SaveHeatSeqParam;
import com.example.demo.Model.vo.CowHeatVO;
import com.example.demo.Model.vo.ForecastCowHeatSeqVO;
import com.example.demo.algo.Predict;
import com.example.demo.constant.Global;
import com.example.demo.convert.CowHeatSeqConvert;
import com.example.demo.util.MyCollectionUtil;
import com.example.demo.util.OffsetDateTimeUtil;
import com.github.signaflo.timeseries.TimeSeries;
import com.github.signaflo.timeseries.forecast.Forecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/11/2
 */
@Service
public class CowHeatSeqService {

    @Autowired
    CowHeatSeqMapper seqMapper;

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
        return seqMapper.insert(cowHeatSeqPO);
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

}
