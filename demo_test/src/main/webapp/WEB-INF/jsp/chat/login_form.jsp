<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>로그인 폼</title>
<style type="text/css">
	main { width:fit-content; padding:15px; margin:0 auto;}
	#loginForm {width:fit-content; padding:15px; border:1px solid black; margin:0 auto; 
			border-radius: 5px; margin-top:5px; }
	main>h3 { width:fit-content; margin:0 auto; }
	label {display:inline-block; width:3em; text-align: right; margin-right:1em;}
	div:nth-child(4) {width:fit-content; margin:0 auto; margin-top:1em;}
	input {width:10em;}
	a { text-decoration: none; color:blue; }
	
	.link_user { display:inline-block; width:fit-content; font-size: 0.8em; magrin:10 auto;}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	function login(){
		alert('login');
		var serData = $('#loginForm').serialize();
		$.ajax({
			url:'/ws/logincheck',
			method:'post',
			cache:false,
			data:serData,
			dataType:'json',
			success:function(res){
				alert(res.logincheck ? '로그인 성공' : '로그인 실패');
				location.href="/ws/chat";
			},
			error:function(xhr, status, err){
				alert(err);
			}
		});
		return false;
	}
	
	function logout()
	{
		if(!confirm('정말로 로그아웃하시겠어요?')) return;
		$.ajax({
			url:'/ws/logout',
			method:'get',
			cache:false,
			dataType:'json',
			success:function(res){
				alert(res.logout ? '로그아웃 성공':'로그아웃 실패');
			},
			error:function(xhr,status,err){
				alert(err);
			}
		});
	}
</script>
</head>
<body>
<main>
<h3>로그인</h3>
<div class="link_user"><a href="user?cmd=add_form">회원등록</a> |
		<a href="">아이디/암호 찾기</a> | <a href="javascript:logout();">로그아웃</a></div>
<form id="loginForm" action="/ws/logincheck" method="post" onsubmit="return login();">
	<div><label for="uid">아이디</label><input type="text" id="uid" name="uid" value="smith"></div>
	<div><label for="pwd">암 호</label><input type="password" id="pwd" name="pwd" value="smithpwd"></div>
	<div><button type="reset">취소</button>
		<button type="submit">로그인</button>
	</div>
</form>
<div class="link_user">[<a href="user?cmd=edit">회원정보 변경</a>]</div>
</main>
</body>
</html>


