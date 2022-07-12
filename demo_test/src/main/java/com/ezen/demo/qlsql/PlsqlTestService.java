package com.ezen.demo.qlsql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.ezen.demo.mappers.EmpPLSQLMapper;
import com.ezen.demo.model.Emp;

@Service
public class PlsqlTestService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private EmpPLSQLMapper dao;
	
	public Map<String, Object> getEnameByEmpno(int empno) {
		List<SqlParameter> param = Arrays.asList(
				new SqlParameter(Types.INTEGER),
				new SqlOutParameter("v_ename", Types.VARCHAR)
		);
		
		
		return jdbcTemplate.call(new CallableStatementCreator() { 
			// call은 콜러블크리에이터 객체
			// call이 리턴하는 값이 Map<String, Object>
			
			@Override //콜러블스테이트먼트를 완성하기위한 메소드
			public CallableStatement createCallableStatement(Connection con) throws SQLException{
				CallableStatement cs = con.prepareCall("{ call ename_by_empno(?,?) }");
				cs.setInt(1, empno);
				cs.registerOutParameter(2, Types.VARCHAR);
				return cs;
			}
		}, param);
	}
	
	public Map<String, Object> getEnameByEmpno2(int empno){
		//OutParameter는 설정하지 않고 리턴값으로 받아옴
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("ename_by_empno"); // sql developer에서 만든 프로시저 이
		Map<String, Object> paramMap = new HashMap<>(); // 파라미터를 설정할 준비 ( Map Type )
		paramMap.put("v_empno", empno);
		//출력 파라미터는 언급이 없음
		SqlParameterSource in = new MapSqlParameterSource(paramMap); // 56~59 Line이 파라미터 설정
		
		Map<String, Object> resultMap = simpleJdbcCall.execute(in); // in 파라미터를 주고 OutParameter를 받아냄, 출력파라미터가 resultMap으로 들어옴
		logger.debug("SimpleJdbc Call 리턴값 : {}", resultMap); // SimpleJdbc Call 리턴값 : {V_ENAME=SMITH} <- 대문자
		return resultMap;
	}
	
	// SimpleJdbcCall을 사용하여 오라클로부터 커서를 받아오는 예
	public List<Emp> getEmpByDeptno(int deptno) {
		// 72~79 Line이 파라미터 설정
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("emp_by_deptno")
				.declareParameters(
						new SqlParameter("p_deptno", Types.INTEGER),// 프로시저의 파라미터 이름과 같아야 함
						new SqlOutParameter("p_cur", Types.REF_CURSOR)
				)
				.returningResultSet("p_cur", BeanPropertyRowMapper.newInstance(Emp.class));
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("p_deptno", deptno);
		SqlParameterSource in = new MapSqlParameterSource(paramMap);
		
		Map<String, Object> resultMap = simpleJdbcCall.execute(in);
//		logger.info("결과 맵 : {}", resultMap.toString());
		List<Emp> list = (List<Emp>)resultMap.get("p_cur");
		
		return list;
	}
	
	public List<Emp> getEmpByYear(int year) {
		// 72~79 Line이 파라미터 설정
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("emp_by_year2")
				.declareParameters(
						new SqlParameter("p_year", Types.INTEGER),// 프로시저의 파라미터 이름과 같아야 함
						new SqlOutParameter("p_cur", Types.REF_CURSOR)
				)
				.returningResultSet("p_cur", BeanPropertyRowMapper.newInstance(Emp.class));
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("p_year", year);
		SqlParameterSource in = new MapSqlParameterSource(paramMap);
		
		Map<String, Object> resultMap = simpleJdbcCall.execute(in);
//		logger.info("결과 맵 : {}", resultMap.toString());
		List<Emp> list = (List<Emp>)resultMap.get("p_cur");
		
		return list;
	}
	
	public Map<String, Object> getEnameByEmpno3(int empno){
		Map<String, Object> map = new HashMap<>();
		map.put("empno", empno);
		map.put("ename", null);
		dao.getEnameByEmpno(map);
		return map;
	}
	
	public Map<String, Object> getEmpByEmpno(int empno){
		Map<String, Object> map = new HashMap<>();
		map.put("empno", empno);
		map.put("empno_out", null);
		map.put("ename", null);
		map.put("hiredate", null);
		dao.getEmpByEmpno(map);
		return map;
	}
	
	public Emp getEmpByEmpno2(int empno){
		Emp emp = new Emp();
		emp.setEmpno(empno);
		dao.getEmpByEmpno2(emp);
		return emp;
	}
	
	public Emp getEmpByDeptno2(int deptno) {
		Emp emp = new Emp();
		emp.setDeptno(deptno);
		dao.getEmpByDeptno(emp);
		
		return emp;
	}
}
