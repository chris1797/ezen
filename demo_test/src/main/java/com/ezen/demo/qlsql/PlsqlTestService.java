package com.ezen.demo.qlsql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

@Service
public class PlsqlTestService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
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
				.withProcedureName("ename_by_empno");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("v_empno", empno);
		SqlParameterSource in = new MapSqlParameterSource(paramMap);
		
		Map<String, Object> resultMap = simpleJdbcCall.execute(in); // in 파라미터를 주고 OutParameter를 받아냄
		logger.info("SimpleJdbc Call 리턴값 : {}", resultMap);
		return resultMap;
	}
}
