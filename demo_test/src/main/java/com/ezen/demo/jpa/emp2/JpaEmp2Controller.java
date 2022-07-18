package com.ezen.demo.jpa.emp2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.demo.jpa.board.Board;
import com.ezen.demo.jpa.board.JpaBoardController;
import com.ezen.demo.jpa.board.JpaBoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/jpaemp2")
public class JpaEmp2Controller {

	@Autowired
	private JpaEmp2Service svc;
	
	
	@GetMapping("/list")
	public String list(Pageable pageable, Model model) {
		Page<Emp2> pageInfo = svc.getList(pageable);
		List<Emp2> list = pageInfo.getContent();
		
		int[] linkRange = svc.getLinkRange(pageInfo);
		
		model.addAttribute("list", list);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("start", linkRange[0]);
		model.addAttribute("end", linkRange[1]);
		return "jpaEmpList";
	}
	
	@GetMapping("/test1")
	@ResponseBody
	public String test1() {
		String ename = svc.ename_by_empno(7369);
		log.info("ename={}", ename);
		return ename;
	}
	
	@GetMapping("/test2")
	@ResponseBody
	@Transactional
	public String test2() {
		Emp2 emp = svc.cur_by_empno(7369);
		log.info("emp={}", emp);
		return emp.toString();
	}
	
	@GetMapping("/test3")
	@ResponseBody
	@Transactional
	public String test3() {
		List<Emp2> emplist = svc.emp_by_deptno(30);
		log.info("emplist={}", emplist);
		return emplist.toString();
	}
}
