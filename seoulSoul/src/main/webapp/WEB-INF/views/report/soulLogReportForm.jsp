<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Insert title here</title>

</head>
<body>

<jsp:include page="../common/menubar.jsp"/>

<div class="content" style="margin: 30px">

	<form action="insertSoulLogReport" method="post" onsubmit="return validateForm()">
		
		<div class="insert-group" style="height: 50px; background: white">
			<span style="margin-left:20px">제목</span>
			<input type="text" id="title" name="title" style="margin-top: 10px; margin-left: 15px; width: 400px; height: 25px; background-color: #f0f0f0; border: 1px solid #c0c0c0;" maxlength="20" required>
			<span style="margin-left:50px">신고자</span>
			<input type="text" style="margin-top: 10px; margin-left: 15px; width: 150px; height: 25px; background-color: #f0f0f0; border: 1px solid #c0c0c0;" value="홍길동" disabled> <!-- 로그인된 유저nickname으로 바꿔야 함. 그냥 보여주기 용이라 컨트롤러로 바꿀 필요 없음 -->
			<input type="hidden" name="userNo" value="1"> <!-- 로그인 된 유저의 no로 수정해야 함. post라 굳이 컨트롤러에서 받는 걸로 안 해도 될지도..-->
			<span style="margin-left:50px">신고 대상 로그</span>
			<input type="text" style="margin-top: 10px; margin-left: 15px; width: 360px; height: 25px; background-color: #f0f0f0; border: 1px solid #c0c0c0;" value="${reportedSoulLog.title}" disabled>
			<input type="hidden" name="soulLogNo" value="${reportedSoulLog.soulLogNo}">
		</div>
		

		<br>
		
		<div class="insert-group" style="height: 170px; background: white">
			<br>
			<span style="margin-left:20px">신고 사유</span>
			<br>
			<textarea id="content" name="reason" style="margin-top: 10px; margin-left: 15px; width: 1230px; height: 100px; background-color: #f0f0f0; border: 1px solid #c0c0c0; resize: none;" maxlength="250" required></textarea>
		</div>
		
		<br>
		<br>
		
		<div align="center">
			<button class="btns" type="submit" style="width: 110px; height: 35px; background: #3982BC; color: white; border: 1px solid #c0c0c0;">신고글 작성</button>
		</div>
		
		
	</form>
	
</div> <!-- content div -->

</body>
</html>