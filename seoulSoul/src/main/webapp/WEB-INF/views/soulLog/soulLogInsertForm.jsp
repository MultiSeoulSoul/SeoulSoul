<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Insert title here</title>

<script type="text/javascript">
	
	$(document).ready(function() {			
		
		$.ajax({
			url: "${pageContext.servletContext.contextPath}/soulLog/locationList",
						
			success: function(data) {
				var locationSelect = $('select[name="locationCode"]');
				// 받아온 데이터로 <select> 내용채우기
				data.forEach(function(location) {
					locationSelect.append($('<option></option>').attr('value', location.locationCode).text(location.locationName));
				});
			},
			error: function() {
				alert('자치구 목록을 불러오는 데 실패했습니다.');
			}
		})
		
		$.ajax({
			url: "${pageContext.servletContext.contextPath}/soulLog/categoryList",
						
			success: function(data) {
				var categorySelect = $('select[name="categoryCode"]');
				// 받아온 데이터로 <select> 내용채우기
				data.forEach(function(category) {
					categorySelect.append($('<option></option>').attr('value', category.categoryCode).text(category.categoryName));
				});
			},
			error: function() {
				alert('카테고리 목록을 불러오는 데 실패했습니다.');
			}
		})
					
	})
</script>


<script>
			
	function loadImg(value, num) {
		if (value.files && value.files[0]) {
			const reader = new FileReader();
			reader.onload = function(e) {
				
				switch(num){
					case 1:
						document.getElementById("img1").src = e.target.result;
						break;
					case 2:
						document.getElementById("img2").src = e.target.result;
						break;
					case 3:
						document.getElementById("img3").src = e.target.result;
						break;
					case 4:
						document.getElementById("img4").src = e.target.result;
						break;
					case 5:
						document.getElementById("img5").src = e.target.result;
						break;
				}
			}
			
			reader.readAsDataURL(value.files[0]);
			
		}
	}
			
</script>


<script>

	function validateForm() {
		const inputs = document.querySelectorAll('input[type="file"]');
		let fileSelected = false;

		for (let i = 0; i < inputs.length; i++) {
			if (inputs[i].files.length > 0) {
				fileSelected = true;
                break;
                }
		}

		if (!fileSelected) {
			alert('이미지를 최소 1개 이상 업로드해 주세요.');
			return false;
		}

		return true;
	
	}
</script>

</head>
<body>

<jsp:include page="../common/menubar.jsp"/>

<div class="content" style="margin: 30px">

	<form action="insertSoulLog" method="post" encType="multipart/form-data" onsubmit="return validateForm()">
		
		<div class="insert-group" style="height: 50px; background: white">
			<span style="margin-left:20px">제목</span>
			<input type="text" id="title" name="title" style="margin-top: 10px; margin-left: 15px; width: 400px; height: 25px; background-color: #f0f0f0; border: 1px solid #c0c0c0;" maxlength="20" required>
			<span style="margin-left:50px">작성자</span>
			<input type="text" style="margin-top: 10px; margin-left: 15px; width: 150px; height: 25px; background-color: #f0f0f0; border: 1px solid #c0c0c0;" value="<sec:authentication property="principal.nickname"/>" disabled>
			<span style="margin-left:50px">자치구</span>
			<select name="locationCode" style="margin-left: 15px; width: 140px; height: 30px; background-color: #f0f0f0; border: 1px solid #c0c0c0;">
			</select>
			<span style="margin-left:50px">카테고리</span>
			<select name="categoryCode" style="margin-left: 15px; width: 140px; height: 30px; background-color: #f0f0f0; border: 1px solid #c0c0c0;">
			</select>
		</div>
		
		<br>
	
		<table>
			<tr>
				<td>
					<div id="imgArea1" style="width: 240px; height: 240px; background: white">
						<img id="img1" width="240px" height="240px">
						<input type="file" name="imgList" onchange="loadImg(this,1)">
					</div>
				</td>
				<td>
					<div id="imgArea2" style="margin-left: 18px; width: 240px; height: 240px; background: white">
						<img id="img2" width="240px" height="240px">
						<input type="file" name="imgList" onchange="loadImg(this,2)">
					</div>
				</td>
				<td>
					<div id="imgArea3" style="margin-left: 18px; width: 240px; height: 240px; background: white">
						<img id="img3" width="240px" height="240px">
						<input type="file" name="imgList" onchange="loadImg(this,3)">
					</div>
				</td>
				<td>
					<div id="imgArea4" style="margin-left: 18px; width: 240px; height: 240px; background: white">
						<img id="img4" width="240px" height="240px">
						<input type="file" name="imgList" onchange="loadImg(this,4)">
					</div>
				</td>
				<td>
					<div id="imgArea5" style="margin-left: 18px; width: 240px; height: 240px; background: white">
						<img id="img5" width="240px" height="240px">
						<input type="file" name="imgList" onchange="loadImg(this,5)">
					</div>
				</td>
			</tr>
		</table>
		
		<br>
		<br>
		<br>
		
		<div class="insert-group" style="height: 270px; background: white">
			<br>
			<span style="margin-left:20px">내용</span>
			<br>
			<textarea id="content" name="content" style="margin-top: 10px; margin-left: 15px; width: 1230px; height: 200px; background-color: #f0f0f0; border: 1px solid #c0c0c0; resize: none;" maxlength="500" required></textarea>
		</div>
		
		<br>
		<br>
		
		<div align="center">
			<button class="btns" type="submit" style="width: 110px; height: 35px; background: #3982BC; color: white; border: 1px solid #c0c0c0;">로그 작성</button>
		</div>
		
		
	</form>
	
</div> <!-- content div -->

</body>
</html>