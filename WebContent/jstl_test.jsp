<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %> <!-- page directive -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	List<String> list = new ArrayList<>();
	list.add("강호동");
	list.add("이재훈");
	list.add("네이마르");
	
	//pageContext.setAttribute("list", list); <c:set 을 쓰게 되면 필요없음>
	// EL 만으로는 jsp에서 빼내기가 힘듦
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>JSTL TEST</title>
</head>
<body>
<c:set var="list" value="<%=list%>"/> <!-- var: 변수라는 뜻이지만 본질은 영역에 들어가는 key-->
<c:forEach var="mem" items="${list}"> <!-- 영역에 있는 list를 의미 -->
									  <!-- ${list} 가 컬렉션이고, 하나씩 빠져서 mem으로 들어감 -->
	<div>${mem}</div> <!-- get은 빼고 쓰는 것 -->
</c:forEach>
</body>
</html>