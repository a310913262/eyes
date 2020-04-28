package com.cmccsi.user.server.entity;

import java.io.Serializable;

/**
 * (Hr)实体类
 *
 * @author makejava
 * @since 2020-04-27 19:59:05
 */
public class Hr implements Serializable {
    private static final long serialVersionUID = -63506447963818592L;
    /**
    * hrID
    */
    private Integer id;
    /**
    * 姓名
    */
    private String name;
    /**
    * 手机号码
    */
    private String phone;
    /**
    * 住宅电话
    */
    private String telephone;
    /**
    * 联系地址
    */
    private String address;
    
    private Boolean enabled;
    /**
    * 用户名
    */
    private String username;
    /**
    * 密码
    */
    private String password;
    
    private String userface;
    
    private String remark;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserface() {
        return userface;
    }

    public void setUserface(String userface) {
        this.userface = userface;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}