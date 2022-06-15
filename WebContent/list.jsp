<%@page import="com.ezen.vo.BBSVO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	List<BBSVO> list = (List<BBSVO>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판 글 목록</title>
<style type="text/css">
	main { width:fit-content; margin:0 auto; }
	main>h3 { width:fit-content; margin:10px auto; }
	table { border:1px solid black; width:500px; border-spacing:0; border-collapse: collapse;}
	th,td { border-right: 1px dashed black; text-align:center;}
	th:last-child(), td:last-child() { border-right:none; }
	th {background-color: #fe0; border-bottom:3px double black; }
	tr:nth-child(odd) {background-color:#eee;}
	tr:hover { background-color: orange; }
	footer { width:fit-content; margin:5px auto; }
	a { text-decoration: none;  }
</style>
</head>
<body>
<main>
<h3>게시판 글 목록</h3>
<table>
<tr><th>번호</th><th>제목</th><th>작성자</th><th>작성일</th></tr>
<%
	for(int i=0;i<list.size();i++) {
		BBSVO bbs = list.get(i); %>
		<tr>
			<td><%=bbs.getNum()%></td>
			<td>
				<a href="bbs?cmd=detail&num=<%=bbs.getNum()%>"><%=bbs.getTitle()%></a>
			</td>
			<td><%=bbs.getAuthor()%></td>
			<td><%=bbs.getWdate()%></td>
		</tr>
<%	}
%>
</table>
<hr>
<footer>
	[<a href="bbs?cmd=add_form">글쓰기</a>]
</footer>
</main>
</body>
</html>