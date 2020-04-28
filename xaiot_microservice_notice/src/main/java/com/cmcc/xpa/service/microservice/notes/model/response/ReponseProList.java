
package com.cmcc.xpa.service.microservice.notes.model.response;


import com.cmcc.xpa.service.microservice.notes.entity.XaProclamation;

import java.io.Serializable;
import java.util.List;


/**
 * 角色
 */
public class ReponseProList implements Serializable {


/**
     * 结果列表
     */

    private List<XaProclamation> list;


/**
     * 总数
     */

    private long total = 0;


    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<XaProclamation> getList() {
        return list;
    }

    public void setList(List<XaProclamation> list) {
        this.list = list;
    }
}

