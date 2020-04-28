package com.cmccsi.user.server.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (DmpNode)实体类
 *
 * @author makejava
 * @since 2020-04-10 15:28:01
 */
public class DmpNode implements Serializable {
    private static final long serialVersionUID = -58921384040270270L;
    /**
    * 节点id
    */
    private String nodeId;
    /**
    * 任务id
    */
    private String taskId;
    /**
    * 节点名称
    */
    private String nodeName;
    /**
    * X轴坐标
    */
    private Integer x;
    /**
    * Y轴坐标
    */
    private Integer y;
    /**
    * 节点执行状态
枚举值：fail(执行失败), succ(执行成功), running(执行中), appending(等待执行)
    */
    private String exeStatus;
    /**
    * 节点类型id
    */
    private String typeId;
    /**
    * 父节点id，多个以英文逗号隔开
    */
    private String parents;
    /**
    * 子节点id，多个以英文逗号隔开
    */
    private String children;
    /**
    * 节点参数
值以json数组形式保存，格式为：
[{参数名：参数值}]
    */
    private String parameters;
    
    private Date createTime;
    
    private Date modifyTime;


    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getExeStatus() {
        return exeStatus;
    }

    public void setExeStatus(String exeStatus) {
        this.exeStatus = exeStatus;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getParents() {
        return parents;
    }

    public void setParents(String parents) {
        this.parents = parents;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

}