<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<link rel="icon" href="${pageContext.request.contextPath}/resources/img/soul_icon_favicon.png"/>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
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
}

.write-btn {
	display: block;
	position: absolute;
	top: 150px;
	right: 50px;
	padding: 5px 5px;
	background-color: #4382a6;
	color: white;
	border: none;
	border-radius: 5px;
	font-size: 14px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.write-btn:hover {
	background-color: #0056b3;
}

.post-slider {
	width: 70%;
	margin: 0px auto;
	position: relative;
}

.post-slider .silder-title {
	text-align: center;
	margin: 30px auto;
}

.post-slider .next {
	position: absolute;
	top: 50%;
	right: 30px;
	font-size: 4em;
	color: gray;
	cursor: pointer;
}

.post-slider .prev {
	position: absolute;
	top: 50%;
	left: 30px;
	font-size: 4em;
	color: gray;
	cursor: pointer;
}

.post-slider .post-wrapper {
	width: 84%;
	height: 350px;
	margin: 0px auto;
	overflow: hidden;
	padding: 10px 0px 10px 0px;
}

.post-slider .post-wrapper .post {
	width: 400px;
	height: 600px;
	margin: 10px 10px;
	display: inline-block;
	background: white;
	border-radius: 0px;
	cursor: pointer;
}

.post-slider .post-wrapper .post .post-info {
	font-size: 15px;
	height: 30%;
	padding-left: 10px;
	padding-bottom: 20px;
	text-align: center;
	color: white;
	background: rgba(0, 0, 0, 0.5);
	position: absolute;
	bottom: 0;
	width: 100%;
	box-sizing: border-box;
}

.post-slider .post-wrapper .post .slider-image {
	width: 100%;
	height: 60%;
	margin-bottom: -30px; /* 이미지와 텍스트 사이의 여백 추가 */
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
	border-bottom-left-radius: 5px;
	border-bottom-right-radius: 5px;
	text-align: center;
	text-color: white;
	object-fit: cover;
}
</style>
</head>
<body>
	<jsp:include page="../common/menubar.jsp" />
	<h3 class="title">&#x1F4CC 추천</h3>
	<p class="body-text">
		서울의 다채로운 문화생활을 즐겨보세요!<br>새로운 장소를 발견하고 소중한 추억을 만들어 볼까요?
	</p>
	<sec:authorize access="hasRole('ADMIN')">
		<button class="write-btn"
			onclick="location.href='${pageContext.request.contextPath}/rec/recInsertForm'">글쓰기</button>
	</sec:authorize>

	<br>
	<br>
	<div class="page-wrapper">
		<div class="post-slider">
			<i class="fas fa-chevron-left prev"></i> <i
				class="fas fa-chevron-right next"></i>
			<div class="post-wrapper">
				<c:forEach items="${recList}" var="rec">
					<div class="post" data-rec-id="${rec.recommendationNo}">
						<c:if test="${not empty rec.imagePath}">
							<img class="slider-image"
								src="${pageContext.servletContext.contextPath}/resources/uploadFiles/${rec.imagePath}"
								alt="Image">
						</c:if>
						<div class="post-info">
							<h3>${rec.title}</h3>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('.post-wrapper').slick({
							slidesToShow : 3,
							slidesToScroll : 1,
							autoplay : true,
							autoplaySpeed : 3000,
							nextArrow : $('.next'),
							prevArrow : $('.prev'),
						});

						$(".post")
								.click(
										function() {
											var recommendationNo = $(this)
													.data("rec-id");
											location.href = '${pageContext.request.contextPath}/rec/recDetail?recommendationNo='
													+ recommendationNo;
										});

						$(".post").css("cursor", "pointer");
					});
</script>
</html>