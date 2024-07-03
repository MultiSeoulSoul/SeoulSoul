<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="${pageContext.request.contextPath}/resources/img/soul_icon_favicon.png"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>서울소울 SEOUL SOUL</title>

</head>
<body>

<jsp:include page="../common/menubar.jsp"/>

<div class="content" style="margin: 30px">

	<form action="insertSoulLogReport" method="post" onsubmit="return validateForm()">
		
		<div class="insert-group" style="height: 50px; background: white">
			<span style="margin-left:20px">제목</span>
			<input type="text" id="title" name="title" style="margin-top: 10px; margin-left: 15px; width: 400px; height: 25px; background-color: #f0f0f0; border: 1px solid #c0c0c0;" maxlength="20" required>
			<span style="margin-left:50px">신고자</span>
			<input type="text" style="margin-top: 10px; margin-left: 15px; width: 150px; height: 25px; background-color: #f0f0f0; border: 1px solid #c0c0c0;" value="<sec:authentication property="principal.nickname"/>" disabled>
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