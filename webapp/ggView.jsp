<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% // Scriptlet
	String msg = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단 보기</title>
</head>
<body>
<%= msg %> <%-- Expression --%>
</body>
</html>