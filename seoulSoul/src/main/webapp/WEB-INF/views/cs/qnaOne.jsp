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
h1 {
    padding-bottom: 10px;
}
.content {
    width: 80%;
    margin: 0 auto;
}
.table-container {
    margin-top: 20px;
}
table.detail-table, table.answer-table {
    width: 100%;
    border-collapse: collapse;
}
.detail-table th, .detail-table td, .answer-table th, .answer-table td {
    border: 1px solid #ddd;
    padding: 10px;
    text-align: left;
}
.detail-table th, .answer-table th {
    background-color: #f2f2f2;
}
.detail-table td, .answer-table td {
    background-color: white;
}
.form-group textarea {
    width: 100%;
    padding: 10px;
    font-size: 14px;
    resize: vertical;
}
</style>

</head>
<body>

    <!-- 메뉴바 -->
    <jsp:include page="../common/menubar.jsp"/>

    <!-- div content -->
    <div class="content">
        <br><br>

        <!-- [관리자]로 로그인한 경우: 문의글 상세 보기 -->
        <h1>문의글 상세 보기</h1>
        <p>문의글은 최대 일주일 이내로 답변 완료하도록 하며, 작성된 답변은 수정이 불가하니 유의하시길 바랍니다.</p>
        <br>
        
        <div class="table-container">
            <table class="detail-table">
                
                <tr>
                	<th colspan="8">문의글</th>
                </tr>
                
                <tr>
                	<th>카테고리</th>
                    <td>${qna.categoryInfo.categoryName}</td>
                    
                    <th>작성자</th>
                    <td>${qna.writerInfo.nickname}</td>
                    <th>조회수</th>
                    <td>${qna.views}</td>
                </tr>
                
                <tr>
                	<th>제목</th>
                    <td colspan="5">${qna.title}</td>
                </tr>
                
                <tr><!-- 내용 -->
                    <td colspan="8"><pre>${qna.content}</pre></td>
                </tr>
                
                <c:if test="${not empty qna.files}">
                    <tr>
                        <th>첨부 파일</th>
                        <td colspan="7">
                            <ul>
                                <c:forEach var="file" items="${qna.files}">
                                    <li><a href="${pageContext.request.contextPath}/cs/download?fileNo=${file.fileNo}&questionNo=${qna.questionNo}">${file.originalFileName}</a></li>
                                </c:forEach>
                            </ul>
                        </td>
                    </tr>
                </c:if>                
                <tr>
                    <th>작성일시</th>
                    <td colspan="7"><fmt:formatDate value="${qna.createdDate}" dateStyle="long" type="both" timeStyle="long"/></td>
                </tr>
                <c:if test="${qna.createdDate != qna.modifiedDate}">
	                <tr>
	                	<th>수정일시</th>
	                    <td colspan="7"><fmt:formatDate value="${qna.modifiedDate}" dateStyle="long" type="both" timeStyle="long"/></td>
	                </tr>
                </c:if>
            </table>
        </div>
        <br>

        <!-- 답변 -->
        <c:if test="${not empty qna.answers}">
        	<table class="answer-table">
			<c:forEach var="answer" items="${qna.answers}">
				<tr>
                	<th colspan="4" bgcolor="white">답변</th>
                </tr>
                <tr>
                	<td colspan="4" bgcolor="white"><pre>${answer.content}</pre></td>
                </tr>
                <tr>
					<th>작성자</th>
                    <td>${answer.writerInfo.nickname}</td>
                    <th>작성일시</th>
                    <td><fmt:formatDate value="${answer.createdDate}" dateStyle="long" type="both" timeStyle="long"/></td>
                </tr>
			</c:forEach>
			</table>
			<br>
			
			<div class="form-group" align ="right">
	            <button type="button" class="submit-button" onclick="location.href='${pageContext.request.contextPath}/cs/qnaAll'">돌아가기</button>
	        </div>
        </c:if>

        <!-- [관리자]로 로그인한 경우: 답변 작성 폼 -->
		<c:if test="${empty qna.answers}">
		    <div class="form-group">
		    <h3>답변 작성하기</h3>
		        <form action="${pageContext.request.contextPath}/cs/answerInsert" method="post" style="text-align: right;">
		            <input type="hidden" name="qnaId" value="${qna.questionNo}">
		            <textarea name="content" rows="5" placeholder="" required></textarea>
		            <br>
		            <button type="submit" class="submit-button">작성하기</button>
		            <button type="button" class="submit-button" onclick="location.href='${pageContext.request.contextPath}/cs/qnaAll'">돌아가기</button>
		        </form>
		    </div>
		</c:if>
		
    </div>
    <br><br>

</body>
</html>
