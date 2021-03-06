package com.example.demo.model.param;

import com.example.demo.deser.LocalDateTimeStandardDeserialize;
import com.example.demo.deser.LocalDateTimeStandardSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    @JsonProperty("cow_id")
    Integer cowId;
    @JsonProperty("predict_num")
    Integer predictNum;
    @JsonProperty("begin_time")
    @JsonSerialize(using = LocalDateTimeStandardSerialize.class)
    @JsonDeserialize(using = LocalDateTimeStandardDeserialize.class)
    LocalDateTime beginTime;
}
