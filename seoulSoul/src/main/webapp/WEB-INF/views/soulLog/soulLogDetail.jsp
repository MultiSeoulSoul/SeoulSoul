<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %> 
<%@ page import="com.multi.seoulsoul.soulLog.model.dto.SoulLogDTO" %>
<%@ page import="com.multi.seoulsoul.soulLog.model.dto.RepliesDTO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

<script>
function updateReply(num) {
    document.getElementById('replyContent' + num).style.display = 'none';
    document.getElementById('replyUpdateBtn' + num).style.display = 'none';
    document.getElementById('replyContentInput' + num).style.display = 'block';
    document.getElementById('replyUpdateFormBtn' + num).style.display = 'block';
}
</script>

<script>
function activatingLike() {
	
    document.getElementById('inactiveLike').style.display = 'none';
    document.getElementById('activeLike').style.display = 'inline';
    
    var userNo = 2; // 로그인된 유저의 No로 수정 필요!
    var soulLogNo = parseInt("${soulLogDetail.soulLogNo}", 10);
    var likesCount = parseInt("${soulLogDetail.likesCount}", 10);
    
    $.ajax({
    	url: "${pageContext.servletContext.contextPath}/soulLog/insertLike", 
    	type: 'POST', 
    	data: {
    		userNo: userNo,
    		soulLogNo: soulLogNo
    	},
    	success: function(response) {
    		var spanElement = document.getElementById('likesCount');
    		var curLikeCount = parseInt(spanElement.textContent, 10);
    		spanElement.textContent = curLikeCount + 1;
    	},
    	error: function(error) {
    		
    	}
    });
}

function inactivatingLike() {
	document.getElementById('inactiveLike').style.display = 'inline';
    document.getElementById('activeLike').style.display = 'none';
    
    var userNo = 2; // var userNo = <sec:authentication property="principal.userNo"/>;
    var soulLogNo = parseInt("${soulLogDetail.soulLogNo}", 10);
    var likesCount = parseInt("${soulLogDetail.likesCount}", 10);
    
    $.ajax({
    	url: "${pageContext.servletContext.contextPath}/soulLog/deleteLike", 
    	type: 'POST', 
    	data: {
    		userNo: userNo,
    		soulLogNo: soulLogNo
    	},
    	success: function(response) {
    		var spanElement = document.getElementById('likesCount');
    		var curLikeCount = parseInt(spanElement.textContent, 10);
    		spanElement.textContent = curLikeCount - 1;
    	},
    	error: function(error) {
    		
    	}
    });
    
}

</script>

</head>
<body>

<jsp:include page="../common/menubar.jsp"/>

<div class="content" style="margin: 30px;">
		
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
		
		<div style="background: white; padding: 20px;">
			<table>
				<tr>
					<c:forEach items="${soulLogDetail.files}" var="one">
						<td>
							<div style="width: 230px; height: 225px; margin-right: 17px; background: white">
								<img src="${pageContext.servletContext.contextPath}/resources/uploadFiles/${one.savedName}" width="225px" height="225px">
							</div>
						</td>
					</c:forEach>
				</tr>
			</table>
		</div>
		
		<br>
		
		<span id="likeSpan" style="margin-right:7px">
		
			<svg id="activeLike" xmlns="http://www.w3.org/2000/svg" width="22" viewBox="0 0 512 512" fill="#1172F8" onclick="inactivatingLike()"
			style="cursor: pointer; 
				
				<c:if test="${soulLogDetail.viewerLike == 0}">display: none;</c:if>
				<c:if test="${soulLogDetail.viewerLike == 1}">display: inline;</c:if>
			
			">
            	<!--!Font Awesome Free 6.5.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.-->
            	<path d="M47.6 300.4L228.3 469.1c7.5 7 17.4 10.9 27.7 10.9s20.2-3.9 27.7-10.9L464.4 300.4c30.4-28.3 47.6-68 47.6-109.5v-5.8c0-69.9-50.5-129.5-119.4-141C347 36.5 300.6 51.4 268 84L256 96 244 84c-32.6-32.6-79-47.5-124.6-39.9C50.5 55.6 0 115.2 0 185.1v5.8c0 41.5 17.2 81.2 47.6 109.5z"/>
        	</svg>
        	
        	<svg id="inactiveLike" xmlns="http://www.w3.org/2000/svg" width="22" viewBox="0 0 512 512" fill="#C0C0C0" onclick="activatingLike()"
        	style="cursor: pointer; 
        	
        		<c:if test="${soulLogDetail.viewerLike == 0}">display: inline</c:if>
				<c:if test="${soulLogDetail.viewerLike == 1}">display: none;</c:if>
        	
        	">
            	<!--!Font Awesome Free 6.5.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.-->
            	<path d="M47.6 300.4L228.3 469.1c7.5 7 17.4 10.9 27.7 10.9s20.2-3.9 27.7-10.9L464.4 300.4c30.4-28.3 47.6-68 47.6-109.5v-5.8c0-69.9-50.5-129.5-119.4-141C347 36.5 300.6 51.4 268 84L256 96 244 84c-32.6-32.6-79-47.5-124.6-39.9C50.5 55.6 0 115.2 0 185.1v5.8c0 41.5 17.2 81.2 47.6 109.5z"/>
        	</svg>
        	
        </span>
		
		<span id="likesCount" style="margin-right:40px; font-size:24px;">${soulLogDetail.likesCount}</span>
	    <span style="margin-right:40px; font-size:24px;">&#128064; &nbsp;${soulLogDetail.views}</span>
	    <span style="font-size:24px;">&#128172; &nbsp;${soulLogDetail.repliesCount}</span>
	    
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
							홍길동 <!-- <sec:authentication property="principal.nickname"/> -->
						</td>
						<td>
							<input type="hidden" name="soulLogNo" value="${soulLogDetail.soulLogNo}">
							<input type="hidden" name="userNo" value="2"> <!-- 로그인된 유저no로 바꿔야 함. post라 컨트롤러에서 안 받아도 될지도.. -->
							<input id="content" name="content" style="width: 917px; height: 25px; background-color: #f0f0f0; border: 1px solid #c0c0c0;" placeholder=" 댓글 입력..." maxlength="100" required>
						</td>
						<td>
							<button type="submit" style="width: 110px; height: 35px; margin-left: 30px; background: #3982BC; color: white; border: 1px solid #c0c0c0; cursor: pointer;">댓글 작성</button>
						</td>
					</tr>
				</table>
			</form>
			<br>
			<hr>
			
			
			<%
    		SoulLogDTO soulLogDetail = (SoulLogDTO) request.getAttribute("soulLogDetail");
			%>
				
			<%
			List<RepliesDTO> replies = soulLogDetail.getReplies();
					
			for(int i = 0; i < replies.size(); i++) {
				int replyNo = replies.get(i).getReplyNo();
				int soulLogNo = replies.get(i).getSoulLogNo();
				String replyWriter = replies.get(i).getWriter().getNickname();
				String replyContent = replies.get(i).getContent();
			%>
					
			<form action="updateSoulLogReply" method="post">
				<table>
					<tr>
						<td style="width: 130px; height: 40px; border-bottom: 1px solid #ccc;">
							<span style="width: 40px;"><%= replyWriter %></span>
						</td>
						<td style="width: 1000px; border-bottom: 1px solid #ccc;">
							<div id="replyContent<%= (i+1) %>">
								<span><%= replyContent %></span>
							</div>
							<div id="replyContentInput<%= (i+1) %>" style="display: none">
								<input type="hidden" name="replyNo" value="<%= replyNo %>">
								<input type="hidden" name="soulLogNo" value="<%= soulLogNo %>">
								<input name="content" style="width: 900px; height: 25px; background-color: #f0f0f0; border: 1px solid #c0c0c0;" value="<%= replyContent %>" maxlength="100" required>
							</div>
						</td>
						<td style="width: 5px; border-bottom: 1px solid #ccc;">
							<div id="replyUpdateBtn<%= (i+1) %>">
								<button type="button" style="width: 40px; height: 25px; background: #A0A0A0; color: white; border: 1px solid #c0c0c0; cursor: pointer;" onclick="updateReply(<%= (i+1) %>)">수정</button>
							</div>
							<div id="replyUpdateFormBtn<%= (i+1) %>" style="display: none">
								<button type="submit" style="width: 40px; height: 25px; background: #3982BC; color: white; border: 1px solid #c0c0c0; cursor: pointer;">완료</button>
							</div>
						</td>
						<td style="width: 50px; border-bottom: 1px solid #ccc;">	
							<a href="deleteSoulLogReply?replyNo=<%= replyNo %>&soulLogNo=<%= soulLogNo %>" onclick="return confirmDelete();">
								<button type="button" style="width: 40px; height: 25px; background: #C42A2A; color: white; border: 1px solid #c0c0c0; cursor: pointer;">삭제</button>
							</a>
						</td>
					</tr>
				</table>
			</form>
						
			<%
			}
			%>
				
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