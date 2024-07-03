<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>서울소울 SEOUL SOUL</title>
<link rel="icon" href="${pageContext.request.contextPath}/resources/img/soul_icon_favicon.png"/>
</head>
<body>
	<sec:authorize access="isAuthenticated()">
		<jsp:forward page="WEB-INF/views/main.jsp" />
    </sec:authorize>
    <sec:authorize access="!isAuthenticated()">
		<jsp:forward page="WEB-INF/views/user/login.jsp" />
    </sec:authorize>
</body>
</html>