package com.ezen.demo.model;

import java.util.*;

import org.springframework.stereotype.Component;

@Component
public class Fileupload {
	private int num;
	private String writer;
	private java.sql.Date udate;
	private String comments;
	private String fpath;
	private List<AttachVO> fname = new ArrayList<>(); //첨부파일 명
	//List<String> -> List<AttachVO> 로 변경
	private int pnum; // AttachVO로 담김
	
	@Override
	public int hashCode() {
		return Objects.hash(this.num, this.num);
	}
	
	@Override
	public boolean equals(Object obj) {
		Fileupload other = (Fileupload) obj;
		return this.num == other.num;
	}
	
	
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
	public List<AttachVO> getFname() {
		return fname;
	}
	public void setFname(List<AttachVO> fname) {
		this.fname = fname;
	}
	
	public String getFpath() {
		return fpath;
	}
	public void setFpath(String fpath) {
		this.fpath = fpath;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	
	
	
}
