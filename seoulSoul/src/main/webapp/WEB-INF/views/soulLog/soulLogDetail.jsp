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

<script>
	function confirmDelete() {
	    return confirm("정말로 삭제하시겠습니까?");
	}
</script>

</head>
<body>

<jsp:include page="../common/menubar.jsp"/>

<div class="content" style="margin: 30px">
		
		<div style="height: 50px; background: white">
			<span style="margin-left:20px">제목</span>
			<input type="text" id="title" name="title" style="margin-top: 10px; margin-left: 15px; width: 400px; height: 25px; background-color: #f0f0f0; border: 1px solid #c0c0c0;" value=" ${soulLogDetail.title}" disabled>
			<span style="margin-left:50px">작성자</span>
			<input type="text" style="margin-top: 10px; margin-left: 15px; width: 150px; height: 25px; background-color: #f0f0f0; border: 1px solid #c0c0c0;" value=" ${soulLogDetail.writer.nickname}" disabled>
			<span style="margin-left:50px">자치구</span>
			<input name="locationCode" style="margin-left: 15px; width: 140px; height: 25px; background-color: #f0f0f0; border: 1px solid #c0c0c0;" value=" ${soulLogDetail.location.locationName}" disabled>
			<span style="margin-left:50px">카테고리</span>
			<input name="categoryCode" style="margin-left: 15px; width: 140px; height: 25px; background-color: #f0f0f0; border: 1px solid #c0c0c0;" value=" ${soulLogDetail.category.categoryName}" disabled>
		</div>
		
		<br>
		
		<div align="right">
			<a href="${pageContext.servletContext.contextPath}/report/soulLogReportForm?soulLogNo=${soulLogDetail.soulLogNo}">
				<button class="btns" style="width: 110px; height: 35px; margin-right: 10px; background: #3982BC; color: white; border: 1px solid #c0c0c0; cursor: pointer;">로그 신고</button>
			</a>
			<a href="soulLogUpdateForm?soulLogNo=${soulLogDetail.soulLogNo}">
				<button class="btns" style="width: 110px; height: 35px; margin-right: 10px; background: #3982BC; color: white; border: 1px solid #c0c0c0; cursor: pointer;">로그 수정</button>
			</a>
			<a href="deleteSoulLog?soulLogNo=${soulLogDetail.soulLogNo}" onclick="return confirmDelete();">
				<button class="btns" style="width: 110px; height: 35px; background: #C42A2A; color: white; border: 1px solid #c0c0c0; cursor: pointer;">로그 삭제</button>
			</a>
		</div>
		
		<br>
	
		<table>
			<tr>
				<c:forEach items="${soulLogDetail.files}" var="one">
					<td>
						<div style="width: 240px; height: 240px; margin-right: 14px; background: white">
							<img src="${pageContext.servletContext.contextPath}/resources/uploadFiles/${one.savedName}" width="240px" height="240px">
						</div>
					</td>
				</c:forEach>
			</tr>
		</table>
		
		<br>
		
		<span style="margin-right:20px; font-size:20px;">&#128153; &nbsp;${soulLogDetail.likesCount}</span>
	    <span style="margin-right:20px; font-size:20px;">&#128064; &nbsp;${soulLogDetail.views}</span>
	    <span style="font-size:20px;">&#128172; &nbsp;${soulLogDetail.repliesCount}</span>
	    
	    <br>
		<br>
		
		<div style="padding: 30px; background: white">
			${soulLogDetail.content}
		</div>
		
		<br>
		
		<div style="padding: 30px; background: white">
			<span>댓글</span>
			<br>
			<br>
			<form action="insertSoulLogReply" method="post">
				<table>
					<tr>
						<td style="width: 130px; height: 40px;">
							홍길동 <!-- 로그인된 유저 nickname으로 바꿔야 함. 역시 단순 보여주기 용. -->
						</td>
						<td>
							<input type="hidden" name="soulLogNo" value="${soulLogDetail.soulLogNo}">
							<input type="hidden" name="userNo" value="2"> <!-- 로그인된 유저no로 바꿔야 함. post라 컨트롤러에서 안 받아도 될지도.. -->
							<input id="content" name="content" style="width: 900px; height: 25px; background-color: #f0f0f0; border: 1px solid #c0c0c0;" placeholder=" 댓글 입력..." maxlength="100" required>
						</td>
						<td>
							<button type="submit" style="width: 110px; height: 35px; margin-left: 30px; background: #3982BC; color: white; border: 1px solid #c0c0c0; cursor: pointer;">댓글 작성</button>
						</td>
					</tr>
				</table>
			</form>
			<br>
			<hr>
			<table>
				<c:forEach items="${soulLogDetail.replies}" var="one">
					<tr>
						<td style="width: 130px; height: 40px; border-bottom: 1px solid #ccc;">
							<span style="width: 40px;">${one.writer.nickname}</span>
						</td>
						<td style="width: 1000px; border-bottom: 1px solid #ccc;">
							${one.content}
						</td>
						<td style="width: 100px; border-bottom: 1px solid #ccc;">
							<a href="updateSoulLogReply?replyNo=${one.replyNo}">
								<button class="btns" style="width: 40px; height: 25px; background: #B0B0B0; color: white; border: 1px solid #c0c0c0; cursor: pointer;">수정</button>
							</a>
							<a href="deleteSoulLogReply?replyNo=${one.replyNo}&soulLogNo=${one.soulLogNo}" onclick="return confirmDelete();">
								<button class="btns" style="width: 40px; height: 25px; background: #C42A2A; color: white; border: 1px solid #c0c0c0; cursor: pointer;">삭제</button>
							</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
		
		
		<br>
		<br>
		
		<div align="center">
			<a href="soulLogMain?page=1">
				<button class="btns" style="width: 110px; height: 35px; background: #3982BC; color: white; border: 1px solid #c0c0c0; cursor: pointer;">목록으로</button>
			</a>
		</div>
	
</div> <!-- content div -->

</body>
</html>