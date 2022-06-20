package com.ezen.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.demo.dao.JdbcEmpDao;
import com.ezen.demo.model.Emp;

//@RestController 모든 메소드가 ResponseBody가 됨

@Controller
@RequestMapping("/jdbc/emp")
public class JdbcEmpController {
	
	@Autowired
	private JdbcEmpDao dao;
	
	@GetMapping("/all")
	public String getEmpList(Model model) {
//		List<Emp> list = dao.getListAll();
		model.addAttribute("list", dao.getListAll());
		return "empList";
	}
	
	@GetMapping("/dept")
	public String getListByDeptno() {
		return dao.getListByDeptno(20).toString();
	}
	
	@GetMapping("/empno")
	public String getEmp() {
		return dao.getEmpById(1234).toString();
	}
	
	@GetMapping("/add")
	public String addEmp() {
		Emp emp = new Emp();
		emp.setEmpno(1234);
		emp.setEname("Chris");
		emp.setHiredate(java.sql.Date.valueOf("2022-06-20"));
		emp.setSal(40000);
		
		boolean added = dao.add(emp);
		return "added=" + added;
	}
	
	@GetMapping("/getkey")
	public int addAndGetKey() {
		Emp emp = new Emp();
		emp.setEmpno(1234);
		emp.setEname("Chris");
		emp.setHiredate(java.sql.Date.valueOf("2022-06-20"));
		emp.setSal(40000);
		
		int addedKey = dao.addAndGetKey(emp);
		return addedKey;
	}
	
	@GetMapping("/update/{empno}/{deptno}/{sal}")
	public String updateemp(@PathVariable("empno")int empno,
			@PathVariable("deptno")int deptno, @PathVariable("sal")int sal, Model model) {
		// gugu 때와 다르게 쓴건 이 Controller 전체가 jsp 없이 body로 쓰고 있어 model 객체를 받을 필요가 없음
		Emp emp = new Emp();
		emp.setEmpno(empno);
		emp.setDeptno(deptno);
		emp.setSal(sal);
		boolean updated = dao.update(emp);
		return "update=" + updated;
	}
	
	@GetMapping("/delete") /* delete?empno=7369 */
	public String delete(@RequestParam("empno")int empno) {
		boolean deleted = dao.delete(empno);
		return "deleted=" + deleted;
	}
	
}
