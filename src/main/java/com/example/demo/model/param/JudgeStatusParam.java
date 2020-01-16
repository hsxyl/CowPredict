package com.example.demo.model.param;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2020/1/12
 */
@Data
public class JudgeStatusParam {
    LocalDateTime beginTime;
    LocalDateTime endTime;
    List<Double> valueList;
}
