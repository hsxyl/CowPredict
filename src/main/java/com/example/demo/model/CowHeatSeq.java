package com.example.demo.model;

import com.example.demo.constant.Global;
import com.example.demo.util.MyCollectionUtil;
import com.example.demo.util.OffsetDateTimeUtil;
import com.github.signaflo.timeseries.TimeSeries;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * @author xushenbao
 * @desc 奶牛体温
 * @create 2019/11/1
 */
@Data
@Accessors(chain = true)
public class CowHeatSeq {
    Long cowId;

    TimeSeries timeSeries;



    public CowHeatSeq(long startTime, List<Double> heats) {
        OffsetDateTime dateTime = OffsetDateTimeUtil.fromLong(startTime);
        timeSeries = TimeSeries.from(Global.TIME_UNIT,dateTime, MyCollectionUtil.listToArray(heats));
    }

    public CowHeatSeq() {

    }

}
