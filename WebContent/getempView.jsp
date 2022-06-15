<%@ page import="com.ezen.vo.EmpVO" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  
	EmpVO getEmp = (EmpVO)request.getAttribute("getEmp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	main { width:fit-content; border:1px solid black; padding:10px; margin:0 auto;}<!-- 자동중앙 -->
	main>div { text-decoration: underline;} <!-- main>div는 자식 selector -->
</style>
</head>
<body>
	<h3>사원정보 보기</h3>
		<div>번호 <%= getEmp.getNum() %></div>
		<div>이름 <%= getEmp.getName() %></div>
		<div>전화 <%= getEmp.getPhone() %></div>
		<div>메일 <%= getEmp.getEmail() %></div>
</body>
</html>