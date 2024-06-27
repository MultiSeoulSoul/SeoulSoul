<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.side-menu {
        width: 100px;
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
	        <li><a href="#">소울 보기</a></li>
	        <li><a href="#">업적 보기</a></li>
	        <li><a href="#">소울로그 보기</a></li>
	        <li><a href="#">댓글 보기</a></li>
	        <li><a href="#">좋아요 보기</a></li>
	        <li><a href="#">문의내역 보기</a></li>
	        <li><a href="#">회원 탈퇴</a></li>
	    </ul>
	</div>
</body>
</html>