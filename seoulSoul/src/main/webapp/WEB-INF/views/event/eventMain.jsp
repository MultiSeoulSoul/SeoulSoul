<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서울소울 SEOUL SOUL</title>
<link rel="icon" href="${pageContext.request.contextPath}/resources/img/soul_icon_favicon.png"/>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
<style>
.event-container {
    width: 80%;
    margin: 0 auto;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
}

.event-card {
    width: 45%;
    background-color: white;
    border: 1.5px solid #ddd;
    border-radius: 5px;
    margin-bottom: 20px;
    overflow: hidden;
    display: flex;
    cursor: pointer;
    height: 180px; /* 카드의 높이를 더 크게 설정 */
    align-items: stretch;
}

.event-card img {
    flex: 0 0 150px; /* 이미지의 고정 너비와 높이를 설정 */
    height: 100%; /* 이미지가 카드의 전체 높이를 덮도록 설정 */
    object-fit: cover; /* 이미지가 컨테이너를 덮도록 설정 */
    border-radius: 5px 0 0 5px;
}

.event-details {
    flex: 1;
    padding: 10px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    text-align: center;
    justify-content: center; /* 세로 중앙 정렬 */
	align-items: center; /* 가로 중앙 정렬 */
}

.event-title {
    font-size: 18px;
    font-weight: bold;
    margin-bottom: 5px;
}

.event-info {
    font-size: 14px;
    color: #555;
}

.event-info span {
    display: block;
    margin-bottom: 5px;
}

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
    top: 150px; /* 조정하여 원하는 위치에 배치 */
    right: 50px; /* 조정하여 원하는 위치에 배치 */
    padding: 5px 5px;
    background-color: #4382A6; /* 버튼 배경 색상 */
    color: white;
    border: none;
    border-radius: 5px;
    font-size: 14px;
    cursor: pointer;
    transition: background-color 0.3s;
}

.write-btn:hover {
    background-color: #0056b3; /* 호버 시 배경 색상 */
}
</style>
<script>
    function navigateToDetail(eventNo) {
        location.href = '${pageContext.request.contextPath}/event/eventDetail?eventNo=' + eventNo;
    }
</script>
</head>
<body>
    <jsp:include page="../common/menubar.jsp" />

    <h3 class="title">📢 이벤트</h3>
    <p class="body-text">
        관심 있는 이벤트에 댓글을 남겨주세요!<br>여러분의 참여를 기다립니다.
    </p>
    <sec:authorize access="hasRole('ADMIN')">
        <button class="write-btn" onclick="location.href='eventInsertForm'">글쓰기</button>
    </sec:authorize>

<div class="event-container">
    <c:forEach items="${eventList}" var="event">
        <div class="event-card" onclick="navigateToDetail(${event.eventNo})">
            <c:if test="${not empty event.imagePath}">
                <img src="${pageContext.servletContext.contextPath}/resources/uploadFiles/${event.imagePath}" alt="Event Image">
            </c:if>
            <div class="event-details">
                    <div>
                        <div class="event-title">${event.title}</div>
                        <div class="event-info">
                            <span>이벤트 기간: <fmt:formatDate value="${event.startDate}" pattern="yyyy-MM-dd" /> ~ <fmt:formatDate value="${event.endDate}" pattern="yyyy-MM-dd" /></span>
                            <span>등록일: <fmt:formatDate value="${event.createdDate}" pattern="yyyy-MM-dd" /></span>
                            <span id ="address">장소: ${event.address}</span>
                            <span>조회수: ${event.views}</span>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

</body>
</html>