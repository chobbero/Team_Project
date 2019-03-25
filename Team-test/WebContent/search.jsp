<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="css/search.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.down_scroll').click(function() {
			var h = $('.container_rank').height();
			if (h > 250) {
				$('.container_rank').css('height', '200px');
				$('.rankOthers').css('display', 'none');
			} else {
				$('.container_rank').css('height', '1140px');
				$('.rankOthers').toggle('slow');
			}
		});
	});
</script>
</head>
<body>
	<div id="container">
		<jsp:include page="include/menuBar.jsp" />
		<section id="search">
			<div class="search_bar">
				<form action='<c:url value="./Search.bo"/>' method="post" name="search">

					<div class="search_bar_text">추천 음식점을 Pick 하세요.</div>
					<div class="search_input_wrap">
						<img class="search_input_logo" alt="" src="img/bar_logo.png">
						<select name="category" class="search_category">
							<optgroup label="검색">
								<option value="subject" <c:if test="${category.equals('subject') }">selected</c:if>>글제목</option>
	    						<option value="content" <c:if test="${category.equals('content') }">selected</c:if>>글내용</option>
								<option value="store" <c:if test="${category.equals('store') }">selected</c:if>>매장명</option>
							</optgroup>
						</select>
						<input class="search_input" type="text" name="search_input">
					</div>
					<button type="submit" class="search_btn_icon">
						<img class="btn_for_search" alt="search" src="img/Search.png">
					</button>
					<button type="submit" class="search_btn_text">검색</button>
				</form>
			</div>
		</section>
		
		<section id="searchResultView">
			<div id="searchTitle">
				<c:choose>
					<c:when test="${searchCount eq 0 }">검색 결과가 없어요 ㅠㅠ</c:when>
					<c:otherwise><p>검색어 : "${search }" (${searchCount })</p></c:otherwise>
				</c:choose>
			</div>
			<article id="searchResult">
					<!-- 검색어 카테고리 (상호, 게시글) -->
				<c:forEach var="sl" items="${searchList }"> <!-- 반복문 -->
					<!-- 이미지 파일 -->
					<c:forEach var="img" items="${imgFileList }">
						<c:if test="${sl.board_num == img.board_num }"><img src="files/${img.image }" style="width:100px;"></c:if>
					</c:forEach><br>
					글번호 : ${sl.board_num }<br>
					글쓴이 : ${sl.user_id }<br>
					글제목 : ${sl.board_subject }<br>
					글내용 : ${sl.board_content }<br>
				</c:forEach>
				<c:forEach var="sl2" items="${searchList2 }"> <!-- 반복문 -->

					<img src="files/${sl2.store_image }" style="width: 200px;"><br>
					상호명 : ${sl2.store_name }<br>
					주소 : ${sl2.store_address }<br>
					전화번호 : ${sl2.store_contact }<br>
					영업시간 : ${sl2.store_time }<br>

				</c:forEach>
					
			</article>
		</section>
		
		<jsp:include page="include/footer.jsp" />
	</div>
</body>
</html>