package com.training.userservice.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class User {

	private int userid;
	private String username;
	private String addr;
	private String email;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	public User(int userid, String username, String addr, String email,String password) {
		super();
		this.userid = userid;
		this.username = username;
		this.addr = addr;
		this.email = email;
		this.password = password;
	}
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", addr=" + addr + ", email=" + email
				+ ", password=" + password + "]";
	}
	
	
	
	
}
