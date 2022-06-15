<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사용자 등록</title>
<style type="text/css">
	main { width:fit-content; margin:0 auto; }
	main>h3 { width:fit-content; margin:0 auto;}
	#add_form { width:fit-content; margin:0 auto; border:1px solid black; 
				padding:1em; border-radius: 5px;}
	#add_form input {width:80px; }
	label { display:inline-block; width: 3em; text-align: right; margin-right:10px;}
	.div_btn {width:fit-content; margin:0.7em auto; margin-bottom:0;}
	div#result { color:green; font-weight: bolder;}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	function add(){
		var serData = $('#add_form').serialize();
		$.ajax({
			url:'user',
			method:'post',
			cache:false,
			data:serData,
			dataType:'json',
			success:function(res){
				if (res.added){
					var msg = res.added ? "추가 성공" : "추가 실패";
					$('#result').text(msg);
				}else if(res.login==false){
					alert('로그인 후에 사용할 수 있습니다');
					location.href = 'user?cmd=login_form';
				}
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
<h3>사용자 등록 폼</h3>
<div id="result"></div>
<form id="add_form" onsubmit="return add();">
	<input type="hidden" name="cmd" value="add">
	<div><label for="uid">아이디</label><input type="text" id="uid" name="uid"></div>
	<div><label for="pwd">암 호</label><input type="password" id="pwd" name="pwd"></div>
	<div class="div_btn">
		<button type="reset">취소</button>
		<button type="submit">저장</button>
	</div>
</form>
</main>
</body>
</html>