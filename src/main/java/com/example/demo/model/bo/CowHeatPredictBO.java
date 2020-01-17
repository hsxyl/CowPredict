package com.example.demo.model.bo;

import com.example.demo.model.TimeSequence;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2020/1/12
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class CowHeatPredictBO {
    LocalDateTime beginTime;
    LocalDateTime endTime;
    List<Double> cowHeat;
}
