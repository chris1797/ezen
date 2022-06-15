package com.ezen.vo;

import java.util.Objects;

public class UserVO 
{
	private String uid;
	private String pwd;
	
	public UserVO() {}
	public UserVO(String uid, String pwd) {
		this.uid = uid;
		this.pwd = pwd;
	}
	public UserVO(String[] token) {
		this(token[0], token[1]);
	}
	@Override
	public int hashCode() {
		return Objects.hash(this.uid, this.pwd);
	}
	@Override
	public boolean equals(Object obj) {
		UserVO other = (UserVO) obj;
		return this.uid.equals(other.uid) &&
				this.pwd.equals(other.pwd);
	}
	@Override
	public String toString() {
		return String.format("%s %s", this.uid, this.pwd);
	}
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
