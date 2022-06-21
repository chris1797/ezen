package com.ezen.demo.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mybatis/dept")
public class DeptController {
	
	@Autowired
	private DeptMapper dao; // Interface
	
	@GetMapping("list")
	public String getList() {
		
		return dao.getList().toString();
	}
	
}
