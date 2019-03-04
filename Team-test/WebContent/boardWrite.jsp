<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick_Pick</title>
<link href="css/boardWrite.css" rel="stylesheet">
<link href="css/menuBar2.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div id="container">
		<jsp:include page="include/menuBar.jsp" />
		<section id="boardWrite">
			<form action="QnAWritePro.jsp" method="post" name="fr" enctype="multipart/form-data">
				<table>
					<tr>
						<td class="sub"><label for="sub" class="subL">제목</label> <select
							name="sub" id="sub">
								<option value="#상품 문의 드립니다.">#상품 문의 드립니다.</option>
								<option value="#배송 문의 드립니다.">#배송 문의 드립니다.</option>
								<option value="#입금 문의 드립니다.">#입금 문의 드립니다.</option>
								<option value="#교환 문의 드립니다.">#교환 문의 드립니다.</option>
								<option value="#반품 문의 드립니다.">#반품 문의 드립니다.</option>
								<option value="#기타 문의 드립니다.">#기타 문의 드립니다.</option>
						</select></td>
					</tr>
					<tr>
						<td class="cont"><textarea name="content" rows="22"
								cols="105" id="cont"></textarea></td>
					</tr>
					<tr>
						<td class="file"><label for="file" class="fileL">첨부파일</label>
							<input type="file" id="file" name="file"></td>
					</tr>
				</table>

				<input class="list" type="button" value="목록"
					onclick="location.href='QnAList.jsp'"> <input
					class="submit" type="submit" value="등록"> <input
					class="cancel" type="button" value="취소"
					onclick="location.href='QnAList.jsp'">
			</form>
		</section>

		<jsp:include page="include/footer.jsp" />
	</div>


</body>
</html>