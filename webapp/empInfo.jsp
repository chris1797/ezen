<%@page import="com.ezen.vo.EmpVO"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	EmpVO emp = (EmpVO) request.getAttribute("emp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원정보 상세보기</title>
<style type="text/css">
	main {width:fit-content; border:1px solid black; padding:10px;
		margin:0 auto;}
	main>div { text-decoration: underline;}
	a { text-decoration: none; color:blue; }
</style>
</head>
<body>

<main>
	<h3>사원정보 상세보기</h3>
	<div>번호 <%=emp.getNum() %></div>
	<div>이름 <%=emp.getName() %></div>
	<div>전화 <%=emp.getPhone() %></div>
	<div>메일 <%=emp.getEmail() %></div>
	<hr>
	[<a href="emp?cmd=list">목록보기</a>]
	[<a href="emp?cmd=edit&num=<%=emp.getNum()%>">수정</a>]
</main>
</body>
</html>