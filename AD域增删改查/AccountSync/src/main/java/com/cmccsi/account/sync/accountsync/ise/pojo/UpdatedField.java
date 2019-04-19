package com.cmccsi.account.sync.accountsync.ise.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)  
@XmlType(name = "updatedField",propOrder = {"newValue","oldValue"}) 
public class UpdatedField {
	
	@XmlAttribute(name="field")
    protected String name;
	
	@XmlElement(name = "NEWVALUE", required = true)
	private String newValue;//信值
	
	
	@XmlElement(name = "OLDVALUE", required = true)
	private String oldValue;//旧值
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getNewValue() {
		return newValue;
	}


	public String getOldValue() {
		return oldValue;
	}


	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}


	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	
	

}
