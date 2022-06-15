<%@page import="com.ezen.vo.EmpVO"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	EmpVO emp = (EmpVO) request.getAttribute("emp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원정보 수정폼</title>
</head>
<body>
<h3>사원정보를 수정해주세요</h3>

<form action="emp" method="post">
<input type="hidden" name="cmd" value="update">
<input type="hidden" name="num" value="<%=emp.getNum()%>">
<div>번호 : <%=emp.getNum()%></div>
<div>이름 : <%=emp.getName()%></div>
<div>전화 : <input type="text" name="phone" value="<%=emp.getPhone()%>"></div>
<div>메일 : <input type="text" name="email" value="<%=emp.getEmail()%>"></div>
<button type="submit">저장</button>
</form>

</body>
</html>