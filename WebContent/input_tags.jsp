<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>서버로 데이터 전송을 위한 HTML 태그들</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	$(function(){ <!--  <- 이렇게 써야지만 돌아감  -->
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
	사번검색:<input type="radio" name="search" value="1">
	이름검색:<input type="radio" name="search" value="0">

	숫자 입력 : <input type="text" list="nums">
	<datalist id="nums">
		<c:forEach var="list" items="${list}">
		<option value="${list.empno}">
		</c:forEach>
	</datalist>
</form>
</body>
</html>