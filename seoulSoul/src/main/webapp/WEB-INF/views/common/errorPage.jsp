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

<jsp:include page="menubar.jsp"/>

<br>
<br>
<br>

<div id="errorMessage" align="center">

	<img src="${pageContext.request.contextPath}/resources/img/ms_sad-but-relieved-face.png" style="width: 200px">
	<br>
	<br>
	<h1>이런, 문제가 발생했어요.</h1>
	<p>${ msg }</p>
	
	<a href="${pageContext.request.contextPath}">
		<span>메인으로 돌아가기</span>
	</a>
</div>

</body>
</html>