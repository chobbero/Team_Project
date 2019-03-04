<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="css/login.css" rel="stylesheet">
<link href="css/menuBar2.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div id="container">
		<jsp:include page="include/menuBar.jsp" />

		<section id="loginForm">
			<div class="login_title">Pick Pick 로그인</div>
			<form action="loginPro.jsp" method="post" name="loginForm">
				<div class="login">
					<div class="input_info">
						<input class="id" type="text" maxlength="16" placeholder="   ID"
							name="id"> 
							<input class="pass" type="password"
							maxlength="16" placeholder="   PASSWORD" name="pass"> 
							<input class="submit_login" type="submit" value="로그인">
					</div>
					<div class="join_find">
					<a class="to_join" href="#">회원가입</a>
					<a class="to_find" href="#">계정찾기</a>
					</div>
				</div>

			</form>
		</section>

		<jsp:include page="include/footer.jsp" />
	</div>


</body>
</html>