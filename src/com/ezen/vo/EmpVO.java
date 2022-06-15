package com.ezen.vo;

public class EmpVO {
	
	private int num;
	private String name;
	private String phone;
	private String email;
	
	
	
	@Override
	public boolean equals(Object obj) {
		EmpVO other = (EmpVO) obj;
		return this.num == other.num ? true : false;
	}

	@Override
	public String toString() {
		//문자열로 객체를 출력
		return String.format("%d\t%s\t%s\t%s", num, name, phone, email);
	}


	public EmpVO(int num, String name, String phone, String email) {
		this.num = num;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
