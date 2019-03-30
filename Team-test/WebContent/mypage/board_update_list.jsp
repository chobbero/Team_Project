<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="css/myPage4.css" rel="stylesheet">
<link href="css/board_update.css" rel="stylesheet">
<link href="css/menuBar3.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div id="container">
		<jsp:include page="../include/menuBar.jsp" />

		<section id="myPage">
		
			<div class="myInfo">
			<div class="myInfo_name">안녕하세요.<br> 저는 ${mb.user_name}입니다.</div>
			<div class="myInfo_email">${mb.user_email }</div>
			<div class="myInfo_birth">${mb.user_birth }</div>
			</div>
		
			<ul class="myPage_list">
			<a href="./UserUpdateForm.mp"><li><div>프로필 수정</div><img class="list_imgs" src="img/edit_profile.png"/></li></a>
			<a href="#"><li><div>Pick리스트</div><img class="list_imgs" src="img/mypage_heart.png"/></li></a>
			<a href="./boardUpdateListForm.mp"><li><div>게시글 수정</div><img class="list_imgs" src="img/edit_board_mypage.png"/></li></a>
			<a href="#"><li><div>회원전환</div><img class="list_imgs" src="img/change_to_business.png"/></li></a>
			<a href="./UserDeleteForm.mp"><li><div>회원탈퇴</div><img class="list_imgs" src="img/remove-user4.png"/></li></a>
			<a href="#"><li><div>로그아웃</div><img class="list_imgs" src="img/logout_mypage.png"/></li></a>
			</ul>
			
			<div class="pick_title">내가 찜한 가게</div>
			
			<table class="pick_table">
			
			<c:forEach begin="1" end="7">
			<tr class="pick_list">
			<td class="subject"><div>맛있는 맛집!</div></td>
			<td class="writer"><div>홍길동</div></td>
			<td class="date"><div>2019.03.31</div></td>
			<td class="delete">
			<a href="#">삭제</a>
			</td>
			<td class="update">
			<a href="./BoardUpdateForm.mp">수정</a>
			</td>
			</tr>
			</c:forEach>
			
			</table>
		
		
		</section>

		<jsp:include page="../include/footer.jsp" />
	</div>


</body>
</html>