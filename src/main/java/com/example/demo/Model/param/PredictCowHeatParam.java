package com.example.demo.model.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xushenbao
 * @desc 预测奶牛体温参数
 * @create 2020/1/12
 */
@Data
public class PredictCowHeatParam {
    @JsonProperty("origin_value")
    List<Double> originValue;
    @JsonProperty("predict_num")
    int predictNum;
    @JsonProperty("begin_time")
    LocalDateTime beginTime;
}
