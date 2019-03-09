<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="css/boardList3.css" rel="stylesheet">
<link href="css/menuBar2.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div id="container">
		<jsp:include page="include/menuBar.jsp" />
		<section id="boardList">
			<div class="boardList_title">
			<div class="t1"> 맛집 리얼 후기 </div>
			<div class="t2">"솔직하고 믿을 수 있는 리뷰"</div>
			</div>
			<c:forEach begin="1" end="7">
			<div class="boardList_content">
			<div class="boardList_content_layer01">
			<a href="#" class="boardList_content_layer02_r"></a>
			<div class="boardList_content_layer02_l">
			<a href="#"><span class="boardList_content_layer02_l_1">1. 해룡마라룽샤 </span></a>
			<div class="boardList_content_layer02_l_2">서울시 광진구 자양동 857</div>
			<div class="boardList_content_layer02_l_3"># 중식</div>
			<div class="boardList_content_layer02_l_4">가로수길빵슈니 마라음식은 맛있다고 듣기만 했는데 본격으로 입문하게 된 곳. 4.7이라는 평점은 안가보곤 배길 수가 </div>
			</div>
			</div>
			</div>
			</c:forEach>
		</section>

		<jsp:include page="include/footer.jsp" />
	</div>


</body>
</html>