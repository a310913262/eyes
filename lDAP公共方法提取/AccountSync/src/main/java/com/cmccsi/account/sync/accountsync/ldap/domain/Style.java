package com.cmccsi.account.sync.accountsync.ldap.domain;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;

@Entry(objectClasses = {"top", "cmcc-baseBusinessObj", "cmcc-style"})
public class Style {

    @Attribute(name = "name")
    private String name;

    @Attribute(name = "cn")
    private String cn;
    /**
     *  版本
     */
    @Attribute(name = "version")
    private String version;

    /**
     *  主题显示名称
     */
    @Attribute(name = "displayStyleName")
    private String displayStyleName;

    /**
     *  logo路径
     */
    @Attribute(name = "logoPath")
    private String logoPath;

    /**
     *  主题缩略图路径
     */
    @Attribute(name = "stylePath")
    private String stylePath;

    /**
     *  缩略图路径
     */
    @Attribute(name = "imagePath")
    private String imagePath;
    /**
     *  色系
     */
    @Attribute(name = "color")
    private String color;

    /**
     *  主题状态（stop:禁用；start：启用）
     */
    @Attribute(name = "status")
    private String status;

    /**
     *  是否是系统默认皮肤(0:不是默认，1：默认)
     */
    @Attribute(name = "isDefault")
    private String isDefault;


    public String getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDisplayStyleName() {
        return displayStyleName;
    }

    public void setDisplayStyleName(String displayStyleName) {
        this.displayStyleName = displayStyleName;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getStylePath() {
        return stylePath;
    }

    public void setStylePath(String stylePath) {
        this.stylePath = stylePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public String toString() {
        return "Style{" +
                "name='" + name + '\'' +
                ", cn='" + cn + '\'' +
                ", version='" + version + '\'' +
                ", displayStyleName='" + displayStyleName + '\'' +
                ", logoPath='" + logoPath + '\'' +
                ", stylePath='" + stylePath + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", color='" + color + '\'' +
                ", status='" + status + '\'' +
                ", isDefault='" + isDefault + '\'' +
                '}';
    }
}
