package com.cmccsi.account.sync.accountsync.ise.pojo;


import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ns2:updatedFields")
public class ResBean {
	
	  @XmlAttribute(name="xmlns:ns2")
      protected String ns2="ers.ise.cisco.com";
	   
	  @XmlElement(name = "", required = true)
	  List <UpdatedField>  UpdatedField ;
	  
	  
	




	public List<UpdatedField> getUpdatedField() {
		return UpdatedField;
	}


	public void setUpdatedField(List<UpdatedField> updatedField) {
		UpdatedField = updatedField;
	}


	@Override
	public String toString() {
		return "ResBean [UpdatedField=" + UpdatedField + ", getUpdatedField()=" + getUpdatedField() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}





	  
	  
	  
	  
	  
}
