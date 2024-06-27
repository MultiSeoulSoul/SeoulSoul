<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
<title>서울소울 SEOUL SOUL</title>

<style type="text/css">
.content {
    width: 80%;
    margin: 0 auto;
}

</style>

</head>
<body>

	<!-- 메뉴바 -->
	<jsp:include page="../common/menubar.jsp"/>

	<!-- div content -->
     <div class="content">
     	<br><br>
     	
     	<!-- 문의글 작성하기 페이지 -->   
        <h1>문의글 작성하기</h1>
        <p>문의글 작성하기 설명글</p>
              
        
        <div class="form-container">  
                 
	        <!-- 회원: 문의글 작성 폼 -->
            <form action="qnaInsert" method="post" enctype="multipart/form-data">                
                
                <!-- 테스트용: 작성자 writer(user_no) -->    
                <div class="form-group">
                    <input type="text" id="writer" name="writer" placeholder="테스트용: 3" required>
                </div>
                
                <!-- 카테고리 -->
                <div class="form-group">
                	<select id="category" name="category_code">
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.categoryCode}">${category.categoryName}</option>
                        </c:forEach>
                    </select>
                </div>
                
                <!-- 제목 -->    
                <div class="form-group">
                    <input type="text" id="title" name="title" placeholder="제목" required>
                </div>
                
                <!-- 내용 -->
                <div class="form-group">
                    <textarea id="content" name="content" placeholder="내용" rows="10" required></textarea>
                </div>
                
                <!-- 파일 업로드를 위한 input -->
                <div class="form-group">
                    <input type="file" id="multiFiles" name="multiFiles" multiple>
                </div>
                
                <!-- 하단 버튼 -->
                <div class="form-group" align ="right">
                	<button type="submit" class="submit-button">작성하기</button>
                    <a href="csMain"><button type="button" id="back-button">돌아가기</button></a>
                </div>
            </form>
        </div>
            
       <br><br>	
   	</div>
   	
</body>
</html>