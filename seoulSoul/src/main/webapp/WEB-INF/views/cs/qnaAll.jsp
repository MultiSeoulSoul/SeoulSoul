<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    String pageParam = request.getParameter("page");
    if (pageParam == null) {
        response.sendRedirect("qnaAll?page=1");
        return;
    }
%>
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
.table-container {
    margin-top: 20px;
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
}
th.title {
    width: 40%;
}
</style>

</head>
<body>

	<!-- 메뉴바 -->
	<jsp:include page="../common/menubar.jsp"/>

	<!-- div content -->
     <div class="content">
     	<br><br>
        
        <!-- [관리자]로 로그인한 경우: 전체 문의글 목록 -->
        <h1>문의글 전체 목록 보기</h1>
        <p>회원들이 작성한 문의글 전체 목록을 볼 수 있습니다. 최대 일주일 이내로 답변 완료하도록 하며, 작성된 답변은 수정이 불가하니 유의하시길 바랍니다.</p>
        
     	<div class="form-container">
     	
	     	<div class="table-container">
				<c:choose>
		            <c:when test="${empty questions}">
		                <p>작성된 글이 없습니다.</p>
		            </c:when>
		            <c:otherwise>
		                <table>
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
		                                <td class="title"><a href="qnaOne?id=${qna.questionNo}">${qna.title}</a></td>
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
				
			    <!-- 하단 버튼 -->
                <div class="form-group" align ="right">
                
                    <a href="csMain"><button type="button" id="back-button">돌아가기</button></a>
                </div>	
			</div>
			
			<!-- 페이징 처리-->
			<div class="pagination">
				<c:forEach var="i" begin="1" end="${totalPages}">
                    <a href="qnaAll?page=${i}" class="${pageParam == i ? 'active' : ''}">${i}</a>
                </c:forEach>
			</div>
			
		</div>        
       <br><br>	
   	</div>
   	
</body>
</html>
