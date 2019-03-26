<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="../css/businessChange.css" rel="stylesheet">
<link href="../css/menuBar3.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div id="container">
		<jsp:include page="/include/menuBar.jsp" />

<!--모바일 -->
		
		<section id="myPage">
			<form action="businessJoinPro.bp" method="post" name="businessJoinForm">
			<div class="bcange_title">사업자 정보 입력</div>
			<div class="bchange">
			<div class="change">
			<input class="a" type="text" maxlength="20" placeholder="사업자등록번호" name="business_number"> 
			<input class="a" type="text" maxlength="20" placeholder="상호" name="business_store">
			<input class="a" type="hidden" name="user_grade" value="B"> 
			</div>
			
			<div class="com"><input class="submit_com" type="submit" value="제출" ></div>
			</div>
		
			<ul class="myPage_list">
			<a href="#"><li><div>프로필 수정</div><img src="../img/edit_profile.png"></li></a>
			<a href="#"><li><div>Pick리스트</div><img src="../img/mypage_heart.png"></li></a>
			<a href="#"><li><div>게시글 수정</div><img src="../img/edit_board_mypage.png"></li></a>
			<a href="#"><li><div>회원전환</div><img src="../img/change_to_business.png"></li></a>
			<a href="#"><li><div>로그아웃</div><img src="../img/logout_mypage.png"></li></a>
			</ul>
			
<!--탭, 모니터 -->
			
			<div class="bchange2">
			<div class="bcange_title2">사업자 정보 입력</div>
			<div class="change2">
			<input class="a2" type="text" maxlength="20" placeholder="사업자등록번호" name="business_number"> 
			<input class="a2" type="text" maxlength="20" placeholder="상호" name="business_store">
			<input class="a2" type="hidden" name="user_grade" value="B"> 
			</div>
			
			<div class="com2"><input class="submit_com2" type="submit" value="제 출" ></div>
			</div>
			</form>
		</section>

		<jsp:include page="/include/footer.jsp" />
	</div>


</body>
</html>