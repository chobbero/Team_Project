<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
// 	ArrayList<BoardBean> articleList = (ArrayList<BoardBean>)request.getAttribute("articleList");
// 	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	
// 	int listCount = pageInfo.getListCount();
// 	int nowPage = pageInfo.getPage();
// 	int startPage = pageInfo.getStartPage();
// 	int endPage = pageInfo.getEndPage();
// 	int maxPage = pageInfo.getMaxPage();
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="css/boardList5.css" rel="stylesheet">
<link href="css/menuBar2.css" rel="stylesheet">
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
// 				$.ajax({
// 					url: "pick_like.jsp",
// 					type: "POST",
// 					dataType: "json",
// 					data: {
// 						'id': id , 'board_num':board_num
// 					}
					
// 				});
			} else if(img == "img/filled_heart.png") {
				$(this).attr("src", "img/heart.png");
// 				$.ajax({
// 					url: "pick_like.jsp",
// 					type: "POST",
// 					dataType: "json",
// 					data: {
// 						'id': id , 'board_num':board_num
// 					}
// 				});
			}
		});
	});
</script>
</head>
<body>
	<div id="container">
		<jsp:include page="include/menuBar.jsp" />
		<section id="boardList">
			<div class="boardList_title">
			<div class="t1"> 맛집 리얼 후기 </div>
			<div class="t2">"솔직하고 믿을 수 있는 리뷰"</div>
			</div>
			<c:forEach begin="1" end="7" varStatus="status">
			<div class="boardList_content">
			<div class="boardList_content_layer01">
			<a href="#" class="boardList_content_layer02_r" style=" background-image: url('img/1_c_f.jpg')"></a>
			<div class="boardList_content_layer02_l">
			<a href="#"><span class="boardList_content_layer02_l_1">1. 해룡마라룽샤 </span></a>
			<label for="pick"><img class="pick" src="img/heart.png"></label><input id="pick" type="checkbox" name="id", value="board_num">
			<div class="boardList_content_layer02_l_2">서울시 광진구 자양동 857</div>
			<div class="boardList_content_layer02_l_3"># 중식</div>
			<div class="boardList_content_layer02_l_4">가로수길빵슈니 마라음식은 맛있다고 듣기만 했는데 본격으로 입문하게 된 곳. 4.7이라는 평점은 안가보곤 배길 수가 </div>
			</div>
			</div>
			</div>
			</c:forEach>
		</section>
		
		<section id="pageList">
<%-- 		<%if(nowPage <= 1) { %> --%>
<!-- 			[이전]&nbsp; -->
<%-- 		<%} else {%> --%>
<%-- 			<a href="BoardList.bo?page=<%=nowPage - 1%>">[이전]</a>&nbsp; --%>
<%-- 		<%} %> --%>
	
<%-- 		<%for(int i = startPage; i <= endPage; i++) { --%>
<%-- 		    if(i == nowPage) {%> --%>
<%-- 				[<%=i %>] --%>
<%-- 		<%} else {%> --%>
<%-- 				<a href="BoardList.bo?page=<%=i %>">[<%=i %>]</a>&nbsp; --%>
<%-- 			<%} %> --%>
<%-- 		<%} %> --%>
	
<%-- 		<%if(nowPage >= maxPage) {%> --%>
<!-- 			&nbsp;[다음] -->
<%-- 		<%} else { %> --%>
<%-- 			<a href="BoardList.bo?page=<%=nowPage + 1%>">&nbsp;[다음]</a> --%>
<%-- 		<%} %> --%>
		</section>
		
		<jsp:include page="include/footer.jsp" />
	</div>


</body>
</html>