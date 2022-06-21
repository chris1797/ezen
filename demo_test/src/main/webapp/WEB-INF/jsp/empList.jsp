<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Emp List</title>
<style type="text/css">
	main { width:fit-content; margin:0 auto; }
	main>h3 { width:fit-content; margin:10px auto; }
	table {border:1px solid black; width:600px; border-spacing:0; border-collapse: collapse;}
	th{ border-right:3px double black; background-color:#eee; width:15%;}
	tr:last-child td { width:fit-content; height:7em; overflow: auto;}
	th, td { border-bottom: 1px solid black; padding:5px;}
	td:nth-child(2) { width:15%;}
	footer { width:fit-content; margin:1em auto; }
	a { text-decoration: none; }
</style>
</head>
<body>
<main>
<h3>Emp List</h3>
<table>
<tr><th>사원번호</th><th>이름</th><th>입사일</th><th>부서번호</th><th>연봉</th></tr>
<c:forEach var="list" items="${list }">
	<tr>
	<td>${list.empno}</td>
	<td><a href="/jdbc/emp/empno/${list.empno}">${list.ename }</a></td>
	<td>${list.hiredate }</td>
	<td>${list.deptno }</td>
	<td>${list.sal }</td>
	</tr>
</c:forEach>
</table>
</main>
</body>
</html>