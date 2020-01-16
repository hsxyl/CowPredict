package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xushenbao
 * @desc 牛
 * @create 2019/11/1
 */
@Data
@Accessors(chain = true)
public class Cow {
    /**
     * id
     */
    private Long id;
    /**
     * 名字
     */
    private String name;
    /**
     * 年龄
     */
    private Integer old;
    /**
     * 入场时间
     */
    @JsonProperty("catch_time")
    private String catchTime;
    /**
     * 体重
     */
    private Double weight;
    /**
     * 繁殖状态
     */
    @JsonProperty("breed_status")
    private String breedStatus;
    /**
     * 是否发情
     */
    @JsonProperty("is_heat")
    private Boolean isHeat;


    public Cow() {

    }
    public Cow(Long id) {
        this.id = id;
    }
}
