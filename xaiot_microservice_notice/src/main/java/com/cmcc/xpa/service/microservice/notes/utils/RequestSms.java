package com.cmcc.xpa.service.microservice.notes.utils;



public class RequestSms {

    /**
     * 用户ID，非必填
     */
    private String receiverId;

    /**
     * 模板ID，非必填
     */
    private String tempId;

    /**
     * 短信内容，非必填
     */
    private String smsContent;

    /**
     * 下发时间，非必填
     */
    private String sendTime;
    /**
     * 下发状态，非必填
     */
    private String errorCode;

    /**
     * 页码，起始为1，非必填
     */
    Integer page;

    /**
     * 页面大小，不传查全部。非必填
     */
    Integer pageSize;

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getTempId() {
        return tempId;
    }

    public void setTempId(String tempId) {
        this.tempId = tempId;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
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
