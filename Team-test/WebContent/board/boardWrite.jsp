<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="../css/boardWrite3.css" rel="stylesheet">
<link href="../css/menuBar3.css" rel="stylesheet">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="../js/jquery-3.3.1.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		$("#store_name").autocomplete({
			source : function(request, response) {
				//많이 봤죠? jquery Ajax로 비동기 통신한 후
				//json객체를 서버에서 내려받아서 리스트 뽑는 작업
				$.ajax({
					//호출할 URL
					url : "../search.jsp",
					//우선 jsontype json으로
					dataType : "json",
					// parameter 값이다. 여러개를 줄수도 있다.
					data : {
						//request.term >> 이거 자체가 text박스내에 입력된 값이다.
						"searchValue" : request.term
					},
					success : function(result) {
						//return 된놈을 response() 함수내에 다음과 같이 정의해서 뽑아온다.
						response($.map(result, function(item) {
							return {
								//label : 화면에 보여지는 텍스트
								//value : 실제 text태그에 들어갈 값
								//본인은 둘다 똑같이 줬음
								//화면에 보여지는 text가 즉, value가 되기때문 
								label : item.store_name +" / "+ item.store_address,
								value : item.store_name,
								store_num : item.store_num
							}
							
						}));
					}
				});
			},
			//최소 몇자 이상되면 통신을 시작하겠다라는 옵션
			minLength : 1,
			//자동완성 목록에서 특정 값 선택시 처리하는 동작 구현
			//구현없으면 단순 text태그내에 값이 들어간다.
			select : function(event, ui) {
				$("#store_num").val(ui.item.store_num);
			}
		});
	});
</script>
</head>
<body>
	<div id="container">

		<jsp:include page="../include/menuBar.jsp" />

		<section id="boardDeatilContentArea">
			<h2>게시판 글 등록</h2>
			<form action="BoardWritePro.bo" method="post"
				enctype="multipart/form-data" name="boardform">
				<input type="text" name="store_num" id="store_num" >
				<table>
					<tr>
						<td class="menu"><label for="store_name">음식점 선택</label></td>
						<td class="menuDetail"><input type="text" name="store_name"
							id="store_name" class="a"></td>
					</tr>
					<tr>
						<td  class="menu"><label for="board_name">글쓴이</label></td>
						<td class="menuDetail"><input type="text" name="user_id"
							id="user_id" required="required" class="a"></td>
					</tr>
					<tr>
						<td  class="menu"><label for="board_subject">제목</label></td>
						<td class="menuDetail"><input type="text" name="board_subject"
							id="board_subject" required="required" class="a"></td>
					</tr>
					<tr>
						<td  class="menu"><label for="board_content">내용</label></td>
						<td class="menuDetail"><textarea name="board_content"
								id="board_content" cols="40" rows="15" required="required"></textarea></td>
					</tr>
					<tr>
						<td  class="menu"><label for="board_file">사진</label></td>
						<td class="menuDetail"><input type="file" name="board_image"
							id="board_file" required="required" class="a"></td>
					</tr>
				</table>

				<input type="submit" value="등록">&nbsp;&nbsp; <input
					type="reset" value="다시쓰기">
			</form>
		</section>

		<jsp:include page="../include/footer.jsp" />

	</div>
</body>
</html>