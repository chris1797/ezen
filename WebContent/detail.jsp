<%@page import="com.ezen.vo.BBSVO"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	BBSVO bbs = (BBSVO) request.getAttribute("bbs");
	String uid = (String) session.getAttribute("uid");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시글 상세보기</title>
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
	function edit(){
		alert('1!!');
		var Obj
		$.ajax({
			url:'bbs',
			method:'post',
			cache:false,
			data:
			dataType:'json',
			success:function(res){
				
			}
		})
	}
</script>
</head>
<body>
<main>
<h3>게시글 보기</h3>
<table>
	<tr><th>번호</th><td><%=bbs.getNum()%></td><th>제목</th><td><%=bbs.getTitle()%></td></tr>
	<tr><th>작성자</th><td><%=bbs.getAuthor()%></td><th>작성일</th><td><%=bbs.getWdate()%></td></tr>
	<tr><th>내용</th><td colspan="3"><%=bbs.getContents()%></td></tr>
</table>
<hr>
<footer>
	[<a href="bbs?cmd=list">목록보기</a>]
	[<a href="javascript:edit();">수정</a>]
</footer>
</main>
</body>
</html>