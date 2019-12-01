package com.example.demo.algo;

import com.example.demo.Model.param.ForecastCowHeatSeqParam;
import com.example.demo.constant.Global;
import com.example.demo.util.MyCollectionUtil;
import com.example.demo.util.OffsetDateTimeUtil;
import com.github.signaflo.timeseries.TimeSeries;
import com.github.signaflo.timeseries.forecast.Forecast;
import com.github.signaflo.timeseries.model.arima.Arima;
import com.github.signaflo.timeseries.model.arima.ArimaOrder;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;


/**
 * @author xushenbao
 * @desc https://github.com/signaflo/java-timeseries/wiki/ARIMA%20models
 * @create 2019/10/31
 */
public class Predict {
    public static Forecast predict(ForecastCowHeatSeqParam param,int bound) {
        TimeSeries timeSeries =
                TimeSeries.from(Global.TIME_UNIT,
                        OffsetDateTimeUtil.fromString(param.getStartTime()),
                                MyCollectionUtil.listToArray(param.getHeats().subList(0,bound)));
        ArimaOrder modelOrder = ArimaOrder.order(0,1,1,0,1,1);
        Arima model = Arima.model(timeSeries,modelOrder);
        return model.forecast(param.getHeats().size()-bound);
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
