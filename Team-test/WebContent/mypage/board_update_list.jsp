<%@page import="board.vo.PageInfo"%>
<%@page import="board.vo.ListBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	ArrayList<ListBean> list = (ArrayList<ListBean>)request.getAttribute("ListBean");

	PageInfo info = (PageInfo)request.getAttribute("pageInfo");
	
	System.out.println(info.getPage());
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
<link href="css/myPage4.css" rel="stylesheet">
<link href="css/board_update.css" rel="stylesheet">
<link href="css/menuBar3.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div id="container">
		<jsp:include page="../include/menuBar.jsp" />

		<section id="myPage">
		
			<div class="myInfo">
			<div class="myInfo_name">안녕하세요.<br> 저는 ${mb.user_name}입니다.</div>
			<div class="myInfo_email">${mb.user_email }</div>
			<div class="myInfo_birth">${mb.user_birth }</div>
			</div>
		
			<ul class="myPage_list">
			<a href="./IdCheck.mp"><li><div>프로필 수정</div><img class="list_imgs" src="img/edit_profile.png"/></li></a>
			<a href="./PickList.mp"><li><div>Pick리스트</div><img class="list_imgs" src="img/mypage_heart.png"/></li></a>
			<a href="./boardUpdateListForm.mp"><li><div>게시글 수정</div><img class="list_imgs" src="img/edit_board_mypage.png"/></li></a>
			<a href="#"><li><div>회원전환</div><img class="list_imgs" src="img/change_to_business.png"/></li></a>
			<a href="./UserDeleteForm.mp"><li><div>회원탈퇴</div><img class="list_imgs" src="img/remove-user4.png"/></li></a>
			<a href="./Logout.mp"><li><div>로그아웃</div><img class="list_imgs" src="img/logout_mypage.png"/></li></a>
			</ul>
			
			<div class="pick_title">내가 쓴 게시물</div>
			
			<table border="1" class="pick_table">
			
<%-- 			<c:forEach begin="1" end="7"> --%>
<!-- 			<tr class="pick_list"> -->
<!-- 			<td class="subject"><div>맛있는 맛집!</div></td> -->
<!-- 			<td class="writer"><div>홍길동</div></td> -->
<!-- 			<td class="date"><div>2019.03.31</div></td> -->
<!-- 			<td class="delete"> -->
<!-- 			<a href="#">삭제</a> -->
<!-- 			</td> -->
<!-- 			<td class="update"> -->
<!-- 			<a href="./BoardUpdateForm.mp">수정</a> -->
<!-- 			</td> -->
<!-- 			</tr> -->
<%-- 			</c:forEach> --%>
				
				<tr>
					<td>아이디</td>
					<td>가게명</td>
					<td>제목</td>
					<td>별점</td>
					<td>좋아요 개수</td>
					<td>작성일</td>
					<td>조회수</td>
					<td>수정</td>
					<td>삭제</td>
				</tr>
				
				<%
				if (list != null && listCount > 0) {
					for (int i = 0; i < list.size(); i++) {
				%>
				<tr>
					<td><%=list.get(i).getUser_id() %></td>
					<td><%=list.get(i).getStore_name() %></td>
					<td><a href="BoardDetail.bo?board_num=<%=list.get(i).getBoard_num()%>&page=<%=nowPage%>"><%=list.get(i).getBoard_subject() %></a></td>
					<td><%=list.get(i).getBoard_rating() %></td>
					<td><%=list.get(i).getBoard_like() %></td>
					<td><%=list.get(i).getBoard_date() %></td>
					<td><%=list.get(i).getBoard_readcount() %>
					<td><a href="BoardUpdateForm.bo?board_num=<%=list.get(i).getBoard_num() %>">수정</a></td>
					<td><a href="BoardDelete.bo?board_num=<%=list.get(i).getBoard_num() %>&page=<%=nowPage %>">삭제</a>
				</tr>
				<%
					}
				%>
			</table>
					
<%-- 					<% if(nowPage <= 1) { %> --%>
<!-- 					[이전] &nbsp; -->
<%-- 					<%} else { %> --%>
<%-- 						<a href="BoardList.bo?page=<%=nowPage-1 %>">[이전]</a>&nbsp; --%>
<%-- 					<%} %> --%>
				
<%-- 					<% for(int a = startPage; a <= endPage; a++) { --%>
<%-- 						if(a==nowPage) { %> --%>
<%-- 							[<%=a %>] --%>
<%-- 						<%}else {%> --%>
<%-- 							<a href="BoardList.bo?page=<%=a %>">[<%=a %>] --%>
<!-- 							</a>&nbsp; -->
<%-- 						<%} %> --%>
<%-- 					<%} %> --%>
					
<%-- 					<%if(nowPage>=maxPage){ %> --%>
<!-- 						[다음] -->
<%-- 					<%}else{ %> --%>
<%-- 						<a href="BoardList.bo?page=<%=nowPage+1 %>">[다음]</a> --%>
<%-- 					<%}  --%>
					<%}else {%>
						<section id="emptyArea">등록된 글이 없습니다</section>
					<%} %>
			
		
		
		</section>

		<jsp:include page="../include/footer.jsp" />
	</div>


</body>
</html>