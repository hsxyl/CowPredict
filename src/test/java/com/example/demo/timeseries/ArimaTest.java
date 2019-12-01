package com.example.demo.timeseries;

import com.github.signaflo.timeseries.TestData;
import com.github.signaflo.timeseries.TimeSeries;
import com.github.signaflo.timeseries.forecast.Forecast;
import com.github.signaflo.timeseries.model.arima.Arima;
import com.github.signaflo.timeseries.model.arima.ArimaOrder;
import org.junit.Test;

import static com.github.signaflo.data.visualization.Plots.plot;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/11/2
 */
public class ArimaTest {
    @Test
    public void testArima() {
        TimeSeries timeSeries = TestData.debitcards;
        ArimaOrder modelOrder = ArimaOrder.order(0,1,1,0,1,1);
        Arima model = Arima.model(timeSeries, modelOrder);

        System.out.println("aic:");
        System.out.println(model.aic()); // Get and display the model AIC
        System.out.println("\ncoefficients:");
        System.out.println(model.coefficients()); // Get and display the estimated coefficients
        System.out.println("\nstderrors:");
        System.out.println(java.util.Arrays.toString(model.stdErrors()));
        plot(model.predictionErrors());

        System.out.println("forecast:\n");
        Forecast forecast = model.forecast(12);
        System.out.println(forecast);
    }
}
