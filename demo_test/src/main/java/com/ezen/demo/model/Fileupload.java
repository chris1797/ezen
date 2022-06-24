package com.ezen.demo.model;

import org.springframework.stereotype.Component;

@Component
public class Fileupload {
	private int num;
	private String writer;
	private java.sql.Date udate;
	private String comments;
	private String fname;
	private String fpath;
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public java.sql.Date getUdate() {
		return udate;
	}
	public void setUdate(java.sql.Date udate) {
		this.udate = udate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFpath() {
		return fpath;
	}
	public void setFpath(String fpath) {
		this.fpath = fpath;
	}
	
	
}
