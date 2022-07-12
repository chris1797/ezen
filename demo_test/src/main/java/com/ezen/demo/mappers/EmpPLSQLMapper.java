package com.ezen.demo.mappers;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.demo.model.Emp;

@Mapper
public interface EmpPLSQLMapper {
	void getEnameByEmpno(Map<String, Object> map);
	
	void getEmpByDeptno(Emp emp);
	
	void getEmpByEmpno(Map<String, Object> map);
	
	void getEmpByEmpno2(Emp emp);
}
