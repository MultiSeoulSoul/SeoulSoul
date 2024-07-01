<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.js"></script>
<style>
	/* 버튼과 입력 요소에 대한 일반 스타일 */
	div > button, input {
	    width: 100px;
	    align-items: right;
	}
	
	/* 입력 그룹 스타일 */
	.input-group {
	    display: flex;
	    width: 100%;
	    position: relative;
	}
	.input-group-text {
	    width: 120px;
	}
	
	/* 프로필 섹션 스타일 */
	.profile {
	    justify-content: space-between;
	    align-items: center;
	    text-align: center;
	    background-color: white;
	    padding: 20px;
	    border: 1px solid #ddd;
	    border-radius: 8px;
	    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	    margin-bottom: 20px;
	    height: auto;
	}
	.profile h2 {
	    margin-bottom: 20px;
	}
	.profile p {
	    margin-bottom: 20px;
	    color: #666;
	}
	
	/* 프로필 정보 스타일 */
	.profile-info, .profile-info-edit {
	    display: flex;
	    align-items: center;
	    gap: 15px;
	}
	
	/* 프로필 이미지 스타일 */
	.profile-img-info {
	    width: 100px;
	    height: 100px;
	    border-radius: 50%;
	    border: 1px solid #ddd;
	}
	.profile-image {
	    width: 200px;
	    text-align: center;
	}
	
	/* 프로필 버튼 스타일 */
	.profile-buttons {
	    position: absolute;
	    right: 20px;
	    top: 50%;
	    transform: translateY(-50%);
	}
	.edit-btn, .save-btn {
	    background-color: #5c9eaf;
	    color: white;
	    padding: 10px 20px;
	    border: none;
	    border-radius: 4px;
	    cursor: pointer;
	}
	
	/* 입력 표시 스타일 */
	#nickname-input, #content-input {
	    display: none;
	}
	
	/* 내용 감싸기 및 높이 조정 */
	.profile-content .form-control, .form-control, .input-group-text, .profile {
	    word-wrap: break-word;
	    height: auto;
	}
</style>
<script>
	function changeProfile() {
	    // 입력 필드를 표시하고 버튼의 가시성을 변경
	    document.getElementById('content-display').style.display = 'none';
	 /* document.getElementById('profile-img').style.display = 'none'; */
	 
	    document.getElementById('content-input').style.display = 'inline';
	    document.getElementById('profile-img-input').style.display = 'inline';
	
	    document.querySelector('.edit-btn').style.display = 'none';
	    document.querySelector('.save-btn').style.display = 'inline';
	}
	
	function saveProfile() {
	    // 입력 필드에서 업데이트된 값 가져오기
	    const updatedContent = document.getElementById('content-input').value;
	    const updatedProfileImg = document.getElementById('profile-img-input').files[0];
	
	    // 데이터를 보내기 위해 FormData 객체 생성
	    let formData = new FormData();
	    formData.append('profileContent', updatedContent);
	    if (updatedProfileImg) {
	        formData.append('profileImage', updatedProfileImg);
	    }
	    sendUpdateRequest(formData);
	}
	
	function sendUpdateRequest(formData) {
		var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
        var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
		
	    $.ajax({
	        url: '${pageContext.request.contextPath}/user/updateProfile',
	        type: 'POST',
	        data: formData,
	        processData: false,
	        contentType: false,
	        
/* 			beforeSend: function(xhr) {
	            xhr.setRequestHeader(csrfHeader, csrfToken);
	        }, */
	        
	        success: function(data) {
	            // 새로운 값으로 화면 업데이트
	            document.getElementById('content-display').textContent = data.ProfileContent;
	            if (data.profileImagePath) {
	                document.getElementById('profile-img').src = data.ProfilePicName;
	            }

	            // 입력 필드를 숨기고 표시 필드를 보여줌
	            document.getElementById('content-display').style.display = 'inline';
	            document.getElementById('profile-img').style.display = 'inline';
	            
	            document.getElementById('content-input').style.display = 'none';
	            document.getElementById('profile-img-input').style.display = 'none';

	            // 버튼 가시성 변경
	            document.querySelector('.edit-btn').style.display = 'inline';
	            document.querySelector('.save-btn').style.display = 'none';
	        },
	        error: function() {
	            alert("프로필 업데이트 중 오류가 발생했습니다.");
	        }
	    });
	}
	
	function previewImg(event) {
        const reader = new FileReader();
        reader.onload = function() {
            const output = document.getElementById('profile-img');
            const outputInfo = document.getElementById('profile-img-info');
            output.src = reader.result;
            outputInfo.src = reader.result;
        };
        reader.readAsDataURL(event.target.files[0]);
    }

</script>
</head>
<body>
	<div class="profile">
		<h2>나의 프로필</h2>
	    <p>프로필 사진, 프로필 설명을 변경할 수 있습니다</p>
		<div class="profile-info">
			<table class="profile-table">
				<tr>
					<td rowspan="2" class="profile-image">
						<img src="${pageContext.request.contextPath}/resources/uploadFiles/<sec:authentication property="principal.profilePicName"/>" alt="Profile Image" class="profile-img-info" id="profile-img-info"><br>
						<input type="file" id="profile-img-input" style="display: none;" onchange="previewImg(event)">
					</td>
					<td class="profile-nickname">
						<div class="input-group mb-3 input-group-lg">
							<div class="input-group-prepend">
								<span class="input-group-text">닉네임</span>
							</div>
							<span id="nickname-display" class="form-control"><sec:authentication property="principal.nickname" /></span>
						</div>
					</td>
				</tr>
				<tr>
					<td class="profile-content">
						<div class="input-group mb-3 input-group-lg">
							<div class="input-group-prepend">
								<span class="input-group-text">프로필 설명</span>
							</div>
							<span id="content-display" class="form-control"><sec:authentication property="principal.profileContent" /></span>
							<input type="text" id="content-input" class="form-control" style="display: none;" value="<sec:authentication property="principal.profileContent"/>">
						</div>
					</td>

				</tr>
			</table>
			<div style="text-align: left;">
				<button class="edit-btn" onclick="changeProfile()">변경하기</button>
				<button class="save-btn" onclick="saveProfile()" style="display: none;">저장하기</button>
			</div>
		</div>
	</div>
</body>
</html>