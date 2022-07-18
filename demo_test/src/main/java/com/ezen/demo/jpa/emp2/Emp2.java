package com.ezen.demo.jpa.emp2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="emp2")
@NamedStoredProcedureQueries({
	   @NamedStoredProcedureQuery(name="sp_ename_by_empno", // 자바에서 쓸 이름
	      procedureName = "ename_by_empno", // 오라클 서버에 저장된 프로시저 이름
	      parameters = {
	    		// 프로시저 파라미터 설정 (오라클에서 설정된 파라미터)
	    		// 프로시저 파라미터 이름 오라클과 동일하게 설정해야 함
	            @StoredProcedureParameter(mode=ParameterMode.IN, name="v_empno", type=Integer.class),
	            @StoredProcedureParameter(mode=ParameterMode.OUT, name="v_ename", type=String.class)
	      }
	   ),
	   @NamedStoredProcedureQuery(name = "sp_cur_by_empno", 
	      procedureName = "cur_by_empno",
	      resultClasses = Emp2.class,
	      parameters = {
	            @StoredProcedureParameter(mode=ParameterMode.IN, type=Integer.class),
	            @StoredProcedureParameter(mode=ParameterMode.REF_CURSOR, type=void.class)
	      }
	   ),
	   @NamedStoredProcedureQuery(name = "sp_emp_by_deptno", 
	      procedureName = "emp_by_deptno",
	      resultClasses = Emp2.class,
	      parameters = {
	            @StoredProcedureParameter(mode=ParameterMode.IN, type=Integer.class),
	            @StoredProcedureParameter(mode=ParameterMode.REF_CURSOR, type=void.class)
	      }
	   ),
	   
	})
public class Emp2 {
	
	
	@Id //프라이머리키
	
	@Column(name="empno")
	private int empno;
	private String ename;
	private String job;
	private java.sql.Date hiredate;
	private float sal;
	private int deptno;
	

}


