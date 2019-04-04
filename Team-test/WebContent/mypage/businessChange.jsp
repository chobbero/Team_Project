<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="css/businessChange2.css" rel="stylesheet">
<link href="css/myPage4.css" rel="stylesheet">
<link href="css/menuBar3.css" rel="stylesheet">
<link href="css/FileUpload2.css" rel="stylesheet">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="js/jquery-3.3.1.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.test1').on('change', function() { // 값이 변경되면 
			var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출 
			$('.t1').val(filename);

		});
	});
</script>
</head>
<body>
	<div id="container">
		<jsp:include page="../include/menuBar.jsp" />

		<!--모바일 -->

		<section id="myPage">
			<div class="wrap1">
				<form action="./BusinessChangePro.mp" method="post"
					name="businessJoinForm" enctype="multipart/form-data">
					<div class="bcange_title">사업자 정보 입력</div>

					<div class="bchange">
						<div class="change">
							<input class="a" type="text" maxlength="20" placeholder="사업자등록번호"
								name="business_number"> <input class="a" type="text"
								maxlength="20" placeholder="상호" name="business_name"> <input
								class="a" type="hidden" name="user_grade" value="B"> <input
								class="a" type="hidden" name="user_id"
								value="${sessionScope.user_id }">
						</div>

						<div class="bcange_title">음식점 정보 입력</div>
						<div class="change">
							<input class="a" type="text" maxlength="20" placeholder="소재지"
								name="store_address"> <input class="a" type="text"
								maxlength="20" placeholder="분류" name="store_category"> <input
								class="a" type="text" maxlength="20" placeholder="대표 메뉴"
								name="store_menu"> <input class="a" type="text"
								maxlength="20" placeholder="가격" name="store_price"> <input
								class="a" type="text" maxlength="20" placeholder="영업 시간"
								name="store_time"> <input class="a" type="text"
								maxlength="20" placeholder="전화번호" name="store_contact">
							<div class="filebox">
								<input class="upload-name t1" value="대표사진" disabled="disabled">
								<label for="ex_filename1">업로드</label> <input type="file"
									id="ex_filename1" class="upload-hidden test1" name="file">
							</div>

						</div>

						<div class="com">
							<input class="submit_com" type="submit" value="제출">
						</div>
					</div>
				</form>
			</div>

			<ul class="myPage_list">
				<a href="./IdCheck.mp"><li><div>프로필 수정</div> <img
						class="list_imgs" src="img/edit_profile.png" /></li></a>
				<a href="./PickList.mp"><li><div>Pick리스트</div> <img
						class="list_imgs" src="img/mypage_heart.png" /></li></a>
				<a href="./boardUpdateListForm.mp"><li><div>게시글 수정</div> <img
						class="list_imgs" src="img/edit_board_mypage.png" /></li></a>
				<a href="./BusinessChange.mp"><li><div>회원전환</div> <img
						class="list_imgs" src="img/change_to_business.png" /></li></a>
				<a href="./UserDeleteForm.mp"><li><div>회원탈퇴</div> <img
						class="list_imgs" src="img/remove-user4.png" /></li></a>
				<a href="./Logout.mp"><li><div>로그아웃</div> <img
						class="list_imgs" src="img/logout_mypage.png" /></li></a>
			</ul>

		</section>

		<jsp:include page="../include/footer.jsp" />
	</div>


</body>
</html>