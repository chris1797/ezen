<%@ page import="com.ezen.vo.Employee" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("greeting", "Hello World");

	Employee emp = new Employee();
	emp.setEmpno(11);
	emp.setEname("Scott");
	emp.setHiredate(java.sql.Date.valueOf("2001-06-17"));
	//emp를 영역 오브젝트 중 하나에 저장하고 EL을 통하여 화면에 표시해보세요.
	pageContext.setAttribute("emp", emp);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>EL (Expression Language)</title>
</head>
<body>
<% 
	String greet = (String) pageContext.getAttribute("greeting"); 
	out.print("자바 코드를 사용한 경우 : " + greet);	
%>
<p>
Expression 태그를 사용한 경우 : <%= greet %> <%-- Expression Tag를 이용하여 화면에 표시 --%>
<p>
EL을 사용한 경우 : ${greeting} <!-- EL을 사용하여 화면에 표시 -->
<p>
<div>사번 : ${emp.empno}</div>
<div>이름 : ${emp.ename}</div>
<div>입사 : ${emp.hiredate}</div>
</body>
</html>