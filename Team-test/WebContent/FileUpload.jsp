<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="FileUpload.css" rel="stylesheet">
<script src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				var fileTarget = $('.filebox .upload-hidden');
				fileTarget.on('change', function() { // 값이 변경되면 
					if (window.FileReader) { // modern browser 
						var filename = $(this)[0].files[0].name;
					} else { // old IE 
						var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출 
					} // 추출한 파일명 삽입 
					$(this).siblings('.upload-name').val(filename);
				});
			});
</script>
</head>
<body>
	<div class="filebox">
		<input class="upload-name" value="파일선택" disabled="disabled"> <label
			for="ex_filename">업로드</label> <input type="file" id="ex_filename"
			class="upload-hidden">
	</div>

</body>
</html>