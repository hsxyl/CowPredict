package com.example.demo.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/11/30
 */
@Data
@Accessors(chain = true)
public class Device {
    Long id;
    Long cowId;
    String buyTime;
    String installTime;
}
