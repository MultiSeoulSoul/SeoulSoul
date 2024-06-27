<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
.update-form {
    flex: 1;
    justify-content: space-between;
    align-items: center;
    background-color: white;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    margin-bottom: 20px;
}
.update-form-userPw {
    flex: 1;
    justify-content: space-between;
    align-items: center;
    background-color: white;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    margin-bottom: 20px;
}
</style>
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/menubar.jsp" />
<main>
	<jsp:include page="/WEB-INF/views/user/sideMenu.jsp" />
	<div class="content">
		<div class="main-content">
			<div class="update-form">
			<form action="${pageContext.request.contextPath}/user/userUpdate" method="post" id="userUpdateForm">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<table align="center">
					<tr>
						<td>
							<div class="input-group mb-3 input-group-lg">
								<div class="input-group-prepend">
									<span class="input-group-text">닉네임</span>
								</div>
								<input type="text" class="form-control" name="nickname" id="nickname" value="<sec:authentication property="principal.nickname"/>">
								<div class="input-group-append">
									<button type="button" class="btn btn-info" onclick="checkDuplicateNickname()">중복체크</button>
								</div>
							</div>
							<div id="nicknameCheckMessage" style="color: red; display: none;">중복된 닉네임입니다.</div>
							<div id="nicknameCheckSuccessMessage" style="color: green; display: none;">사용 가능한 닉네임입니다.</div>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="input-group mb-3 input-group-lg">
								<div class="input-group-prepend">
									<span class="input-group-text">전화번호</span>
								</div>
								<input type="text" class="form-control" name="phone" value="<sec:authentication property="principal.phone"/>">
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="input-group mb-3 input-group-lg">
								<div class="input-group-prepend">
									<span class="input-group-text">이메일</span>
								</div>
								<input type="email" class="form-control" name="email" value="<sec:authentication property="principal.email"/>">
							</div>
						</td>
					</tr>
				</table>
				<div align="right">
					<input type="submit" value="회원정보 변경하기" class="btn btn-info"><br>
				</div>
			</form>
			</div>
			
			<div class="update-form-userPw">
				<form action="${pageContext.request.contextPath}/user/userPwUpdate" method="post" id="userPwUpdateForm">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<table align="center">
						<tr>
							<td colspan="2">
								<div class="input-group mb-3 input-group-lg">
									<div class="input-group-prepend">
										<span class="input-group-text">현재 비밀번호</span>
									</div>
									<input type="password" class="form-control" name="userPw">
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="input-group mb-3 input-group-lg">
									<div class="input-group-prepend">
										<span class="input-group-text">새 비밀번호</span>
									</div>
									<input type="password" class="form-control" name="newUserPw">
								</div>
								<div id="passwordCurrentMessage" style="color: red; display: none;">현재 비밀번호와 같습니다.</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="input-group mb-3 input-group-lg">
									<div class="input-group-prepend">
										<span class="input-group-text">새 비밀번호 확인</span>
									</div>
									<input type="password" class="form-control" name="newConfirmPw">
								</div>
								<div id="passwordMatchMessage" style="color: red; display: none;">비밀번호가 일치하지 않습니다.</div>
							</td>
						</tr>
					</table>
					<div align="right">
						<input type="submit" value="비밀번호 변경하기" class="btn btn-info"><br>
					</div>
				</form>
				
			</div>
		</div>
	</div>
</main>
<script>
var isUserNicknameValid = false;	
var isPasswordMatch = false;

function checkDuplicateNickname() {
	var nickname = $("#nickname").val();
	if (!nickname) {
		alert("닉네임을 입력해주세요.");
		return;
		}
	$.ajax({
		url : '${pageContext.request.contextPath}/user/checkDuplicateNickname',
		type : 'GET',
		data : {nickname : nickname},
		success : function(data) {
		    if (data) {
		        $("#nicknameCheckMessage").hide();
		        $("#nicknameCheckSuccessMessage").show();
		        isUserNicknameValid = true;
		    } else {
		        $("#nicknameCheckMessage").show();
		        $("#nicknameCheckSuccessMessage").hide();
		        isUserNicknameValid = false;
		    }
		},
		error : function() {
			alert("중복 확인 중 오류가 발생했습니다.");
			}
		});
	}

function validatePasswordMatch() {
    var password = $("input[name='newUserPw']").val();
    var confirmPassword = $("input[name='newConfirmPw']").val();
    if (password === confirmPassword) {
        $("#passwordMatchMessage").hide();
        isPasswordMatch = true;
    } else {
        $("#passwordMatchMessage").show();
        isPasswordMatch = false;
    }
}

function checkCurrentPassword(callback) {
    var currentPassword = $("input[name='userPw']").val();
    if (!currentPassword) {
        alert("현재 비밀번호를 입력해주세요.");
        return;
    }
    $.ajax({
        url: '${pageContext.request.contextPath}/user/checkCurrentPassword',
        type: 'POST',
        data: {currentPassword: currentPassword},
        success: function(isValid) {
            callback(isValid);
        },
        error: function() {
            alert("비밀번호 검증 중 오류가 발생했습니다.");
        }
    });
}

$(document).ready(function() {
    $("input[name='newUserPw'], input[name='newConfirmPw']").on("keyup", validatePasswordMatch);

    // 회원정보 수정 버튼 이벤트
    $("#userUpdateForm input[type=submit]").click(function(e) {
        if (!isUserNicknameValid) {
            alert("닉네임 중복 체크를 해주세요.");
            e.preventDefault();
            return false;
        }
        $("#userUpdateForm").submit();
    });

    // 비밀번호 수정 버튼 이벤트
    $("#userPwUpdateForm input[type=submit]").click(function(e) {
        if (!isPasswordMatch) {
            alert("비밀번호가 일치하지 않습니다.");
            e.preventDefault();
            return false;
        }
        checkCurrentPassword(function(isValid) {
            if (isValid) {
            	$("#passwordCurrentMessage").hide();
                $("#userPwUpdateForm").submit();
            } else {
                $("#passwordCurrentMessage").show();
                e.preventDefault();
            }
        });
    });
});
</script>
</body>
</html>
