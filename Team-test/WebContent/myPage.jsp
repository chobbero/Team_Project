<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="css/myPage2.css" rel="stylesheet">
<link href="css/menuBar3.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div id="container">
		<jsp:include page="include/menuBar.jsp" />

		<section id="myPage">
			<div class="myInfo">
			<div class="myInfo_id"></div>
			<div class="myInfo_email"></div>
			
			</div>
		
			<ul class="myPage_list">
			<a href="#"><li><div>프로필 수정</div><img src="img/edit_profile.png"></li></a>
			<a href="#"><li><div>Pick리스트</div><img src="img/mypage_heart.png"></li></a>
			<a href="#"><li><div>게시글 수정</div><img src="img/edit_board_mypage.png"></li></a>
			<a href="#"><li><div>회원전환</div><img src="img/change_to_business.png"></li></a>
			<a href="#"><li><div>로그아웃</div><img src="img/logout_mypage.png"></li></a>
			</ul>
			
			
		</section>

		<jsp:include page="include/footer.jsp" />
	</div>


</body>
</html>