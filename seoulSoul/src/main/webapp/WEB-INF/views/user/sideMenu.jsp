<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.side-menu {
        width: 150px;
        background-color: #e0f7fa;
        border-right: 1px solid #ddd;
        padding: 20px;
    }
    .side-menu ul {
        list-style: none;
        padding: 0;
        margin: 0;
    }
    .side-menu ul li {
        margin: 10px 0;
    }
    .side-menu ul li a {
        text-decoration: none;
        color: #333;
        font-weight: bold;
        display: block;
        padding: 10px;
        border-radius: 4px;
        transition: background-color 0.3s;
    }
    .side-menu ul li a:hover {
        background-color: #b2ebf2;
    }
</style>
</head>
<body>
	<div class="side-menu">
	    <ul>
	        <li><a href="${pageContext.request.contextPath}/user/userUpdateForm">회원정보 수정</a></li>
	        <li><a href="${pageContext.request.contextPath}/user/userSoulDetail">나의 소울</a></li>
	        <li><a href="${pageContext.request.contextPath}/user/userBoardDetail">나의 활동</a></li>	        
	        <li><a href="${pageContext.request.contextPath}/user/userAchievementDetail">나의 업적</a></li>
	        <li><a href="${pageContext.request.contextPath}/user/userDelete" style="color: red;">회원 탈퇴</a></li>
	    </ul>
	</div>
</body>
</html>