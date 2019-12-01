package com.example.demo.Model;

import com.example.demo.constant.CowbreedStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.Assert;

import java.util.Arrays;

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
    Integer old;
    /**
     * 入场时间
     */
    String catchTime;
    /**
     * 体重
     */
    Double weight;
    /**
     * 繁殖状态
     */
    String breedStatus;
    /**
     * 是否发情
     */
    Boolean isHeat;


    public Cow() {

    }
    public Cow(Long id) {
        this.id = id;
    }

    public Cow setBreedStatus(String breedStatus) {
        Assert.isTrue(CowbreedStatusEnum.contain(breedStatus),
                "breedStatus类型不对，合法类型:"+ Arrays.toString(CowbreedStatusEnum.values()));
        this.breedStatus = breedStatus;
        return this;
    }
}
