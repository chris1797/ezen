package com.ezen.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.service.EmpService;
import com.ezen.vo.EmpVO;

@WebServlet("/emp")
public class EmpServlet extends HttpServlet 
{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		EmpService svc = new EmpService(request);
		String view = svc.process();

		request.getRequestDispatcher(view).forward(request, response);
	}
}
