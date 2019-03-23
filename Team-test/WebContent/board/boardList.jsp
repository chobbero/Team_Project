<%@page import="board.vo.ListBean"%>
<%@page import="board.vo.StoreBean"%>
<%@page import="board.vo.PageInfo"%>
<%@page import="board.vo.BoardBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	ArrayList<ListBean> list = (ArrayList<ListBean>) request.getAttribute("ListBean");
	PageInfo info = (PageInfo) request.getAttribute("pageInfo");
// 	String id =	(String)session.getAttribute("id");
	String id = "demian";

	int nowPage = info.getPage(); // 현재 페이지
	int listCount = info.getListCount(); // 전체 게시물 개수
	int startPage = info.getStartPage(); // 해당 페이지의 첫번째 페이지 수
	int endPage = info.getEndPage(); // 해당 페이지의 마지막 페이지 수
	int maxPage = info.getMaxPage(); // 전체 페이지 수
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="css/boardList5.css" rel="stylesheet">
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

		<section id="boardList">
			<div class="boardList_title">
				<div class="t1">리얼 후기</div>
				<div class="t2">"솔직하고 믿을 수 있는 리뷰"</div>
				<a class="toWriteForm" href='<c:url value="/BoardWriteForm.bo"/>'></a>
			</div>
			
			<!-- 메인이미지,가게명,가게주소,음식분류,내용 -->
			<%
				if (list != null && listCount > 0) {
					for (int i = 0; i < list.size(); i++) {
			%>
			<div class="boardList_content">
				<a href="BoardDetail.bo?board_num=<%=list.get(i).getBoard_num()%>&page=<%=nowPage%>" class="boardList_content_layer02_r" style="background-image: url('files/<%=list.get(i).getStore_image()%>');">
<%-- 				<img src="files/<%=list.get(i).getStore_image()%>"> --%>
				</a>
				<div class="boardList_content_layer02_l">
				<a href="BoardDetail.bo?board_num=<%=list.get(i).getBoard_num()%>&page=<%=nowPage%>">
				<span class="boardList_content_layer02_l_1"><%=list.get(i).getBoard_subject()%>
				</span></a>
				 <label for="pick">
				 <img class="pick" src="img/heart.png">
				 </label>
				<input id="pick" type="checkbox" name="<%=id %>" value="<%=list.get(i).getBoard_num()%>">
				<div class="boardList_content_layer02_l_2"><%=list.get(i).getStore_address()%></div>
				<div class="boardList_content_layer02_l_3">
				# <%=list.get(i).getStore_category()%>
				</div>
				<div class="boardList_content_layer02_l_4">
				<%=list.get(i).getBoard_content()%>
				</div>
				</div>
			</div>

			<%
				}
			%>

			<%--  		 	<c:forEach var="store" items="${list }" varStatus="status"> --%>
			<!-- 				<div class="boardList_content"> -->
			<!-- 					<div class="boardList_content_layer01"> -->
			<!-- 						썸네일 사진 -->
			<%-- 						<a href="BoardDetail.bo?num=${store.board_num }&page=<%=nowPage %>" --%>
			<%-- 						class="boardList_content_layer02_r" style=" background-image: url('${store.store_image }')"></a> --%>
			<!-- 						썸네일 사진 -->
			<!-- 						<div class="boardList_content_layer02_l"> -->
			<!-- 							제목 -->
			<%-- 							<a href="BoardDetail.bo?num=${store.board_num }&page=<%=nowPage %>"> --%>
			<%-- 							<span class="boardList_content_layer02_l_1">${store.board_subject } </span></a> --%>
			<!-- 							제목 -->
			<!-- 							<label for="pick"><img class="pick" src="../img/heart.png"></label> -->
			<!-- 							<input id="pick" type="checkbox" name="id" value="board_num"> -->
			<%-- 							<div class="boardList_content_layer02_l_2">${store.store_address }</div> --%>
			<%-- 							<div class="boardList_content_layer02_l_3"># ${store.store_category }</div> --%>
			<%-- 							<div class="boardList_content_layer02_l_4">${store.board_content }</div> --%>
			<!-- 						</div> -->
			<!-- 					</div> -->
			<!-- 				</div> -->
			<%-- 			</c:forEach> --%>

		</section>

		<section id="pageList">
			<%
				if (nowPage <= 1) {
			%>
			<img src="img/left_click.png" class="arrow">이전&nbsp;&nbsp;
			<%
				} else {
			%>
			<a href="BoardList.bo?page=<%=nowPage - 1%>"><img src="img/left_click.png" class="arrow">이전</a>&nbsp;&nbsp;
			<%
				}
			%>

			<%
				for (int i = startPage; i <= endPage; i++) {
						if (i == nowPage) {
			%>
			<%=i%>
			<%
				} else {
			%>
			<a href="BoardList.bo?page=<%=i%>"><%=i%>
			</a>&nbsp;
			<%
				}
			%>
			<%
				}
			%>

			<%
				if (nowPage >= maxPage) {
			%>
			&nbsp;&nbsp;다음<img class="arrow" src="img/right_click.png">
			<%
				} else {
			%>
			<a href="BoardList.bo?page=<%=nowPage + 1%>">&nbsp;&nbsp;다음<img src="img/right_click.png" class="arrow"></a>
			<%
				}
			%>
		</section>
		<%
			} else {
		%>
		<section id="emptyArea">등록된 글이 없습니다.</section>
		<%
			}
		%>

		<jsp:include page="../include/footer.jsp" />
	</div>


</body>
</html>