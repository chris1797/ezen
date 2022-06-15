package com.ezen.service;

import javax.servlet.http.HttpServletRequest;

public class HelloService 
{
	private HttpServletRequest request;
	
	public HelloService(HttpServletRequest request) 
	{
		this.request = request;
	}
	
	public String getGugu()
	{
		String sDan = request.getParameter("dan");
		int dan = 0;
		try {
			dan = Integer.parseInt(sDan);
		}catch(NumberFormatException nfe) {
			dan = 2;
		}
		
		String gStr = "";
		for(int i=1;i<=9;i++) {
			gStr += String.format("%d * %d = %d<br>", dan, i, dan*i);
		}
		return gStr;
	}
}
