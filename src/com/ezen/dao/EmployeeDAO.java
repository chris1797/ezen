package com.ezen.dao;

import java.sql.*;
import java.util.*;

import com.ezen.vo.Employee;

public class EmployeeDAO {
	private Connection conn = null;
	private Statement stmt;
	private ResultSet rs;
	
	
	private Connection getConn() {
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver"); ojdbc8.jar
			Connection conn = DriverManager.getConnection(
	                   "jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Employee> getList(){
		conn = getConn();
		try {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM employee";
			ResultSet rs = stmt.executeQuery(sql);
			List<Employee> list = new ArrayList<>();
			
			while(rs.next()) {
				int empno = rs.getInt("EMPNO");
				String ename = rs.getString("ENAME");
				String job = rs.getString("JOB");
				int mgr = rs.getInt("MGR");
				java.sql.Date hiredate = rs.getDate("HIREDATE");
				int sal = rs.getInt("SAL");
				int comm = rs.getInt("COMM");	
				int deptno = rs.getInt("DEPTNO");
				
				Employee emp = new Employee();
				emp.setEmpno(empno);
				emp.setEname(ename);
				emp.setJob(job);
				emp.setMgr(mgr);
				emp.setHiredate(hiredate);
				emp.setSal(sal);
				emp.setComm(comm);
				emp.setDeptno(deptno);
				
				list.add(emp);	
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}
	
	public Employee getEmp(int num) {
		conn = getConn();
		
		try {
			String sql = "SELECT * FROM employee WHERE empno=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int empno = rs.getInt("EMPNO");
				String ename = rs.getString("ENAME");
				String job = rs.getString("JOB");
				int mgr = rs.getInt("MGR");
				java.sql.Date hiredate = rs.getDate("HIREDATE");
				int sal = rs.getInt("SAL");
				int comm = rs.getInt("COMM");	
				int deptno = rs.getInt("DEPTNO");
				
				Employee emp = new Employee();
				emp.setEmpno(empno);
				emp.setEname(ename);
				emp.setJob(job);
				emp.setMgr(mgr);
				emp.setHiredate(hiredate);
				emp.setSal(sal);
				emp.setComm(comm);
				emp.setDeptno(deptno);
				return emp;
				}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}
	
	public boolean updateEmp(Employee emp) {
		conn = getConn();
		try {
			stmt = conn.createStatement();
			String sql = "update employee set deptno=?, sal=? where empno=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp.getDeptno());
			pstmt.setInt(2, emp.getSal());
			pstmt.setInt(3, emp.getEmpno());
			int n = pstmt.executeUpdate();
			//emp.getDeptno(), emp.getSal(), emp.getEmpno());
			// executeQuery가 아닌 update,
			// sql 리턴값이 없고 row만 리턴되니까 row 수를 int n 변수에 저장
											
			return n>0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return false;
	}
	
	public List<Employee> getDept(int num){
		conn = getConn();
		try {
			String sql = "SELECT * FROM employee WHERE deptno=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			List<Employee> list = new ArrayList<>();
			
			while(rs.next()) {
				int empno = rs.getInt("EMPNO");
				String ename = rs.getString("ENAME");
				String job = rs.getString("JOB");
				int mgr = rs.getInt("MGR");
				java.sql.Date hiredate = rs.getDate("HIREDATE");
				int sal = rs.getInt("SAL");
				int comm = rs.getInt("COMM");	
				int deptno = rs.getInt("DEPTNO");
				
				Employee emp = new Employee();
				emp.setEmpno(empno);
				emp.setEname(ename);
				emp.setJob(job);
				emp.setMgr(mgr);
				emp.setHiredate(hiredate);
				emp.setSal(sal);
				emp.setComm(comm);
				emp.setDeptno(deptno);
				
				list.add(emp);	
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}
	
	public Employee searchByName(String key){
		conn = getConn();
		try {
			String sql = "SELECT * FROM employee WHERE ename=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int empno = rs.getInt("EMPNO");
				String ename = rs.getString("ENAME");
				String job = rs.getString("JOB");
				int mgr = rs.getInt("MGR");
				java.sql.Date hiredate = rs.getDate("HIREDATE");
				int sal = rs.getInt("SAL");
				int comm = rs.getInt("COMM");	
				int deptno = rs.getInt("DEPTNO");
				
				Employee emp = new Employee();
				emp.setEmpno(empno);
				emp.setEname(ename);
				emp.setJob(job);
				emp.setMgr(mgr);
				emp.setHiredate(hiredate);
				emp.setSal(sal);
				emp.setComm(comm);
				emp.setDeptno(deptno);
				return emp;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}
		

	public List<String> getItemList(String search) {
		conn = getConn();
		try {
			String sql = "SELECT " + search +" FROM employee";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<String> list = new ArrayList<>();
			
			while(rs.next()) {
				list.add(rs.getString(search));	
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}
	
	private void closeAll() {
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean remove(int key){
		conn = getConn();
		try {
			String sql = "DELETE FROM employee WHERE empno=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, key);
			int n = pstmt.executeUpdate();
			return n>0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return false;
	}
	
}
