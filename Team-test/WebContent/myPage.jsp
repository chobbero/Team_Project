<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="css/myPage3.css" rel="stylesheet">
<link href="css/menuBar3.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div id="container">
		<jsp:include page="include/menuBar.jsp" />

		<section id="myPage">
			<div class="myInfo">
			<div class="myInfo_name">안녕하세요.<br> 저는 김성민입니다.</div>
			<div class="myInfo_email">stranger_m@naver.com</div>
			<div class="myInfo_birth">1991.11.06</div>
			</div>
		
			<ul class="myPage_list">
			<a href="#"><li><div>프로필 수정</div><img src="img/edit_profile.png"></li></a>
			<a href="#"><li><div>Pick리스트</div><img src="img/mypage_heart.png"></li></a>
			<a href="#"><li><div>게시글 수정</div><img src="img/edit_board_mypage.png"></li></a>
			<a href="#"><li><div>회원전환</div><img src="img/change_to_business.png"></li></a>
			<a href="#"><li><div>로그아웃</div><img src="img/logout_mypage.png"></li></a>
			</ul>
			
			<table class="detail_ordinary_info">
			<tr class="myPage_table_top">
			<td colspan="2" class="mypage_title">   기본정보</td>
			</tr>
			<tr>
			<td class="info_left">ID</td><td class="info_right">demian</td>
			</tr>
			<tr>
			<td class="info_left">이름</td><td class="info_right">김성민</td>
			</tr>
			<tr>
			<td class="info_left">생년월일</td><td class="info_right">1991.11.06</td>
			</tr>
			<tr>
			<td class="info_left">이메일</td><td class="info_right">stranger_m@naver.com</td>
			</tr>
			<tr >
			<td class="info_left">전화번호</td><td class="info_right">010-7186-6065</td>
			</tr>
			<tr>
			<td class="info_left">닉네임</td><td class="info_right">빨리 수료하자!!</td>
			</tr>
			</table>
			
			<table class="detail_business_info">
			<tr class="myPage_table_top">
			<td colspan="2" class="mypage_title">   사업자정보</td>
			</tr>
			<tr>
			<td class="info_left">사업자등록번호</td><td class="info_right">506-81-00017</td>
			</tr>
			<tr>
			<td class="info_left">상호</td><td class="info_right">포스코</td>
			</tr>
			<tr>
			<td class="info_left">소재지</td><td class="info_right">경상북도 포항시 남구</td>
			</tr>
			<tr>
			<td class="info_left">전환일자</td><td class="info_right">2019.03.13</td>
			</tr>
			</table>
			
		</section>

		<jsp:include page="include/footer.jsp" />
	</div>


</body>
</html>