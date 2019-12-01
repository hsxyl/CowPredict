package com.example.demo.Model.param;

import com.example.demo.constant.Global;
import com.example.demo.util.LocalDateUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.signaflo.timeseries.TimeUnit;
import lombok.Data;

import java.time.LocalDate;


/**
 * @author xushenbao
 * @desc 创建体温序列
 * @create 2019/11/2
 */
@Data
public class CreateHeatSeqParam {
    /**
     * 开始时间
     */
    Long startTime;

    /**
     * 总量
     */
    long sum;

    /**
     * 时间单位
     */
    TimeUnit timeUnit = Global.TIME_UNIT;

    /**
     * 上限
     */
    double upper;

    /**
     * 下限
     */
    double lower;


    public void setStartTime(String startTime) {
        this.startTime = LocalDateUtil.localDateToMilli(LocalDateUtil.string2LocalDate(startTime));
    }
}
