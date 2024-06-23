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
	<jsp:include page="../common/menubar.jsp"/>
	
	<form action="achieveInsertForm" method = "post">
		<table border="1">
			<tr>
				<td class="t1">자치구 :</td>
				<td><input name="locationCode"></td>
			</tr>
			<tr>
				<td class="t1">카테고리 :</td>
				<td><input name="categoryCode"></td>
			</tr>
			<tr>
				<td class="t1">타이틀명 :</td>
				<td><input name="title"></td>
			</tr>
			<tr>
				<td class="t1">달성 필요 횟수 :</td>
				<td><input name="count"></td>
			</tr>
			<tr>
				<td colspan="2" class="t1">
					<button id="b2">업적 정보 전송</button>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>