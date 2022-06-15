<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	boolean saved = (Boolean) request.getAttribute("saved");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원정보 추가 결과</title>
<script type="text/javascript">
	var saved = <%=saved%>;
	alert(saved ? '사원정보 추가 성공' : '사원정보 추가 실패');
	location.href = 'emp?cmd=list';
</script>
</head>
<body>

</body>
</html>