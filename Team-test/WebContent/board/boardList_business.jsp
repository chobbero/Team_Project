<%@page import="board.vo.ListBean"%>
<%@page import="board.vo.StoreBean"%>
<%@page import="board.vo.PageInfo"%>
<%@page import="board.vo.BoardBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="css/boardListBusiness.css" rel="stylesheet">
<link href="css/menuBar3.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.pick').click(function() {

			var img = $(this).attr("src");

			var id = $(this).parent().next().attr("name");
			var board_num = $(this).parent().next().attr("value");

			if (img == "img/heart.png") {
				$(this).attr("src", "img/filled_heart.png");
				$.ajax({
					url : "./pick_like.jsp",
					type : "POST",
					dataType : "json",
					data : {
						'id' : id,
						'board_num' : board_num
					}
				});
			} else if (img == "img/filled_heart.png") {
				$(this).attr("src", "img/heart.png");
				$.ajax({
					url : "./pick_unlike.jsp",
					type : "POST",
					dataType : "json",
					data : {
						'id' : id,
						'board_num' : board_num
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

		<section id="boardList">
			<div class="boardList_title">
				<div class="t1">제휴 음식점</div>
				<div class="t2">"나에게 딱 맞는 할인 이벤트 찾기"</div>
			</div>

			<!-- 메인이미지,가게명,가게주소,음식분류,내용 -->

			<c:forEach var="store" items="${storeBean}" varStatus="status">
				<div class="boardList_content">
					<a href='<c:url value="BoardBusinessDetail.bo?store_num=${store.store_num}&page=${pageInfo.page}" />'
						class="boardList_content_layer02_r"
						style="background-image : url('./files/${store.store_image }' ); ">
					</a>
					<div class="boardList_content_layer02_l">
						<a href="BoardBusinessDetail.bo?store_num=${store.store_num}&page=${pageInfo.page}">
							<span class="boardList_content_layer02_l_1">[${store.store_name}] ${boardBean[status.index].board_subject}
						</span>
						</a>

						<div class="boardList_content_layer02_l_2">${store.store_address}</div>
						<div class="boardList_content_layer02_l_3">#
							${store.store_category}</div>
						<div class="boardList_content_layer02_l_4">
							${boardBean[status.index].board_content}</div>
					</div>
				</div>
			</c:forEach>



		</section>

		<section id="pageList">

			<c:choose>
				<c:when test="${pageInfo.page <= 1 }">
					<img src="img/left_click.png" class="arrow">이전&nbsp;&nbsp;
			</c:when>

				<c:otherwise>
					<a href="./BusinessBoardList.bo?page=${pageInfo.page -1}"
						class="prev"><img src="img/left_click.png" class="arrow">이전</a>&nbsp;&nbsp;
			</c:otherwise>
			</c:choose>

			<c:forEach var="i" begin="${pageInfo.startPage}"
				end="${pageInfo.endPage}">
				<c:choose>
					<c:when test="${pageInfo.page eq i }">
						<a class="cur">${i} </a>
					</c:when>

					<c:otherwise>
						<a href="./BusinessBoardList.bo?page=${i}" class="current">${i}
						</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:choose>
				<c:when test="${pageInfo.page >= pageInfo.maxPage }">
					&nbsp;&nbsp;다음<img class="arrow" src="img/right_click.png">
				</c:when>

				<c:otherwise>
					<a href="./BusinessBoardList.bo?page=${pageInfo.page +1}"
						class="next">&nbsp;&nbsp;다음<img src="img/right_click.png"
						class="arrow">
					</a>
				</c:otherwise>
			</c:choose>

		</section>


		<jsp:include page="../include/footer.jsp" />
	</div>


</body>
</html>