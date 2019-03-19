<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="FileUpload2.css" rel="stylesheet">
<script src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				
				$('.test1').on('change', function() { // 값이 변경되면 
					var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출 
					$('.t1').val(filename);
					$('.bb').css('display', 'block');
				
				});
				
				$('.test2').on('change', function() { // 값이 변경되면 
					var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출 
					$('.t2').val(filename);
					$('.cc').css('display', 'block');
				
				});
				
				$('.test3').on('change', function() { // 값이 변경되면 
					var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출 
					$('.t3').val(filename);
				
				});
				
			});
	
	
	
// 	$(document).ready(
// 			function() {
// 				var fileTarget = $('.filebox .upload-hidden');
// 				fileTarget.on('change', function() { // 값이 변경되면 
// 					if (window.FileReader) { // modern browser 
// 						var filename = $(this)[0].files[0].name;
// 					} else { // old IE 
// 						var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출 
// 					} // 추출한 파일명 삽입 
// 					$(this).siblings('.upload-name').val(filename);
// 				var check = $('.parent:first-child').chilren('.upload-name').val();
// 				if(check != '파일선택') {
// 				$('.parent:nth-child(2)').html("<input class='upload-name' value='파일선택' disabled='disabled'>"+ 
// 						"<label for='ex_filename'>업로드</label>"+ 
// 						"<input type='file' id='ex_filename' class='upload-hidden'>");
					
// 				}
// 				});
				
// 			});
</script>
</head>
<body>


		<div class="filebox aa">
			<input class="upload-name t1" value="파일선택" disabled="disabled">
			<label for="ex_filename1">업로드</label> <input type="file"
				id="ex_filename1" class="upload-hidden test1" name="file" multiple="multiple">
		</div>
		
		<div class="filebox bb">
			<input class="upload-name t2" value="파일선택" disabled="disabled">
			<label for="ex_filename2">업로드</label> <input type="file"
				id="ex_filename2" class="upload-hidden test2" name="file" multiple="multiple" >
		</div>

		<div class="filebox cc">
			<input class="upload-name t3" value="파일선택" disabled="disabled">
			<label for="ex_filename3">업로드</label> <input type="file"
				id="ex_filename3" class="upload-hidden test3" name="file" multiple="multiple" >
		</div>



	</div>
</body>
</html>