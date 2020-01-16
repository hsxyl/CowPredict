package com.example.demo.model.vo;

import com.example.demo.deser.LocalDateTimeStandardDeserialize;
import com.example.demo.deser.LocalDateTimeStandardSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2020/1/11
 */
@Data
@Accessors(chain = true)
public class TrueCowHeatVO {
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
//    List<>
}
