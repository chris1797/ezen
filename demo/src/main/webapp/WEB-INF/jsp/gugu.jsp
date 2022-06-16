<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단 페이지</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	function req() {
		$.ajax({
			url:'/index/res/dan/5',
			method:'post',
			dataType:'json',
			success:function(res){ //res는 object
				alert(res.num); //res object 안에있는 num
			},
			error:function(xhr,status,err){
				alert(err);
			}
		});
	}
$(function(){	
	$('#fruit').change(function(evt){ //ready함수를 사용, 아래까지 모든 코드가 실행될때까지 기다리겠다.
		//이 태그를 클릭했을때 이 함수가 돌아가게 하겠다. change이벤트 핸들러가 됨
		//this.value // 현재값
		//evt.target.value; // this.value와 똑같은 말
		var fruit = evt.target.value;
		
		$.ajax({
			url:'/index/res/fruit/'+fruit,
			method:'post',
			data:fruit,
			dataType:'json',
			success:function(res){
				alert(res.fruit);
			},
			error:function(xhr,status,err){
				alert(err);
			}
    	});
	});
});
</script>
</head>
<body>
<h3>구구단</h3>
<c:forEach var="gugu" items="${list}">
<div>${gugu}</div>
</c:forEach>
<button type="button" onclick="req();">저장</button>
<select name="fruit" id="fruit">
	<option value="apple">사과</option>
	<option value="orange">오렌지</option>
	<option value="peach">복숭아</option>
	<option value="melon">멜론</option>
</select>
<div>
<br>
	<c:forEach var="dan" begin="2" end="9">
		<a href="/index/gugu/${dan}">${dan}</a>
	</c:forEach>
</div>
</body>
</html>