<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="css/boardComment3.css" rel="stylesheet">
<link href="css/menuBar2.css" rel="stylesheet">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
<link rel="stylesheet" href="css/fontawesome-stars.css">
<script type="text/javascript" src="css/jquery.barrating.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<header>
		<jsp:include page="include/menuBar.jsp" />
</header>
<body>
<script type="text/javascript"> $(function() { $('#example').barrating({ theme: 'fontawesome-stars' }); }); </script>


<!-- https://lemontia.tistory.com/444        <<<< 요 사이트 보고 했어요 -->
<%
	
	
%>
		<section id="CommentForm">
			<div class="Comment_title">Pick Pick 댓글 작성</div>
			<form action="CommentPro.jsp" method="post" name="CommentForm">
				<div class="Comment">
					<div class="Comment_1">
					<div class="Comment_store_star">
						<div class="store">
						@가게이름
<%-- 						<%=dao.store_name() %> --%>
						</div>
					<div class="star">
<!-- 						<img class="starimg" alt="star" src="img/star.png" width="80px"> -->
						<select id="example">
  						<option value="1">1</option>
  						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						</select>
					</div>
					</div>
					<div class="store2">에 대한 솔직한 리뷰를 써주세요.</div>
						</div>
						<div class="Comment_2">
					<div class="user">
					@유저닉네임
<%-- 					<%=dao.user_nickname %> --%>
					</div>
						<textarea class="commentBox" rows="6" cols="35"></textarea>
					</div>
					<div class="com">
					<input class="submit submit_com" type="submit" value="확 인" >
					<input class="submit submit_re" type="submit" value="취 소" >
				</div>
					</div>
			</form>
		</section>
</body>
<footer>
		<jsp:include page="include/footer.jsp" />
</footer>
</html>