<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Main</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.js"></script>
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<style>
	div>button, input {
		width: 200px;
		align-items: right;
	}
	.input-group {
		display: flex;
	    width: 100%;
	    position: relative;
	}
	.input-group-text {
		width: 150px;
	}
	.input-group-append .btn {
	    margin: 0;
	    width: 100px;
	}
	#nickname-input, #content-input {
	    display: none;
	}
    body {
        display: flex;
        flex-direction: column;
        height: 100vh;
        margin: 0;
    }
    header {
        flex-shrink: 0;
    }
    main {
        display: flex;
        flex: 1;
        overflow: hidden;
    }
    .content {
        display: flex;
        flex: 1;
        overflow: hidden;
    }
    .main-content {
        flex: 1;
        padding: 0px 10px 0px 10px;
        overflow: auto;
    }
    .profile {
        display: flex;
        justify-content: space-between;
        align-items: center;
        background-color: white;
        padding: 20px;
        border: 1px solid #ddd;
        border-radius: 8px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        margin-bottom: 20px;
	    height: 250px;
	    position: relative;
    }
    .profile-info {
        align-items: center;
        gap: 15px;
    }
    .profile-info-edit {
        align-items: center;
        gap: 15px;
    }
    .profile-img {
        width: 100px;
        height: 100px;
        border-radius: 50%;
        border: 1px solid #ddd;
    }
    .profile-image {
	    width: 200px;
	    text-align: center;
	}
	.profile-buttons {
        position: absolute;
        right: 20px;
        top: 50%;
        transform: translateY(-50%); /* 수직 중앙 정렬 */
    }
    .board-tabs {
        display: flex;
        gap: 10px;
        padding: 0;
        margin: 0;
        list-style: none;
    }

    .board-tabs li {
        margin: 0;
    }

    .board-tabs li a {
        text-decoration: none;
        color: #333;
        font-weight: bold;
        padding: 10px 20px;
        border-radius: 4px;
        transition: background-color 0.3s;
        display: block;
    }

    .board-tabs li a:hover {
        background-color: #b2ebf2;
    }
    .edit-btn {
        background-color: #5c9eaf;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
    .save-btn {
        background-color: #5c9eaf;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
    .chart, .board, .achievement {
        background-color: white;
        justify-content: space-between;
        padding: 20px;
        border: 1px solid #ddd;
        border-radius: 8px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        margin-bottom: 20px;
    }
    .chart-item {
        display: flex;
        justify-content: space-between;
        margin-bottom: 10px;
    }
    .board-list {
        width: 100%;
        border-collapse: collapse;
    }
    .board-list th, .board-list td {
        border: 1px solid #ddd;
        padding: 10px;
        text-align: left;
    }
</style>
<script>
	document.addEventListener('DOMContentLoaded', function() {
	    showBoard('soul-log', '/seoulsoul/user/SLBoardPage?page=1');
	});
	
	function showBoard(boardType, url) {
	    fetch(url)
	        .then(response => response.json())
	        .then(data => {
	            const tbody = document.querySelector('.board-list tbody');
	            tbody.innerHTML = '';
	
	            if (boardType === 'soul-log') {
	                renderSoulLog(data.slBoard, tbody);
	            } else if (boardType === 'soul-log-reply') {
	                renderSoulLogReply(data.slReply, tbody);
	            }
	
	            updateTableHeader(boardType);
	            
	            const pages = data.pages || 1;
	            updatePagination(pages, boardType);
	        })
	        .catch(error => {
	            console.error('Error fetching data:', error);
	        });
	}
	
	function renderSoulLog(data, tbody) {
	    data.forEach(item => {
	        const row = document.createElement('tr');
	
	        const soulLogNo = document.createElement('td');
	        soulLogNo.textContent = item.soulLogNo;
	        row.appendChild(soulLogNo);
	
	        const locationName = document.createElement('td');
	        locationName.textContent = item.locationName;
	        row.appendChild(locationName);
	
	        const categoryName = document.createElement('td');
	        categoryName.textContent = item.categoryName;
	        row.appendChild(categoryName);
	
	        const title = document.createElement('td');
	        title.textContent = item.title;
	        row.appendChild(title);
	
	        const replyCount = document.createElement('td');
	        replyCount.textContent = item.replyCount;
	        row.appendChild(replyCount);
	
	        const createdDate = document.createElement('td');
	        createdDate.textContent = item.createdDate;
	        row.appendChild(createdDate);
	
	        const views = document.createElement('td');
	        views.textContent = item.views;
	        row.appendChild(views);
	
	        tbody.appendChild(row);
	    });
	}
	
	function renderSoulLogReply(data, tbody) {
	    data.forEach(item => {
	        const row = document.createElement('tr');
	
	        const replyNo = document.createElement('td');
	        replyNo.textContent = item.replyNo;
	        row.appendChild(replyNo);
	
	        const soulLogNo = document.createElement('td');
	        soulLogNo.textContent = item.soulLogNo;
	        row.appendChild(soulLogNo);
	
	        const content = document.createElement('td');
	        content.textContent = item.content;
	        row.appendChild(content);
	
	        const createdDate = document.createElement('td');
	        createdDate.textContent = item.createdDate;
	        row.appendChild(createdDate);
	
	        tbody.appendChild(row);
	    });
	}
	
	function updateTableHeader(boardType) {
	    const headerRow = document.querySelector('.board-list thead tr');
	    headerRow.innerHTML = '';  // 기존 헤더를 초기화
	
	    let headers = [];
	    switch (boardType) {
	        case 'soul-log':
	            headers = ['소울로그번호', '지역', '카테고리', '제목', '댓글수', '작성시간', '조회수'];
	            break;
	        case 'soul-log-reply':
	            headers = ['댓글번호', '소울로그번호', '내용', '작성시간'];
	            break;
	    }
	
	    headers.forEach(header => {
	        const th = document.createElement('th');
	        th.textContent = header;
	        headerRow.appendChild(th);
	    });
	}
	
	function updatePagination(pages, boardType) {
	    const pagination = document.querySelector('.pagination');
	    pagination.innerHTML = '';
	    for (let page = 1; page <= pages; page++) {
	        const pageLink = document.createElement('a');
	        const url = "/seoulsoul/user/SLBoardPage?page=" + page;
	        pageLink.href = 'javascript:void(0);';
	        pageLink.textContent = page;
	        pageLink.onclick = () => showBoard(boardType, url);
	        pagination.appendChild(pageLink);
	    }
	}
</script>

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
/* 	        beforeSend: function(xhr) {
	            xhr.setRequestHeader(csrfHeader, csrfToken);
	        }, */
	        success: function(data) {
	        	console.log("date:", data);
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
            output.src = reader.result;
        };
        reader.readAsDataURL(event.target.files[0]);
    }

</script>

</head>
<body>
<jsp:include page="/WEB-INF/views/common/menubar.jsp" />
<main>
    <jsp:include page="/WEB-INF/views/user/sideMenu.jsp" />
    <div class="content">
        <div class="main-content">
            <div class="profile">
                <div class="profile-info">
				    <table class="profile-table">
					    <tr>
					        <td rowspan="2" class="profile-image">
					            <img src="${pageContext.request.contextPath}/resources/uploadFiles/<sec:authentication property="principal.profilePicName"/>" alt="Profile Image" class="profile-img" id="profile-img"><br>
					            <input type="file" id="profile-img-input" style="display: none;" onchange="previewImg(event)">
				            </td>
					        <td class="profile-nickname">
			                    <div class="input-group mb-3 input-group-lg">
				                    <div class="input-group-prepend">
										<span class="input-group-text">닉네임</span>
									</div>
									<span id="nickname-display" class="form-control"><sec:authentication property="principal.nickname"/></span>
			                    </div>
			                </td>
					    </tr>
					    <tr>
					        <td class="profile-content">
					        	<div class="input-group mb-3 input-group-lg">
					        		<div class="input-group-prepend">
										<span class="input-group-text">프로필 설명</span>
									</div>
							        <span id="content-display" class="form-control"><sec:authentication property="principal.profileContent"/></span>
							        <input type="text" id="content-input" class="form-control" style="display: none;" value="<sec:authentication property="principal.profileContent"/>">
						        </div>
					        </td>
					        
					    </tr>
					</table>
					<div style="text-align: left;">
			    		<button class="edit-btn" onclick="changeProfile()">수정하기</button>
						<button class="save-btn" onclick="saveProfile()" style="display: none;">저장하기</button>
					</div>
				</div>
            </div>
            <div class="chart">
                <div class="chart-item">
                    <span>양천구</span>
                    <span>70%</span>
                </div>
                <div class="chart-item">
                    <span>용산구</span>
                    <span>20%</span>
                </div>
            </div>
            <div class="board">
                <ul class="board-tabs">
                    <li><a href="javascript:void(0);" onclick="showBoard('soul-log', '${pageContext.request.contextPath}/user/SLBoardPage?page=1');">소울로그</a></li>
                    <li><a href="javascript:void(0);" onclick="showBoard('soul-log-reply', '${pageContext.request.contextPath}/user/SLReplyPage?page=1');">소울로그 댓글</a></li>
                    <li><a href="javascript:void(0);" onclick="showBoard('event-reply')">이벤트 댓글</a></li>
                    <li><a href="javascript:void(0);" onclick="showBoard('like')">좋아요</a></li>
                    <li><a href="javascript:void(0);" onclick="showBoard('favorite')">찜</a></li>
                    <li><a href="javascript:void(0);" onclick="showBoard('cs')">문의내역</a></li>
                    <li><a href="javascript:void(0);" onclick="showBoard('report')">신고내역</a></li>
                </ul>
                <table class="board-list">
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>지역</th>
                            <th>카테고리</th>
                            <th>제목</th>
                            <th>댓글 수</th>
                            <th>작성일</th>
                            <th>조회 수</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
				<div class="pagination">
				</div>
            </div>
            <div class="achievement">
                <h3>{achievement_title}</h3>
            </div>
        </div>
    </div>
</main>
</body>
</html>
