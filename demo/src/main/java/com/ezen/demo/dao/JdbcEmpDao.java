package com.ezen.demo.dao;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ezen.demo.model.Emp;

@Repository
public class JdbcEmpDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Emp> getListAll(){
		String sql = "SELECT * FROM emp2";
		List<Emp> list = jdbcTemplate.query(sql, (rs,i)->{ // 자동으로 List에 담아줌
			Emp emp = new Emp();
			emp.setEmpno(rs.getInt("EMPNO"));
			emp.setEname(rs.getString("ENAME"));
			emp.setHiredate(rs.getDate("HIREDATE"));
			emp.setSal(rs.getFloat("SAL"));
			return emp;
		});
		return list; // List에 담겨서 return 된다
	}
	
	
	public List<Emp> getListByDeptno(int deptno){
		String sql = "SELECT * FROM emp WHERE DEPTNO=?";
		List<Emp> list = jdbcTemplate.query(sql, (rs,i)->{ // 자동으로 List에 담아줌
			Emp emp = new Emp();
			emp.setEmpno(rs.getInt("EMPNO"));
			emp.setEname(rs.getString("ENAME"));
			emp.setHiredate(rs.getDate("HIREDATE"));
			emp.setSal(rs.getFloat("SAL"));
			return emp;
		}, deptno);
		return list; // List에 담겨서 return 된다
	}


	public Emp getEmpById(int empno) {
		String sql = "SELECT * FROM emp WHERE EMPNO=?";
		List<Emp> list = jdbcTemplate.query(sql, (rs,i)->{ // 자동으로 List에 담아줌
			Emp emp = new Emp();
			emp.setEmpno(rs.getInt("EMPNO"));
			emp.setEname(rs.getString("ENAME"));
			emp.setHiredate(rs.getDate("HIREDATE"));
			emp.setSal(rs.getFloat("SAL"));
			return emp;
		}, empno);
		return list.get(0);
	}


	public boolean add(Emp emp) {
		String sql = "INSERT INTO emp (EMPNO, ENAME, HIREDATE, SAL) VALUES(?,?,?,?)";
		int result = jdbcTemplate.update(sql, emp.getEmpno(), emp.getEname(), emp.getHiredate(), emp.getSal());
			
		return result>0;
	}
	
	public int addAndGetKey(Emp emp) {
		String sql = "INSERT INTO emp (EMPNO, ENAME, HIREDATE, SAL) VALUES(?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(conn->{ 
				PreparedStatement pstmt;
				pstmt = conn.prepareStatement(sql, new String[] {"empno"}); // PK 컬럼
				pstmt.setInt(1, emp.getEmpno());
				pstmt.setString(2, emp.getEname());
				pstmt.setDate(3, emp.getHiredate());
				pstmt.setFloat(4, emp.getSal());
				return pstmt;
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	public boolean update(Emp emp) {
		String sql = "UPDATE emp SET DEPTNO=?, SAL=? WHERE EMPNO=?";
		int result = jdbcTemplate.update(sql, emp.getDeptno(), emp.getSal(), emp.getEmpno());
			
		return result>0;
	} 
	
	public boolean delete(int empno) {
		String sql = "DELETE emp2 WHERE EMPNO=?";
		int result = jdbcTemplate.update(sql, empno);
			
		return result>0;
	}
}
