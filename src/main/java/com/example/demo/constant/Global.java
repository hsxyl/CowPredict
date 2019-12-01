package com.example.demo.constant;


import com.github.signaflo.timeseries.TimePeriod;
import com.github.signaflo.timeseries.TimeUnit;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/11/2
 */
public interface Global {
    TimeUnit TIME_UNIT = TimeUnit.HOUR;
    TimePeriod TIME_PERIOD = new TimePeriod(TIME_UNIT,2);
}
