<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
<style>
    .container {
        width: 80%;
        margin: 0 auto;
        padding: 20px;
    }

    .header {
        text-align: center;
        padding: 20px 0;
    }

    .grid-container {
        display: grid;
        grid-template-columns: 1fr 3fr;
        gap: 20px;
    }

    .grid-container div {
        background-color: #f9f9f9;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    .full-width {
        grid-column: span 2;
    }

    .form-group {
        margin-bottom: 15px;
    }

    .form-group label {
        display: block;
        margin-bottom: 5px;
    }

    .form-group input, .form-group textarea, .form-group select {
        width: 100%;
        padding: 8px;
        box-sizing: border-box;
    }

    .button-group {
        display: flex;
        justify-content: space-between;
    }

    .button-group button {
        padding: 10px 20px;
        background-color: #333;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    .button-group button.back {
        background-color: #888;
    }

    .preview {
        margin-top: 15px;
        text-align: center;
    }

    .preview img {
        max-width: 100%;
        height: auto;
    }
</style>
<title>Insert title here</title>
</head>
<body>
    <jsp:include page="../common/menubar.jsp"/>
    
    <div class="container">
        <div class="full-width">
            <form action="${pageContext.request.contextPath}/admin/achCateInsertForm" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="singleFile"><strong>업적 아이콘 업로드 폼</strong></label>
                    <input type="file" id="singleFile" name="singleFile" onchange="previewImage(event)">
                </div>
                <div class="preview">
                    <img id="preview" src="#" alt="이미지 미리보기" style="display: none;">
                </div>
                <div class="form-group">
                    <label for="categoryCode"><strong>업적 조건 입력 폼</strong></label>
                    <select id="categoryCode" name="categoryCode">
                        <c:forEach items="${categoryList}" var="category">
                            <option value="${category.categoryCode}">${category.categoryName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="maxCount"><strong>필요 횟수 입력 폼</strong></label>
                    <input type="number" id="maxCount" name="maxCount" value="${achCate.maxCount}">
                </div>
                <div class="form-group">
                    <label for="title"><strong>타이틀 이름 입력 폼</strong></label>
                    <input type="text" id="title" name="title" value="${achCate.title}">
                </div>
                <div class="button-group">
                    <button type="button" class="back" onclick="history.back()">뒤로가기</button>
                    <button type="submit">업적 생성</button>
                </div>
            </form>
        </div>
    </div>

    <script>
        function previewImage(event) {
            var reader = new FileReader();
            reader.onload = function(){
                var output = document.getElementById('preview');
                output.src = reader.result;
                output.style.display = 'block';
            };
            reader.readAsDataURL(event.target.files[0]);
        }
    </script>
</body>
</html>