<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
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
</head>
<body>
    <jsp:include page="../common/menubar.jsp" />

    <h3 class="title">📍 추천</h3>
    <p class="body-text">
        서울의 다채로운 문화생활을 즐겨보세요!<br>새로운 장소를 발견하고 소중한 추억을 만들어 볼까요?
    </p>
   <button class="write-btn" onclick="location.href='${pageContext.request.contextPath}/rec/recInsertForm'">글쓰기</button>
    <br><br>

</body>
</html>