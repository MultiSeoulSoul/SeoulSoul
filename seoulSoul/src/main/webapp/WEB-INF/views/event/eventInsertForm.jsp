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
}

.content {
    width: 80%;
    margin: 0 auto;
}

.form-group input, .form-group textarea {
    width: 100%; /* 부모 패딩을 고려한 폭 조정 */
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
    font-weight: bold; /* 진하게 설정 */
    color: #333; /* 색상 변경 */
}

.left-pane, .right-pane {
    width: 30%;
    background-color: #f0f0f0;
    padding: 20px;
    box-sizing: border-box;
}

.left-pane {
    height: 450px; /* 좌측 컨테이너 높이 설정 */
    width: 30%; /* 좌측 컨테이너 가로폭 설정 */
    background-color: #f0f0f0;
    padding: 20px;
    box-sizing: border-box;
}

.right-pane {
    width: 68%; /* 우측 컨테이너 가로폭 설정 */
    background-color: #f0f0f0;
    padding: 20px;
    box-sizing: border-box;
    display: flex;
    flex-direction: column;
    height: 450px
}

.right-pane label {
    margin-bottom: 10px;
}

#b1 {
    display: inline-block;
    padding: 5px 10px;
    background-color: #4382A6; /* 버튼 배경 색상 */
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
    background-color: #0056b3; /* 호버 시 배경 색상 */
}

#image-preview {
    width: 100%;
    height: auto;
    color: #999; /* 텍스트 색상 연하게 설정 */
    display: block;
    text-align: center;
    line-height: 450px; /* 컨테이너 높이에 맞추기 */
}

.custom-file-input {
    display: none;
}

.custom-file-label {
    display: inline-block;
    padding: 5px 10px;
    background-color: #4382A6; /* 버튼 배경 색상 */
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
    background-color: #0056b3; /* 호버 시 배경 색상 */
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
            output.style.lineHeight = 'normal'; /* 이미지 로드 후 텍스트 라인 높이 초기화 */
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
            method="post">
            <!-- 제목 -->
            <div class="form-group">
                <label for="title"></label> <input type="text" id="title"
                    name="title" placeholder="제목:" required>
            </div>
            <!-- 시작일과 종료일 같은 줄에 배치 -->
            <div class="date-group">
                <div class="form-group">
                    <label for="startDate">시작일</label>
                    <input type="datetime-local" id="startDate" name="startDate" placeholder="시작일:" required>
                </div>
                <div class="form-group">
                    <label for="endDate">종료일</label>
                    <input type="datetime-local" id="endDate" name="endDate" placeholder="종료일:" required>
                </div>
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

            <button type="submit" class="submit-button" id="b1">등록</button>
        </form>

    </div>

    <br>
    <br>

</body>
</html>