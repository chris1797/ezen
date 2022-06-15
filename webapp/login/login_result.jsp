<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	boolean pass = (boolean) request.getAttribute("pass");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>로그인 결과</title>
<script type="text/javascript">
	var pass = <%=pass%>;
	alert(pass ? "로그인 성공" : "로그인 실패");
</script>
</head>
<body>

</body>
</html>