<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
</head>
<body>
	<%
	// 세션에서 사용자 로그인 여부 확인
	if (session.getAttribute("user") == null) {
		// 로그인 페이지로 리다이렉트
		response.sendRedirect(request.getContextPath() + "/user/login");
		return;
	}
	%>

	<!-- 로그인된 경우 보여줄 내용 -->
	<jsp:forward page="WEB-INF/views/main.jsp" />

</body>
</html>