<%@ page import="com.ezen.vo.Employee" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	Employee emp = (Employee) request.getAttribute("searched");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�˻� ���</title>
<style type="text/css">
	main { width : fit-content; margin: 0 auto;}
	main>h3 { width:fit-content; margin: 1em auto;}
	.container>div { border-bottom:1px dashed black; padding:0.2em 0.5em;}
	div>label { display:inline-block; width:3em; padding-right: 1em; text-align:right;}
	nav { width:fit-content; margin:1em quto;}
	a { text-decoration:none; color:blue; }
</style>
</head>
<body>
<main>
<h3>���� ��</h3>
<table>
<c:set var="emp" value="<%=emp%>"/>
<div class="container">
	<div><label>No.</label>${emp.empno}</div>
	<div><label>�̸�</label>${emp.ename}</div>
	<div><label>����</label>${emp.job}</div>
	<div><label>���</label>${emp.mgr}</div>
	<div><label>�Ի���</label>${emp.hiredate}</div>
	<div><label>�޿�</label> ${emp.sal}</div>
	<div><label>�μ�</label>${emp.deptno}</div>
</div>
<hr>
<nav>[<a href="?cmd=edit&num=${emp.empno}">����</a>]</nav>
</table>
</main>
</body>
</html>