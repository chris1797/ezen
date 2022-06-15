package com.ezen.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.service.EmpService;

/**
 * Servlet implementation class EmpServlet
 */

@WebServlet("/emp")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public EmpServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		EmpService svc = new EmpService(request);
		//service에서 처리해주고 view만 꺼내서 view에 전달 
		String view = svc.process();
			
		request.getRequestDispatcher(view).forward(request, response);
		}
	}

