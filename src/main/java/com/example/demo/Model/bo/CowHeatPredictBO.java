package com.example.demo.model.bo;

import com.example.demo.model.TimeSequence;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2020/1/12
 */
@Data
@Accessors(chain = true)
public class CowHeatPredictBO {
    TimeSequence timeSequence;

}
