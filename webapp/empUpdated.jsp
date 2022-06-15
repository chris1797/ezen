<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	boolean updated = (Boolean) request.getAttribute("updated");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>수정 결과</title>
<script type="text/javascript">
	var updated = <%=updated%>;
	alert(updated ? '수정 성공': 'Failed');
	location.href='emp?cmd=list';
</script>
</head>
<body>

</body>
</html>