package com.ezen.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.service.MathService;

@WebServlet("/math")
public class MathServlet extends HttpServlet 
{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		MathService svc = new MathService(request);
		String res = svc.calculate();
		
		request.setAttribute("res", res);
		request.getRequestDispatcher("/mathView.jsp").forward(request, response);
	}
}
