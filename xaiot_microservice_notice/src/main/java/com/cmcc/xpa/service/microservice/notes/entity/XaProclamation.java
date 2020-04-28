package com.cmcc.xpa.service.microservice.notes.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class XaProclamation {
    /**
     * 公告主键id
     */
    private Long p_id;

    /**
     * 公告标题
     */
    private String p_title;

    /**
     * 公告类型
     */
    private String p_type;

    /**
     * 推送对象
     */
    private String p_push_object;

    /**
     * 公告内容
     */
    private String p_content;

    /**
     * 是否定时推送
     */
    private String p_isnot_push;

    /**
     * 推送时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date p_push_time;

    /**
     * 推送是否成功
     */
    private String p_is_sucess;

    /**
     * 浏览量
     */
    private Long p_pageview;

    /**
     * 创建人
     */
    private String create_by;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date create_time;

    /**
     * 更新人
     */

    private String update_by;

    /**
     * 更新时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date update_time;

    public Long getP_id() {
        return p_id;
    }

    public void setP_id(Long p_id) {
        this.p_id = p_id;
    }

    public String getP_title() {
        return p_title;
    }

    public void setP_title(String p_title) {
        this.p_title = p_title;
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

    public String getP_content() {
        return p_content;
    }

    public void setP_content(String p_content) {
        this.p_content = p_content;
    }

    public String getP_isnot_push() {
        return p_isnot_push;
    }

    public void setP_isnot_push(String p_isnot_push) {
        this.p_isnot_push = p_isnot_push;
    }

    public Date getP_push_time() {
        return p_push_time;
    }

    public void setP_push_time(Date p_push_time) {
        this.p_push_time = p_push_time;
    }

    public String getP_is_sucess() {
        return p_is_sucess;
    }

    public void setP_is_sucess(String p_is_sucess) {
        this.p_is_sucess = p_is_sucess;
    }

    public Long getP_pageview() {
        return p_pageview;
    }

    public void setP_pageview(Long p_pageview) {
        this.p_pageview = p_pageview;
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
}