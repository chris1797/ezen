<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원정보 추가 폼</title>
</head>
<body>
<h3>사원정보 추가</h3>
<form action="emp" method="post">
	<input type="hidden"  name="cmd"  value="add">
	<div><label for="num">번호</label><input type="text" name="num"></div>
	<div><label for="name">이름</label><input type="text" name="name"></div>
	<div><label for="phone">전화</label><input type="text" name="phone"></div>
	<div><label for="email">메일</label><input type="text" name="email"></div>
	<button type="reset">취소</button>
	<button type="submit">저장</button>
</form>
</body>
</html>