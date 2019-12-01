package com.example.demo.Model.param;

import com.example.demo.constant.Global;
import com.example.demo.util.LocalDateUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.signaflo.timeseries.TimeSeries;
import com.github.signaflo.timeseries.TimeUnit;
import lombok.Data;

import java.util.List;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/11/2
 */
@Data
public class SaveHeatSeqParam {
    Long cowId;

    List<Double> heats;

    @JsonProperty("start_time")
    long startTime ;

    /**
     * 时间单位
     */
    TimeUnit timeUnit = Global.TIME_UNIT;

    public void setStartTime(String startTime) {
        this.startTime = LocalDateUtil.localDateToMilli(LocalDateUtil.string2LocalDate(startTime));
    }
}
