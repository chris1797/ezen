package com.ezen.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.dao.UserDAO;
import com.ezen.vo.UserVO;

public class UserService 
{
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public UserService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public String process() 
	{
		String cmd = request.getParameter("cmd");
		
		if(cmd==null || cmd.equals("login_form"))
		{
			return "/login/login_form.jsp";
		}
		else if(cmd.equals("login"))
		{
			//이용자의 아이디, 암호를 접수한다
			// UserVO 객체에 저장(초기화)
			// member.txt 파일에서 해당 아이디, 암호를 검색하여 회원인증이 된 경우
			// 웹브라우저에 로그인 성공/실패 메시지를 출력한다
			// session에 인증사실을 기억시킨다
			String uid = request.getParameter("uid");
			String pwd = request.getParameter("pwd");
			UserVO user = new UserVO(uid,pwd);
			
			UserDAO dao = new UserDAO();
			boolean pass = dao.login(user);
			if(pass) {
				request.getSession().setAttribute("uid", uid);
			}
			try {
				PrintWriter out = response.getWriter();
				String jsonStr = "{\"pass\":" +pass+"}";
				out.println(jsonStr);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(cmd.equals("add_form"))
		{
			return "/login/add_form.jsp";
		}
		else if(cmd.equals("add"))
		{
			Object obj = request.getSession().getAttribute("uid");
			System.out.println(obj);
			if (obj==null)
			{
				try {
					PrintWriter out = response.getWriter();
					String jsonStr = "{\"login\":" +false+"}";
					out.println(jsonStr);
					out.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
			String uid = request.getParameter("uid");
			String pwd = request.getParameter("pwd");
			UserVO newUser = new UserVO(uid,pwd);
			UserDAO dao = new UserDAO();
			boolean added = dao.add(newUser);
			try {
				PrintWriter out = response.getWriter();
				String jsonStr = "{\"added\":" +added+"}";
				out.println(jsonStr);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(cmd.equals("logout"))
		{
			try {
				request.getSession().invalidate();  //세션 오브젝트에 저장된 모든 속성을 제거
				PrintWriter out = response.getWriter();
				String jsonStr = "{\"logout\":" +true+"}";
				out.println(jsonStr);
				out.flush();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				PrintWriter out = response.getWriter();
				String jsonStr = "{\"logout\":" +false+"}";
				out.println(jsonStr);
				out.flush();
			}catch(Exception e) {}
			return null;
		}
		else if(cmd.equals("edit"))
		{
			String uid = (String)request.getSession().getAttribute("uid");
			if (uid==null) {
				try {
					response.sendRedirect("user?cmd=login_form");
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
			request.setAttribute("uid", uid);
			request.setAttribute("pwd", ".....");
			return "/login/edit_user.jsp";
		}
		else if(cmd.equals("update"))
		{
			String uid = request.getParameter("uid");
			String pwd = request.getParameter("pwd");
			UserVO user = new UserVO(uid,pwd);
			
			UserDAO dao = new UserDAO();
			boolean updated = dao.update(user);
			PrintWriter out;
			try {
				out = response.getWriter();
				String jsonStr = "{\"updated\":" +updated+"}";
				jsonStr = jsonStr.trim();
				System.out.println("json 문자열 테스트:" + jsonStr);
				out.println(jsonStr);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	


}
