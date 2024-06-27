<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<style>
div>button, input[type=submit] {
	width: 200px;
	margin-top: 10px;
}

.input-group-text {
	width: 150px;
}

.input-group-append .btn {
    margin: 0;
    width: 100px;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<div align="center" class="logo">
		<a href="${pageContext.request.contextPath}"><img src="${pageContext.request.contextPath}/resources/img/SeoulSoul_logo.png" alt="SOUL Logo"></a>
	</div>
	<form action="${pageContext.request.contextPath}/join" method="post" id="joinForm">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<table align="center">
			<tr>
				<td>
					<div class="input-group mb-3 input-group-lg">
						<div class="input-group-prepend">
							<span class="input-group-text">아이디</span>
						</div>
						<input type="text" class="form-control" name="userId" id="userId">
						<div class="input-group-append">
							<button type="button" class="btn btn-info"
								onclick="checkDuplicateId()">중복체크</button>
						</div>
					</div>
					<div id="idCheckMessage" style="color: red; display: none;">중복된 아이디입니다.</div>
					<div id="idCheckSuccessMessage" style="color: green; display: none;">사용 가능한 아이디입니다.</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="input-group mb-3 input-group-lg">
						<div class="input-group-prepend">
							<span class="input-group-text">비밀번호</span>
						</div>
						<input type="password" class="form-control" name="userPw">
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="input-group mb-3 input-group-lg">
						<div class="input-group-prepend">
							<span class="input-group-text">비밀번호 확인</span>
						</div>
						<input type="password" class="form-control" name="confirmPw">
					</div>
					<div id="passwordMatchMessage" style="color: red; display: none;">비밀번호가 일치하지 않습니다.</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="input-group mb-3 input-group-lg">
						<div class="input-group-prepend">
							<span class="input-group-text">닉네임</span>
						</div>
						<input type="text" class="form-control" name="nickname"  id="nickname">
						<div class="input-group-append">
							<button type="button" class="btn btn-info"
								onclick="checkDuplicateNickname()">중복체크</button>
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
						<input type="text" class="form-control" name="phone">
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="input-group mb-3 input-group-lg">
						<div class="input-group-prepend">
							<span class="input-group-text">이메일</span>
						</div>
						<input type="email" class="form-control" name="email">
					</div>
				</td>
			</tr>
		</table>
		<div align="center">
			<input type="submit" value="회원가입" class="btn btn-info"><br>
		</div>
	</form>
	<!-- <div align="center">
		<button class="btn btn-success">카카오 회원가입</button>
	</div> -->

	<script>
	var isUserIdValid = false;
	var isUserNicknameValid = false;
	
	function checkDuplicateId() {
		var userId = $("#userId").val();
		if (!userId) {
			alert("아이디를 입력해주세요.");
			return;
			}
		$.ajax({
			url : '${pageContext.request.contextPath}/user/checkDuplicateId',
			type : 'GET',
			data : {userId : userId},
			success : function(data) {
			    if (data) {
			        $("#idCheckMessage").hide();
			        $("#idCheckSuccessMessage").show();
			        isUserIdValid = true;
			    } else {
			        $("#idCheckMessage").show();
			        $("#idCheckSuccessMessage").hide();
			        isUserIdValid = false;
			    }
			},
			error : function() {
				alert("중복 확인 중 오류가 발생했습니다.");
				}
			});
		}
	
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
	</script>
	
	<script>
		var isPasswordMatch = false;
		
		function validatePasswordMatch() {
		    var password = $("input[name='userPw']").val();
		    var confirmPassword = $("input[name='confirmPw']").val();
		    if (password === confirmPassword) {
		        $("#passwordMatchMessage").hide();
		        isPasswordMatch = true;
		    } else {
		        $("#passwordMatchMessage").show();
		        isPasswordMatch = false;
		    }
		}
	</script>
	
	<script>
		$(function() {
		    $("input[name='userPw'], input[name='confirmPw']").on("keyup", validatePasswordMatch);
	
		    $("input[type=submit]").click(function(e) {
		        if (!isUserIdValid) {
		            alert("아이디 중복 체크를 해주세요.");
		            e.preventDefault();
		            return false;
		        }
		        if (!isUserNicknameValid) {
		            alert("닉네임 중복 체크를 해주세요.");
		            e.preventDefault();
		            return false;
		        }
		        if (!isPasswordMatch) {
		            alert("비밀번호가 일치하지 않습니다.");
		            e.preventDefault();
		            return false;
		        }
		        $("#joinForm").submit();
		    });
		});
	</script>
</body>
</html>
