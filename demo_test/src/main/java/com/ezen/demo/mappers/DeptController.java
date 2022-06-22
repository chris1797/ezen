package com.ezen.demo.mappers;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.demo.vo.Dept;

@Controller
@RequestMapping("/mybatis/dept")
public class DeptController {
	
	@Autowired
	private DeptMapper dao; // Interface
	
	@GetMapping("")
	public String getListAll() {
		
		return "dept/form";
	}
	
	
	@GetMapping("/list")
	public String getList() {
		
		return dao.getList().toString();
	}
	
	@GetMapping("/listByDeptno")
	public String getListByDeptno() {
		return dao.getListByDeptno(0).toString();
	}
	
	@GetMapping("/infoByDeptno")
	public String getInfoByDeptno() {
		return dao.getInfoByDeptno(20).toString();
	}
	
	@GetMapping("/add")
	public int add() {
		Dept dept = new Dept();
		dept.setDeptno(50);
		dept.setDname("Chris");
		dept.setLoc("Seoul");
		return dao.add(dept);
	}
	
	@GetMapping("/update")
	public int update() {
		Dept dept = dao.getInfoByDeptno(50);
		dept.setDname("이재훈");
		dept.setLoc("신림");
		int i =  dao.update(dept);
		return i;
	}
	
	@GetMapping("/delete/{deptno}")
	public int delete(@PathVariable("deptno")int deptno) {
		int i =  dao.delete(deptno);
		return i;
	}
	
	@GetMapping("/getDeptByLike")
	public String getDeptByLike() {
		return dao.getDeptByLike("ES").toString();
	}
	
	@GetMapping("/getListMap")
	public String getListMap() {
		return dao.getListMap().toString();
	}
	
	@PostMapping("/getemp")
	public String getListByDeptnos(HttpServletRequest request) {
		String[] sDeptno = request.getParameterValues("deptno");
		
		List<Integer> list = new ArrayList<>();
		//list.add("20");
		//list.add("30");
		for(int i=0; i<sDeptno.length; i++) {
			list.add(Integer.valueOf(sDeptno[i]));
		}
		System.out.println(dao.getListByDeptnos(list).toString());
		return null;
	}
	
}
