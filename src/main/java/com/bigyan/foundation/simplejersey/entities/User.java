package com.bigyan.foundation.simplejersey.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	String userName;
	String password;
	String phoneNo;
	String address;
	
	 public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
