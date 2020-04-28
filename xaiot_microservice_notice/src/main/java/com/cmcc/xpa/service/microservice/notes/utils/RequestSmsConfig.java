package com.cmcc.xpa.service.microservice.notes.utils;

public class RequestSmsConfig {

    /**
     * 页码，起始为1，非必填
     */
    private String name;
    /**
     * 页码，起始为1，非必填
     */
    Integer page;

    /**
     * 页面大小，不传查全部。非必填
     */
    Integer pageSize;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
