package com.ezen.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.service.HelloService;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/hello") // ~/hello?dan=5 -> 이 Servlet에 대해서 get방식으로 dan=5를 전달
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("Hello Servlet 실행됨"); //현재 시스템의 출력장치에다가 출력
//		요청한 클라이언트에게 응답하려면?
		response.setContentType("text/html; charset=utf-8"); // 응답의 header, 이건 브라우저가 참조만 함 / text의 범주안에 html이 들어와 있는 것
		PrintWriter out = response.getWriter(); //문자열을 write할거다
		
		request.setCharacterEncoding("utf-8");
		
		HelloService svc = new HelloService(request);
		String msg = svc.getGugu(); //service를 시켜서 getGugu를 실행시키고, 리턴값을 msg에 저장
		//System.out.println(msg);
		
		//받은 msg를 jsp(view)로 forwarding 해줘야 함
		
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("/ggView.jsp").forward(request, response);
		// /ggView.jsp한테 request, response 객체를 보낸다.
	}
}
