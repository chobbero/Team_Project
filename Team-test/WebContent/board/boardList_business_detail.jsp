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
<link href="css/boardListBusiness_detail.css" rel="stylesheet">
<link href="css/menuBar3.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div id="container">

		<jsp:include page="../include/menuBar.jsp" />

		<section id="boardList">
			<div class="boardList_title">
				<div class="t1">${storeBean.store_name }</div>
				<div class="t2"> 작성 일자 : ${boardBean.board_date } &nbsp;&nbsp;&nbsp;&nbsp; 조회수 : ${boardBean.board_readcount }</div>
			</div>

			<!-- 메인이미지,가게명,가게주소,음식분류,내용 -->


			<div class="boardList_content">

			
				<div class="boardList_content_layer02_l">
					<div>
						<span class="boardList_content_layer02_l_1">
							${boardBean.board_subject} </span>
					</div>

					<div class="boardList_content_layer02_l_2">${storeBean.store_address}</div>
					<div class="boardList_content_layer02_l_3">#
						${storeBean.store_category}</div>
						
					<c:forEach var="list" items="${fileList }">
						<div class="boardList_content_layer02_r" style="background-image : url('./files/${list.image }' ); ">
						</div>
					</c:forEach>
					
					<div class="boardList_content_layer02_l_4">
						${boardBean.board_content}</div>
				</div>
			</div>



		</section>


		<jsp:include page="../include/footer.jsp" />
	</div>


</body>
</html>