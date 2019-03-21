<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="css/boardDetail2.css" rel="stylesheet">
<link href="css/menuBar3.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
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
				<c:forEach var="imgName" items="${img }">
					<img src="./files/${imgName}">							
				</c:forEach> 	
			
				<!-- 이미지 슬라이드 스크립트 구현 (보류)
				<a class="imageSildeBtn1" onclick="plusDivs(1)">❯</a>
				<a class="imageSildeBtn2" onclick="plusDivs(-1)">❮</a> -->
			</aside>
				
			<!-- 화면크기에 따라 표시 width=767미만 -->
			<aside id="contentImageArea2">
				
				<c:forEach var="imgName" items="${img }">
					<img src="./files/${imgName}" class="imgSlides">							
				</c:forEach>
				
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
					<table>
						<tr><td colspan="5" id="contentTitle">${bb.board_subject }</td></tr>
						<tr id="contentSub">
							<td id="contentWriter">${bb.user_id }</td>
							<td id="contentTime">${bb.board_date }</td>
							<td id="contentViewCount">조회수(${bb.board_readcount })</td>
							<td id="contentStar">공감(${bb.board_like })</td>
							<td id="contentReviewCount">댓글(${cc })</td>
						</tr>
						<tr><td colspan="5" id="content">${bb.board_content }</td></tr>
					</table>
					<div class="reviewWrite_favorite">
						<!-- <div><a href="#"><img src="img/review.png"><br>리뷰쓰기</a></div> -->
						<!-- <div><a href="#"><img src="img/recommendation.png"><br>즐겨찾기</a></div> -->
					</div>
				</article>
					
				<!-- 매장 정보 표시 -->
				<aside id="etcArea">
					<p class="storeInfo">매장 정보</p>
					<p class="storeTitle">${sb.store_name }</p>
					<!-- <p class="storeSubTitle">Outback Stake House (Pusan Station)</p> -->
					<p class="storeETC">후기 갯수 (${bc })</p>
					<hr>
					<table>
						<tr><td class="storeMenu">주소</td><td class="storeMenuDetail">${sb.store_address }</td></tr>
						<tr><td class="storeMenu">전화번호</td><td class="storeMenuDetail">${sb.store_contact }</td></tr>
						<tr><td class="storeMenu">음식종류</td><td class="storeMenuDetail">${sb.store_category }</td></tr>
						<tr><td class="storeMenu">영업시간</td><td class="storeMenuDetail">${sb.store_time }</td></tr>
					<!--	<tr><td class="storeMenu">주차가능</td><td class="storeMenuDetail">주차장</td></tr>
					 	<tr><td class="storeMenu">쉬는시간</td><td class="storeMenuDetail">14:00 ~ 16:00</td></tr>
						<tr><td class="storeMenu">휴일</td><td class="storeMenuDetail">연중휴무</td></tr> -->
						<tr><td class="storeMenu">대표메뉴</td><td class="storeMenuDetail">${sb.store_menu } ${sb.store_price }원</td></tr>
					</table>
				</aside>
			</section>
				
			<!-- 리뷰표시 -->
			<section id="reviewArea">
				<hr>
				<div id="js-load" class="main">
					<p>리뷰 (${cc })</p>
					<ul class="lists">
						<c:forEach var="cb" items="${cl }">
							<li class="lists__item js-load">
								<table class="review_table"><tr>
									<td class="review_id">${cb.user_id}</td>
									<td class="review_content">${cb.comment_content }</td>
								</tr></table>
							</li>
						</c:forEach>
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