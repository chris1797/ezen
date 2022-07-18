package com.ezen.demo.jpa.emp2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface emp2Repository extends JpaRepository<Emp2, Integer>{
	
	// ename_by_empno
	   @Procedure(name="sp_ename_by_empno")
	   String ename_by_empno(@Param("v_empno") int empno);
	   
	   @Procedure(name="sp_cur_by_empno")
	   Emp2 cur_by_empno(@Param("p_empno") int empno);
	   
	   @Procedure(name="sp_emp_by_deptno")
	   List<Emp2> emp_by_deptno(@Param("p_deptno") int empno);
}