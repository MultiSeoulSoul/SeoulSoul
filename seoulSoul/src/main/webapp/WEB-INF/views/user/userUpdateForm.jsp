<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.js"></script>
<title>Insert title here</title>
<style>
	div>button, input[type=submit] {
		width: 200px;
		margin-top: 10px;
	}
	h2 {
        margin-bottom: 20px;
    }
    p {
        margin-bottom: 20px;
        color: #666;
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
	.update-form {
	    flex: 1;
	    justify-content: space-between;
	    align-items: center;
	    text-align: center;
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
	    text-align: center;
	    background-color: white;
	    padding: 20px;
	    border: 1px solid #ddd;
	    border-radius: 8px;
	    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	    margin-bottom: 20px;
}
</style>
<script> // 닉네임 중복확인 및 제출
var isUserNicknameValid = false;
var isNicknameChanged = false;

function checkDuplicateNickname() {
    var nickname = $("#nickname").val();
    if (!nickname) {
        alert("닉네임을 입력해주세요.");
        return;
    }
    $.ajax({
        url: '${pageContext.request.contextPath}/checkDuplicateNickname',
        type: 'GET',
        data: { nickname: nickname },
        success: function(data) {
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
        error: function() {
            alert("중복 확인 중 오류가 발생했습니다.");
        }
    });
}

$(document).ready(function() {
    var currentNickname = $("<div>").html("<sec:authentication property='principal.nickname'/>").text();
    
    $("#nickname").on("input", function() {
        var enteredNickname = $(this).val();
        if (currentNickname === enteredNickname) {
            $(".input-group-append .btn").prop("disabled", true);
            $("#nicknameCheckMessage").hide();
            $("#nicknameCheckSuccessMessage").hide();
            isUserNicknameValid = true;
            isNicknameChanged = false;
        } else {
            $(".input-group-append .btn").prop("disabled", false);
            isUserNicknameValid = false;
            isNicknameChanged = true;
        }
    });
    
    // 회원정보 수정 버튼 이벤트
    $("#userUpdateForm input[type=submit]").click(function(e) {
        var enteredNickname = $("#nickname").val();
        if (isNicknameChanged && !isUserNicknameValid) {
            alert("닉네임 중복 체크를 해주세요.");
            e.preventDefault();
            return false;
        }
        $("#userUpdateForm").submit();
    });
});

</script>
<script> // 비밀번호 검증 및 제출
	var isPasswordMatch = false;
	var isCurrentPasswordValid = false;
	var isNewPasswordDifferent = false;
	
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
	        url: '${pageContext.request.contextPath}/checkCurrentPassword',
	        type: 'POST',
	        data: { currentPassword: currentPassword },
	        
/* 	        beforeSend: function(xhr) {
	            xhr.setRequestHeader(csrfHeader, csrfToken);
	        }, */
	        
	        success: function(isValid) {
	        	console.log("isValid:", isValid);
	        	
	            if (isValid) {
	                isCurrentPasswordValid = true;
	                $("#passwordCurrentMessage").hide();
	            } else {
	                isCurrentPasswordValid = false;
	                $("#passwordCurrentMessage").show();
	                alert("현재 비밀번호가 잘못되었습니다.");
	            }
	            callback(isValid);
	        },
	        error: function() {
	            alert("비밀번호 검증 중 오류가 발생했습니다.");
	            callback(false);
	        }
	    });
	}
	
	function validateNewPasswordDifferent() {
	    var currentPassword = $("input[name='userPw']").val();
	    var newPassword = $("input[name='newUserPw']").val();
	    if (currentPassword !== newPassword) {
	        $("#passwordCurrentMessage").hide();
	        isNewPasswordDifferent = true;
	    } else {
	        $("#passwordCurrentMessage").show();
	        isNewPasswordDifferent = false;
	    }
	}
	
	$(document).ready(function() {
	    $("input[name='newUserPw'], input[name='newConfirmPw']").on("keyup", validatePasswordMatch);
	    $("input[name='newUserPw']").on("blur", validateNewPasswordDifferent);
	
	    // 비밀번호 수정 버튼 이벤트
	    $("#userPwUpdateForm input[type=submit]").click(function(e) {
	        e.preventDefault(); // 기본 폼 제출 동작 중지
	        checkCurrentPassword(function(isValid) {
	            if (!isValid) {
	                return false;
	            }
	            if (!isPasswordMatch) {
	                alert("새 비밀번호가 일치하지 않습니다.");
	                return false;
	            }
	            if (!isNewPasswordDifferent) {
	                alert("현재 비밀번호와 새 비밀번호가 동일합니다.");
	                return false;
	            }
	            $("#userPwUpdateForm").submit(); // 모든 검증 통과 시 폼 제출
	        });
	    });
	});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/menubar.jsp" />
<main>
	<jsp:include page="/WEB-INF/views/user/sideMenu.jsp" />
	<div class="content">
		<div class="main-content">
			<div class="update-form">
			<h2>회원정보 변경</h2>
       		<p>닉네임, 전화번호, 이메일을 변경할 수 있습니다</p>
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
									<button type="button" class="btn btn-info" onclick="checkDuplicateNickname()" disabled>중복체크</button>
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
			    <p>비밀번호를 변경할 수 있습니다</p>
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
			                    <div id="passwordCurrentMessage" style="color: red; display: none;">현재 비밀번호와 새 비밀번호가 동일합니다.</div>
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
</body>
</html>
