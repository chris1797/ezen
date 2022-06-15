<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String res = (String) request.getAttribute("res");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>수학 서블릿</title>
</head>
<body>
<h1><%= res %></h1>
</body>
</html>