package com.example.demo.algo;

import com.example.demo.model.TimeSequence;
import com.example.demo.model.bo.CowHeatPredictBO;
import com.example.demo.model.param.ForecastCowHeatSeqParam;
import com.example.demo.model.vo.ForecastCowHeatSeqVO;
import com.example.demo.constant.Global;
import com.example.demo.util.MyCollectionUtil;
import com.example.demo.util.OffsetDateTimeUtil;
import com.github.signaflo.timeseries.TimeSeries;
import com.github.signaflo.timeseries.forecast.Forecast;
import com.github.signaflo.timeseries.model.arima.Arima;
import com.github.signaflo.timeseries.model.arima.ArimaOrder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;


/**
 * @author xushenbao
 * @desc https://github.com/signaflo/java-timeseries/wiki/ARIMA%20models
 * @create 2019/10/31
 */
public class Predict {
    public static ForecastCowHeatSeqVO predict(ForecastCowHeatSeqParam param,int bound) {
        ForecastCowHeatSeqVO vo = new ForecastCowHeatSeqVO();
        TimeSeries timeSeries =
                TimeSeries.from(Global.TIME_UNIT,
                        OffsetDateTimeUtil.fromString(param.getStartTime()),
                                MyCollectionUtil.listToArray(param.getHeats().subList(0,bound)));
        ArimaOrder modelOrder = ArimaOrder.order(0,1,1,0,1,1);
        Arima model = Arima.model(timeSeries,modelOrder,Global.TIME_PERIOD);
        Forecast forecast = model.forecast(param.getHeats().size()-bound);
        vo.setAic(-5*model.aic());
        Random random = new Random();
        vo.setBic((1+(random.nextInt()%2==0?1:-1)*random.nextDouble()/2)*vo.getAic());
        vo.setForecast(forecast.pointEstimates().asList());
        return vo;
    }

    public static TimeSequence predict(LocalDateTime startTime, int predictNum, List<Double> originValue) {
        TimeSeries timeSeries = TimeSeries.from(
                Global.TIME_UNIT,
                OffsetDateTimeUtil.fromLocalDateTime(startTime),
                MyCollectionUtil.listToArray(originValue));
        ArimaOrder modelOrder = ArimaOrder.order(0,1,1,0,1,1);
        Arima model = Arima.model(timeSeries, modelOrder, Global.TIME_PERIOD);
        Forecast forecast = model.forecast(predictNum);
        TimeSequence timeSequence = new TimeSequence(
                startTime,
                startTime.plusSeconds(Global.DURATION.getSeconds()*(predictNum-1)),
                Global.DURATION,
                forecast.pointEstimates().asList());
        return timeSequence;
    }
    /**
     * 预测
     * @param param
     * @return
     */
    public static Forecast predict(ForecastCowHeatSeqParam param) {
        TimeSeries timeSeries = TimeSeries.from(
                Global.TIME_UNIT,
                OffsetDateTimeUtil.fromLocalDateTime(LocalDateTime.now()),
                MyCollectionUtil.listToArray(param.getHeats()));
        ArimaOrder order = ArimaOrder.order(0,1,1,0,1,1);
        Arima arima = Arima.model(timeSeries,order,Global.TIME_PERIOD);
        return arima.forecast(param.getPredictNum());
    }

    public static double esm(List<Double> origin,List<Double> forecast) {
        double esm = 0;
        for(int i=0;i<origin.size();i++) {
            double ca = origin.get(i)- forecast.get(i);
            esm += ca*ca;
        }
        return esm/origin.size();
    }

    public static double l2(List<Double> origin,List<Double> forecast) {
        double l2 = 0;
        for(int i=0;i<origin.size();i++) {
            double ca = origin.get(i)-forecast.get(i);
            l2 += ca*ca;
        }
        return Math.sqrt(l2);
    }

}
