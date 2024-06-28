<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
<title>Insert title here</title>
</head>
<body>
    <section class="right-column">
        <h2>업적 수정 페이지</h2>
        <div class="achievement-update-container">
            <div class="achievement-info">
                <div>
                    <label>수정 전 업적 아이콘</label>
                    <div class="achievement-icon">업적 아이콘</div>
                </div>
                <div>
                    <label>수정 전 업적 조건</label>
                    <div class="achievement-condition">수정 전 업적 조건</div>
                </div>
                <div>
                    <label>수정 전 필요 횟수</label>
                    <div class="achievement-count">수정 전 필요 횟수</div>
                </div>
                <div>
                    <label>수정 전 타이틀 이름</label>
                    <div class="achievement-title">수정 전 타이틀 이름</div>
                </div>
            </div>
            <form action="${pageContext.request.contextPath}/admin/updateAchievement" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="iconUpload">업적 아이콘 업로드 폼</label>
                    <input type="file" id="iconUpload" name="iconUpload">
                </div>
                <div class="form-group">
                    <label for="conditionInput">업적 조건 입력 폼</label>
                    <input type="text" id="conditionInput" name="condition" required>
                </div>
                <div class="form-group">
                    <label for="countInput">필요 횟수 입력 폼</label>
                    <input type="number" id="countInput" name="count" required>
                </div>
                <div class="form-group">
                    <label for="titleInput">타이틀 이름 입력 폼</label>
                    <input type="text" id="titleInput" name="title" required>
                </div>
                <div class="form-actions">
                    <a href="${pageContext.request.contextPath}/admin/adminMain" class="button">뒤로가기</a>
                    <button type="submit" class="button">업적 수정</button>
                </div>
            </form>
        </div>
    </section>
</body>
</html>