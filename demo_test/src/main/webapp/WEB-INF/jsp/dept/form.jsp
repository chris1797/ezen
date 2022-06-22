<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DEPT Test Form</title>
</head>
<body>
	<h3>DEPT 테스트용 폼</h3>
	<form id="sampleform" method="post" action="/mybatis/dept/getemp">
		<select name="deptno" multiple>
			<option>10</option>
			<option>20</option>
			<option>30</option>
			<option>40</option>
		</select>
			<button type="submit">전송</button>
			<button type="reset">취소</button>
	</form>
</body>
</html>