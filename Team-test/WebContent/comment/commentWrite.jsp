<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="css/boardComment3.css" rel="stylesheet">
<link href="css/menuBar3.css" rel="stylesheet">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
<link rel="stylesheet" href="css/fontawesome-stars.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.eval_l').click(function() {
			$('.eval_l').css("background-image", "url('img/like_comment.png')");
			$('.eval_d').css("background-image", "url('img/unfilled_unlike_comment.png')");
		});

		$('.eval_d').click(function() {
			$('.eval_d').css("background-image", "url('img/dislike_comment.png')");
			$('.eval_l').css("background-image", "url('img/unfilled_like_comment.png')");

		});
	});
</script>

</head>
<header>
	<jsp:include page="../include/menuBar.jsp" />
</header>
<body>
	<section id="CommentForm">
		<div class="Comment_title">Pick Pick 댓글</div>
		<form action="CommentWritePro.co" method="post" name="CommentForm">
			<input type="hidden" name="user_id" value="${memberBean.user_id } ">
			<input type="hidden" name="board_num"
				value="${boardBean.board_subject}">

			<div class="Comment">
				<div class="Comment_1">
					<div class="Comment_store_star">
						<div class="store">${boardBean.board_subject}</div>
						<div class="star">
							<label class="eval_l" for="like"></label><input id="like"
								type="radio" name="comment_like" value="Y"> <label
								class="eval_d" for="dislike"></label><input id="dislike"
								type="radio" name="comment_like" value="N">
						</div>
						<div class="store2">에 대한 솔직한 리뷰를 써주세요.</div>
					</div>
					<div class="Comment_2">
						<div class="user">@ ${memberBean.user_nickname }</div>
						<textarea class="commentBox" rows="6" cols="35"
							name="comment_content" placeholder="여러분의 소중한 리뷰를 남겨주세요."></textarea>
					</div>
					<div class="com">
						<input class="submit submit_com" type="submit" value="확 인">
						<input class="submit submit_re" type="reset" value="취 소">
					</div>
				</div>
		</form>
	</section>
</body>
<footer>
	<jsp:include page="../include/footer.jsp" />
</footer>
</html>