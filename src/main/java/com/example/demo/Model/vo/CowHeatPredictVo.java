package com.example.demo.model.vo;

import com.example.demo.deser.LocalDateTimeStandardDeserialize;
import com.example.demo.deser.LocalDateTimeStandardSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xushenbao
 * @desc 奶牛体温预测结果VO
 * @create 2020/1/17
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class CowHeatPredictVO {
    @JsonProperty(value = "begin_time")
    @JsonSerialize(using = LocalDateTimeStandardSerialize.class)
    @JsonDeserialize(using = LocalDateTimeStandardDeserialize.class)
    LocalDateTime beginTime;
    @JsonProperty(value = "end_time")
    @JsonSerialize(using = LocalDateTimeStandardSerialize.class)
    @JsonDeserialize(using = LocalDateTimeStandardDeserialize.class)
    LocalDateTime endTime;
    @JsonProperty(value = "cow_heat")
    List<Double> cowHeat;
}
