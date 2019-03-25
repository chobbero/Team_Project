<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="css/Join2.css" rel="stylesheet">
<link href="css/menuBar3.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
// 		아이디 중복 확인
		$('.dupli_check').blur(function() {
			
			var id = $(this).val();
			var check = null;
				
			if(id == "") {
				swal({title: "아이디를 입력하세요.",
					  button: "확인",
					  icon: "warning"
					});
			}	
			
			$.ajax({
				url: "./id_duplicate_check.jsp",
				type: "POST",
				dataType: "json",
				data: {'id': id },
				success : function(data) {
					check = data.user_id;
					if(check != null){
						swal({title: "이미 사용중인 아이디입니다.",
							  button: "확인",
							  icon: "warning"
							});
						$('.dupli_check').val("");
					}
				}
			});
		});
		
// 		비밀번호 확인
	$('.pw_check2').blur(function() {
			
			var pw1 = $('.pw_check1').val();
			var pw2 = $('.pw_check2').val();
				
			if(pw1 != pw2) {
				swal({title: "비밀번호가 일치하지 않습니다.",
					  button: "확인",
					  icon: "warning"
					});
// 				$('.pw_check1').val("");
				$('.pw_check2').val("");
			} 
		});
		
//		닉네임 중복 확인
	$('.nick_check').blur(function() {
		
		var nickname = $(this).val();
		var check = null;
			
		if(nickname == "") {
			swal({title: "닉네임을 입력하세요.",
				  button: "확인",
				  icon: "warning"
				});
		}	
		
		$.ajax({
			url: "./nickname_duplicate_check.jsp",
			type: "POST",
			dataType: "json",
			data: {'nickname': nickname },
			success : function(data) {
				check = data.nickname;
				if(check != null){
					swal({title: "이미 사용중인 닉네임입니다.",
						  button: "확인",
						  icon: "warning"
						});
					$('.nick_check').val("");
				}
			}
		});
	});
		
		
	});
</script>
</head>
<body>
	<div id="container">
		<jsp:include page="../include/menuBar.jsp" />
		<section id="JoinForm">
			<div class="Join_title">Pick Pick 회원가입</div>
			<form action="./UserJoinPro.mb" method="post" name="JoinForm">
				<div class="Join">
					<input class="a dupli_check" type="text" maxlength="16" placeholder="아이디" name="user_id" required="required"> 
					<input class="a pw_check1" type="password" maxlength="16" placeholder="비밀번호" name="user_pw" required="required">
					<input class="a pw_check2" type="password" maxlength="16" placeholder="비밀번호 재확인" name="repass" required="required">
					<input class="a" type="hidden" name="user_grade" value="C">
					<input class="a nick_check" type="text" maxlength="16" placeholder="닉네임" name="user_nickname" required="required">
					<input class="a" type="text" maxlength="16" placeholder="이름" name="user_name" required="required">
					<input class="a" type="date" maxlength="16" placeholder="oooo-oo-oo(생년월일)" name="user_birth" required="required">		
					<input class="a" type="text" maxlength="16" placeholder="전화번호" name="user_phone" required="required">
					<input class="phone" type="email" placeholder="이메일 입력" name="user_email" required="required">
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