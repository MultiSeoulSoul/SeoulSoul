<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<style>
div>button, input[type=submit] {
	width: 200px;
	margin-top: 10px;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<div align="center" class="logo">
		<a href="${pageContext.request.contextPath}"><img src="${pageContext.request.contextPath}/resources/img/SeoulSoul_logo.png" alt="SOUL Logo"></a>
	</div>
	<form action="${pageContext.request.contextPath}/user/login" method="post" id="loginForm">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<table align="center">
			<tr>
				<td>
					<div class="input-group mb-3 input-group-lg">
						<div class="input-group-prepend">
							<span class="input-group-text" style="width: 120px">아이디</span>
						</div>
						<input type="text" class="form-control" name="userId">
					</div>
				</td>
				<td rowspan="2"></td>
			</tr>
			<tr>
				<td>
					<div class="input-group mb-3 input-group-lg">
						<div class="input-group-prepend">
							<span class="input-group-text" style="width: 120px">비밀번호</span>
						</div>
						<input type="password" class="form-control" name="userPw">
					</div>
				</td>
			</tr>
		</table>
		<div align="center">
			<input type="submit" value="로그인" class="btn btn-info"><br>
		</div>
	</form>
	<div align="center">
		<button class="btn btn-success" onclick="window.location.href='${pageContext.request.contextPath}/join'">회원 가입 하기</button>
		<br>
		<button class="btn btn-secondary">아이디 / 비밀번호 찾기</button>
	</div>

	<!-- <script>
		$(function() {
			$("input[name=userId]").focus();
			$("input[type=submit]").click(function() {
				$("#loginForm").submit();
			});
		})
	</script> -->
</body>
</html>
