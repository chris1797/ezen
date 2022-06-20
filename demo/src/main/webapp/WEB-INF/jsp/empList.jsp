<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Emp List</title>
</head>
<body>
<h3>Emp List</h3>
<table>
<tr><th>사원번호</th><th>이름</th><th>입사일</th><th>연봉</th></tr>
<c:forEach var="list" items="${list }">
	<tr>
	<td>${list.empno }</td>
	<td><a href="">${list.ename }</a></td>
	<td>${list.hiredate }</td>
	<td>${list.sal }</td>
	</tr>
</c:forEach>
</table>
</body>
</html>