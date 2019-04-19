package com.cmccsi.account.sync.accountsync.ldap.domain;


import java.util.List;


import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;

@Entry(objectClasses = {"group", "top"})
public class Group {

    @Attribute(name = "member")
    private List<String> members;

    @Attribute(name = "name")
    private String name;

    @Attribute(name = "groupType")
    private String groupType;

    @Attribute(name = "distinguishedName")
    private String dn;

    @Attribute(name = "cn")
    private String commonName;



    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    @Override
    public String toString() {
        return "Group [members=" + members + ", name=" + name
                + ", groupType=" + groupType + ", dn=" + dn + ", commonName=" + commonName + "]";
    }


}

