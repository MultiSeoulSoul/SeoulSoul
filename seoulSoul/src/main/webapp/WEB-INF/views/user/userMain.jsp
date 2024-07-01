<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Main</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.js"></script>
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<style>
	div>button, input {
		width: 200px;
		align-items: right;
	}

    body {
        display: flex;
        flex-direction: column;
        height: 100vh;
        margin: 0;
    }
    header {
        flex-shrink: 0;
    }
    main {
        display: flex;
        flex: 1;
        overflow: hidden;
    }
    .content {
        display: flex;
        flex: 1;
        overflow: hidden;
    }
    .main-content {
        flex: 1;
        padding: 0px 10px 0px 10px;
        overflow: auto;
    }
</style>

</head>
<body>
<jsp:include page="/WEB-INF/views/common/menubar.jsp" />
<main>
    <jsp:include page="/WEB-INF/views/user/sideMenu.jsp" />
    <div class="content">
        <div class="main-content">
			<jsp:include page="/WEB-INF/views/user/userProfile.jsp" />
            <jsp:include page="/WEB-INF/views/user/userSoul.jsp" />
			<jsp:include page="/WEB-INF/views/user/userBoard.jsp" />
            <jsp:include page="/WEB-INF/views/user/userAchievement.jsp" />
        </div>
    </div>
</main>
</body>
</html>
