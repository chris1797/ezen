package com.ezen.demo.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Emp 
{
   private int empno;
   private String ename;
   private java.sql.Date hiredate;
   private float sal;
   private int deptno;
   
   private List<Emp> list;

   @Override
   public String toString() {
	   return String.format("%d\t%s\t%s\t%f", empno,ename,hiredate,sal);
   }
   public List<Emp> getList() {
	   return list;
   }
   public void setList(List<Emp> list) {
	   this.list = list;
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
   public java.sql.Date getHiredate() {
      return hiredate;
   }
   public void setHiredate(java.sql.Date hiredate) {
      this.hiredate = hiredate;
   }
   public float getSal() {
      return sal;
   }
   public void setSal(float sal) {
      this.sal = sal;
   }
   public void setDeptno(int deptno) {
	   this.deptno = deptno;
   }
   public int getDeptno() {
	return deptno;
   }
}