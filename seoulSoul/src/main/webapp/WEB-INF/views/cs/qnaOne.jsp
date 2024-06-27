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
        
        <!-- 내가 쓴 문의글 보기 페이지 -->   
        <h1>내가 쓴 문의글 보기</h1>
        <p>내가 쓴 문의글 설명글</p>

        <div class="form-container">
                
            <!-- 문의글 상세 페이지 -->
            <div class="details-container">
                <table>
                    <tr>
                        <th>제목</th>
                        <td>${qna.title}</td>
                    </tr>
                    <tr>
                        <th>카테고리</th>
                        <td>${qna.categoryCode}</td>
                    </tr>
                    <tr>
                        <th>작성자</th>
                        <td>${qna.writer}</td>
                    </tr>
                    <tr>
                        <th>작성일</th>
                        <td>${qna.createdDate}</td>
                    </tr>
                    <c:if test="${qna.createdDate != qna.modifiedDate}">
                        <tr>
                            <th>수정일</th>
                            <td>${qna.modifiedDate}</td>
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
             <div class="answer-section">
                <h3>답변</h3>
                <c:forEach var="answer" items="${qna.answers}">
                    <div class="answer">
                        <p><strong>${answer.writer}</strong> (${answer.createdDate})</p>
                        <p>${answer.content}</p>
                    </div>
                </c:forEach>
                
                <!-- (관리자) 답변 작성 폼 -->
                <div class="form-group">
                    <form action="answerInsert" method="post">
                        <input type="hidden" name="qnaId" value="${qna.questionNo}">
                        <textarea name="content" rows="5" placeholder="답변하기 " required></textarea>
                        
                    </form>
                </div>
                
            </div>
            
            <br>
            <div class="form-group" align ="right">
                <!-- (회원) 하단 버튼 -->
                <a href="qnaDelete?id=${qna.questionNo}" onclick="return confirm('정말 삭제하시겠습니까?');"><button type="button" id="delete-button">삭제하기</button></a>
                <a href="qnaUpdate?id=${qna.questionNo}"><button type="button" id="edit-button">수정하기</button></a>
                
                <!-- (관리자) 답변 작성 버튼 -->
                <button type="submit" class="submit-button">답변 작성</button>
                
                <!-- (공통) 돌아가기 버튼 -->
                <a href="qnaAll"><button type="button" id="back-button">돌아가기</button></a>           
            </div>
            
        </div>
                            
       <br><br>    
    </div>
    
</body>
</html>
