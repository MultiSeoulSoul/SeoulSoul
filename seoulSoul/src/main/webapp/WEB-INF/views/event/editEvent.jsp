<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>이벤트 수정</title>
    <style type="text/css">
        .detail-container {
            margin: 0 auto;
            text-align: center;
        }

        .body-text {
            font-family: 'Freesentation-5Black', sans-serif;
            font-weight: normal;
            text-align: center;
            font-size: 15px;
        }

        .event-title {
            font-family: 'Freesentation-9Black', sans-serif;
            font-weight: bold;
            text-align: center;
            font-size: 30px; /* 제목을 더 크게 강조 */
            margin-bottom: 10px; /* 제목과 내용 사이에 여백 추가 */
        }

        .image-container {
            width: 300px;  /* 고정된 너비 */
            height: 400px; /* 고정된 높이 */
            overflow: hidden; /* 이미지가 컨테이너를 넘지 않도록 숨김 */
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto; /* 컨테이너를 중앙으로 배치 */
        }

        .detail-image {
            width: 100%;
            height: auto; /* 이미지 비율을 유지하면서 너비에 맞추어 조정 */
            max-height: 100%;
        }

        .detail-btn {
            background-color: #4382A6;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 5px 10px;
            cursor: pointer;
            margin-right: 10px;
        }

        .detail-btn:hover {
            background-color: #0056b3;
        }

        .detail-btn1 {
            background-color: #C42A2A;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 5px 10px;
            cursor: pointer;
            margin-right: 10px;
        }

        .detail-btn1:hover {
            background-color: #9b1d1d;
        }
    </style>
</head>
<body>
    <jsp:include page="../common/menubar.jsp" />
    <div class="detail-container">
        <h2 class="event-title">이벤트 수정</h2>
        <form action="${pageContext.request.contextPath}/event/updateEvent" method="post" enctype="multipart/form-data">
            <input type="hidden" name="eventNo" value="${event.eventNo}">
            <div>
                <label for="title">제목:</label>
                <input type="text" id="title" name="title" value="${event.title}" required>
            </div>
            <div>
                <label for="content">내용:</label>
                <textarea id="content" name="content" required>${event.content}</textarea>
            </div>
            <div>
                <label for="address">주소:</label>
                <input type="text" id="address" name="address" value="${event.address}" required>
            </div>
            <div>
                <label for="startDate">시작 날짜:</label>
                <input type="datetime-local" id="startDate" name="startDate" value="${event.startDate}" required>
            </div>
            <div>
                <label for="endDate">종료 날짜:</label>
                <input type="datetime-local" id="endDate" name="endDate" value="${event.endDate}" required>
            </div>
            <div>
                <label for="image">이미지 업로드:</label>
                <input type="file" id="image" name="image">
            </div>
            <div>
                <button type="submit" class="detail-btn">수정하기</button>
                <button type="button" class="detail-btn1" onclick="history.back()">취소</button>
            </div>
        </form>
    </div>
</body>
</html>