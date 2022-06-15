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
		if(!confirm('�Է��Ͻ� ������ �����Ͻðھ��?')) return false;
		var formdata = $('#edit_form').serialize();
		$.ajax({
			url:'Jdbcemp',
			method:'post',
			cache:false,
			data:formdata,
			dataType:'json',
			success:function(res){
				alert('��������');
				//alert(res.updated ? '���� ����':'Failed');
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
<h3 align="center">���� ���� ����</h3>
<form id="edit_form" onsubmit="return updated();">
<input type="hidden" name="cmd" value="update">
<input type="hidden" name="empno" value="${emp.empno}">
	<div>	
		<label>No.</label>${emp.deptno}
	</div>
	<div>	
		<label>�̸�</label>${emp.ename}
	</div>
	
	<div>	
		<label for="deptno">�μ���ȣ</label>
		<input type="text" id="deptno" name="deptno" value="${emp.deptno}">
	</div>
	
	<div>	
		<label for="sal">�޿�</label>
		<input type="text" id="sal" name="sal" value="${emp.sal}">
	</div>
	
	<div>
		<button type="submit">����</button>
		<button type="reset">���</button>
	</div>
</form>
</main>
</body>
</html>