package com.cmcc.xpa.service.microservice.notes.model.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RequestList {

    /**
     * 开始时间
     */
    private String StarTime;

    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 搜索词，非必填
     */
    private String pSysContent;

    /**
     * 页码，起始为1，非必填
     */
    Integer page;

    /**
     * 页面大小，不传查全部。非必填
     */
    Integer pageSize;

    public String getpSysContent() {
        return pSysContent;
    }

    public void setpSysContent(String pSysContent) {
        this.pSysContent = pSysContent;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getStarTime() {
        return StarTime;
    }

    public void setStarTime(String starTime) {
        StarTime = starTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
