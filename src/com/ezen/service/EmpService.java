package com.ezen.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ezen.dao.EmpDAO;
import com.ezen.vo.EmpVO;

public class EmpService {

	private HttpServletRequest request;
	
	public EmpService(HttpServletRequest request) {
		this.request = request;
	}

	// view의 경로만 리턴하자
	public String process() {
		String cmd = request.getParameter("cmd");
		
		if(cmd == null) cmd = "list";
		
		if(cmd.equals("list")) {
			EmpDAO empDAO = new EmpDAO();
			List<EmpVO> list = empDAO.getList();
			request.setAttribute("list", list);
			return "/empView.jsp";
			
		} else if(cmd.equals("getEmp")) { //emp?cmd=getEmp&num=12
			EmpDAO empDAO = new EmpDAO();
			int num = Integer.parseInt(request.getParameter("num"));
			EmpVO emp = empDAO.getEmp(num);
			request.setAttribute("getEmp", emp);
			return "/getempView.jsp";
		}
		
		return null;
	}
}
