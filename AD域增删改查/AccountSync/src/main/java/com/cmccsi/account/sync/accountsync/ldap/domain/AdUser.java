package com.cmccsi.account.sync.accountsync.ldap.domain;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;

@Entry(objectClasses = {"user", "organizationalPerson", "person", "top"})
public class AdUser {
 /*
  sn	姓	string
givenName	名	string
initials	英文缩写	string
displayName	显示名称	string
description	描述	string
physicalDeliveryOfficeName	办公室	string
mail	电子邮件	string
telephoneNumber	电话号码	string
wWWHomePage	网页	string
co	地址-国家/地区	string
c	地址-国家/地区缩写	string
countryCode	地址-国家/地区编码	int
st	地址-省/自治区	string
l	地址-市/县	string
streetAddress	地址-街道	string
postalCode	地址-邮政编码	string
postOfficeBox	地址-邮政信箱	string
title	职务	string
department	部门	string
company	公司	string
directReports	直接下属	string[]
homePhone	家庭电话	string
mobile	移动电话	string
facsimileTelephoneNumber	传真	string
ipPhone	IP电话	string
pager	寻呼机	string
info	注释	string
manager	经理	string
otherMobile	移动电话其它	string[]
otherIpPhone   IP电话其它	string[]
  */

    // 唯一（uuid）
    @DnAttribute(value = "CN", index = 0)
    @Attribute(name = "sAMAccountName")
    private String accountName;

    @Attribute(name = "userAccountControl")
    private String userAccountControl;

    private String userPassword;
    // 唯一
    @Attribute(name = "cn")
    private String commonName;

    @Attribute(name = "sn")
    private String surName;

    @Attribute(name = "givenName")
    private String givenName;

    @Attribute(name = "distinguishedName")
    private String dn;

    @Attribute(name = "displayName")
    private String displayName;

    @Attribute(name = "mobile")
    private String mobile;

    @Attribute(name = "mail")
    private String email;

    @Attribute(name = "title")
    private String title;

    @Attribute(name = "description")
    private String description;

    @Attribute(name = "st")
    private String st;


    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserAccountControl() {
        return userAccountControl;
    }

    public void setUserAccountControl(String userAccountControl) {
        this.userAccountControl = userAccountControl;
    }



    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }



    @Override
    public String toString() {
        return "AdUser [accountName=" + accountName + ", userAccountControl=" + userAccountControl + ", userPassword="
                + userPassword + ", commonName=" + commonName + ", surName=" + surName + ", givenName=" + givenName
                + ", dn=" + dn + ", displayName=" + displayName + ", mobile=" + mobile + ", email=" + email + ", title="
                + title + ", description=" + description + ", st=" + st + "]";
    }
}
