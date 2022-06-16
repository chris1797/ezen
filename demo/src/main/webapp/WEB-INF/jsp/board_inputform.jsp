<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board</title>
<style type="text/css">
	main { width:fit-content; margin:10px auto; }
	main>h3 { width:fit-content; margin:10px auto; }
	table {border:1px solid black; width:600px; border-spacing:0; border-collapse: collapse;}
	th{ border-right:3px double black; background-color:#eee; width:15%;}
	tr:last-child td { width:85%; height:7em; overflow: auto;}
	th, td { border-bottom: 1px solid black; padding:5px;}
	td:nth-child(2) { width:15%;}
	footer { width:fit-content; margin:1em auto; }
	a { text-decoration: none; }
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
function add(){
	var formdata = $('#input_form').serialize();
	$.ajax({
		url:'board',
		method:'post',
		cache:false,
		data:formdata,
		dataType:'json',
		success:function(res){
			alert(res.added ? '���� ����' : '���� ����');
			if(res.added){
				location.href="board/save";
			}
		},
		error:function(request,status,err){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
	return false;
}
</script>
</head>
<body>
<main>
<h3>���ۼ�</h3>
<hr>
<br>
<form id="input_form" action="/board/save" onsubmit="return add();">
<input type="hidden" name="cmd" value="write">
<div>���� : <input type="text" id="title" name="title"></div>
<div>���� : <input type="text" id="contents" name="contents"></div>
<!-- <div>�ۼ��� : <input type="text" id="author" name="author"></div>-->  
<!--  <div>�ۼ��� : <input type="date" id="wdate" name="wdate"></div> -->
<!--  <div>�θ�� : <input type="text" id="pcode" name="pcode"></div> -->
<br>
<hr>
<div align="center">
	<button type="submit">�ۼ�</button>
	<button><a href="?cmd=list">�۸��</a></button>
</div>
</form>
</main>
</body>
</html>