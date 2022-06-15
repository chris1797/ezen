<%@page import="com.ezen.vo.EmpVO"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	List<EmpVO> list = (List<EmpVO>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원정보 목록</title>
	<style type="text/css">
		main { width:fit-content; margin:0 auto; border:1px solid black; padding:10px;}
		table { border:1px solid black; border-spacing:0px; 
				border-collapse: collapse; margin-top:20px;}
		td, th { border:1px solid black; padding:5px 10px; text-align: center;}
		th { background-color: #eee;}
		a { text-decoration: none; color:blue; }
		a:hover { color:gold; }
	</style>
</head>
<body>
<main>
<h3>사원정보 보기</h3>
<table>
	<tr><th>번호</th><th>이름</th><th>전화</th><th>이메일</th></tr>
<%
	for (int i=0;i<list.size();i++)
	{ 
		EmpVO emp = list.get(i);
	%>
		<tr>
			<td><%=emp.getNum()%></td>
			<td><a href="emp?cmd=getEmp&num=<%=emp.getNum()%>"><%=emp.getName()%></a></td>
			<td><%=emp.getPhone()%></td>
			<td><%=emp.getEmail()%></td>
		</tr>
<%	}
%>
</table>
<hr>
[<a href="emp?cmd=add_form">사원정보 추가</a>]
</main>
</body>
</html>