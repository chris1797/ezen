package com.ezen.vo;

import java.util.Objects;

public class Employee {
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private java.sql.Date hiredate;
	private int sal;
	private int comm;
	private int deptno;
	
	@Override
	public int hashCode() {
		return Objects.hash(this.empno);
	}
	
	@Override
	public boolean equals(Object obj) {
		Employee emp = (Employee) obj;
		return this.empno == emp.empno;
	}
	
	@Override
	public String toString() {
		return String.format("%d\t%s\t%d\t%d\t%s", empno, ename, deptno, sal, hiredate);
	}
	
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getMgr() {
		return mgr;
	}

	public void setMgr(int mgr) {
		this.mgr = mgr;
	}

	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public int getComm() {
		return comm;
	}

	public void setComm(int comm) {
		this.comm = comm;
	}

	public java.sql.Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(java.sql.Date hiredate) {
		this.hiredate = hiredate;
	}

}
