<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
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