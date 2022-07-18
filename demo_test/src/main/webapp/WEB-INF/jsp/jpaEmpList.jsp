<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=EUC-KR" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Emp2 List</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
</script>
<style type="text/css">
	main { width:fit-content; margin:0 auto; }
	main>h3 { width:fit-content; margin:10px auto; }
	table { border:1px solid black; width:500px; border-spacing:0; border-collapse: collapse;}
	th,td { border-right: 1px dashed black; text-align:center;}
	td {border-right: 1px dashed black; text-align:left;}
	th:last-child(), td:last-child() { border-right:none; }
	th {background-color: #7bcabe; border-bottom:3px double black; }
	tr:nth-child(odd) {background-color:#eee;}
	tr:hover { background-color: highlight; }
	footer { width:fit-content; margin:5px auto; }
	a { text-decoration: none;  }
	span { color: red;}
</style>
</head>
<body>
<main>
<h3>[ 사원목록 ]</h3>
<table>
	<tr>
		<th>사원번호</th><th>사원이름</th><th>입사연도</th><th>급여</th><th>부서번호</th>
	</tr>
<c:forEach var="emp2" items="${list}">
	<tr>
		<td>${emp2.empno}</td>
		<td><a href="/jpaemp2/detail/${emp2.empno}">${emp2.ename}</a></td>
		<td>${emp2.hiredate}</td>
		<td>${emp2.sal}</td>
		<td>${emp2.deptno}</td>
	</tr>
</c:forEach>
</table>
<footer>
	<c:forEach var="i" begin="${start}" end="${end}">
		<a href="/jpaemp2/list?page=${i}&size=2&sort=empno,desc&sort=empno,desc&sort=ename,asc">
			<c:if test="${i==pageInfo.number }"><span>${i+1}</span></c:if>
			<c:if test="${i!=pageInfo.number }">${i+1}</c:if>
		</a>
	</c:forEach>
[<a href="/jpaemp2/inputform">글쓰기</a>]
</footer>
</main>
</body>
</html>