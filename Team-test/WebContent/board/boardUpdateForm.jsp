<%@page import="board.vo.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardBean boardBean = (BoardBean)request.getAttribute("BoardBean");
	int board_num = (int)request.getAttribute("board_num");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="BoardUpdatePro.bo" method="post">
	<input type="hidden" name="user_id" value="<%=boardBean.getUser_id() %>">
	<input type="hidden" name="board_num" value="<%=board_num %>">
	제목 : <input type="text" name="subject" value="<%=boardBean.getBoard_subject() %>"><br>
	내용 : <textarea name="content" cols="100" rows="10"><%=boardBean.getBoard_content() %></textarea><br>
	<input type="submit" value="수정하기">
</form>
</body>
</html>