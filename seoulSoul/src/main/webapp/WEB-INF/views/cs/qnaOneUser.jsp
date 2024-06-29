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
        
        <!-- [회원]으로 로그인한 경우: 내가 쓴 문의글 상세 페이지 -->
        <h1>내가 쓴 문의글 보기</h1>
        <p>답변 완료된 문의글은 수정이 불가하니 유의하시길 바랍니다.</p>
        <p>서울소울 고객센터는 여러분의 소중한 의견을 듣고, 신속하게 도움을 드리기 위해 항상 노력하고 있습니다.</p>
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
                	<th colspan="4">답변</th>
                </tr>
                <tr>
                	<td colspan="4"><pre>${answer.content}</pre></td>
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
        </c:if>

        <!-- [회원]으로 로그인한 경우: 하단 버튼 -->
        <div class="form-group" align ="right">  
            <c:if test="${empty qna.answers}">
            	<a href="qnaUpdate?id=${qna.questionNo}"><button type="button" id="edit-button">수정하기</button></a>
            </c:if>           
        	<a href="qnaDelete?id=${qna.questionNo}" onclick="return confirm('정말 삭제하시겠습니까?');"><button type="button" id="delete-button">삭제하기</button></a>
            <a href="qnaAllUser"><button type="button" id="back-button">돌아가기</button></a>           
        </div>

                            
    </div>
    <br><br>
    
</body>
</html>
