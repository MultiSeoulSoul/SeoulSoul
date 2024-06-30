<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
table.main-table {
    width: 100%;
    border-spacing: 0;
    text-align: center;
}
.main-table img {
    width: 290px;
}
a:link {
	color: black;
	text-decoration: none;
}
a:visited  {
	color: black;
	text-decoration: none;
}
a:hover { 
	color: black;
	text-decoration: underline;
}
</style>

</head>
<body>

	<!-- [관리자]로 로그인한 경우: qnaAll.jsp로 포워드 -->
    <%-- <sec:authorize access="hasRole('ROLE_ADMIN')">
        <jsp:forward page="/cs/qnaAll.jsp"/>
    </sec:authorize> --%>
    
    <!-- [회원]으로 로그인한 경우: 고객센터 메인 페이지 -->
    <%-- <sec:authorize access="hasRole('ROLE_USER')"> --%>
        <!-- 메뉴바 -->
        <jsp:include page="../common/menubar.jsp"/>
        
        <!-- div content -->
        <div class="content">
        <br><br>
            
            <!-- 고객센터 설명글 -->
            <h1>서울소울 고객센터</h1>   
            <p>고객센터에 오신 것을 환영합니다!</p>
            <p>서울소울 고객센터는 여러분의 소중한 의견을 듣고, 신속하게 도움을 드리기 위해 항상 노력하고 있습니다.</p>
            <p>자주 묻는 질문(FAQ)은 챗봇이 안내해드릴 수 있으며, 그 외의 문의사항은 문의글 작성하기 페이지에서 질문해주세요.</p>
            <br>
            
            <div class="table-container">
				<table class="main-table">
				    <tr>
				        <td colspan="2"><h2>QNA</h2></td>
				        <td><h2>FAQ</h2></td>
				    </tr>
				    
				    <tr>
				        <td colspan="2">
				            <img src="${pageContext.request.contextPath}/resources/img/csQnaIcon.png" alt="Q&A Icon">
				        </td>
				        <td>
				            <img src="${pageContext.request.contextPath}/resources/img/csChatbotIcon.png" alt="Chatbot Icon">
				        </td>
				    </tr>
				    
				    <tr>
				        <td>
				            <h3><a href="${pageContext.request.contextPath}/cs/qnaInsert">문의글 작성하기</a></h3>
				        </td>
				        <td>
				            <h3><a href="${pageContext.request.contextPath}/cs/qnaAllUser">내가 쓴 문의글 보기</a></h3>
				        </td>
				        <td>
						    <h3><a href="" onclick="openChatbot()">챗봇</a></h3>
						</td>
				    </tr>
				</table>
			</div>
   
            <br><br>  
            <br><br>
            <br><br>
            <br><br>
            <br><br>
            <!-- 아이콘 이미지 출처: 푸터로 옮기기? -->
		    <div><a href="https://www.flaticon.com/kr/free-icons/-" title="문의 양식 아이콘">문의 양식 아이콘 제작자: rukanicon - Flaticon</a></div>
		    <div><a href="https://www.flaticon.com/kr/free-icons/" title="챗봇 아이콘">챗봇 아이콘 제작자: rukanicon - Flaticon</a></div> 
        </div>
        
        <!-- 챗봇 -->
        <script type="text/javascript">
            function openChatbot() {
                window.open("${pageContext.request.contextPath}/chatbot/chatbot", "chatbotWindow", "width=450,height=800");
            }
        </script>
    <%-- </sec:authorize> --%>
    
</body>
</html>
