package com.cmccsi.account.sync.accountsync.ise.pojo;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ns3:internaluser")
public class ReqEntity {
	
	
	    @XmlAttribute(name="description")
	    protected String description=null;
	    
	    @XmlAttribute(name="name")
	    protected String name=null;
	    
	    @XmlAttribute(name="id")
	    protected String id=null;
	    
	    @XmlAttribute(name="xmlns:ns2")
	    protected String ns2="ers.ise.cisco.com";
	    
	    @XmlAttribute(name="xmlns:ns3")
	    protected String ns3="http://wx.gmw9.com";
	    
	    
//	    @XmlElement(required = true,name="")
//	    private  AddHeader customAttributes;
	    
	    @XmlElementWrapper(name = "customAttributes")
		@XmlElement(name = "ENTRY", required = true)
		List<ReqHeader> customAttributes;
	    
	  /*  @XmlElement(required = true,name="")
	    protected AddReqBody body;*/
	  @XmlElement(name = "CHANGEPASSWORD", required = true)
	  private String changePassword;//邮箱


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
	    
	    
	    

		public String getDescription() {
			return description;
		}

		public String getName() {
			return name;
		}

		public String getId() {
			return id;
		}

		public String getNs2() {
			return ns2;
		}

		public String getNs3() {
			return ns3;
		}

	

	/*	public AddReqBody getBody() {
			return body;
		}*/

		public void setDescription(String description) {
			this.description = description;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setId(String id) {
			this.id = id;
		}

		public void setNs2(String ns2) {
			this.ns2 = ns2;
		}

		public void setNs3(String ns3) {
			this.ns3 = ns3;
		}

		
/*
		public void setBody(AddReqBody body) {
			this.body = body;
		}*/



		public String getEmail() {
			return email;
		}

		/*public AddHeader getCustomAttributes() {
			return customAttributes;
		}

		public void setCustomAttributes(AddHeader customAttributes) {
			this.customAttributes = customAttributes;
		}*/

		
		
		public String getEnabled() {
			return enabled;
		}

		public List<ReqHeader> getCustomAttributes() {
			return customAttributes;
		}

		public void setCustomAttributes(List<ReqHeader> customAttributes) {
			this.customAttributes = customAttributes;
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

	public String getChangePassword() {
		return changePassword;
	}

	public void setChangePassword(String changePassword) {
		this.changePassword = changePassword;
	}
}
