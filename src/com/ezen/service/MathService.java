package com.ezen.service;

import javax.servlet.http.HttpServletRequest;

public class MathService {

	private HttpServletRequest request;
	
	public MathService(HttpServletRequest request) {
		this.request = request;
	}
	
	public String getMath() {
		String cmd = request.getParameter("cmd");
		if(cmd==null) return "계산 요청이 없습니다.";
		String sNum1 = request.getParameter("Num1");
		String sNum2 = request.getParameter("Num2");
		
		int Num1 = sNum1 == null ? 0 : Integer.parseInt(sNum1);
		int Num2 = sNum2 == null ? 0 : Integer.parseInt(sNum2);
		/*
		조건이 true면 0, false면 Integer.parseInt 실행
		조건이 2개일때는 3항 연산자를 사용
		*/
		
		String scmd = null;
		if(cmd.equals("add")) {
			scmd = String.format("%d + %d = %d",Num1,Num2, Num1+Num2);
		} else if(cmd.equals("sub")) {
			scmd = String.format("%d - %d = %d",Num1,Num2, Num1-Num2);
		} else if(cmd.equals("mul")) {
			scmd = String.format("%d * %d = %d",Num1,Num2, Num1*Num2);
		} else if(cmd.equals("div")) {
			scmd = String.format("%d / %d = %d",Num1,Num2, Num1/Num2);
		} else scmd = "에러 발생";
			return scmd;
	}
	
}
