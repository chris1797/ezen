package com.ezen.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.service.HelloService;

@WebServlet("/hello")  // ~/hello?dan=5
public class HelloServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청한 클라이언트에게 응답하려면?
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		request.setCharacterEncoding("utf-8");
		
		HelloService svc = new HelloService(request);
		String msg = svc.getGugu();
		
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("/ggView.jsp").forward(request, response);
	}
}
