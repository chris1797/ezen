package com.ezen.service;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.dao.BBSDAO;
import com.ezen.vo.BBSVO;

public class BBSService 
{
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public BBSService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public String process() 
	{
		String cmd = request.getParameter("cmd");
		if(cmd==null || cmd.equals("index"))
		{
			return "/index.jsp";
		}
		else if(cmd.equals("add_form"))
		{
			return "/add_form.jsp";
		}
		else if(cmd.equals("add"))
		{
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			String uid = (String)request.getSession().getAttribute("uid");
			java.sql.Date now = new java.sql.Date(new java.util.Date().getTime());
			BBSDAO dao = new BBSDAO();
			int num =  dao.getLastNum() + 1;
			
			BBSVO bbs = new BBSVO();
			bbs.setNum(num);
			bbs.setTitle(title);
			bbs.setContents(contents);
			bbs.setAuthor(uid);
			bbs.setWdate(now);
			
			boolean added = dao.add(bbs);
			try {
				PrintWriter out = response.getWriter();
				out.println(String.format("{\"%s\":%b}", "added", added));
				out.flush();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(cmd.equals("list"))
		{
			BBSDAO dao = new BBSDAO();
			List<BBSVO> list = dao.getList();
			request.setAttribute("list", list);
			return "/list.jsp";
		}
		else if(cmd.equals("detail"))
		{
			int num = Integer.parseInt(request.getParameter("num"));
			BBSDAO dao = new BBSDAO();
			BBSVO bbs = dao.getBBS(num);
			request.setAttribute("bbs", bbs);
			return "/detail.jsp";
		}
		return null;
	}
	
}