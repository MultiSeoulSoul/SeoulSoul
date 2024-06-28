<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.js"></script>
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
		<br><br>
		<img src="${pageContext.request.contextPath}/resources/img/kakao_login_medium_narrow.png" alt="kakao_login" style="cursor:pointer;" onclick="kakaoLogin()">
	</div>
</body>

<script>
// 카카오 초기화
Kakao.init('70797760257800161976c750323d9935');

function kakaoLogin() {
    Kakao.Auth.login({ // 인증코드, 토큰을 자동으로 처리
        success: function(response) {
            Kakao.API.request({
                url: '/v2/user/me', // 카카오 API 엔드포인트, 사용자 정보를 요청함
                success: function(response) { // 받아오는 데이터: id(고유ID), nickname, email, profile_image_url
                	
                	console.log('response:', response);
                	
                    var kakaoid = response.id + "K"; // K를 붙여 카카오회원 구분
                    
                    console.log('kakaoid:', kakaoid);
                    
                    $.ajax({
                        type: "post",
                        url: '/seoulsoul/kakao/idDuplicateCheck', // ID중복체크(카카오회원인지 확인)
                        data: {"userId": kakaoid},
                        dataType: "json",
                        success: function(json) {
                        	
                        	console.log('json:', json);
                        	
                            if (json.idExists) {
                                createHiddenLoginForm(kakaoid); // 아이디가 존재하면 HiddenLoginForm으로 kakaoid를 전달
                            } else { // 아이디가 없으면(신규회원)
                                $.ajax({
                                    type: "post",
                                    url: '/seoulsoul/kakao/kakaoSignUp', // 회원가입 url
                                    data: { // 보내는 데이터: kakaoid, nickname, email
                                        "userId": kakaoid, 
                                        "nickname": response.properties.nickname,
                                        "email": response.kakao_account.email
                                    },
                                    dataType: "json",
                                    success: function(json) {
                                        if (json.success) { // 회원가입 성공시
                                            createHiddenLoginForm(kakaoid); // HiddenLoginForm으로 kakaoid를 전달
                                        } else {
                                            alert('카카오 회원가입 실패. 일반계정으로 로그인하시기 바랍니다.');
                                        }
                                    },
                                    error: function(request, status, error) {
                                        alert("code: " + request.status + "\nmessage: " + request.responseText + "\nerror: " + error);
                                    }
                                });
                            }
                        },
                        error: function(request, status, error) {
                            alert("code: " + request.status + "\nmessage: " + request.responseText + "\nerror: " + error);
                        }
                    });
                }
            });
        },
        fail: function(error) {
            alert(error);
        }
    });
}

function createHiddenLoginForm(kakaoid) {
  	var frm = document.createElement('form'); 		 	 // 제출을 위한 form 태그 생성
    frm.setAttribute('method', 'post'); 				 // post방식으로 요청보냄
    frm.setAttribute('action', '/seoulsoul/user/login'); // 제출할 로그인 url
    
    var hiddenInputId = document.createElement('input');	 // input 태그 생성
    hiddenInputId.setAttribute('type', 'hidden'); 		 // 사용자에게 보이지 않음
    hiddenInputId.setAttribute('name', 'userId');			 // name="userId"
    hiddenInputId.setAttribute('value', kakaoid); 		 // userId값으로 kakaoid 입력
    
    var hiddenInputPw = document.createElement('input');	 // input 태그 생성
    var kakaoPw = 'kakaoPw';
    hiddenInputPw.setAttribute('type', 'hidden'); 		 // 사용자에게 보이지 않음
    hiddenInputPw.setAttribute('name', 'userPw');			 // name="userPw"
    hiddenInputPw.setAttribute('value', kakaoPw); 		 // userPw값으로 kakaoPw 입력
    
    frm.appendChild(hiddenInputId); 						 // form태그에 생성한 input태그 추가
    frm.appendChild(hiddenInputPw); 						 // form태그에 생성한 input태그 추가
    
    document.body.appendChild(frm);						 // body에 form태그 추가
    frm.submit();										 // form 제출
}
</script>

</html>
