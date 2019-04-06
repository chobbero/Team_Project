<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="css/menuBar3.css" rel="stylesheet">
<header>
	<a href="./main" class="logo_link">
		<div id="logo">
			<img class="logo_img" alt="logo" src="img/logo_pink.png"> <img
				class="logo_img_wh" alt="logo" src="img/logo.png">
		</div>
	</a>
	<nav>
		<ul>
			<li><a href="./Search.bo">
					<div class="nav_wrap">
						<img class="nav_img" alt="search" src="img/search_nav.png">
						<div class="nav_text">검색하기</div>
					</div>
			</a></li>
			<li><a href="./BoardList.bo">
					<div class="nav_wrap">
						<img class="nav_img" alt="review" src="img/review.png">
						<div class="nav_text">리얼후기</div>
					</div>
			</a></li>
			<li><a href="./BoardListRank.bo">
					<div class="nav_wrap">
						<img class="nav_img" alt="recommend" src="img/recommendation.png">
						<div class="nav_text">추천음식점</div>
					</div>
			</a></li>
			<li><a href="./BusinessBoardList.bo">
					<div class="nav_wrap">
						<img class="nav_img" alt="cowork" src="img/organization.png">
						<div class="nav_text">제휴음식점</div>
					</div>
			</a></li>
			<c:choose>
				<c:when test="${sessionScope.user_id !=null }">
					<li><a href="./myPage.mp">
							<div class="nav_wrap">
								<img class="nav_img" alt="MyPage" src="img/MyPage.png">
								<div class="nav_text">마이페이지</div>
							</div>
					</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="./UserLoginForm.mb">
							<div class="nav_wrap">
								<img class="nav_img" alt="Log_in" src="img/log_in.png">
								<div class="nav_text">로그인</div>
							</div>
					</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>
</header>