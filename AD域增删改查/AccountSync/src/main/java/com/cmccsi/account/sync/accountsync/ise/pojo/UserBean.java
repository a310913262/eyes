package com.cmccsi.account.sync.accountsync.ise.pojo;

import java.util.List;

public class UserBean {

    private String id;//用户id

    private String name;//用户名

    private String  description;//描述

    private String firstname;//名字

    private String lastname;//姓氏

    private String changePassword;//是否改变密码

    private String email;//用户邮箱

    private String enabled;//启用

    private String password;//密码

    private String  identityGroups;//分组

    private  List<Entry> entry;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getChangePassword() {
        return changePassword;
    }

    public void setChangePassword(String changePassword) {
        this.changePassword = changePassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getIdentityGroups() {
        return identityGroups;
    }

    public void setIdentityGroups(String identityGroups) {
        this.identityGroups = identityGroups;
    }

    public List<Entry> getEntry() {
        return entry;
    }

    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
