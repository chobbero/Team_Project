<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="css/menuBar.css" rel="stylesheet">
<link href="css/board.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div id="container">
	
		<jsp:include page="include/menuBar.jsp" />
		
		<article id="board_frame">
			<div id="board_view">
				<section id="board_title">
					<div>
						<h1>어디엔가 있는 존나 맛있는 맛집 추천!???</h1>
					</div>
				</section>
				<!-- 글 갯수 만큼 반복문 사용으로 출력 -->
				<section id="board_content">
					<div class="content_img">
						<img src="img/review.png" class="content_img_size">
					</div>
					<div class="content_content">
						<span class="restaurant_name">상호 : <span class="name_emphasis">존나 맛있는 맛집 상호명</span></span><br>
						<span class="restaurant_address">주소 : 부산광역시 어디에 있는 맛있는 집 주소를 입력!??????</span><br>
						<span class="restaurant_content">메뉴 & 잡다한 내용 : <br> 어쩌구 저쩌구 존나 맛잇구요 맛있어요? or 뭐 다른 내용 (ex. 베댓이라든가?)
						테스트용글테스트용글테스트용글테스트용글테스트용글테스트용글테스트용글테스트용글테스트용글테스트용글테스트용글테스트용글테스트용글테스트용글테스트용글테스트용글테스트용글테스트용글테스트용글테스트용글테스트용글테스트용글테스트용글테스트용글 
						</span> <!-- 표시 글자수 제한? -->
					</div>
				</section>
			</div>
		</article>

		<jsp:include page="include/footer.jsp"/>
	</div>
</body>
</html>