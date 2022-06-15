package com.ezen.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.*;

import com.ezen.dao.EmployeeDAO;
import com.ezen.vo.Employee;

public class JdbcEmpSvc {
	private HttpServletRequest request;
	private HttpServletResponse response;

	public JdbcEmpSvc(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	public String process() {
		String cmd = request.getParameter("cmd");
		if(cmd==null || cmd.equals("list")) {
			
			EmployeeDAO dao = new EmployeeDAO();
	      	List<Employee> list = dao.getList();
	      	
	      	request.setAttribute("list", list);
	      	return "/jdbcEmpList.jsp";
	      	}
		else if(cmd.equals("detail")) {
			int num = Integer.parseInt(request.getParameter("num"));
			EmployeeDAO dao = new EmployeeDAO();
			Employee detail = dao.getEmp(num);
			
			request.setAttribute("detail", detail);
			return "/empDetail.jsp";
		}
		else if(cmd.equals("edit")) {
			int num = Integer.parseInt(request.getParameter("num"));
			EmployeeDAO dao = new EmployeeDAO();
			Employee emp = dao.getEmp(num);
			
			request.setAttribute("emp", emp);
			return "/empUpdate.jsp";
		}
		else if(cmd.equals("update")) {
			int empno = Integer.parseInt(request.getParameter("empno"));
			int deptno = Integer.parseInt(request.getParameter("deptno"));
			int sal = Integer.parseInt(request.getParameter("sal"));
			
			Employee emp = new Employee();
			emp.setEmpno(empno);
			emp.setDeptno(deptno);
			emp.setSal(sal);
			
			EmployeeDAO dao = new EmployeeDAO();
			boolean updated = dao.updateEmp(emp);
			System.out.println(updated);
			try { //ajax를 쓰기 위한 print 
				PrintWriter out = response.getWriter();
				String jsStr = String.format("{\"%s\":%b}", "updated", updated);
				out.println(jsStr);
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(cmd.equals("getDept")) {
			int deptno = Integer.parseInt(request.getParameter("deptno"));
			
			EmployeeDAO dao = new EmployeeDAO();
	      	List<Employee> list = dao.getDept(deptno);
	      	
	      	request.setAttribute("list", list);
	      	return "/empDept.jsp";
		}
		else if(cmd.equals("search")) {
			String search = request.getParameter("search");
			EmployeeDAO dao = new EmployeeDAO();
			
			if(search.equals("ename")) { // 1 : 이름
				String key = request.getParameter("searchVal");
				Employee searched = dao.searchByName(key);
				request.setAttribute("searched", searched);
			} else if(search.equals("empno")) { // 2: 사번
				int val = Integer.parseInt(request.getParameter("searchVal"));
				Employee searched = dao.getEmp(val);
				request.setAttribute("searched", searched);
			}
			return "/empSearched.jsp";
		}
		else if(cmd.equals("getItemList")) {
			String search = request.getParameter("search");
			EmployeeDAO dao = new EmployeeDAO();
			List<String> list = dao.getItemList(search);
			
			JSONArray jsArr = new JSONArray();
			for(int i=0; i<list.size(); i++) {
				jsArr.add(list.get(i));
			}
			String items = jsArr.toJSONString();
			/*
			String items = "[";
			for(int i=0; i<list.size(); i++) {
				items += String.format("\"%s\"", list.get(i));
				if(i<list.size()-1) {
					items += ",";
				}
			}
			items += "]";
			*/
			try { //ajax를 쓰기 위한 print 
				PrintWriter out = response.getWriter();
				out.println(items);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		else if(cmd.equals("getImage")) {
			int empno = Integer.parseInt(request.getParameter("empno"));
			String imgPath = empno + ".jpg";
			EmployeeDAO dao = new EmployeeDAO();
			JSONObject jobj = new JSONObject();
			jobj.put("pic", imgPath);
			System.out.println(jobj);
			
			try { //ajax를 쓰기 위한 print 
				PrintWriter out = response.getWriter();
				out.println(jobj.toJSONString());
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		else if(cmd.equals("remove")) {
			int empno = Integer.parseInt(request.getParameter("empno"));
			EmployeeDAO dao = new EmployeeDAO();
			boolean deleted = dao.remove(empno);
			System.out.println(deleted);
			try { //ajax를 쓰기 위한 print 
				PrintWriter out = response.getWriter();
				out.println(deleted); //서비스에서 떠나 ajax 변수로 들어감
				System.out.println(deleted);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
