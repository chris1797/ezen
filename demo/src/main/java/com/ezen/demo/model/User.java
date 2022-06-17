package com.ezen.demo.model;

import java.util.Objects;

public class User {

	private String uid;
	private String pwd;
	
	public User() {}
	
	public User(String uid, String pwd) {
		this.uid = uid;
		this.pwd = pwd;
	}
	public User(String[] token) {
		this(token[0], token[1]);
	}
	@Override
	public int hashCode() {
		return Objects.hash(this.uid, this.pwd);
	}
	
	@Override
	public boolean equals(Object obj) {
		User other = (User) obj;
		return this.uid.equals(other.uid) &&
				this.pwd.equals(other.pwd);
	}
	
//=========================================================================//
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
