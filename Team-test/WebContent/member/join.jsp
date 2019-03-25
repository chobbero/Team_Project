<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="../css/Join2.css" rel="stylesheet">
<link href="../css/menuBar3.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div id="container">
		<jsp:include page="../include/menuBar.jsp" />
		<section id="JoinForm">
			<div class="Join_title">Pick Pick 회원가입</div>
			<form action="./UserJoinProAction.mb" method="post" name="JoinForm">
				<div class="Join">
					<input class="a" type="text" maxlength="16" placeholder="아이디" name="user_id"> 
					<span class="check_box" id="idcheck1" style="display:none" role="alert">이미 사용중인 아이디 입니다.</span>
					<span class="check_box" id="idcheck2" style="display:none" role="alert">사용가능한 아이디 입니다.</span>
					<input class="a" type="text" maxlength="16" placeholder="비밀번호" name="user_pw">
					<input class="a" type="text" maxlength="16" placeholder="비밀번호 재확인" name="repass">
					<input class="a" type="hidden" name="user_grade" value="C">
					<input class="a" type="text" maxlength="16" placeholder="닉네임" name="user_nickname">
					<input class="a" type="text" maxlength="16" placeholder="이름" name="user_name">
					<span class="check_box" id="passck11" style="display:none" role="alert">5~12자의 영문 소문자, 숫자만 사용 가능합니다.</span>
					<input class="a" type="date" maxlength="16" placeholder="oooo-oo-oo(생년월일)" name="user_birth">		
					<input class="a" type="text" maxlength="16" placeholder="전화번호" name="user_phone">
					<input class="phone" type="email" maxlength="16" placeholder="이메일 입력" name="user_email">
					<button class="num">인증번호받기</button>
					<input class="a" type="text" maxlength="16" placeholder="인증번호 입력" name="num">
					<input class="submit_Join" type="submit" value="회원가입">
				</div>

			</form>
		</section>
		</div>
		<jsp:include page="../include/footer.jsp" />
</body>
</html>