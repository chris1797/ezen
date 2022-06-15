<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<% 
	String scmd = (String)request.getAttribute("scmd");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Math Page</title>
</head>
<body>
<h2>Math Page</h2>

<a><%= scmd %></a>
</body>
</html>