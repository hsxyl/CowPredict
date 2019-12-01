package com.example.demo.Model.vo;

import com.example.demo.constant.Global;
import com.example.demo.util.LocalDateUtil;
import lombok.Data;

import java.util.List;

/**
 * @author xushenbao
 * @desc 奶牛体温VO
 * @create 2019/11/2
 */
@Data
public class CowHeatVO {
    /**
     * 奶牛id
     */
    Long cowId;

    /**
     * 体温
     */
    List<Double> heats;

    /**
     * 开始时间
     */
    Long startTime;

    /**
     * 时间单位
     */
    String timeUnit = Global.TIME_UNIT.toString();

    public String getStartTime() {
        return LocalDateUtil.localDate2String(LocalDateUtil.milliToLocalDate(startTime));
    }
}
