<%@page import="board.vo.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    BoardBean boardBean = (BoardBean) request.getAttribute("BoardBean");
    int board_num = (int) request.getAttribute("board_num");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="css/boardWrite6.css" rel="stylesheet">
<link href="css/menuBar3.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div id="container">

		<jsp:include page="../include/menuBar.jsp" />

		<section id="boardWrite">
			<h2>리얼 후기 수정</h2>
			<form action="BoardUpdatePro.bo" method="post"
				 name="boardform" id="writeF">

			<input type="hidden" name="user_id" value="<%=boardBean.getUser_id() %>">
			<input type="hidden" name="board_num" value="<%=board_num %>">


				<table>

					<tr>
						<td><label for="board_name" class="menu">제목</label></td>
					</tr>
					<tr>
						<td><input type="text" name="subject" value="<%=boardBean.getBoard_subject() %>"
							id="board_subject" required="required" class="a"></td>
					</tr>
					<tr>
						<td><label for="board_content" class="menu">내용</label></td>
					</tr>
					<tr>
						<td><textarea name="content" id="board_content"
								cols="30" rows="12" required="required" class="a">
								<%=boardBean.getBoard_content() %>
						</textarea></td>
					</tr>

				</table>
				<input type="submit" value="게시글 수정" class="submit_com"> <input
					type="reset" value="다시쓰기" class="submit_re">
			</form>
		</section>

		<jsp:include page="../include/footer.jsp" />

	</div>
</body>
</html>