package com.ezen.service;

import javax.servlet.http.HttpServletRequest;

public class MathService 
{
	HttpServletRequest request;
	
	public MathService(HttpServletRequest request) 
	{
		this.request = request;
	}

	public String calculate() 
	{
		String cmd = request.getParameter("cmd");
		if (cmd==null) return "계산 요청이 없습니다";
		
		String snum1 = request.getParameter("num1");
		String snum2 = request.getParameter("num2");
		
		int num1 = snum1==null ? 0 : Integer.parseInt(snum1);
		int num2 = snum2==null ? 0 : Integer.parseInt(snum2);
		
		// 1항, 2항, 3항( x x )
		
		String res = null;
		if 		(cmd.equals("add")) res = String.format("%d + %d = %d", num1, num2, num1+num2);
		else if (cmd.equals("sub")) res = String.format("%d - %d = %d", num1, num2, num1-num2);
		else if (cmd.equals("mul")) res = String.format("%d * %d = %d", num1, num2, num1*num2);
		else if (cmd.equals("div")) res = String.format("%d / %d = %d", num1, num2, num1/num2);
		else res = "잘못된 요청이 전달되었습니다";
		return res;
	}
}
