package com.cmcc.xpa.service.microservice.notes.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class XaSysteamProclamation {
    /**
     * 系统公告id
     */
    private Long p_sys_id;

    /**
     * 系统模块
     */
    private String p_sys_module;

    /**
     * 系统公告标题
     */
    private String p_sys_title;

    /**
     * 标题内容
     */
    private String p_sys_content;

    /**
     * 创建人
     */
    private String create_by;
    /**
     * 创建人id
     */
    private Integer create_byId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_time;

    /**
     * 更新人
     */
    private String update_by;
    /**
     * 更新人id
     */
    private Integer update_byId;;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date update_time;



    public Long getP_sys_id() {
        return p_sys_id;
    }

    public void setP_sys_id(Long p_sys_id) {
        this.p_sys_id = p_sys_id;
    }

    public String getP_sys_module() {
        return p_sys_module;
    }

    public void setP_sys_module(String p_sys_module) {
        this.p_sys_module = p_sys_module;
    }

    public String getP_sys_title() {
        return p_sys_title;
    }

    public void setP_sys_title(String p_sys_title) {
        this.p_sys_title = p_sys_title;
    }

    public String getP_sys_content() {
        return p_sys_content;
    }

    public void setP_sys_content(String p_sys_content) {
        this.p_sys_content = p_sys_content;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Integer getCreate_byId() {
        return create_byId;
    }

    public void setCreate_byId(Integer create_byId) {
        this.create_byId = create_byId;
    }

    public Integer getUpdate_byId() {
        return update_byId;
    }

    public void setUpdate_byId(Integer update_byId) {
        this.update_byId = update_byId;
    }

    @Override
    public String toString() {
        return "XaSysteamProclamation{" +
                "p_sys_id=" + p_sys_id +
                ", p_sys_module='" + p_sys_module + '\'' +
                ", p_sys_title='" + p_sys_title + '\'' +
                ", p_sys_content='" + p_sys_content + '\'' +
                ", create_by='" + create_by + '\'' +
                ", create_time=" + create_time +
                ", update_by='" + update_by + '\'' +
                ", update_time=" + update_time +
                '}';
    }
}