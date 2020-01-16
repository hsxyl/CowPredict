package com.example.demo.model.vo;


import lombok.Data;

import java.util.List;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/12/1
 */
@Data
public class ForecastSeqVO {
    /**
     * 预测结果
     */
    List<Double> result;
}
