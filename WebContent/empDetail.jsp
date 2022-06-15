<%@ page import="com.ezen.vo.Employee" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	Employee emp = (Employee) request.getAttribute("detail");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>EMP Detail</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">

	function remove(empno){
		if(!confirm('�ش� ��������� �����Ͻðھ��?')) return;
		
		var obj = {};
		obj.cmd = 'remove';
		obj.empno = empno;
		
		$.ajax({
			url : 'Jdbcemp',
			type : "post",
			cache: false,
			data : obj,
			dataType : 'json',
			success:function(res){
				if(res.deleted){
					alert('���� ����');
					location.href = "Jdbcemp?cmd=list";
				} else {
					alert('Failed');
					location.href = "Jdbcemp?cmd=list";
				}
			},
			error:function(xhr, status, err){
				alert('Error:' + err)
			}
		});
	}
</script>
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
<nav>
	[<a href="?cmd=edit&num=${emp.empno}">����</a>]
	[<a href="javascript:remove(${emp.empno})">����</a>]
</nav>
</table>
</main>
</body>
</html>