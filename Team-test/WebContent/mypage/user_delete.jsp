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
</head>
<body>
	<div id="container">
		<jsp:include page="../include/menuBar.jsp" />

		<section id="myPage">
		
			<section id="JoinForm">
		
			<div class="Join_title">회원 탈퇴</div>
			
			<form action="UserDeletePro.mp" method="post" name="JoinForm">
			
				<div class="Join">
				
					<input class="a dupli_check disabled" type="text" maxlength="16" value="<%=session.getAttribute("user_id") %>" name="user_id" required="required" disabled="disabled"> 
					
					<input class="a pw_check1" type="password" maxlength="16" placeholder="비밀번호" name="user_pw" required="required">
					
					<input class="submit_Join" type="submit" value="회원 탈퇴">
					
				</div>

			</form>
		</section>	
		
			<ul class="myPage_list">
			<a href="./IdCheck.mp"><li><div>프로필 수정</div><img class="list_imgs" src="img/edit_profile.png"/></li></a>
			<a href="./PickList.mp"><li><div>Pick리스트</div><img class="list_imgs" src="img/mypage_heart.png"/></li></a>
			<a href="./boardUpdateListForm.mp"><li><div>게시글 수정</div><img class="list_imgs" src="img/edit_board_mypage.png"/></li></a>
			<a href="#"><li><div>회원전환</div><img class="list_imgs" src="img/change_to_business.png"/></li></a>
			<a href="./UserDeleteForm.mp"><li><div>회원탈퇴</div><img class="list_imgs" src="img/remove-user4.png"/></li></a>
			<a href="./Logout.mp"><li><div>로그아웃</div><img class="list_imgs" src="img/logout_mypage.png"/></li></a>
			</ul>
			
<!-- 			<section id="JoinForm"> -->
		
<!-- 			<div class="Join_title">회원 탈퇴</div> -->
			
<!-- 			<form action="UserDeletePro.mp" method="post" name="JoinForm"> -->
			
<!-- 				<div class="Join"> -->
				
<%-- 					<input class="a dupli_check disabled" type="text" maxlength="16" value="<%=session.getAttribute("user_id") %>" name="user_id" required="required" disabled="disabled">  --%>
					
<!-- 					<input class="a pw_check1" type="password" maxlength="16" placeholder="비밀번호" name="user_pw" required="required"> -->
					
<!-- 					<input class="submit_Join" type="submit" value="회원 탈퇴"> -->
					
<!-- 				</div> -->

<!-- 			</form> -->
<!-- 		</section>	 -->
			
		</section>

		<jsp:include page="../include/footer.jsp" />
	</div>


</body>
</html>