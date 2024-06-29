<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서울소울 SEOUL SOUL</title>
<style type="text/css">
.detail-container {
	margin: 0 auto;
	text-align: center;
}

.body-text {
	font-family: 'Freesentation-5Black', sans-serif;
	font-weight: normal;
	text-align: center;
	font-size: 15px;
}

.event-title {
	font-family: 'Freesentation-9Black', sans-serif;
	font-weight: bold;
	text-align: center;
	font-size: 30px; /* 제목을 더 크게 강조 */
	margin-bottom: 10px; /* 제목과 내용 사이에 여백 추가 */
}

.detail-image {
	width: 100%;
	max-width: 300px;
	height: auto;
	max-height: 400px; /* 필요에 따라 최대 높이를 설정 */
	object-fit: contain; /* 이미지를 컨테이너에 맞추어 조정 */
}

.image-container {
	width: 300px; /* 고정된 너비 */
	height: 400px; /* 고정된 높이 */
	overflow: hidden; /* 이미지가 컨테이너를 넘지 않도록 숨김 */
	display: flex;
	align-items: center;
	justify-content: center;
	margin: 0 auto;
}

.detail-btn {
	background-color: #4382A6;
	color: white;
	border: none;
	border-radius: 5px;
	padding: 5px 10px;
	cursor: pointer;
	margin-right: 10px;
}

.detail-btn:hover {
	background-color: #0056b3;
}

.detail-btn1 {
	background-color: #C42A2A;
	color: white;
	border: none;
	border-radius: 5px;
	padding: 5px 10px;
	cursor: pointer;
	margin-right: 10px;
}

.detail-btn1:hover {
	background-color: #9b1d1d;
}

#map {
	width: 500px;
	height: 400px;
	margin: 20px auto;
}

.comment-container {
	margin: 20px auto;
	width: 90%;
}

.comment-input {
	width: calc(100% - 70px); /* 버튼을 제외한 너비 */
	height: 50px;
	padding: 10px;
	font-size: 16px;
	border: 1px solid #ccc;
	border-radius: 5px 0 0 5px;
	box-sizing: border-box;
}

.comment-submit {
	width: 60px;
	height: 50px;
	background-color: #4382A6;
	color: white;
	border: none;
	border-radius: 0 5px 5px 0;
	cursor: pointer;
}

.comment-submit:hover {
	background-color: #0056b3;
}

.comment-list {
	margin: 5px auto;
	width: 90%;
	border-top: 1px solid #ccc;
	padding-top: 10px;
}

.comment-item {
	border-bottom: 1px solid #eee;
	padding: 10px 0;
	position: relative;
}

.comment-actions {
	margin-top: 10px;
	display: flex;
	gap: 10px;
}
 .comment-edit-container {
   display: none;
            margin-top: 10px;
            width: 100%;
        }

.comment-edit-input {
	flex-grow: 1;
            height: 40px;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            display: none;
}

.comment-update-submit {
		width: 70px;
		height: 40px;
		background-color: #4382A6;
		color: white;
		border: none;
		border-radius: 5px;
		cursor: pointer;
		display: none;
		margin-left: 20px;
}

.comment-update-submit:hover {
	background-color:#0056b3;
}

.comment-delete-submit {
	background-color: #C42A2A;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.edit {
	background-color: #C42A2A;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}
</style>
</head>
<body>
	<jsp:include page="../common/menubar.jsp" />
	<div class="detail-container">
		<h2 class="event-title">&#127881; ${event.title}</h2>
		<p>
			<strong>작성일:</strong> ${event.createdDate}
		</p>
		<p>
			<strong>조회수:</strong> ${event.views}
		</p>
		<p class="body-text">${event.content}</p>

		<c:if test="${not empty event.imagePath}">
			<div class="image-container">
				<img
					src="${pageContext.servletContext.contextPath}/resources/uploadFiles/${event.imagePath}"
					alt="Image" class="detail-image">
			</div>
		</c:if>
		<br>
		<br>
		<p>
			<span id="address">장소: ${event.address}</span>
		</p>
		<div id="map"></div>
		<script type="text/javascript"
			src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=${kakaoApiKey}&libraries=services,clusterer,drawing"></script>
		<script type="text/javascript">
	        var addressElement = document.getElementById('address');
	        if (!addressElement) {
	            console.error('Address element not found.');
	            alert('주소를 찾을 수 없습니다.');
	        }
	
	        var address = addressElement.textContent.replace('장소: ', '').trim();
	        
	        var geocoder = new kakao.maps.services.Geocoder();

	        var callback = function(result, status) {
	            if (status === kakao.maps.services.Status.OK) {
	                var x = result[0].road_address.x;
	                var y = result[0].road_address.y;
	                
	                var container = document.getElementById('map');
	    			var options = {
	    				center: new kakao.maps.LatLng(y, x),
	    				level: 3
	    			};
	    	
	    			var map = new kakao.maps.Map(container, options);
	            }
	        };

	        geocoder.addressSearch(address, callback);
	        
	        
	        
			
		</script>

		<!-- 댓글 입력 폼 추가 -->
		<div class="comment-container">
			<input type="text" id="commentContent" class="comment-input"
				placeholder="댓글을 입력해 주세요." />
			<button class="comment-submit" onclick="submitComment()">등록</button>
		</div>

		<!-- 댓글 리스트 표시 영역 -->
		<div id="commentList" class="comment-list">
			<c:forEach var="comment" items="${comments}">
				<div class="comment-item" data-reply-no="${comment.replyNo}">
					<p>
						<strong>${comment.nickname}</strong>: <span
							class="comment-content">${comment.content}</span>
					</p>
					<div class="comment-actions">
						<button class="edit" onclick="enableEdit(${comment.replyNo})">수정</button>
						<button class="comment-delete-submit"
							onclick="deleteComment(${comment.replyNo})">삭제</button>
					</div>
					<input type="text" class="comment-edit-input"
						id="editContent-${comment.replyNo}" value="${comment.content}">
					<button class="comment-update-submit"
						onclick="updateComment(${comment.replyNo})">수정완료</button>
				</div>
			</c:forEach>
		</div>
		<br>
		<button class="detail-btn" onclick="history.back()">뒤로가기</button>
		<button class="detail-btn1"
			onclick="location.href='${pageContext.request.contextPath}/event/editEvent?eventNo=${event.eventNo}'">수정하기</button>
		<form action="${pageContext.request.contextPath}/event/deleteEvent"
			method="post" style="display: inline;"
			onsubmit="return confirmDeletion(event)">
			<input type="hidden" name="eventNo" value="${event.eventNo}">
			<button type="submit" class="detail-btn1">삭제하기</button>
		</form>
		<br>
		<br>
	</div>


	<script type="text/javascript">
    function confirmDeletion(event) {
        if (confirm("정말로 이 이벤트를 삭제하시겠습니까? 😱")) {
            alert('이벤트가 삭제되었습니다.');
            return true;
        } else {
            return false;
        }
    }
    </script>
	<script type="text/javascript">
        var userNo = 1; // 테스트를 위한 사용자 번호
        var nickname = '테스트닉네임'; // 실제 구현 시 서버에서 가져온 닉네임 사용

        function submitComment() {
            var content = document.getElementById('commentContent').value;
            if (content.trim() === '') {
                alert('댓글을 입력해 주세요.');
                return;
            }

            // AJAX 요청을 통해 서버로 댓글 전송
            var xhr = new XMLHttpRequest();
            xhr.open('POST', '${pageContext.request.contextPath}/event/addComment', true);
            xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
            xhr.onreadystatechange = function() {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        // 댓글 등록 후, 입력 필드 초기화 및 댓글 리스트 갱신
                        document.getElementById('commentContent').value = '';
                        loadComments();
                    } else {
                        alert('댓글 등록에 실패했습니다.');
                    }
                }
            };
            xhr.send(JSON.stringify({
                userNo: userNo,
                eventNo: ${event.eventNo},
                content: content,
                nickname: nickname
            }));
        }

        function loadComments() {
            // AJAX 요청을 통해 댓글 리스트를 가져와서 화면에 표시
            var xhr = new XMLHttpRequest();
            xhr.open('GET', '${pageContext.request.contextPath}/event/getComments?eventNo=' + ${event.eventNo}, true);
            xhr.onreadystatechange = function() {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        var comments = JSON.parse(xhr.responseText);
                        var commentList = document.getElementById('commentList');
                        commentList.innerHTML = '';
                        comments.forEach(function(comment) {
                            var commentElement = document.createElement('div');
                            commentElement.className = 'comment-item';
                            commentElement.dataset.replyNo = comment.replyNo;
                            commentElement.innerHTML = '<p><strong>' + comment.nickname + '</strong>: <span class="comment-content">' + comment.content + '</span></p>' +
                                '<div class="comment-actions">' +
                                '<button class="edit" onclick="enableEdit(' + comment.replyNo + ')">수정</button>' +
                                '<button class="comment-delete-submit" onclick="deleteComment(' + comment.replyNo + ')">삭제</button>' +
                                '</div>' +
                                '<input type="text" class="comment-edit-input" id="editContent-' + comment.replyNo + '" value="' + comment.content + '">' +
                                '<button class="comment-update-submit" onclick="updateComment(' + comment.replyNo + ')">수정 완료</button>';
                            commentList.appendChild(commentElement);
                        });
                    } else {
                        alert('댓글을 불러오는 데 실패했습니다.');
                    }
                }
            };
            xhr.send();
        }

        function enableEdit(replyNo) {
            var commentElement = document.querySelector('.comment-item[data-reply-no="' + replyNo + '"]');
            var contentElement = commentElement.querySelector('.comment-content');
            var editInput = commentElement.querySelector('.comment-edit-input');
            var updateButton = commentElement.querySelector('.comment-update-submit');

            contentElement.style.display = 'none';
            editInput.style.display = 'inline-block';
            updateButton.style.display = 'inline-block';
            editInput.focus();
        }

        function updateComment(replyNo) {
            var commentElement = document.querySelector('.comment-item[data-reply-no="' + replyNo + '"]');
            var editInput = commentElement.querySelector('.comment-edit-input');
            var content = editInput.value;

            if (content.trim() === '') {
                alert('댓글을 입력해 주세요.');
                return;
            }

            // AJAX 요청을 통해 서버로 수정된 댓글 전송
            var xhr = new XMLHttpRequest();
            xhr.open('POST', '${pageContext.request.contextPath}/event/updateComment', true);
            xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
            xhr.onreadystatechange = function() {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        loadComments();
                    } else {
                        alert('댓글 수정에 실패했습니다.');
                    }
                }
            };
            xhr.send(JSON.stringify({
                replyNo: replyNo,
                userNo: userNo,
                content: content
            }));
        }

        function deleteComment(replyNo) {
            if (confirm('정말로 이 댓글을 삭제하시겠습니까?')) {
                // AJAX 요청을 통해 서버로 댓글 삭제 요청 전송
                var xhr = new XMLHttpRequest();
                xhr.open('POST', '${pageContext.request.contextPath}/event/deleteComment', true);
                xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === XMLHttpRequest.DONE) {
                        if (xhr.status === 200) {
                            loadComments();
                        } else {
                            alert('댓글 삭제에 실패했습니다.');
                        }
                    }
                };
                xhr.send(JSON.stringify({
                    replyNo: replyNo,
                    userNo: userNo
                }));
            }
        }

        // 페이지 로드 시 댓글 리스트를 로드
        window.onload = loadComments;
    </script>
</body>
</html>