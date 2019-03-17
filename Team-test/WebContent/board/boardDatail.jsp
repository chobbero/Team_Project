<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="../css/boardDetail2.css" rel="stylesheet">
<link href="../css/menuBar3.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	$(window).on('load', function () {
	    load('#js-load', '5');
	    $("#js-btn-wrap .button").on("click", function () {
	        load('#js-load', '5', '#js-btn-wrap');
	    })
	});
	 
	function load(id, cnt, btn) {
	    var review_list = id + " .js-load:not(.active)";
	    var review_length = $(review_list).length;
	    var review_total_cnt;
	    if (cnt < review_length) {
	    	review_total_cnt = cnt;
	    } else {
	    	review_total_cnt = review_length;
	        $('.button').hide()
	    }
	    $(review_list + ":lt(" + review_total_cnt + ")").addClass("active");
	}
</script>
</head>
<body>
	<div id="container">
		
		<jsp:include page="../include/menuBar.jsp" />
		
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
				<hr>
				<div id="js-load" class="main">
					<p>리뷰 (n)</p>
					<ul class="lists">
						<li class="lists__item js-load"><table class="review_table"><tr><td class="review_id">이름!!!!!!!!</td><td class="review_content">내용!!!!!</td></tr></table></li>
						<li class="lists__item js-load"><table class="review_table"><tr><td class="review_id">이름!!!!!!1111111111111111111!!</td><td class="review_content">내용!!!!!</td></tr></table></li>
						<li class="lists__item js-load"><table class="review_table"><tr><td class="review_id">이름!!!!!!!!</td><td class="review_content">
							<p>111111111111
							테스틍ㅇ
							11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
							11111111111111111111111111111111111133
							111111111111111122222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222
							</p>
						</td></tr></table></li>
						<li class="lists__item js-load"><table class="review_table"><tr><td class="review_id">이름1!!!!!!!!</td><td class="review_content">내용!!!!!</td></tr></table></li>
						<li class="lists__item js-load"><table class="review_table"><tr><td class="review_id">이름2!!!!!!!!</td><td class="review_content">내용!!!!!</td></tr></table></li>
						<li class="lists__item js-load"><table class="review_table"><tr><td class="review_id">이름3!!!!!!!!</td><td class="review_content">내용!!!!!</td></tr></table></li>
						<li class="lists__item js-load"><table class="review_table"><tr><td class="review_id">이름4!!!!!!!!</td><td class="review_content">내용!!!!!</td></tr></table></li>
						<li class="lists__item js-load"><table class="review_table"><tr><td class="review_id">이름5!!!!!!!!</td><td class="review_content">내용!!!!!</td></tr></table></li>
						<li class="lists__item js-load"><table class="review_table"><tr><td class="review_id">이름6!!!!!!!!</td><td class="review_content">내용!!!!!</td></tr></table></li>
						<li class="lists__item js-load"><table class="review_table"><tr><td class="review_id">이름7!!!!!!!!</td><td class="review_content">내용/*!!!!!</td></tr></table></li>
						<li class="lists__item js-load"><table class="review_table"><tr><td class="review_id">이름8!!!!!!!!</td><td class="review_content">내용789!!!!!</td></tr></table></li>
						<li class="lists__item js-load"><table class="review_table"><tr><td class="review_id">이름9!!!!!!!!</td><td class="review_content">내용4567!!!!!</td></tr></table></li>
						<li class="lists__item js-load"><table class="review_table"><tr><td class="review_id">이름10!!!!!!!!</td><td class="review_content">내용123!!!!!</td></tr></table></li>
						<li class="lists__item js-load"><table class="review_table"><tr><td class="review_id">이름11!!!!!!!!</td><td class="review_content">내용112!!!!!</td></tr></table></li>
						<li class="lists__item js-load"><table class="review_table"><tr><td class="review_id">이름12!!!!!!!!</td><td class="review_content">내용111!!!!!</td></tr></table></li>	
						<li class="lists__item js-load"><table class="review_table"><tr><td class="review_id">이름13!!!!!!!!</td><td class="review_content">내용111!!!!!</td></tr></table></li>
					</ul>
					<div id="js-btn-wrap" class="btn-wrap">
						<a href="javascript:;" class="button"><img src="img/down3.png"></a>
					</div>
				</div>
			</section>
		</section>
		<jsp:include page="../include/footer.jsp" />
	</div>
</body>
</html>