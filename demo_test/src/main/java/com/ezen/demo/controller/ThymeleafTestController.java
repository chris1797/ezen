package com.ezen.demo.controller;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.demo.jpa.board.JpaBoardController;
import com.ezen.demo.jpa.emp2.Emp2;
import com.ezen.demo.model.Emp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/th")
@Controller
public class ThymeleafTestController {

	@GetMapping("/index")
	public String index() {
		return "thymeleaf/index";
	}
	
	@GetMapping("/test1")
	public String test1(Model model) {
		model.addAttribute("item", "이재훈");
		return "thymeleaf/index";
	}
	
	@GetMapping("/test2")
	public String test2(Model model) {
		Emp2 emp = new Emp2();
		emp.setEmpno(1111);
		emp.setEname("이재훈");
		emp.setDeptno(20);
		emp.setHiredate(java.sql.Date.valueOf("2022-07-18"));
		emp.setJob("clerk");
		emp.setSal(10000);
		model.addAttribute("emp", emp);
		return "thymeleaf/index";
	}
	
	@GetMapping("/test3")
	public String test3(Model model) {
		List<Emp2> list = new ArrayList<Emp2>();
		Emp2 emp2 = new Emp2();
		emp2.setEmpno(1111);
		emp2.setEname("이재훈");
		emp2.setDeptno(20);
		emp2.setHiredate(java.sql.Date.valueOf("2022-07-18"));
		emp2.setSal(10000);
		
		Emp2 emp = new Emp2();
		emp.setEmpno(2222);
		emp.setEname("이재훈2");
		emp.setDeptno(30);
		emp.setHiredate(java.sql.Date.valueOf("2022-07-18"));
		emp.setSal(10000);
		
		list.add(emp2);
		list.add(emp);
		model.addAttribute("list", list);
		return "thymeleaf/index";
	}
	
	@GetMapping("/test4")
	public String test4(Model model) {
		model.addAttribute("user", "chris");
		return "thymeleaf/index";
	}
	
	@GetMapping("/input")
	public String menu(Model model) {
		return "thymeleaf/menu";
	}
	
	@PostMapping("/save")
	@ResponseBody
	public String save(Emp2 emp) {
		log.trace("form data={}", emp);
		return emp.toString();
	}
}
