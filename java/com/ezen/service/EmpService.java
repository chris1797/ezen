package com.ezen.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.ezen.dao.EmpDAO;
import com.ezen.vo.EmpVO;

public class EmpService 
{
	private HttpServletRequest request;
	
	public EmpService(HttpServletRequest request) 
	{
		this.request = request;
	}

	public String process() 
	{
		String cmd = request.getParameter("cmd");
		if (cmd==null) cmd = "list";
		
		if(cmd.equals("list")) 
		{
			EmpDAO empDAO = new EmpDAO();
			List<EmpVO> list = empDAO.getList();
			request.setAttribute("list", list);
			return "/empView.jsp";
		}
		else if(cmd.equals("getEmp"))  // ~emp?cmd=getEmp&num=12
		{
			String sNum = request.getParameter("num");
			EmpVO emp = getEmp(sNum);
			request.setAttribute("emp", emp);
			return "/empInfo.jsp";
		}
		else if(cmd.equals("delete"))  // ~emp?cmd=delete&num=13
		{
			String sNum = request.getParameter("num");
			EmpDAO empDAO = new EmpDAO();
			boolean deleted = empDAO.delete(sNum);
			request.setAttribute("deleted", deleted ? "Emp Deleted":"Failed");
			return "/empDelete.jsp";
		}
		else if(cmd.equals("edit"))   // ~emp?cmd=edit&num=14
		{
			String sNum = request.getParameter("num");
			EmpVO emp = getEmp(sNum);
			request.setAttribute("emp", emp);
			return "/empEdit.jsp";
		}
		else if(cmd.equals("update"))
		{
			String sNum = request.getParameter("num");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			EmpVO emp = new EmpVO(Integer.valueOf(sNum));
			emp.setPhone(phone);
			emp.setEmail(email);
			
			EmpDAO empDAO = new EmpDAO();
			boolean updated = empDAO.update(emp);
			request.setAttribute("updated", updated);
			return "/empUpdated.jsp";
		}
		else if(cmd.equals("add_form"))
		{
			return "/add_form.jsp";
		}
		else if(cmd.equals("add"))
		{
			String sNum = request.getParameter("num");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			
			EmpVO emp = new EmpVO(Integer.parseInt(sNum),name,phone,email);
			EmpDAO empDAO = new EmpDAO();
			
			boolean saved = empDAO.add(emp);
			request.setAttribute("saved", saved);
			return "/empSaved.jsp";
		}
		return null;
	}
	
	private EmpVO getEmp(String sNum) {
		EmpDAO empDAO = new EmpDAO();
		EmpVO emp = empDAO.getEmp(sNum);
		return emp;
	}
}
