<%@page import="board.vo.PageInfo"%>
<%@page import="board.vo.ListBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    ArrayList<ListBean> list = (ArrayList<ListBean>) request.getAttribute("ListBean");

    PageInfo info = (PageInfo) request.getAttribute("pageInfo");

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
<link href="css/pickList2.css" rel="stylesheet">
<link href="css/menuBar3.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div id="container">
		<jsp:include page="../include/menuBar.jsp" />

		<section id="myPage">

		<div class="pick_title">게시글 수정</div>
				<%
				    if (!list.isEmpty()) {
				%>
			<table class="pick_table">
				<tr> 
					<td class="subject_bo">제목</td>
					<td class="rating_bo">평점</td>
					<td class="date_bo">작성일</td>
					<td class="edit_bo">수정</td>
					<td class="delete_bo">삭제</td>
				</tr>

				<%
				    if (list != null && listCount > 0) {
				        for (int i = 0; i < list.size(); i++) {
				%>
				<tr>
					<td class="subject_bo1"><a
						href="BoardDetail.bo?board_num=<%=list.get(i).getBoard_num()%>&page=<%=nowPage%>"><%=list.get(i).getBoard_subject()%></a></td>
					<td class="rating_bo1"><%=list.get(i).getBoard_rating()%></td>
					<td class="date_bo1"><%=list.get(i).getBoard_date()%></td>
					<td class="edit_bo1"><a
						href="BoardUpdateForm.bo?board_num=<%=list.get(i).getBoard_num()%>">수정</a></td>
					<td class="delete_bo1"><a
						href="BoardDelete.bo?board_num=<%=list.get(i).getBoard_num()%>&page=<%=nowPage%>">삭제</a>
				</tr>
				<%
				    }
				    }
				%>
			</table>
				<%
				    }
				%>
			
			<ul class="myPage_list">
				<a href="./IdCheck.mp"><li><div>프로필 수정</div><img class="list_imgs" src="img/edit_profile.png"/></li></a>
			<a href="./PickList.mp"><li><div>Pick리스트</div><img class="list_imgs" src="img/mypage_heart.png"/></li></a>
			<a href="./boardUpdateListForm.mp"><li><div>게시글 수정</div><img class="list_imgs" src="img/edit_board_mypage.png"/></li></a>
			<a href="./BusinessChange.mp"><li><div>회원전환</div><img class="list_imgs" src="img/change_to_business.png"/></li></a>
			<a href="./UserDeleteForm.mp"><li><div>회원탈퇴</div><img class="list_imgs" src="img/remove-user4.png"/></li></a>
			<a href="./Logout.mp"><li><div>로그아웃</div><img class="list_imgs" src="img/logout_mypage.png"/></li></a>
			</ul>

			

		</section>

		<jsp:include page="../include/footer.jsp" />
	</div>


</body>
</html>