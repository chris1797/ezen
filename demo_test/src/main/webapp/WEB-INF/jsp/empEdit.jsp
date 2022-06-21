<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Emp Edit page</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
function updated(){
	$.ajax({
		url:'/jdbc/emp/update/${emp.empno}/${emp.deptno}/${emp.sal}',
		method:'post',
		cache:false,
		data:$('#edit_form').serialize(),
		dataType:'json',
		success:function(res){
			alert(res.updated ? '수정성공' : 'failed');
			location.href="/jdbc/emp/all";
		},
		error:function(request,status,error){
			alert("code:"+request.status+"\n"+
					"message:"+request.responseText+"\n"+
						"error:"+error);
		}
	});
	return false;
}
</script>
</head>
<body>
<h3>Emp List</h3>
<form id="edit_form" method="post" action="/jdbc/emp/update" onsubmit="return updated();">
<input type="hidden" id="empno" name="empno" value="${emp.empno}">
	<div>사원번호 : ${emp.empno}</div>
	<div>이름 : ${emp.ename}</div>
	<div>입사일 : ${emp.hiredate}</div>
	<div>부서번호 : <input type="text" id="deptno" name="deptno" value="${emp.deptno}"></div>
	<div>연봉 : <input type="text" id="sal" name="sal" value="${emp.sal}"></div>
<button type="submit">저장</button> &nbsp;
<button type="reset">취소</button>
</form>
</body>
</html>