<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="${pageContext.request.contextPath}/resources/img/soul_icon_favicon.png"/>
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
    font-weight: bold;
    color: #333; /* 색상 변경 */
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
    height: 450px
}

.right-pane label {
    margin-bottom: 10px;
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
</style>
<script>
function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function(){
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

        <form action="${pageContext.request.contextPath}/rec/recInsertForm"
            method="post" enctype="multipart/form-data">
            
            <div class="form-group">
                <label for="title"></label> <input type="text" id="title"
                    name="title" placeholder="제목:" required>
            </div>

            <div class="form-container">
                
                <div class="left-pane">
                    <label for="image" class="custom-file-label">이미지 첨부</label> <input
                        type="file" id="image" name="file" accept="image/*"
                        class="custom-file-input" onchange="previewImage(event)" required>
                    <img id="image-preview" src="" alt="Image preview">
                </div>

                
                <div class="right-pane">
                    <label for="content">내용 작성</label>
                    <textarea id="content" name="content" rows="25" required></textarea>
                </div>
            </div>

            <button type="submit" class="submit-button" id="b1">등록</button>
        </form>

    </div>

    <br> <br>

</body>
</html>