<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="css/main9_1.css" rel="stylesheet">
<link href="css/menuBar2.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div id="container">

		<jsp:include page="include/menuBar.jsp" />

	<section id="writeForm">
		<h2>게시판 글 등록</h2>
			<form action="BoardWritePro.bo" method="post"
				enctype="multipart/form-data" name="boardform">
				<table>
					<tr>
						<td class="td_left"><label for="board_name">글쓴이</label></td>
						<td class="td_right"><input type="text" name="board_writer"
							id="board_name" required="required"></td>
					</tr>
					<tr>
						<td class="td_left"><label for="board_subject">제목</label></td>
						<td class="td_right"><input type="text" name="board_subject"
							id="board_subject" required="required"></td>
					</tr>
					<tr>
						<td class="td_left"><label for="board_content">내용</label></td>
						<td class="td_right"><textarea name="board_content"
								id="board_content" cols="40" rows="15" required="required"></textarea></td>
					</tr>
					<tr>
						<td class="td_left"><label for="board_file">사진</label></td>
						<td class="td_right"><input type="file" name="board_image"
							id="board_file" required="required"></td>
					</tr>
				</table>

				<input type="submit" value="등록">&nbsp;&nbsp; <input
					type="reset" value="다시쓰기">
			</form>
		</section>
		
		<jsp:include page="include/footer.jsp" />
		
	</div>
</body>
</html>