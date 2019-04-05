<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="css/boardDetail.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	// 더보기 아이콘 기능
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
	
	
	
	// 이미지 애니매이션 레이어팝업 쿼리
	function dialog() {

	    var dialogBox = $('.dialog'),
	        dialogClose = $('.dialog_close'),
	        dialogTitle = $('.dialog_title'),
	        dialogContent = $('.dialog_content'),
	        dialogImg = $('.dialog_img');

	    // 레이어 열기
	    $('.dialog_trigger0').on('click', function(e) {
	    	var imgSrc = $('.dialog_trigger0').attr("src");
	    	$(dialogImg).attr("src", imgSrc);
	    	dialogBox.toggleClass('dialog-active');
	        e.stopPropagation()
	    });
	    
	    $('.dialog_trigger1').on('click', function(e) {
	    	var imgSrc = $('.dialog_trigger1').attr("src");
	    	$(dialogImg).attr("src", imgSrc);
	    	dialogBox.toggleClass('dialog-active');
	        e.stopPropagation()
	    });
	    
	    $('.dialog_trigger2').on('click', function(e) {
	    	var imgSrc = $('.dialog_trigger2').attr("src");
	    	$(dialogImg).attr("src", imgSrc);
	    	dialogBox.toggleClass('dialog-active');
	        e.stopPropagation()
	    });
	    
	    $('.dialog_trigger3').on('click', function(e) {
	    	var imgSrc = $('.dialog_trigger3').attr("src");
	    	$(dialogImg).attr("src", imgSrc);
	    	dialogBox.toggleClass('dialog-active');
	        e.stopPropagation()
	    });

	    // 닫기 버튼 클릭시 레이어닫기
	    dialogClose.on('click', function() {
	        dialogBox.removeClass('dialog-active');
	    });

	    // esc누를시 레이어 닫기
	    $(document).keyup(function(e) {
	        if (e.keyCode === 27) {
	            dialogBox.removeClass('dialog-active');
	        }
	    });

	    // 대화상자 바깥쪽 클릭시 레이어 닫기
	    $(document).on('click', function(e) {
	        if ($(e.target).is(dialogBox) === false &&
	            $(e.target).is(dialogTitle) === false &&
	            $(e.target).is(dialogContent) === false &&
	            $(e.target).is(dialogImg) === false) {
	            dialogBox.removeClass("dialog-active");
	        }
	    });
	    

	};

	// Run function when the document has loaded
	$(function() {
	    dialog();
	});
</script>
<!-- 찜하기 제이쿼리 -->
<script type="text/javascript">
	$(document).ready(function() {
		$('.pick').click(function() {
			
			var img = $(this).attr("src");
			
			var id = $(this).parent().next().attr("name");
			var board_num = $(this).parent().next().attr("value");
			
			console.log("아이디 : " + id + " 글번호 : " + board_num);
			
			if (img == "img/heart.png") {
				$(this).attr("src", "img/filled_heart.png");
								$.ajax({
									url: "./pick_like.jsp",
									type: "POST",
									dataType: "json",
									data: {
										'id': id , 'board_num':board_num
									}
								});
			} else if (img == "img/filled_heart.png") {
				$(this).attr("src", "img/heart.png");
								$.ajax({
									url: "./pick_unlike.jsp",
									type: "POST",
									dataType: "json",
									data: {
										'id': id , 'board_num':board_num
									}
								});
			}
		});
	});
</script>
</head>
<body>
	<div id="container">
		
		<jsp:include page="../include/menuBar.jsp" />
		
		<section id="board_frame">
		
			<!-- 이미지 표시 -->
			<aside id="contentImageArea1">
				<!-- 이미지 있을 시에만 실행 -->
				<c:if test="${ic >0 }"> 
					<!-- 이미지 반복문 -->
					<c:forEach var="i" begin="0" end="${ic - 1}" step="1">
						<img class="dialog_trigger${i }" src="./files/${img[i]}">
					</c:forEach>
						
					<!-- 이미지 팝업레이어 (4개) -->
					<div class="dialog">
						<span class="dialog_close">&#x2715;</span>
						<h2 class="dialog_title">${bb.board_subject }</h2>
						<img class="dialog_img" src="">
					</div>	
				</c:if>				
			</aside>
				
			<!-- 화면크기에 따라 표시 width=767미만일때 표시 -->
			<aside id="contentImageArea2">
				
				<c:forEach var="imgName" items="${img }">
					<img src="./files/${imgName}" class="imgSlides">							
				</c:forEach>
				
				<a class="imageSildeBtn1" onclick="plusDivs(1)">❯</a>
				<a class="imageSildeBtn2" onclick="plusDivs(-1)">❮</a>
				<!-- 이미지 슬라이드 스크립트 -->
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
			
			<section id="contentArea">
				<!-- 주내용 -->
				<article id="boardDeatilContentArea">
				
					<table>
						<tr>
							<td colspan="4" id="contentTitle">${bb.board_subject }</td>
							<td>
							<c:if test="${sessionScope.user_id !=null }">
							<label for="pick">
						 	<img class="pick" src="img/heart.png">
							</label>
							<input id="pick" type="checkbox" name="${sessionScope.user_id }" value="${bb.board_num }">
							</c:if>
							</td>
						</tr>
						<tr id="contentSub">
							<td id="contentWriter">${bb.user_id }</td>
							<td id="contentTime">${bb.board_date }</td>
							<td id="contentViewCount">조회수(${bb.board_readcount })</td>
							<td id="contentStar">공감(${bb.board_like })</td>
							<td id="contentReviewCount">댓글(${cc })</td>
						</tr>
						<tr><td colspan="5" id="content">${bb.board_content }</td></tr>
					</table>
					
				</article>
					
				<!-- 매장 정보 표시 -->
				<aside id="etcArea">
					<p class="storeInfo">매장 정보</p>
					<p class="storeTitle">${sb.store_name }</p>
					<!-- <p class="storeSubTitle">Outback Stake House (Pusan Station)</p> -->
					<p class="storeETC"><img src="img/happy.png">후기 갯수 (${bc })</p>
					<hr>
					<table>
						<tr><td class="storeMenu">주소</td><td class="storeMenuDetail">${sb.store_address }</td></tr>
						<tr><td class="storeMenu">전화번호</td><td class="storeMenuDetail">${sb.store_contact }</td></tr>
						<tr><td class="storeMenu">음식종류</td><td class="storeMenuDetail">${sb.store_category }</td></tr>
						<tr><td class="storeMenu">영업시간</td><td class="storeMenuDetail">${sb.store_time }</td></tr>
						<tr><td class="storeMenu">대표메뉴</td><td class="storeMenuDetail">${sb.store_menu } ${sb.store_price }원</td></tr>
					</table>
				</aside>
				<!-- 버튼 -->
				<div id="buttonArea">
				
					<a href='<c:url value="./BoardList.bo"/>'><img class="btnBoardList" src="img/threebars.png"></a>
					<c:if test="${sessionScope.user_id !=null }">
					<a href='<c:url value="./BoardWriteForm.bo" />'><img class="btnBoardInsert" src="img/boardWrite_l_gray.png"></a>
					</c:if>
					<c:if test="${sessionScope.user_id !=null }">
					<a href='<c:url value="./BoardDelete.bo?board_num=${bb.board_num }" />'><img class="btnBoardDelete" src="img/trashcan.png"></a>
					</c:if>
					
				</div>
			</section>
				
			<!-- 댓글 표시 -->
			<section id="reviewArea">
				<hr>
				<div id="js-load" class="main">
					<p><c:choose>
						<c:when test="${cc eq 0 }">댓글이 없어요 ㅠㅠ</c:when>
						<c:otherwise>댓글 (${cc })</c:otherwise>
					</c:choose></p>
					<a href='<c:url value="./CommentWriteForm.co?board_num=${bb.board_num }" />'>
						<ul>
							<c:if test="${sessionScope.user_id !=null }">
							<li><img class="btnCommentWrite" src="img/comments.png" title="댓글"></li>
						    <li>댓글쓰기</li>
						    </c:if>
						</ul>
					</a>
					
					<ul class="lists">
						<c:forEach var="cb" items="${cl }">
							<li class="lists__item js-load">
								<table class="review_table">
								<tr>
									<td class="review_id">${cb.user_id}</td>
									<td class="review_content">
									${cb.comment_content } 
									</td>
									<td class="review_like">
									<c:choose>
									<c:when test="${cb.comment_like =='Y' }">
									<img alt="like" src='<c:url value="./img/like_comment.png" />' >
									</c:when>
									<c:when test="${cb.comment_like =='N' }">
									<img alt="like" src='<c:url value="./img/dislike_comment.png" />'>
									</c:when>
									</c:choose>
									</td>
									<td class="review_date">
									${cb.comment_date }
									</td>
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