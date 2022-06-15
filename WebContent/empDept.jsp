<%@ page import="com.ezen.vo.Employee" %>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<main>
<h3>직원 목록</h3> 
<table>
<tr><th>사원번호</th><th>이름</th><th>부서번호</th><th>연봉</th><th>입사년도</th></tr>
<c:forEach var="emp" items="${list}">
		<tr>
			<td>${emp.empno}</td>
			<td>${emp.ename}</td>
			<td>${emp.deptno}</td>
			<td>${emp.sal}</td>
			<td>${emp.hiredate}</td>
		</tr>
</c:forEach>
</main>
</body>
</html>