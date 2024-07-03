<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    String pageParam = request.getParameter("page");
    if (pageParam == null) {
        response.sendRedirect("qnaAllUser?page=1");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="${pageContext.request.contextPath}/resources/img/soul_icon_favicon.png"/>
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
table.list-table {
	width: 100%;
	border-spacing: 0;
}
.list-table th {
	background: #eee;
	border-top: 2px solid gray;
    padding: 10px;
}
.list-table td {
	background: white;
	border-bottom: 1px solid #ccc;
    padding: 10px;
    text-align: center;
}
.list-table td:nth-child(3) {
text-align: left;
}
</style>

</head>
<body>

    <!-- 메뉴바 -->
    <jsp:include page="../common/menubar.jsp"/>

    <!-- div content -->
     <div class="content">
     <br><br>
        
        <!-- [회원]으로 로그인한 경우: 내가 쓴 문의글만 보이는 목록 -->
        <h1>내가 쓴 문의글 보기</h1>
        <p>답변 완료된 문의글은 수정이 불가하니 유의하시길 바랍니다.</p>
        <br>
        
	    <div class="table-container">
	    <c:choose>
		    <c:when test="${empty questions}">
		    <p>작성된 글이 없습니다.</p>
		    </c:when>
		<c:otherwise>
			<table class="list-table">
			<thead>
				<tr>
	            	<th class="index">번호</th>
	            	<th class="category">카테고리</th>
	            	<th class="title">제목</th>
	            	<th class="writer">작성자</th>
	                <th class="views">조회수</th>
	                <th class="created_date">작성일</th>
	                <th class="is_answered">답변여부</th>
	            </tr>
	        </thead>
	        <tbody>
	        <c:forEach items="${questions}" var="qna" varStatus="status">
	        	<tr>
	        		<td class="index">${fn:length(questions) - status.index}</td>
	        		<td class="category">${qna.categoryInfo.categoryName}</td>
	                <td class="title"><a href="qnaOneUser?id=${qna.questionNo}">${qna.title}</a></td>
	                <td class="writer">${qna.writerInfo.nickname}</td>
	                <td class="views">${qna.views}</td>
	                <td class="created_date">
	                	<fmt:formatDate value="${qna.createdDate}" pattern="yyyy-MM-dd" />
	                </td>
	                <td class="is_answered">${qna.isAnswered == 'Y' ? "답변 완료" : "답변 대기"}</td>
	            </tr>
	        </c:forEach>
	        </tbody>
	        </table>
		</c:otherwise>
	    </c:choose>
	    <br>
	    
		<!-- 페이징 처리-->
		<div class="pagination">
			<c:forEach var="i" begin="1" end="${totalPages}">
				<a href="qnaAll?page=${i}" class="${pageParam == i ? 'active' : ''}">${i}</a>
			</c:forEach>
		</div>				

        <!-- 하단 버튼 -->
        <div class="form-group" align ="right">
        	<a href="qnaInsert"><button type="button" id="create-button">작성하기</button></a>
        	<a href="csMain"><button type="button" id="back-button">돌아가기</button></a>
        </div>  
        
		</div>
	
	</div>        
   	
</body>
</html>
