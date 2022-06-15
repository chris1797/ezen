<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%-- contentType="text/html; charset=EUC-KR"은 HelloServlet의 response.setContentType과 연결되는 것 --%>
<%	//Scriptlet 자바 영역(스크립트릿)
	String msg = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>구구단 페이지</title>
</head>
<body>
<%=msg %>

</body>
</html>