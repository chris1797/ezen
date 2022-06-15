package com.ezen.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.service.BBSService;
import com.ezen.service.JdbcEmpSvc;

/**
 * Servlet implementation class JdbcEmpServlet
 */
@WebServlet("/Jdbcemp")
public class JdbcEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		JdbcEmpSvc svc = new JdbcEmpSvc(request, response);
		String view = svc.process();
		
		if(view != null) {	
			request.getRequestDispatcher(view).forward(request, response);
		}
	}

}
