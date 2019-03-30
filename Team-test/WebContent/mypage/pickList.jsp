<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="../css/pickList.css" rel="stylesheet">
<link href="../css/menuBar3.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div id="container">
		<jsp:include page="/include/menuBar.jsp" />
<%
String id = request.getParameter("id");
String board_num = request.getParameter("board_num");
// 데이터 베이스를 연결하고 sql구문을 실행해서, id와 board_num을 이용해서 
// update 각 회원의 좋아요를 업데이트 한다.
%>
<!--모바일 -->
		
		<section id="myPage">
			
			<div class="pick_title">내가 찜한 가게</div>
			
			<table class="pick_table" border="1">
			<tr class="pick_list">
			<td class="store"><div>a</div></td><td class="writer"><div>홍길동</div></td><td class="delete">
			<input type="button" value="삭제"></td>
			
			</table>
		
		
		
			<ul class="myPage_list">
			<a href="#"><li><div>프로필 수정</div><img src="img/edit_profile.png"></li></a>
			<a href="#"><li><div>Pick리스트</div><img src="img/mypage_heart.png"></li></a>
			<a href="#"><li><div>게시글 수정</div><img src="img/edit_board_mypage.png"></li></a>
			<a href="#"><li><div>회원전환</div><img src="img/change_to_business.png"></li></a>
			<a href="#"><li><div>로그아웃</div><img src="img/logout_mypage.png"></li></a>
			</ul>
			
			
<!--탭, 모니터 -->
		
			
			<table class="pick_tablemedia" border="1">
			<tr class="pick_titleme"><td colspan="3">내가 찜한 가게</td></tr>
			<tr class="pick_list2">
			<td class="store2"><div>a</div></td><td class="writer2"><div>홍길동</div></td><td class="delete2">
			<input type="button" value="삭제"></td>
			</tr>
			
			</table>
			

		</section>

		<jsp:include page="/include/footer.jsp" />
	</div>


</body>
</html>