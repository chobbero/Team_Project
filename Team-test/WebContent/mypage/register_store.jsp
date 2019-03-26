<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="../css/store.css" rel="stylesheet">
<link href="../css/menuBar3.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div id="container">
		<jsp:include page="../include/menuBar.jsp" />
		<section id="StoreForm">
			<div class="Store_title">Pick Pick Store 등록</div>
			<form action="StoreRegPro.mb" method="post" name="JoinForm">
				<div class="Store">
					<input class="a" type="text" maxlength="16" placeholder="상호" name="store_name"> 
					<input class="a" type="text" maxlength="16" placeholder="소재지" name="store_address">
					<input class="a" type="text" maxlength="16" placeholder="요리 분류" name="store_category">
					<input class="a" type="text" maxlength="16" placeholder="대표 메뉴" name="store_menu">
					<input class="a" type="text" maxlength="16" placeholder="금액" name="store_price">
					<input class="a" type="date" maxlength="16" placeholder="영업시간" name="store_time">	
<!-- 					<input class="a" type="date" maxlength="16" placeholder="대표사진" name="store_image">	 -->
					<input class="a" type="date" maxlength="16" placeholder="전화번호" name="store_contact">		
					<input class="submit_Reg" type="submit" value="가게등록">
				</div>

			</form>
		</section>
		</div>
		<jsp:include page="../include/footer.jsp" />
</body>
</html>