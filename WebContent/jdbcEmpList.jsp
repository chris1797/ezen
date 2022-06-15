<%@ page import="org.json.simple.*" %>
<%@ page import="com.ezen.vo.Employee" %>
<%@ page import="com.ezen.dao.EmployeeDAO" %>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>EMP LIST</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	$(function(){
		$('input[type=radio]').click(function(evt){
			var search = evt.target.value;
			$('#searchVal').val(''); //key 집어넣는곳을 비워라
			var obj = {}; // {} 안에 key와 value가 들어감
			obj.cmd = 'getItemList';
			obj.search = search;
			
			$.ajax({
				url:'Jdbcemp',
				method : 'post',
				cache:false,
				data:obj,
				dataType:'text',
				success:function(res){
					
					var obj = JSON.parse(res); //결과값을 json obj 배열로 parsing, 따라서 obj는 배열이 됨
					var options = "";
					for(var i=0; i<obj.length; i++){
						options += "<option value='"+ obj[i] + "'>";
					}
					$('#list').html(options);
				},
				error:function(xhr, status, err){
					alert(err);
				}
			});
		});
	
	$('.td_empno').mouseover(function(evt) {
			var empno = $(evt.target).text();
			var obj = {};
			obj.cmd = 'getImage';
			obj.empno = empno;
			
			$.ajax({
				url:'Jdbcemp',
				method : 'post',
				cache:false,
				data:obj,
				dataType:'json',
				success:function(res){
					$('#img1').attr("src", "images/" + res.pic);
				},
				error:function(xhr,status,err){
					alert('Error:'+err)
				}
			});
		});
	});
</script>
<style type="text/css">
	a { text-decoration: none; color:blue; }
	a:hover { color:gold;}
	main { width:fit-content; margin:0 auto;}
	main>h3 {width:fit-content; padding:1em; border:1px solid blcak; margin:0 auto;}
	table { width:fit-content; padding:1em; border:1px solid black;
			border-spacing:0; border-collapse:collapse;}
	th { background-color: #eee; border-bottom: 3px double black;}
	th,td { border-right:1px dashed black; width:fit-content; padding:0.2em 1em;}
	td { id='td_empno'; border-bottom: 1px solid black;}
	th:last-child {border-right:none;}
	td:last-child {border-right: none;}
</style>
</head>
<body>
<main>
<h3 align="center">직원 목록</h3>
<table>
<tr><th>사원번호</th><th>이름</th><th>부서번호</th><th>연봉</th><th>입사년도</th></tr>
<c:forEach var="emp" items="${list}">
		<tr>
			<td class="td_empno">${emp.empno}</td>
			<td><a href="?cmd=detail&num=${emp.empno}">${emp.ename}</a></td>
			<td><a href="?cmd=getDept&deptno=${emp.deptno}">${emp.deptno}</td>
			<td>${emp.sal}</td>
			<td>${emp.hiredate}</td>
		</tr>
</c:forEach>
</table>

<form action="Jdbcemp">
<input type="hidden" name="cmd" value="search">
<br>
	<div>
		<label>이름으로 검색</label>
		<input type="radio" id="search" name="search" value="ename">
		<label>사번으로 검색 </label>
		<input type="radio" id="search" name="search" value="empno">
	</div>
	<div>
		<label>검색어</label>
		 <input type="text" id="searchVal" name="searchVal" list="list">
		<button type="submit">검색</button>
		<button type="reset">취소</button>
	</div>
</form>
</main>
<datalist id="list"></datalist>
<img id="img1">
</body>
</html>