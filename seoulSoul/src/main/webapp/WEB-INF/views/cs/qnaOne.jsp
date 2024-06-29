<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
.details-container {
    margin-top: 20px;
}
.details-container h2 {
    margin-bottom: 10px;
}
.details-container p {
    margin-bottom: 10px;
}
table {
    width: 100%;
    border-collapse: collapse;
}
th, td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: left;
}
th {
    background-color: #f2f2f2;
    width: 20%;
}
</style>

</head>
<body>

    <!-- 메뉴바 -->
    <jsp:include page="../common/menubar.jsp"/>

    <!-- div content -->
     <div class="content">
        <br><br>

        <!-- [관리자]로 로그인한 경우: 전체 문의글 상세 보기 -->
        <h1>문의글 상세 보기</h1>
        <p>문의글은 최대 일주일 이내로 답변 완료하도록 하며, 작성된 답변은 수정이 불가하니 유의하시길 바랍니다.</p>

        <div class="form-container">
                
            <div class="details-container">
                <table>
                    <tr>
                        <th>제목</th>
                        <td>${qna.title}</td>
                    </tr>
                    <tr>
                        <th>카테고리</th>
                        <td>${qna.categoryInfo.categoryName}</td>
                    </tr>
                    <tr>
                        <th>작성자</th>
                        <td>${qna.writerInfo.nickname}</td>
                    </tr>
                    <tr>
                        <th>작성일시</th>
                        <td><fmt:formatDate value="${qna.createdDate}" dateStyle="long" type="both" timeStyle="long"/></td>
                    </tr>
                    <c:if test="${qna.createdDate != qna.modifiedDate}">
                        <tr>
                            <th>수정일시</th>
                            <td><fmt:formatDate value="${qna.modifiedDate}" dateStyle="long" type="both" timeStyle="long"/></td>
                        </tr>
                    </c:if>
                    <tr>
                        <th>조회수</th>
                        <td>${qna.views}</td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td>${qna.content}</td>
                    </tr>
                    <c:if test="${not empty qna.files}">
                        <tr>
                            <th>첨부 파일</th>
                            <td>
                                <c:forEach var="file" items="${qna.files}">
					                    <li><a href="${pageContext.request.contextPath}/cs/download?fileNo=${file.fileNo}&questionNo=${qna.questionNo}">${file.originalFileName}</a></li>
					                </c:forEach>
                            </td>
                        </tr>
                    </c:if>
                </table>
            </div>
            
            <!-- 답변 -->
			<c:if test="${not empty qna.answers}">
                <div class="answer-section">
                    <h3>답변</h3>
                    <table class="answer-table">
                        <thead>
                            <tr>
                                <th>답변자</th>
                                <th>작성일시</th>
                                <th>내용</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="answer" items="${qna.answers}">
                                <tr>
                                    <td>${answer.writerInfo.nickname}</td>
                                    <td><fmt:formatDate value="${answer.createdDate}" dateStyle="long" type="both" timeStyle="long"/></td>
                                    <td>${answer.content}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>     
            <br>    
                
            <!-- [관리자]로 로그인한 경우: 답변 작성 폼 -->
			<c:if test="${empty qna.answers}">
				<div class="form-group">
					<form action="${pageContext.request.contextPath}/cs/answerInsert" method="post">
					<input type="hidden" name="qnaId" value="${qna.questionNo}">
					<textarea name="content" rows="5" placeholder="답변하기" required></textarea>
					<button type="submit" class="submit-button">답변 작성</button>
					</form>
				</div>
            </c:if>
            <br>
            
            
        </div>
                            
       <br><br>    
    </div>
    
</body>
</html>
