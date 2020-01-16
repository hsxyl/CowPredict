package com.example.demo.result;

import com.example.demo.model.PageContent;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2020/1/4
 */
@Data
@Accessors(chain = true)
public class RestPageResultVO<T> {
    int code;
    String msg;
    int total;
    @JsonProperty("page_num")
    int pageNum;
    @JsonProperty(value = "data_list")
    List<T> dataList;
    private RestPageResultVO(List<T> dataList,int total,int page) {
        this.dataList = dataList;
        this.total = total;
        this.pageNum = page;
        this.msg = "success";
        this.code = 200;
    }
    private RestPageResultVO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public static <T> RestPageResultVO<T> success(Collection<T> collection,int total,int pageNum) {
        List<T> dataList = new ArrayList<>(collection);
        return new RestPageResultVO<>(dataList,total,pageNum);
    }
    public static <T> RestPageResultVO<T> success(Collection<T> collection, PageContent pageContent) {
        return success(collection,pageContent.getTotal(),pageContent.getPageNum());
    }
    public static RestPageResultVO fail(String message) {
        return new RestPageResultVO(500,message);
    }
}
