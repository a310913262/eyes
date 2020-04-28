package com.cmcc.xpa.service.microservice.notes.model.response;


import com.cmcc.xpa.service.microservice.notes.entity.XaSysteamProclamation;

import java.io.Serializable;
import java.util.List;

/**
 * 角色
 */
public class ReponsetList implements Serializable {

    /**
     * 结果列表
     */
    private List<XaSysteamProclamation> list;

    /**
     * 总数
     */
    private long total = 0;

    public List<XaSysteamProclamation> getList() {
        return list;
    }

    public void setList(List<XaSysteamProclamation> list) {
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
