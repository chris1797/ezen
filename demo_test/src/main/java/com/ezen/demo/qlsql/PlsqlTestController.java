package com.ezen.demo.qlsql;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plsql")
public class PlsqlTestController {
	
//	private final Logger logger = LoggerFactory.getLogger(getClass());
//	private final Logger logger = LoggerFactory.getLogger(this.getClass()); 위 코드와 같음
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//Logger는 개발 뿐만 아니라 여러 기능이 있기 때문에 쓰여지는 곳이 많음
	
	@Autowired
	private PlsqlTestService svc;
	
	@GetMapping("")
	public String test() {
		logger.info("{}. 여기는{}", 1, "test()"); // Trace, Degug, Info, Warn, Error 를 쓸 수 있음
		return "PLSQL Test";
	}

	// plsql/ename/7369
	
	@GetMapping("/ename/{empno}")
	public Map<String, Object> ename_by_empno2(@PathVariable("empno") int empno) {
		Map<String, Object> map = svc.getEnameByEmpno2(empno);
		logger.info("서비스에서 리턴한 값: {}", map);
		return map;
	}
	
	@GetMapping("/ename/{empno}")
	public Map<String, Object> ename_by_empno(@PathVariable("empno") int empno) {
		Map<String, Object> map = svc.getEnameByEmpno(empno);
		logger.info("서비스에서 리턴한 값: {}", map);
		return map;
	}
}
