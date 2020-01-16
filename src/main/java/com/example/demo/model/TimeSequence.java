package com.sankuai.meituan.tsp.digger.horae.core.algorithms.common;

import com.sankuai.meituan.tsp.digger.base.gungnir.util.DateTimeUtil;
import com.sankuai.meituan.tsp.digger.horae.core.domain.TimeSeriesData;
import lombok.Data;
import org.springframework.util.Assert;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xushenbao
 * @desc 时间序列
 * @create 2019/12/31
 */
@Data
public class TimeSequence {
    /**
     * 开始时间
     */
    private LocalDateTime beginDateTime;
    /**
     * 结束时间
     */
    private LocalDateTime endDateTime;
    /**
     * 时间间隔
     */
    private Duration duration;
    /**
     * 数据列表
     */
    private List<Double> timeDataList;

    /**
     * 是否包含
     * @return
     */
    public boolean isContain(LocalDateTime time) {
        // 先判断是否在时间区间内
        if(time.isBefore(this.beginDateTime)||time.isAfter(this.endDateTime)) {
            return false;
        }
        // 再判断是否在时间点上
        if((Duration.between(time,beginDateTime).getSeconds())%this.duration.getSeconds()!=0) {
            return false;
        }
        return true;
    }

    public TimeSequence(TimeSeriesData timeSeriesData) {
        this.beginDateTime = timeSeriesData.getBeginDateTime();
        this.endDateTime = timeSeriesData.getEndDateTime();
        this.duration = timeSeriesData.getDuration();
        this.timeDataList = timeSeriesData.getTimeSeriesValueListWithDefault(0);
    }
    public TimeSequence(LocalDateTime beginDateTime, LocalDateTime endDateTime, Duration duration, List<Double> timeDataList) {
        this.beginDateTime = beginDateTime;
        this.endDateTime = endDateTime;
        this.duration = duration;
        this.timeDataList = timeDataList;
    }


    public Double getValue(int index) {
        return timeDataList.get(index);
    }

    public Double getValue(LocalDateTime dateTime) {
        return getValue(getIndex(dateTime));
    }

    public void setValue(int index,double v) {this.timeDataList.set(index,v); }
    public void setValue(LocalDateTime index,double v) {
        setValue(getIndex(index),v);
    }

    /**
     * 通过索引随机访问
     * @param index
     * @return
     */
    private int getIndex(LocalDateTime index) {
        // 偏移量
        long offset = DateTimeUtil.toEpochSeconds(index) - DateTimeUtil.toEpochSeconds(this.beginDateTime);
        Assert.isTrue(offset>=0,"传入时间不能小于开始时间");
        Assert.isTrue(offset%duration.getSeconds()==0,"无该时间点的时序数据");
        return (int)(offset/duration.getSeconds());
    }
}
