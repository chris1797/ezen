<%@ page import="org.json.simple.*" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	JSONObject jobj = new JSONObject();
	jobj.put("key1", "val1");
	jobj.put("key2", "val2");
	jobj.put("key3", "val3");
	
	String jsStr1 = jobj.toJSONString();
	
	JSONArray jsArr = new JSONArray();
	jsArr.add("Smith");
	jsArr.add("Ward");
	jsArr.add("James");
	
	String jsStr = jsArr.toJSONString();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%=jsStr1 %>
</body>
</html>