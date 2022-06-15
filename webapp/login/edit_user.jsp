<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String uid = (String) request.getAttribute("uid");
	String pwd = (String) request.getAttribute("pwd");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사용자 정보 변경</title>
<style type="text/css">
	main { width:fit-content; margin:0 auto; }
	main>h3 { width:fit-content; margin:10px auto; }
	#edit_form { width:fit-content; padding:1em; border:1px solid black; border-radius: 7px;}
	label {display:inline-block; width:3em; text-align: right; padding-right:10px; }
	input { width:10em; }
	div:nth-child(4) { width:fit-content; margin:0 auto; margin-top:1em; }
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	function update()
	{
		var serData = $('#edit_form').serialize();
		$.ajax({
			url:'user',
			method:'post',
			cache:false,
			data:serData,
			dataType:'json',
			success:function(res){
				alert(res.updated ? '정보변경 성공':'변경 실패');
			},
			error:function(xhr, status, err){
				alert(err);
			}
		});
		return false;
	}
</script>
</head>
<body>
<main>
	<h3>사용자 정보 변경 폼</h3>
	<form id="edit_form" onsubmit="return update();">
		<input type="hidden" name="cmd" value="update">
		<div><label for="uid">아이디</label><input type="text" id="uid" name="uid" value="<%=uid%>" readonly></div>
		<div><label for="pwd">암 호</label><input type="password" id="pwd" name="pwd" value="<%=pwd%>"></div>
		<div>
			<button type="reset">취소</button>
			<button type="submit">저장</button>
		</div>
	</form>
</main>
</body>
</html>