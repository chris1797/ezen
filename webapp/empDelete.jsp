<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String res = (String) request.getAttribute("deleted");
	//System.out.println("삭제결과="+res);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원정보 삭제결과</title>
<script type="text/javascript">

	alert("<%=res %>");
	
	location.href = 'emp?cmd=list';
</script>
</head>
<body>

</body>
</html>