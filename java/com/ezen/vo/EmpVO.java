package com.ezen.vo;

import java.util.Objects;

public class EmpVO 
{
	private int num;
	private String name;
	private String phone;
	private String email;
	
	public EmpVO() {}
	
	public EmpVO(int num) {
		this.num = num;
	}
	
	public EmpVO(int num, String name, String phone, String email) {
		this.num = num;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	
	public EmpVO(String[] empInfo) {
		this(Integer.parseInt(empInfo[0]), empInfo[1], empInfo[2], empInfo[3]);
	}

	@Override
	public boolean equals(Object obj) {
		EmpVO other = (EmpVO) obj;
		return this.num==other.num ? true : false;
	}

	@Override
	public String toString() {
		return String.format("%d %s %s %s", num,name,phone,email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.num);
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
