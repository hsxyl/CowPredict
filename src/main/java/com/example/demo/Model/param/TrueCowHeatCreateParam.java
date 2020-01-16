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
 * @desc 添加描述
 * @create 2020/1/11
 */
@Data
public class TrueCowHeatCreateParam {
    @JsonProperty("cow_id")
    Integer cowId;
    @JsonProperty("begin_time")
    @JsonSerialize(using = LocalDateTimeStandardSerialize.class)
    @JsonDeserialize(using = LocalDateTimeStandardDeserialize.class)
    LocalDateTime beginTime;
    @JsonProperty("end_time")
    @JsonSerialize(using = LocalDateTimeStandardSerialize.class)
    @JsonDeserialize(using = LocalDateTimeStandardDeserialize.class)
    LocalDateTime endTime;
    @JsonProperty("heat_list")
    List<Double> heatList;
}
