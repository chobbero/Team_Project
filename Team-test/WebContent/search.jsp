<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="css/search.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.down_scroll').click(function() {
			var h = $('.container_rank').height();
			if (h > 250) {
				$('.container_rank').css('height', '200px');
				$('.rankOthers').css('display', 'none');
			} else {
				$('.container_rank').css('height', '1140px');
				$('.rankOthers').toggle('slow');
			}
		});
	});
	
	// 더보기 아이콘 기능
	$(window).on('load', function () {
		// 처음 로딩할 게시물 갯수
	    load('#searchResult', '1'); 
	    $("#js-btn-wrap .button").on("click", function () {
	    	// 버튼 클릭시 추가 로딩할 게시물 갯수
	        load('#searchResult', '1', '#js-btn-wrap');
	    })
	});
	 
	function load(id, cnt, btn) {
	    var search_list = id + " .searchTable:not(.active)";
	    var search_length = $(search_list).length;
	    var search_total_cnt;
	    if (cnt < search_length) {
	    	search_total_cnt = cnt;
	    } else {
	    	search_total_cnt = search_length;
	        $('.button').hide()
	    }
	    $(search_list + ":lt(" + search_total_cnt + ")").addClass("active");
	}
	
	// 검색어 공백 처리
	function ckBlank() {
		if($('.search_input').val().trim() == "") {
		    alert("검색어를 입력해 주세요.");
		    $('.search_input').focus();
		    return false;
		} else {
			$('.search_input').val() = $('.search_input').val().trim();
			return true;
		}
	}
</script>
</head>
<body>
	<div id="container">
		<jsp:include page="include/menuBar.jsp" />
		<section id="search">
			<div class="search_bar">
				<form action='<c:url value="./Search.bo"/>' method="post" name="search" onsubmit="return ckBlank()">

					<div class="search_bar_text">추천 음식점을 Pick 하세요.</div>
					<div class="search_input_wrap">
						<img class="search_input_logo" alt="" src="img/bar_logo.png">
						<select name="category" class="search_category">
							<optgroup label="검색">
								<option value="subject" <c:if test="${category.equals('subject') }">selected</c:if>>글제목</option>
	    						<option value="content" <c:if test="${category.equals('content') }">selected</c:if>>글내용</option>
								<option value="store" <c:if test="${category.equals('store') }">selected</c:if>>매장명</option>
							</optgroup>
						</select>
						<input class="search_input" type="text" name="search_input" required="required" maxlength="50">
					</div>
					<button type="submit" class="search_btn_icon">
						<img class="btn_for_search" alt="search" src="img/Search.png">
					</button>
					<button type="submit" class="search_btn_text">검색</button>
				</form>
			</div>
		</section>
		
		<section id="searchResultView">
			<!-- 검색어 유무에 따른 제목 노출 -->
			<div id="searchTitle">
				<c:choose>
					<c:when test="${search != null }">
						<p>검색어 : "${search }" (${searchCount })</p>
						<c:if test="${searchCount eq 0 }">검색 결과가 없어요 ㅠㅠ</c:if>
					</c:when>
					<c:when test="${store_category != null }">
						<p>음식카테고리 : "${store_category }" (${searchCount })</p>
					</c:when>
					<c:otherwise><p>검색어를 입력하세요 :D</p></c:otherwise>
				</c:choose>
			</div>
			
			<div id="searchResult">
				<hr>
				<!-- 게시글(제목,내용) 검색결과 -->
				<c:forEach var="sl" items="${searchList }">
					<table class="searchTable">
						<tr><td class="searchTableCell">
							<c:forEach var="img" items="${imgFileList }" >
								<c:if test="${sl.board_num == img.board_num }">
									<a href="<c:url value='/BoardDetail.bo?board_num=${sl.board_num }'/>">
									<img src="files/${img.image }" class="searchListImg"></a>
								</c:if>
							</c:forEach>
						</td><td class="vertical">
						<table class="searchTableCell2">
							<tr><td class="searchSubject">
								<a href="<c:url value='/BoardDetail.bo?board_num=${sl.board_num }'/>">${sl.board_subject }</a>
							</td></tr>
							<tr><td class="searchUser">글쓴이 : ${sl.user_id }</td></tr>
							<tr><td class="searchCount">조회수 : ${sl.board_readcount }</td></tr>
							<tr><td class="searchContent">글내용 : ${sl.board_content }</td></tr>
						</table></td></tr>
					</table>	
				</c:forEach>
				
				<!-- 상호명 검색결과 -->	
				<c:forEach var="sl2" items="${searchList2 }">
					<table class="searchTable"><tr><td class="searchTableCell">
						<img src="files/${sl2.store_image }" class="searchListImg">
						</td><td><table class="searchTableCell2">
							<tr><td class="searchSubject">${sl2.store_name } </td></tr>
							<tr><td class="searchAddress">${sl2.store_address }</td></tr>
							<tr><td class="searchContact">(Tel) ${sl2.store_contact }</td></tr>
							<tr><td class="searchTime">영업시간 : ${sl2.store_time }</td></tr>
							<tr><td class="searchMenu">주메뉴 : ${sl2.store_menu }</td></tr>
						</table>
					</td></tr></table>
				</c:forEach>
				<div id="js-btn-wrap">
					<a href="javascript:;" class="button"><img src="img/down3.png"></a>
				</div>
			</div>
		</section>
		<jsp:include page="include/footer.jsp" />
	</div>
</body>
</html>