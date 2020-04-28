package com.cmcc.xpa.service.microservice.notes.model.request;


public class RequestProList {

    /**
     * 搜索词，非必填
     */
    private String p_type;

    /**
     * 搜索词，非必填
     */
    private String p_push_object;
    /**
     * 搜索词，非必填
     */
    private String p_is_sucess;
    /**
     * 开始时间
     */
    private String Star_time;

    /**
     * 结束时间
     */
    private String end_time;


    /**
     * 页码，起始为1，非必填
     */
    Integer page;

    /**
     * 页面大小，不传查全部。非必填
     */
    Integer pageSize;

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

    public String getP_type() {
        return p_type;
    }

    public void setP_type(String p_type) {
        this.p_type = p_type;
    }

    public String getP_push_object() {
        return p_push_object;
    }

    public void setP_push_object(String p_push_object) {
        this.p_push_object = p_push_object;
    }

    public String getP_is_sucess() {
        return p_is_sucess;
    }

    public void setP_is_sucess(String p_is_sucess) {
        this.p_is_sucess = p_is_sucess;
    }

    public String getStar_time() {
        return Star_time;
    }

    public void setStar_time(String star_time) {
        Star_time = star_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
