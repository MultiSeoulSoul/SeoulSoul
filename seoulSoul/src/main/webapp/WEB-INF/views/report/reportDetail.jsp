<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
<style>
    body {
        font-family: Arial, sans-serif;
    }

    .container {
        width: 80%;
        margin: 0 auto;
    }

    .header {
        text-align: center;
        padding: 20px 0;
    }

    .report-details {
        display: grid;
        grid-template-columns: repeat(2, 1fr);
        gap: 10px;
        background-color: #f9f9f9;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    .report-details div {
        background-color: #ffffff;
        padding: 15px;
        border-radius: 5px;
        box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
    }

    .report-details .full-width {
        grid-column: span 2;
    }

    .button-container {
        text-align: right;
        margin-top: 20px;
    }

    .button-container button {
        padding: 10px 20px;
        background-color: #333;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
</style>
<title>신고 내용 상세 보기 페이지</title>
</head>
<body>
    <jsp:include page="../common/menubar.jsp"/>
    <div class="container">
        <div class="header">
            <h2>신고 내용 상세 보기 페이지</h2>
        </div>
        <div class="report-details">
            <div><strong>신고 번호:</strong> <c:out value="${report.reportNo}" /></div>
            <div><strong>게시글 번호:</strong> <c:out value="${report.reportedSoulLog.soulLogNo}" /></div>
            <div><strong>게시글 제목:</strong> <c:out value="${report.reportedSoulLog.title}" /></div>
            <div><strong>게시글 작성자:</strong> <c:out value="${report.reporter.nickname}" /></div>
            <div><strong>신고 작성자:</strong> <c:out value="${report.reporter.nickname}" /></div>
            <div><strong>신고글 제목:</strong> <c:out value="${report.title}" /></div>
            <div class="full-width"><strong>신고 내용:</strong><br><c:out value="${report.reason}" /></div>
            <div class="full-width"><strong>신고에 대한 답변:</strong><br>
                <c:forEach items="${report.reportReply}" var="reply">
                    <p><c:out value="${reply.content}" /></p>
                    <hr>
                </c:forEach>
            </div>
        </div>
        <div class="button-container">
            <button onclick="history.back()">뒤로가기</button>
        </div>
    </div>
</body>
</html>