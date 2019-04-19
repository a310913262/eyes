package com.cmccsi.account.sync.accountsync.elasticsearch.domain;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class EsUser {

    private String name;
    private String pwd;
    private JSONObject attr;
    private List<String> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public JSONObject getAttr() {
        return attr;
    }

    public void setAttr(JSONObject attr) {
        this.attr = attr;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "EsUser{" +
                "name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", attr=" + attr +
                ", roles=" + roles +
                '}';
    }
}
