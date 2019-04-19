package com.cmccsi.account.sync.accountsync.ise.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)  
@XmlType(name = "ENTRY",  propOrder = { "key","value"})
@XmlRootElement(name = "aaaa")
public class ReqHeader {
	
	@XmlElement(name = "KEY")
	String key;//
	
	@XmlElement(name = "VAIUE")
	String value;//

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
