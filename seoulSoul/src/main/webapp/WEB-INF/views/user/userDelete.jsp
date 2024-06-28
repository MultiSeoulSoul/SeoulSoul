<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<style>
div>button, input[type=submit] {
	width: 200px;
	margin-top: 10px;
}
.input-group {
	display: flex;
    width: 100%;
}
.input-group-text {
	width: 150px;
}
.input-group-append .btn {
    margin: 0;
    width: 100px;
}
main {
	display: flex;
	flex: 1;
	overflow: hidden;
}
.content {
	flex: none;
	width: 800px;
	flex: 1;
	overflow: hidden;
}
.main-content {
	flex: 1;
	padding: 0px 10px 0px 10px;
	overflow: auto;
}
.side-menu {
	width: 100px;
	background-color: #e0f7fa;
	border-right: 1px solid #ddd;
	padding: 20px;
}
.side-menu ul {
	list-style: none;
	padding: 0;
	margin: 0;
}
.side-menu ul li {
	margin: 10px 0;
}
.side-menu ul li a {
	text-decoration: none;
	color: #333;
	font-weight: bold;
	display: block;
	padding: 10px;
	border-radius: 4px;
	transition: background-color 0.3s;
}
.side-menu ul li a:hover {
	background-color: #b2ebf2; /* 호버 시 배경 색상 */
}
.delete-btn {
    background-color: #ff0000;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}
.delete-btn:disabled {
    background-color: #ffcccc;
    cursor: not-allowed;
}
.user-delete {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: white;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    margin-bottom: 20px;
}
.delete-info {
	align-items: center;
}
.check {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #FFEBCC;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 8px;
    margin-bottom: 20px;
}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/menubar.jsp" />
<main>
	<jsp:include page="/WEB-INF/views/user/sideMenu.jsp" />
	<div class="content">
		<div class="main-content">
			<div class="user-delete">
            	<div class="delete-info">
            		<h2>서울소울 계정 삭제</h2>
		            <p>회원님의 닉네임, 이메일, 프로필 사진, 게시글, 계정 연동을 포함한 모든 개인정보를 삭제합니다.</p>
		            <ul>
		                <li>닉네임, 이메일, 프로필 사진, 게시글, 계정 연동 정보</li>
		            </ul>
		            <div class="check">
			            <label>
			                <input type="checkbox" id="deleteConfirmCheckbox"> 
			                계정을 삭제하면 되돌릴 수 없으며, 삭제한 데이터를 복구할 수 없음을 이해했습니다.
			            </label>
		            </div>
	            </div>
	            <form id="deleteForm" method="post" action="${pageContext.request.contextPath}/user/userDelete" style="display:none;">
	            	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
            	<button class="delete-btn" onclick="document.getElementById('deleteForm').submit();" disabled>회원 탈퇴</button>
            </div>
		</div>
	</div>
</main>
<script>
	document.addEventListener("DOMContentLoaded", function() {
	    var deleteCheckbox = document.getElementById("deleteConfirmCheckbox");
	    var deleteButton = document.querySelector(".delete-btn");
	
	    deleteCheckbox.addEventListener("change", function() {
	        deleteButton.disabled = !deleteCheckbox.checked;
	    });
	});
</script>
</body>
</html>