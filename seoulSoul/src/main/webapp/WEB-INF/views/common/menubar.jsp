<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
<style>
	.profile-img {
	    width: 50px;
	    height: 50px;
	    border-radius: 50%;
	    border: 1px solid #ddd;
	    margin-right: 10px;
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<header>
        <div class="logo">
            <a href="${pageContext.request.contextPath}/main"><img src="${pageContext.request.contextPath}/resources/img/SeoulSoul_logo.png" alt="SOUL Logo"></a>
        </div>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/soulLog/soulLogMain?page=1">소울로그</a></li>
                <li><a href="${pageContext.request.contextPath}/rec/recMain">추천</a></li>
                <li><a href="${pageContext.request.contextPath}/event/eventMain">이벤트</a></li>
                <li><a href="${pageContext.request.contextPath}/cs/csMain">고객센터</a></li>
                
                <sec:authorize access="hasRole('ADMIN')">
                    <li><a href="${pageContext.request.contextPath}/admin/adminMain">관리자</a></li>
                </sec:authorize>
            </ul>
        </nav>
        <div class="user-menu">
		    <a href="${pageContext.request.contextPath}/user/userMain">
		        <img src="${pageContext.request.contextPath}/resources/uploadFiles/<sec:authentication property="principal.profilePicName"/>" alt="Profile Image" class="profile-img" id="profile-img">
		        <sec:authentication property="principal.nickname"/> 님
		    </a> | 
		    <a href="#" onclick="document.getElementById('logoutForm').submit();">로그아웃</a>
		    <form id="logoutForm" method="post" action="${pageContext.request.contextPath}/user/logout" style="display:none;">
		        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		    </form>
		</div>
    </header>
</body>
</html>