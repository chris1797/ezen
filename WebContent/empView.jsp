<%@ page import="com.ezen.vo.EmpVO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%  
	List<EmpVO> list = (List<EmpVO>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원정보 목록</title>
	<style type="text/css">
		table { border:1px solid black; border-spacing:0px; border-collapse: collapse;}
		td, th {border:1px solid black; padding:5px 10px;}
		th { background-color: #eee;}
	</style>
</head>
<body>
<h3>사원정보 보기</h3>
<table>
	<tr><th>번호</th><th>이름</th><th>전화</th><th>이메일</th></tr>
<%
	for(int i=0; i<list.size(); i++) {
		EmpVO emp = list.get(i);
%>	
	<tr>
		<td><%=emp.getNum() %></td>
		<td><%=emp.getName() %></td>
		<td><%=emp.getPhone() %></td>
		<td><%=emp.getEmail() %></td>
	</tr>
<%
	}
%>
	</table>

</body>
</html>