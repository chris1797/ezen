<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String[] hobby = request.getParameterValues("hobby");
	System.out.println("User's Selections:");
	for(int i=0; i<hobby.length; i++){
		System.out.println(hobby[i]);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkbox Test</title>
<style type="text/css">
	fieldset {width: fit-content; margin: 0 auto;}
</style>
</head>
<body>
<h3>Checkbox Test</h3>
<form>
<fieldset>
	<legend>취미</legend>
	<div>
		<label for="game">게임</label>
		<input type="checkbox" value="game" name="hobby">
	</div>
	<div>
		<label for="fishing">낚시</label>
		<input type="checkbox" value="fishing" name="hobby">
	</div>
	<div>
		<label for="napping">낮잠</label>
		<input type="checkbox" value="napping" name="hobby">
	</div>
	<div>
		<button type="reset">취소</button>
		<button type="submit">저장</button>
	</div>
</fieldset>
</form>
</body>
</html>