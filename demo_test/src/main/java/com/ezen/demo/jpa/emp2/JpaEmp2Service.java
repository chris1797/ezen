package com.ezen.demo.jpa.emp2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.ezen.demo.jpa.board.Board;
import com.ezen.demo.jpa.board.JpaBoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JpaEmp2Service {

	@Autowired
	private emp2Repository emp2Repository;
	
	public int[] getLinkRange(Page<Emp2> pageInfo) {
		int start = 0;
		int end = 0;

		if (pageInfo.getNumber() - 2 < 0) {
			start = 0;
		} else {
			start = pageInfo.getNumber() - 2;
		}

		if (pageInfo.getTotalPages() < (start + 4)) {
			end = pageInfo.getTotalPages();
			start = (end - 4) < 0 ? 0 : (end - 4);
		} else {
			end = start + 4;
		}
		return new int[] { start, end };
	}
	
	public Page<Emp2> getList(Pageable pageable) {
		//List<Board> list = boardRepository.findAll();
		Page<Emp2> pageInfo = emp2Repository.findAll(pageable);
		
		return pageInfo;
	}
	
	// ename_by_empno
	 public String ename_by_empno(int empno) {
	 	return emp2Repository.ename_by_empno(empno);
	 }
	 
	// cur_by_empno
	public Emp2 cur_by_empno(int empno) {
		return emp2Repository.cur_by_empno(empno);
	}
	
	// emp_by_deptno
	public List<Emp2> emp_by_deptno(int deptno) {
		return emp2Repository.emp_by_deptno(deptno);
	}
}
