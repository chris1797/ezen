<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 목록</title>
<style type="text/css">
	main { width:fit-content; margin:0 auto; }
	main>h3 { width:fit-content; margin:10px auto; text-align: center;}
	table { border:1px solid black; width:500px; border-spacing:0; border-collapse: collapse;}
	th,td { border-right: 1px dashed black; text-align:center;}
	td {border-right: 1px dashed black; text-align:center;}
	th:last-child(), td:last-child() { border-right:none; }
	th {background-color: #7bcabe; border-bottom:3px double black; }
	tr:nth-child(odd) {background-color:#eee;}
	tr:hover { background-color: highlight; }
	footer { width:fit-content; margin:5px auto; }
	a { text-decoration: none;  }
</style>
</head>
<body>
<main>
<h3>파일목록</h3>
<table>
<tr><th>No.</th><th>글쓴이</th><th>설명</th><th>파일명</th></tr>
<c:forEach var="f" items="${list}">
<tr>
<td>${f.num}</td>
<td>${f.writer}</td>
<td>${f.comments}</td>
<td>${f.fname}</td>
</tr>
</c:forEach>
</table>
</main>
</body>
</html>