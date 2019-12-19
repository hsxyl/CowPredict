package com.example.demo.Model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/11/2
 */
@Data
@Accessors(chain = true)
public class CowHeatSeqPO {
    /**
     * id
     */
    Long id;
    /**
     * 开始时间
     */
    String startTime;
    /**
     * 温度
     */
    String heats;
    /**
     * 时间单位
     */
    String timeUnit = "DAY";

    public CowHeatSeqPO setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
        return this;
    }
}
