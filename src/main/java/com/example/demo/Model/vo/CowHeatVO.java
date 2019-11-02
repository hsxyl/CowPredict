package com.example.demo.Model.vo;

import com.example.demo.constant.Global;

import java.util.List;

/**
 * @author xushenbao
 * @desc 奶牛体温VO
 * @create 2019/11/2
 */
public class CowHeadVO {
    List<Double> heats;
    Long startTime;
    Long timeUnit = Global.TIME_UNIT.unitLength();

}
