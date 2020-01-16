package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 分页类
 * @author yandongshan on 2019/01/10.
 */
@Data
@AllArgsConstructor
public class PageContent {
    /**
     * 总页数
     */
    @JsonProperty("total_page")
    private int pages;

    /**
     * 总数
     */
    @JsonProperty("total_count")
    private int total;

    /**
     * 页码，从1开始
     */
    @JsonProperty("page_number")
    private int pageNum;

    /**
     * 页面大小
     */
    @JsonProperty("page_size")
    private int pageSize;

    public PageContent() {}

    public PageContent(int pageNum, int pageSize) {
        this(0, pageNum, pageSize);
    }

    public PageContent(int total, int pageNum, int pageSize) {
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public int getPages() {
        return Math.max(0,this.total-1)/this.pageSize+ 1;
    }

    @JsonIgnore
    public int getStart() {
        return (this.pageNum - 1) * pageSize;
    }

}
