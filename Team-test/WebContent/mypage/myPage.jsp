<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="css/myPage4.css" rel="stylesheet">
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
			<a href="./PickList.mp"><li><div>Pick리스트</div><img class="list_imgs" src="img/mypage_heart.png"/></li></a>
			<a href="./boardUpdateListForm.mp"><li><div>게시글 수정</div><img class="list_imgs" src="img/edit_board_mypage.png"/></li></a>
			<a href="#"><li><div>회원전환</div><img class="list_imgs" src="img/change_to_business.png"/></li></a>
			<a href="./UserDeleteForm.mp"><li><div>회원탈퇴</div><img class="list_imgs" src="img/remove-user4.png"/></li></a>
			<a href="#"><li><div>로그아웃</div><img class="list_imgs" src="img/logout_mypage.png"/></li></a>
			</ul>
			
			<table class="detail_ordinary_info">
			<tr class="myPage_table_top">
			<td colspan="2" class="mypage_title"><div>기본정보</div></td>
			</tr>
			<tr>
			<td class="info_left"><div>ID</div></td><td class="info_right"><div>${mb.user_id }</div></td>
			</tr>
			<tr>
			<td class="info_left"><div>이름</div></td><td class="info_right"><div>${mb.user_name }</div></td>
			</tr>
			<tr>
			<td class="info_left"><div>생년월일</div></td><td class="info_right"><div>${mb.user_birth }</div></td>
			</tr>
			<tr>
			<td class="info_left"><div>이메일</div></td><td class="info_right"><div>${mb.user_email }</div></td>
			</tr>
			<tr >
			<td class="info_left"><div>전화번호</div></td><td class="info_right"><div>${mb.user_phone }</div></td>
			</tr>
			<tr>
			<td class="info_left"><div>닉네임</div></td><td class="info_right"><div>${mb.user_nickname }</div></td>
			</tr>
			</table>
			
			<c:if test="${sessionScope.user_grade=='B'}">
			<table class="detail_business_info">
			<tr class="myPage_table_top">
			<td colspan="2" class="mypage_title"><div>사업자정보</div></td>
			</tr>
			<tr>
			<td class="info_left"><div>사업자등록번호</div></td><td class="info_right"><div>506-81-00017</div></td>
			</tr>
			<tr>
			<td class="info_left"><div>상호</div></td><td class="info_right"><div>포스코</div></td>
			</tr>
			<tr>
			<td class="info_left"><div>소재지</div></td><td class="info_right"><div>경상북도 포항시 남구</div></td>
			</tr>
			<tr>
			<td class="info_left"><div>전환일자</div></td><td class="info_right"><div>2019.03.13</div></td>
			</tr>
			</table>
			</c:if>

		</section>

		<jsp:include page="../include/footer.jsp" />
	</div>


</body>
</html>