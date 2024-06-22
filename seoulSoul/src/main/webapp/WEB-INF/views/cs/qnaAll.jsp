<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
     	
	     	<!-- 문의글 목록 -->
	     	<div class="table-container">
				<table>
					<thead>
		   	         	<tr>
		       	        	<th class="no">번호</th>
		       	        	<th class="category">카테고리</th>
		           	    	<th class="title">제목</th>
		               		<th class="writer">작성자</th>
		               		<th class="created_date">작성일</th>
		               		<th class="is_answered">답변여부</th>
		            	</tr>
	         	 	</thead>
	         	 	
			   		<tbody>
				      	<c:forEach items="${list}" var="one">
				      	<tr>
				        	<td class="no">${one.rownum}</td> 
				        	<td class="category">${one.category}</td> 
						   	<td class="title"><a href="qnaOne?id=${one.no}">${one.title}</a></td>
				        	<td class="writer">${one.writer}</td>
				        	<td class="created_date">${one.created_date}</td>
				        	<td class="is_answered">${one.is_answered ? "답변 완료" : "답변 대기"}</td>
				      	</tr>
				      	</c:forEach>
			   		</tbody>
				</table>
				<br>
				
			    <!-- 하단 버튼 -->
                <div class="form-group" align ="right">
                	<a href="qnaInsert"><button type="button" id="create-button">작성하기</button></a>
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
