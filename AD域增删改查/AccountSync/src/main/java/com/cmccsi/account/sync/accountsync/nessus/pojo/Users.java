package com.cmccsi.account.sync.accountsync.nessus.pojo;

public class Users {
    //用户id
    private  String id;
    //用户姓名
    private String name;
    //用户权限
    private String permissions;
    //有户最后登录时间
    private  String lastlogin;
    //登录名
    private String username;
    //登录密码
    private String password;
    //用户邮箱
    private String  email;
    //用户类型
    private String type;
    //未知
    private String pro7_whats_new;
    //旧密码
    private String current_password;

    public String getCurrent_password() {
        return current_password;
    }

    public void setCurrent_password(String current_password) {
        this.current_password = current_password;
    }

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

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(String lastlogin) {
        this.lastlogin = lastlogin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPro7_whats_new() {
        return pro7_whats_new;
    }

    public void setPro7_whats_new(String pro7_whats_new) {
        this.pro7_whats_new = pro7_whats_new;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

