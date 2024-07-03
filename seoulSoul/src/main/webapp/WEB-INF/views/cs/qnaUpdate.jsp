<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="${pageContext.request.contextPath}/resources/img/soul_icon_favicon.png"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
<title>서울소울 SEOUL SOUL</title>

<style type="text/css">
.content {
    width: 80%;
    margin: 0 auto;
}

.file-list {
    margin-bottom: 10px;
}

.file-item {
    margin-bottom: 5px;
}

.file-item span {
    margin-right: 10px;
}

</style>

</head>
<body>

    <!-- 메뉴바 -->
    <jsp:include page="../common/menubar.jsp"/>

    <!-- div content -->
    <div class="content">
        <br><br>
        
        <!-- 문의글 수정하기 페이지 -->   
        <h1>문의글 수정하기</h1>
        <p>필요 시 다수의 파일을 첨부하실 수 있으며, 답변 완료된 문의글은 수정이 불가하니 유의하시길 바랍니다.</p>
        <p>서울소울 고객센터는 여러분의 소중한 의견을 듣고, 신속하게 도움을 드리기 위해 항상 노력하고 있습니다.</p>      
        <br>
        
        <div class="form-container">  
                 
            <!-- 문의글 수정 폼 -->
            <form action="${pageContext.request.contextPath}/cs/qnaUpdate" method="post" enctype="multipart/form-data">                
                <input type="hidden" name="id" value="${qna.questionNo}">
                <input type="hidden" name="writer" value="${qna.writer}">
                
                <!-- 카테고리 -->
                <div class="form-group">
                    <select name="category_code">
				        <c:forEach items="${categories}" var="category">
				            <option value="${category.categoryCode}" ${category.categoryCode == qna.categoryCode ? 'selected' : ''}>${category.categoryName}</option>
				        </c:forEach>
				    </select>
                </div>
                
                <!-- 제목 -->    
                <div class="form-group">
                    <input type="text" id="title" name="title" value="${qna.title}" required>
                </div>
                
                <!-- 내용 -->
                <div class="form-group">
                    <textarea id="content" name="content" rows="10" required>${qna.content}</textarea>
                </div>
                
                <!-- 기존 첨부 파일 목록 -->
                <div class="form-group">
                    <label>기존 첨부 파일</label>
                    <c:if test="${empty qna.files}">
                    	<div class="file-item">
                    		<span>첨부파일 없음</span>
                    	</div>
                    </c:if>
                    <div class="file-list">
                        <c:forEach var="file" items="${qna.files}">
                            <div class="file-item">
                                <span>${file.originalFileName}</span>
                                <a href="${pageContext.request.contextPath}/downloadFile?fileNo=${file.fileNo}">다운로드</a>
                                <input type="checkbox" name="deleteFiles" value="${file.fileNo}"> 삭제
                            </div>
                        </c:forEach>
                    </div>
                </div>
                
                <!-- 새로운 파일 업로드를 위한 input -->
                <div class="form-group">
                    <label>새로운 첨부 파일</label>
                    <input type="file" id="multiFiles" name="multiFiles" multiple>
                </div>
                
                <!-- 하단 버튼 -->
                <div class="form-group" align="right">
                    <button type="submit" class="submit-button">수정하기</button>
                    <a href="${pageContext.request.contextPath}/cs/qnaOneUser?id=${qna.questionNo}"><button type="button" id="back-button">돌아가기</button></a>
                </div>
            </form>
        </div>
            
       <br><br>    
    </div>
    
</body>
</html>
