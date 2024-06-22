<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                
                <li><a href="${pageContext.request.contextPath}/cs/csMain">고객센터</a></li>
            </ul>
        </nav>
        <div class="user-menu">
            <a href="${pageContext.request.contextPath}/user/userMain"><span>홍길동님</span></a> | <a href="#">로그아웃</a>
        </div>
    </header>
</body>
</html>