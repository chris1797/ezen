<%@page import="java.util.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	List<Map> list = new ArrayList<>();

	Map<String, Object> map = new HashMap<>();
	map.put("num", 11);
	map.put("title", "축하링~");
	map.put("author", "SCOTT");
	map.put("wdate", new java.sql.Date(new java.util.Date().getTime()));
	map.put("pcode", 0);
	list.add(map);
	
	map = new HashMap<>();
	map.put("num", 12);
	map.put("title", "축하링~");
	map.put("author", "SCOTT");
	map.put("wdate", new java.sql.Date(new java.util.Date().getTime()));
	map.put("pcode", 0);
	list.add(map);
	
	request.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL을 사용한 Map 자료구조 다루기</title>
</head>
<body>
<h3>EL을 사용한 Map 자료구조 다루기</h3>
<c:forEach var="m" items="${list}">

	<div>${m.num}</div>
	<div>${m.title}</div>
	<div>${m.author}</div>
	<div>${m.wdate}</div>
	<div>${m.pcode}</div>
	<hr>

</c:forEach>
</body>
</html>