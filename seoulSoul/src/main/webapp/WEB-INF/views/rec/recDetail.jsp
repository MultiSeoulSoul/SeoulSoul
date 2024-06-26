<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서울소울 SEOUL SOUL</title>
<style type="text/css">
.detail-container {
	margin: 0 auto;
	text-align: center;
}

.detail-image {
    width: 100%;
    max-width: 300px;
    height: auto;
    max-height: 400px; /* 필요에 따라 최대 높이를 설정 */
    object-fit: contain; /* 이미지를 컨테이너에 맞추어 조정 */
}

.detail-btn {
	background-color: #4382A6;
	color: white;
	border: none;
	border-radius: 5px;
	padding: 5px 10px;
	cursor: pointer;
}

.detail-btn:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<div class="detail-container">
		<h2>${rec.title}</h2>
		<p><strong>작성일:</strong> ${rec.createdDate}</p>
		<p><strong>조회수:</strong> ${rec.views}</p>
		<p>${rec.content}</p>
		<c:if test="${not empty rec.imagePath}">
			<img
				src="${pageContext.servletContext.contextPath}/resources/uploadFiles/${rec.imagePath}"
				alt="Image">
		</c:if>
		<br>
		<button class="detail-btn" onclick="history.back()">뒤로가기</button>
	</div>
</body>
</html>

