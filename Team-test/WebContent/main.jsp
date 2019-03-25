<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="css/main9_1.css" rel="stylesheet">
<link href="css/menuBar2.css" rel="stylesheet">
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
						<input class="search_input" type="text" name="search_input">
					</div>
					
					<select name="category" >
						<optgroup label="카테고리">
							<option value="subject">글제목</option>
    						<option value="content">글내용</option>
							<option value="store">상호명</option>
						</optgroup>
					</select>

					<button type="submit" class="search_btn_icon">
						<img class="btn_for_search" alt="search" src="img/Search.png">
					</button>

					<button type="submit" class="search_btn_text">검색</button>

				</form>
			</div>

		</section>

		<section id="rank">
			<div class="title">Pick_Pick이 선정한 맛집 랭킹.</div>

			<div class="container_rank">

				<div class="rank1">

					<a href="#"><img class="rank1_img" alt="no.1" src="img/ex2.jpg"></a>

					<div class="rank1_sub">

						<img class="crown" alt="crown" src="img/crown.png">

						<div class="rank1_title">1. 맛집이름</div>
					</div>

					<div class="rank1_desc">
						# 일식<br>전화번호<br>주소<br>추천
					</div>
					<img class="down_scroll" alt="down_scroll" src="img/down5.png">
				</div>
				<%
				    for (int i = 2; i <= 10; i++) {
				%>
				<div class="rankOthers">
					<a href="#"><img class="ranks_img" alt="no.1" src="img/ex.jpg"></a>
					<div class="ranks_title"><%=i%>. 맛집이름
					</div>
					<div class="ranks_desc"># 일식</div>
				</div>
				<%
				    }
				%>
			</div>
		</section>

		<section id="category">

			<div class="title">원하는 메뉴를 고르세요.</div>

			<div class="container_category">
				<a href="#" class="category"> <img src="img/date.png">
					<div class="describe"># 데이트</div>
				</a> <a href="#" class="category"> <img src="img/chinese_food.png">
					<div class="describe"># 중식</div>
				</a> <a href="#" class="category"> <img src="img/japanese_food.png">
					<div class="describe"># 일식</div>
				</a> <a href="#" class="category"> <img src="img/hot-dog.png">
					<div class="describe"># 분식</div>
				</a> <a href="#" class="category"> <img src="img/meat.png">
					<div class="describe"># 고기</div>
				</a> <a href="#" class="category"> <img src="img/chicken.png">
					<div class="describe"># 치킨</div>
				</a> <a href="#" class="category"> <img src="img/cafe.png">
					<div class="describe"># 카페</div>
				</a> <a href="#" class="category"> <img src="img/dessert.png">
					<div class="describe"># 디저트</div>
				</a> <a href="#" class="category"> <img src="img/noodles.png">
					<div class="describe"># 면류</div>
				</a> <a href="#" class="category"> <img src="img/seafood.png">
					<div class="describe"># 해산물</div>
				</a> <a href="#" class="category"> <img src="img/soup.png">
					<div class="describe"># 얼큰한</div>
				</a> <a href="#" class="category"> <img src="img/etc.png">
					<div class="describe"># 기타</div>
				</a>
			</div>
		</section>

		<section id="region">
			<div class="title">내 주변의 맛집을 찾아보세요.</div>

			<div class="container_region">
				<img class="img_for_region" src="img/region.jpg">
			</div>
		</section>
		
		<jsp:include page="include/footer.jsp" />
		
	</div>
</body>
</html>