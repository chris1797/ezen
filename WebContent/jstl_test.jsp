<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %> <!-- page directive -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	List<String> list = new ArrayList<>();
	list.add("��ȣ��");
	list.add("������");
	list.add("���̸���");
	
	//pageContext.setAttribute("list", list); <c:set �� ���� �Ǹ� �ʿ����>
	// EL �����δ� jsp���� �����Ⱑ ����
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>JSTL TEST</title>
</head>
<body>
<c:set var="list" value="<%=list%>"/> <!-- var: ������� �������� ������ ������ ���� key-->
<c:forEach var="mem" items="${list}"> <!-- ������ �ִ� list�� �ǹ� -->
									  <!-- ${list} �� �÷����̰�, �ϳ��� ������ mem���� �� -->
	<div>${mem}</div> <!-- get�� ���� ���� �� -->
</c:forEach>
</body>
</html>