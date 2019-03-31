<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="css/pickList.css" rel="stylesheet">
<link href="css/menuBar3.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.pick').click(function() {
			
			var id = $(this).next().attr("name");
			var board_num = $(this).next().attr("value");
			
			
			$(this).parent().parent().parent().parent().css("display", "none");
						$.ajax({
							url: "./pick_unlike.jsp",
							type: "POST",
							dataType: "json",
							data: {
								'id': id , 'board_num':board_num
								}
							});
		});
	});
</script>
</head>
<body>
	<div id="container">
		<jsp:include page="../include/menuBar.jsp" />

<!--모바일 -->
		
		<section id="myPage">
			
			<div class="pick_title">내가 찜한 가게</div>
			
			<table class="pick_table" >
			
			<c:forEach var="picklist"  items="${pl }">
			<tr class="pick_list">
			
			<td>
			<div class="listWrap">
			
			<div class="listImage">
			<a href="BoardDetail.bo?board_num=${picklist.board_num}"><img alt="" src='<c:url value="./files/${picklist.store_image }"/>'></a>
			</div>
			
			
			<div class="listDetail">
			<a href="BoardDetail.bo?board_num=${picklist.board_num}"><div class="pSubject">${picklist.board_subject }</div></a>
			<div># ${picklist.store_category }</div>
			</div>
			
			<div class="deleteList">
			<label for="pick" class="pick">
			<span>삭제</span>
		    </label>
				<input id="pick" type="checkbox" name="${sessionScope.user_id }" value="${picklist.board_num}">
			</div>
			
			</div>
			</td>
			
			</tr>
			</c:forEach>
			</table>
		
		
		
			<ul class="myPage_list">
			<a href="./UserUpdateForm.mp"><li><div>프로필 수정</div><img class="list_imgs" src="img/edit_profile.png"/></li></a>
			<a href="#"><li><div>Pick리스트</div><img class="list_imgs" src="img/mypage_heart.png"/></li></a>
			<a href="./boardUpdateListForm.mp"><li><div>게시글 수정</div><img class="list_imgs" src="img/edit_board_mypage.png"/></li></a>
			<a href="#"><li><div>회원전환</div><img class="list_imgs" src="img/change_to_business.png"/></li></a>
			<a href="./UserDeleteForm.mp"><li><div>회원탈퇴</div><img class="list_imgs" src="img/remove-user4.png"/></li></a>
			<a href="#"><li><div>로그아웃</div><img class="list_imgs" src="img/logout_mypage.png"/></li></a>
			</ul>
			
			
<!--탭, 모니터 -->
		
			
<!-- 			<div class="pick_title">내가 찜한 가게</div> -->
			
<!-- 			<table class="pick_table" > -->
			
<%-- 			<c:forEach var="picklist"  items="${pl }"> --%>
<!-- 			<tr class="pick_list"> -->
			
<!-- 			<td> -->
<!-- 			<div class="listWrap"> -->
			
<!-- 			<div class="listImage"> -->
<%-- 			<a href="BoardDetail.bo?board_num=${picklist.board_num}"><img alt="" src='<c:url value="./files/${picklist.store_image }"/>'></a> --%>
<!-- 			</div> -->
			
			
<!-- 			<div class="listDetail"> -->
<%-- 			<a href="BoardDetail.bo?board_num=${picklist.board_num}"><div class="pSubject">${picklist.board_subject }</div></a> --%>
<%-- 			<div># ${picklist.store_category }</div> --%>
<!-- 			</div> -->
			
<!-- 			<div class="deleteList"> -->
<!-- 			<label for="pick" class="pick"> -->
<!-- 			<span>삭제</span> -->
<!-- 		    </label> -->
<%-- 				<input id="pick" type="checkbox" name="${sessionScope.user_id }" value="${picklist.board_num}"> --%>
<!-- 			</div> -->
			
<!-- 			</div> -->
<!-- 			</td> -->
			
<!-- 			</tr> -->
<%-- 			</c:forEach> --%>
<!-- 			</table> -->
			

		</section>

		<jsp:include page="../include/footer.jsp" />
	</div>


</body>
</html>