<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%-- contentType="text/html; charset=EUC-KR"�� HelloServlet�� response.setContentType�� ����Ǵ� �� --%>
<%	//Scriptlet �ڹ� ����(��ũ��Ʈ��)
	String msg = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>������ ������</title>
</head>
<body>
<%=msg %>

</body>
</html>