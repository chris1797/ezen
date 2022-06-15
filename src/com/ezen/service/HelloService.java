package com.ezen.service;

import javax.servlet.http.HttpServletRequest;

public class HelloService {
	//Controller Roll
	
	private HttpServletRequest request;
	
	// 여기 클라이언트의 요청이 다 포함되어 있음, Http라는건 웹브라우저에서 들어온 요청이라는 뜻
	public HelloService(HttpServletRequest request) {
		this.request = request;
	}
	
	public String getGugu() {
		String sDan = request.getParameter("dan");
		int dan = 0;
		try {
			dan = Integer.parseInt(sDan);
		} catch(NumberFormatException nfe) {
			dan =2;
		}
		String gStr = "";
		//↓ Servlet에서 했던 작업 그대로 Controller에서 구현
		for(int i=1; i<=9; i++) {
			gStr += String.format("%d * %d = %d<br>", dan, i, dan*i);
		}
		return gStr;
	}
}
