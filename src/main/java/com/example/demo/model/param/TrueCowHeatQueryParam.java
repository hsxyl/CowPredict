package com.example.demo.model.param;

import com.example.demo.deser.LocalDateTimeStandardDeserialize;
import com.example.demo.deser.LocalDateTimeStandardSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xushenbao
 * @desc 查询奶牛真实数据的参数
 * @create 2020/1/11
 */
@Data
public class TrueCowHeatQueryParam {
    /**
     * 开始时间
     */
    @JsonProperty("begin_time")
    @JsonSerialize(using = LocalDateTimeStandardSerialize.class)
    @JsonDeserialize(using = LocalDateTimeStandardDeserialize.class)
    LocalDateTime beginTime;

    /**
     * 结束时间
     */
    @JsonProperty("end_time")
    @JsonSerialize(using = LocalDateTimeStandardSerialize.class)
    @JsonDeserialize(using = LocalDateTimeStandardDeserialize.class)
    LocalDateTime endTime;
    /**
     * 奶牛id
     */
    @JsonProperty("cow_id")
    Integer cowId;

}
