package com.ezen.board;

import java.util.Objects;

public class BoardVO {
	private int num; // 글번호
	private String title; // 제목
	private String contents; // 내용
	private String author = "이재훈";// 작성자
	private java.sql.Date wdate; // 작성일
	private int pcode = 0; // 부모글
	
	
	public BoardVO() {}
	
	public BoardVO(int num) {
		this.num = num;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.num);
	}
	
	@Override
	public boolean equals(Object obj) {
		BoardVO other = (BoardVO)obj;
		return this.num == other.num;
	}
	@Override
	public String toString() {
		return String.format("%d|%s|%s|%s|%s|%d", num,title,contents,author,wdate,pcode);
	}
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public java.sql.Date getWdate() {
		return wdate;
	}
	public void setWdate(java.sql.Date wdate) {
		this.wdate = wdate;
	}
	public int getPcode() {
		return pcode;
	}
	public void setPcode(int pcode) {
		this.pcode = pcode;
	}
	
}
