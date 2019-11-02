package com.example.demo.Model;

import com.github.signaflo.timeseries.TimePeriod;
import com.github.signaflo.timeseries.TimeSeries;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * @author xushenbao
 * @desc 奶牛体温
 * @create 2019/11/1
 */
@Data
public class CowHeat {
    int cowId;

    TimeSeries timeSeries;

    public static TimePeriod timePeriod = TimePeriod.oneDay();

    long startTime ;

    CowHeat(long startTime, List<Double> heats) {
        OffsetDateTime dateTime = OffsetDateTime.
//        TimeSeries.from(timePeriod,)
    }


}
