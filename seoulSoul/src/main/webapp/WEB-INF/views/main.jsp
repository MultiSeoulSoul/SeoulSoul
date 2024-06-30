<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
<title>SeoulSoul</title>
</head>
<body>
	<sec:authentication var="user" property="principal"/>
	<c:if test="user.blacklistStatus == 'Y'}">
		<jsp:forward page="/WEB-INF/views/common/banned.jsp" />
	</c:if>

	<jsp:include page="common/menubar.jsp" />
	<sec:authentication property="principal"/>
</body>
</html>