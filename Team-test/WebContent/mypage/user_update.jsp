<%@page import="member.vo.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="css/myPage4.css" rel="stylesheet">
<link href="css/user_update.css" rel="stylesheet">
<link href="css/menuBar3.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<!-- <script>
function idCheck(){
	var JoinForm = document.JoinForm;
	window.open("", 'wfr', "width=500, height=300");

	JoinForm.action = "mypage/user_update_passCheck.jsp";
	JoinForm.target = "wfr";
	
	JoinForm.submit();
	
}
	</script> -->
	<%
	MemberBean mb=(MemberBean)request.getAttribute("memberBean");
	%>
</head>
<body>
	<div id="container">
		<jsp:include page="../include/menuBar.jsp" />

		<section id="myPage">
		
			<div class="myInfo">
			<div class="myInfo_name">안녕하세요.<br> 저는 ${memberBean.user_name}입니다.</div>
			<div class="myInfo_email">${memberBean.user_email }</div>
			<div class="myInfo_birth">${memberBean.user_birth }</div>
			</div>
		
			<ul class="myPage_list">
			<a href="./IdCheck.mp"><li><div>프로필 수정</div><img class="list_imgs" src="img/edit_profile.png"/></li></a>
			<a href="./PickList.mp"><li><div>Pick리스트</div><img class="list_imgs" src="img/mypage_heart.png"/></li></a>
			<a href="./boardUpdateListForm.mp"><li><div>게시글 수정</div><img class="list_imgs" src="img/edit_board_mypage.png"/></li></a>
			<a href="#"><li><div>회원전환</div><img class="list_imgs" src="img/change_to_business.png"/></li></a>
			<a href="./UserDeleteForm.mp"><li><div>회원탈퇴</div><img class="list_imgs" src="img/remove-user4.png"/></li></a>
			<a href="./Logout.mp"><li><div>로그아웃</div><img class="list_imgs" src="img/logout_mypage.png"/></li></a>
			</ul>
			
			<section id="JoinForm">
		
			<div class="Join_title">프로필 수정</div>
			
			<form action = "UserUpdatePro.mp" method="post" id = "JoinForm" name="JoinForm" >
			
				<div class="Join">
					
				
					<input class="a dupli_check disabled" type="text" maxlength="16" placeholder="아이디" value="${memberBean.user_id}" name="user_id" required="required" disabled="disabled"> 
					
					<input class="a nick_check" type="text" maxlength="16" placeholder="닉네임" value="${memberBean.user_nickname}" name="user_nickname" required="required" disabled="disabled">
					
					<input class="a" type="text" maxlength="16" placeholder="이름" value="${memberBean.user_name}" name="user_name" required="required">
					
					<input class="a" type="date" maxlength="16" placeholder="oooo-oo-oo(생년월일)" value="${memberBean.user_birth}" name="user_birth" required="required">	
						
					<input class="a" type="text" maxlength="16" placeholder="전화번호" value="${memberBean.user_phone}" name="user_phone" required="required">
					
					<input class="a" type="email" placeholder="이메일 입력" value="${memberBean.user_email}" name="user_email" required="required">
					
					<input class="submit_Join"  type="submit" value="회원 정보 수정" >
					
				</div>

			</form>
		</section>	
			
		</section>

		<jsp:include page="../include/footer.jsp" />
	</div>


</body>
</html>