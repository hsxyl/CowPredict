package com.example.demo.model.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2020/1/4
 */
@Data
public class CowPageParam {
    private String name;
    @JsonProperty("page_num")
    private int pageNum = 1;
    public void setPage_Num(int page_num) {
        this.pageNum = page_num;
    }
}
