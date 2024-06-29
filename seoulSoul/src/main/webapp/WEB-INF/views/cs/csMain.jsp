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
.content {
    width: 80%;
    margin: 0 auto;
}
.content div {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin: 20px;
    text-align: center;
}
.icon-container {
    width: 45%;
    
}
.icon-container img {
    width: 200px;
    height: 200px;
    display: block;
    margin: 0 auto 10px;
}
.icon-container a {
    display: block;
    margin: 10px 0;
    text-decoration: none;
    color: black;
    font-weight: bold;
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
            <h1>고객센터</h1>   
            <p>고객센터에 오신 것을 환영합니다. 서울소울 고객센터는 여러분의 소중한 의견을 듣고, 신속하게 도움을 드리기 위해 항상 노력하고 있습니다.</p>
            
            <div>
                <h2>QNA</h2>
                <h2>FAQ</h2>
            </div>
            
            <div>
                <div class="icon-container">
                    <img src="${pageContext.request.contextPath}/resources/img/csQnaIcon.png" alt="Q&A Icon">
                </div>
                <div class="icon-container">
                    <img src="${pageContext.request.contextPath}/resources/img/csChatbotIcon.png" alt="Chatbot Icon">             
                </div>
            </div>
            
            <div>
                <div class="icon-container">
                    <a href="${pageContext.request.contextPath}/cs/qnaInsert">문의글 작성하기</a>
                    <a href="${pageContext.request.contextPath}/cs/qnaAllUser">내가 쓴 문의글 보기</a>
                </div>
                <div class="icon-container">
                    <a href="" onclick="openChatbot()">챗봇</a>
                </div>
            </div>
                
            <br><br>    
        </div>
        
        <!-- 챗봇 -->
        <script type="text/javascript">
            function openChatbot() {
                window.open("${pageContext.request.contextPath}/chatbot/chatbot", "chatbotWindow", "width=400,height=600");
            }
        </script>
    <%-- </sec:authorize> --%>
    
</body>
</html>