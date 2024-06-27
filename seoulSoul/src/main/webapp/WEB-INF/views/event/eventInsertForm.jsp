<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
<title>서울소울 SEOUL SOUL</title>
<style type="text/css">
.title {
	font-family: 'Freesentation-9Black', sans-serif;
	font-weight: bold;
	text-align: center;
}

.body-text {
	font-family: 'Freesentation-7Black', sans-serif;
	font-weight: normal;
	text-align: center;
	font-size: 12px;
	font-weight: normal;
}

.content {
	width: 80%;
	margin: 0 auto;
}

.form-group input, .form-group textarea {

	width: 100%;
	padding: 10px;
	box-sizing: border-box;
	margin-bottom: 10px;
	
}

.form-group textarea {
	
	resize: none;
	height: 400px;
}

.form-container {

	display: flex;
	justify-content: space-between;
}

.form-group input::placeholder, .form-group textarea::placeholder {
	font-weight: bold;
	color: #333;
}

.left-pane, .right-pane {
	width: 30%;
	background-color: #f0f0f0;
	padding: 20px;
	box-sizing: border-box;
}

.left-pane {
	height: 450px;
	width: 30%;
	background-color: #f0f0f0;
	padding: 20px;
	box-sizing: border-box;
}

.right-pane {
	width: 68%;
	background-color: #f0f0f0;
	padding: 20px;
	box-sizing: border-box;
	display: flex;
	flex-direction: column;
	height: 450px;
}

.right-pane label {
	margin-bottom: 10px;
}

.address {
	width: 96%;
	background-color: #f0f0f0;
	padding: 20px;
	box-sizing: border-box;
	display: flex;
	flex-direction: column;
	height: 450px;
	margin: 0 auto; /* 좌우 중앙 정렬 */
}

#b1 {
	display: inline-block;
	padding: 5px 10px;
	background-color: #4382A6;
	color: white;
	border: none;
	border-radius: 5px;
	font-size: 14px;
	cursor: pointer;
	transition: background-color 0.3s;
	text-align: center;
	box-shadow: 0 -2px 5px rgba(0, 0, 0, 0.1);
	margin-left: auto;
}

#b1 {
	display: flex;
	justify-content: flex-end;
	margin-top: 15px;
}

#b1:hover {
	background-color: #0056b3;
}

#image-preview {
	width: 100%;
	height: auto;
	color: #999;
	display: block;
	text-align: center;
	line-height: 450px;
}

.custom-file-input {
	display: none;
}

.custom-file-label {
	display: inline-block;
	padding: 5px 10px;
	background-color: #4382A6;
	color: white;
	border: none;
	border-radius: 5px;
	font-size: 14px;
	font-weight: 50 !important;
	cursor: pointer;
	transition: background-color 0.3s;
	text-align: center;
	box-shadow: 0 -2px 5px rgba(0, 0, 0, 0.1);
	margin-left: auto;
}

.custom-file-label:hover {
	background-color: #0056b3;
}

.date-group {
	display: flex;
	justify-content: space-between;
}

.date-group .form-group {
	flex: 1;
	margin-right: 10px;
}

.date-group .form-group:last-child {
	margin-right: 0;
}
</style>
<script>
	function previewImage(event) {
		var reader = new FileReader();
		reader.onload = function() {
			var output = document.getElementById('image-preview');
			output.src = reader.result;
			output.style.lineHeight = 'normal';
		}
		reader.readAsDataURL(event.target.files[0]);
	}
</script>
</head>
<body>
	<jsp:include page="../common/menubar.jsp" />
	<div class="content">
		<br> <br>
		<form
			action="${pageContext.request.contextPath}/event/eventInsertForm"
			method="post" enctype="multipart/form-data">
			<!-- 제목 -->
			<div class="form-group">
				<label for="title"></label> <input type="text" id="title"
					name="title" placeholder="제목:" required>
			</div>
			<!-- 시작일과 종료일 같은 줄에 배치 -->
			<div class="date-group">
				<div class="form-group">
					<label for="startDate">시작일</label> <input type="datetime-local"
						id="startDate" name="startDate" placeholder="시작일:" required>
				</div>
				<div class="form-group">
					<label for="endDate">종료일</label> <input type="datetime-local"
						id="endDate" name="endDate" placeholder="종료일:" required>
				</div>
			</div>
			<!-- 주소 -->
			<div class="form-group">
				<label for="address"></label> <input type="text" id="address"
					name="address" placeholder="주소:" required>
			</div>
			<div class="form-container">
				<!-- 이미지 첨부 -->
				<div class="left-pane">
					<label for="image" class="custom-file-label">이미지 첨부</label> <input
						type="file" id="image" name="image" accept="image/*"
						class="custom-file-input" onchange="previewImage(event)">
					<img id="image-preview" src="" alt="Image preview">
				</div>
				<!-- 내용 작성 -->
				<div class="right-pane">
					<label for="content">내용 작성</label>
					<textarea id="content" name="content" rows="25" required></textarea>
				</div>
			</div>
		
	</div>
	<button type="submit" class="submit-button" id="b1">등록</button>
	</form>
	</div>
	<br>
	<br>
</body>
</html>