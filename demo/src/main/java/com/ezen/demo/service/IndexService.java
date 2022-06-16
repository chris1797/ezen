package com.ezen.demo.service;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class IndexService {
	private HttpServletRequest request;
	
	public IndexService() {}
	
	public IndexService(HttpServletRequest request) {
		this.request = request;
	}
	
	@Autowired // Dependency Injection, Setter Injection
	public void setRequset(HttpServletRequest request) {
		this.request = request;
	}

	public List<String> getGugu(int dan) { 
//		int dan = 2;
//		String sDan = request.getParameter("dan");
//		if(sDan != null) {
//			dan = Integer.parseInt(sDan);
//		}
//		@RequestParam(value="dan", required=false, defaultValue="2")int dan
//		IndexController의 위 코드가 위내용을 대체하는 것
		List<String> list = new ArrayList<>();
		
		for(int i=1; i<10; i++) {
			list.add(String.format("%d * %d = %d", dan, i, dan*i));
		}
		
		return list;
	}
}
