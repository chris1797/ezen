package com.ezen.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hello {

	public static void main(String[] args) {
		/*
		 java.sql.Date now = new java.sql.Date(new java.util.Date().getTime());
		 System.out.println(now); // 2022-06-03
		 */
		
		List<Map> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("num", 11);
		map.put("title", "축하링~");
		map.put("author", "SCOTT");
		map.put("wdate", new java.sql.Date(new java.util.Date().getTime()));
		map.put("pcode", 0);
		list.add(map);
		
		
	}

}
