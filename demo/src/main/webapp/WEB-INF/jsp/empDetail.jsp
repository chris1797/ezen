<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Emp Detail</title>
</head>
<body>
<h3>Emp List</h3>
<table>
<tr><th>사원번호</th><th>이름</th><th>입사일</th><th>부서번호</th><th>연봉</th></tr>
	<tr>
	<td>${list.empno}</td>
	<td>${list.ename}</td>
	<td>${list.hiredate}</td>
	<td>${list.deptno}</td>
	<td>${list.sal}</td>
	</tr>
</table>
	<br>
		<a href="/jdbc/emp/edit/${list.empno }">[수정]</a>
</body>
</html>