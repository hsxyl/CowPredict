package com.example.demo.Model.vo;

import com.example.demo.constant.Global;
import lombok.Data;

import java.util.List;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/11/2
 */
@Data
public class ForecastCowHeatSeqVO {
    /**
     * 奶牛序列相关信息
     */
    CowHeatVO cowHeatSeq;

    /**
     * 预测值
     */
    List<Double> forecast;

    /**
     * 均方误差
     */
    double mse;

    /**
     * l2范式
     */
    double l2;
    /**
     * aic
     */
    double aic;
}
