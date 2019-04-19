package com.cmccsi.account.sync.accountsync.ise.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)  
@XmlType(name = "",propOrder = {"email","enabled","firstName","identityGroups","lastName","password"}) 
public class ReqBody {
	
	@XmlElement(name = "EMAIL", required = true)
	private String email;//邮箱
	
	
	@XmlElement(name = "ENABLED", required = true)
	private String enabled;//启用
	
	@XmlElement(name = "FIRSTNAME", required = true)
	private String firstName;//名字
	
	
	@XmlElement(name = "IDENTITYGROUPS", required = true)
	private String identityGroups;//身份组id
	
	@XmlElement(name = "LASTANME", required = true)
	private String lastName;//身份组id
	
	
	@XmlElement(name = "PASSWORD", required = true)
	private String password;//密码


	public String getEmail() {
		return email;
	}


	public String getEnabled() {
		return enabled;
	}


	public String getFirstName() {
		return firstName;
	}


	public String getIdentityGroups() {
		return identityGroups;
	}


	public String getLastName() {
		return lastName;
	}


	public String getPassword() {
		return password;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public void setIdentityGroups(String identityGroups) {
		this.identityGroups = identityGroups;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public void setPassword(String password) {
		this.password = password;
	}


}
