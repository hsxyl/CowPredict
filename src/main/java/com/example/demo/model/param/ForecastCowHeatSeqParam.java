package com.example.demo.model.param;

import lombok.Data;

import java.util.List;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/11/2
 */
@Data
public class ForecastCowHeatSeqParam {
    Integer cowId;

    /**
     * 序列
     */
    List<Double> heats;

    String startTime;

    String timeUnit;

    Double ratio;

    /**
     * 预测个数
     */
    Integer predictNum;

}
