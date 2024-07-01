<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
<title>Insert title here</title>
</head>
<body>
	<header>
        <div class="logo">
            <a href="${pageContext.request.contextPath}"><img src="${pageContext.request.contextPath}/resources/img/SeoulSoul_logo.png" alt="SOUL Logo"></a>
        </div>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/soulLog/soulLogMain">소울로그</a></li>
                <li><a href="${pageContext.request.contextPath}/rec/recMain">추천</a></li>
                <li><a href="${pageContext.request.contextPath}/event/eventMain">이벤트</a></li>
                
                <!-- 택 1: -->
                <li><a href="${pageContext.request.contextPath}/cs/csMain">고객센터</a></li>
                
                
                <!-- 택 2: -->
              	<!-- [관리자]로 로그인한 경우: qnaAll.jsp -->
			    <%-- <sec:authorize access="hasRole('ROLE_ADMIN')">
			         <li><a href="${pageContext.request.contextPath}/cs/qnaAll">고객센터</a></li>
			    </sec:authorize> --%>
				    
			    <!-- [회원]으로 로그인한 경우: csMain.jsp -->
			    <%-- <sec:authorize access="hasRole('ROLE_USER')">
			    <!-- <li><a href="${pageContext.request.contextPath}/cs/csMain">고객센터</a></li> -->
				</sec:authorize> --%>
				    
            </ul>
        </nav>
        <div class="user-menu">
            <a href="${pageContext.request.contextPath}/user/userMain"><span>홍길동님</span></a> | <a href="#">로그아웃</a>
        </div>
    </header>
</body>
</html>