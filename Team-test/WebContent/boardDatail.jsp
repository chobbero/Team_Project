<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="css/menuBar2.css" rel="stylesheet">
<link href="css/boardDetail.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div id="container">
		
			<jsp:include page="include/menuBar.jsp" />
			
			<section id="board_frame">
				<!-- 리뷰의 이미지만 표시(최대 4개 표시) -->
				<aside id="contentImageArea1">
					<img src="img/pasta.png">
					<img src="img/hot-dog.png">
					<img src="img/meat.png">
					<img src="img/noodles.png">
					<!-- 이미지 슬라이드 스크립트 구현 -->
					<a class="imageSildeBtn1" onclick="plusDivs(1)">❯</a>
					<a class="imageSildeBtn2" onclick="plusDivs(-1)">❮</a>
						
				</aside>
				
				<!-- 화면크기에 따라 표시 width=767미만 -->
				<aside id="contentImageArea2">
					<!-- 추가한 이미지들의 클래스는 동일하게 -->
					<img src="img/pasta.png" class="imgSlides">
					<img src="img/hot-dog.png" class="imgSlides">
					<img src="img/meat.png" class="imgSlides">
					<img src="img/noodles.png" class="imgSlides">
					<a class="imageSildeBtn1" onclick="plusDivs(1)">❯</a>
					<a class="imageSildeBtn2" onclick="plusDivs(-1)">❮</a>
					<script>
						// 수동 이미지 슬라이드
						var slideIndex = 1;
						showDivs(slideIndex);
					
						function plusDivs(n) {
						  showDivs(slideIndex += n);
						}
			
						function showDivs(n) {
							var i;
					 	 	var x = document.getElementsByClassName("imgSlides");
						 	if (n > x.length) {slideIndex = 1}    
					 		if (n < 1) {slideIndex = x.length} ;
							for (i = 0; i < x.length; i++) {
						    	x[i].style.display = "none";  
							}
							x[slideIndex-1].style.display = "block";  
						}
						
						// 자동 이미지 슬라이드
						var myIndex = 0;
						carousel();
			
						function carousel() {
			   				var i;
			   				var x = document.getElementsByClassName("imgSlides");
			   				for (i = 0; i < x.length; i++) {
			      			x[i].style.display = "none";  
			    		}
			   			myIndex++;
			   			if (myIndex > x.length) {myIndex = 1}    
			   				x[myIndex-1].style.display = "block";  
			   				setTimeout(carousel, 5000);  // 5초마다 변경
						}
					</script>
				</aside>
				
				<!-- 주내용 -->
				<section id="contentArea">
					<article id="boardDeatilContentArea">
						<div class="reviewWrite_favorite">
							<div><a href="#"><img src="img/review.png"><br>리뷰쓰기</a></div>
							<div><a href="#"><img src="img/recommendation.png"><br>즐겨찾기</a></div>
						</div>
						<p class="contentTitle">아웃백 스테이크 하우스 (부산역점)</p>
						<p class="contentSubTitle">Outback Stake House (Pusan Station)</p>
						<p class="contentETC">기타추가(좋아요 조회수 등등..)</p>
						<hr>
						<table>
							<tr><td class="menu">주소</td><td class="menuDetail">부산광역시~~~~~~부산광역시~~~~~~부산광역시~~~~~~부산광역시~~~~~~부산광역시~~~~~~</td></tr>
							<tr><td class="menu">전화번호</td><td class="menuDetail">000-0000-0000</td></tr>
							<tr><td class="menu">음식종류</td><td class="menuDetail">양식</td></tr>
							<tr><td class="menu">주차가능</td><td class="menuDetail">주차장</td></tr>
							<tr><td class="menu">영업시간</td><td class="menuDetail">09:00 ~ 22:00</td></tr>
							<tr><td class="menu">쉬는시간</td><td class="menuDetail">14:00 ~ 16:00</td></tr>
							<tr><td class="menu">휴일</td><td class="menuDetail">연중휴무</td></tr>
							<!-- 메뉴 수만큼 반복 -->
							<tr>						
							<td class="menu">메뉴</td>
							<td class="menuDetail">허브 스테이크 1인분 (국내산)   50000원</td>
							</tr>
							<tr>
							<td class="menu"></td>
							<td class="menuDetail">치즈 샐러드 1인분 (국내산)   5000원</td>
							</tr>
							<tr>
							<td class="menu"></td>
							<td class="menuDetail">스테이크 1인분 (국내산)   100000원</td>
							</tr>
						</table>
					</article>
					
					<!-- 기타정보 표시 (지도, 주변정보, 태그 등등..) -->
					<aside id="etcArea">
						기타정보<br>
						해당위치<br>
						<img src="img/noodles.png">
						<p>태그</p>
						#스테이크, #양식, ....
					</aside>
				</section>
				
				<!-- 리뷰표시 -->
				<section id="reviewArea">
					<p>리뷰</p>
					<table>
						<!-- 리뷰갯수 마다 반복 -->
						<tr>
							<td class="reviewWriter">홍길동</td>
							<td class="reviewContent">good~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~</td>
						</tr>
						<tr>
							<td class="reviewWriter">지나가던</td>
							<td class="reviewContent">맛있숨1</td>
						</tr>
						<tr>
							<td class="reviewWriter">글쓴이2</td>
							<td class="reviewContent">존맛</td>
						</tr>
						<tr>
							<td class="reviewWriter">글쓴이3</td>
							<td class="reviewContent">비쌈</td>
						</tr>
					</table>
				</section>
			</section>
			
			<jsp:include page="include/footer.jsp" />
	</div>
</body>
</html>