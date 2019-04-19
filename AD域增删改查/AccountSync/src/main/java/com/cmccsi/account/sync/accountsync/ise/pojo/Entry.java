package com.cmccsi.account.sync.accountsync.ise.pojo;

import javax.xml.bind.annotation.XmlElement;

public class Entry {


    String key;


    String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
