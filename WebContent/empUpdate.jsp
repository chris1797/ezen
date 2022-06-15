<%@ page contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Edit page</title>
<style type="text/css">
	label { display:inline-block; width:4em; margin-right:1em; text-align:right;}
	main {width:fit-content; margin:0 auto;}
	main>h3 {text-align:center;}
	form { border:1px solid black; padding:0.5em 1em;}
	form > div:last-child{width:fit-content; margin:0.7em auto; border:1px solid black;}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	function updated(){
		if(!confirm('입력하신 정보로 변경하시겠어요?')) return false;
		var formdata = $('#edit_form').serialize();
		$.ajax({
			url:'Jdbcemp',
			method:'post',
			cache:false,
			data:formdata,
			dataType:'json',
			success:function(res){
				alert('수정성공');
				//alert(res.updated ? '수정 성공':'Failed');
				location.href="Jdbcemp?cmd=list";
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
<h3 align="center">직원 정보 수정</h3>
<form id="edit_form" onsubmit="return updated();">
<input type="hidden" name="cmd" value="update">
<input type="hidden" name="empno" value="${emp.empno}">
	<div>	
		<label>No.</label>${emp.deptno}
	</div>
	<div>	
		<label>이름</label>${emp.ename}
	</div>
	
	<div>	
		<label for="deptno">부서번호</label>
		<input type="text" id="deptno" name="deptno" value="${emp.deptno}">
	</div>
	
	<div>	
		<label for="sal">급여</label>
		<input type="text" id="sal" name="sal" value="${emp.sal}">
	</div>
	
	<div>
		<button type="submit">저장</button>
		<button type="reset">취소</button>
	</div>
</form>
</main>
</body>
</html>