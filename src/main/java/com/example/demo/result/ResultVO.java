package com.sankuai.meituan.waimai.digger.pushcenter.model;

import lombok.Data;
import lombok.ToString;

/**
 * @author liuqingwen
 * @desc 统一返回封装
 * @create 2019/10/24
 */
@ToString
@Data
public class ResultVO {
    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;


    /**
     * 返回数据
     */
    private Object data;
}
