<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>������ ������ ������ ���� HTML �±׵�</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	$(function(){ <!--  <- �̷��� ������� ���ư�  -->
		$('input[type=radio]').click(function(evt){
			var category = evt.target.value;
			if(category == 'empno') {
				
			} else if(category == 'ename') {
				
			}
		});
	});
</script>
</head>
<body>
<form id="input_form" onsubmit="return test();">
	����˻�:<input type="radio" name="search" value="1">
	�̸��˻�:<input type="radio" name="search" value="0">

	���� �Է� : <input type="text" list="nums">
	<datalist id="nums">
		<c:forEach var="list" items="${list}">
		<option value="${list.empno}">
		</c:forEach>
	</datalist>
</form>
</body>
</html>