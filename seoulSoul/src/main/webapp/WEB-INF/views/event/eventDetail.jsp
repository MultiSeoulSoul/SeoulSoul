<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서울소울 SEOUL SOUL</title>
<style type="text/css">
body {
	margin: 0;
	padding: 0;
}

.detail-container {
	margin: 20px auto;
	display: flex;
	flex-direction: column;
	align-items: center;
}

.content-wrapper {
	display: flex;
	width: 80%;
	margin-bottom: 20px;
}

.image-container {
	flex: 0 0 40%;
	max-height: 400px;
	overflow: hidden;
	margin-right: 20px;
	margin-left: 20px;
	display: flex;
	align-items: center;
	justify-content: center;
}

.detail-image {
	width: 100%;
	height: 100%;
	object-fit: contain;
}

.content-container {
	flex: 1;
	margin-right: 20px;
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

.heart-btn {
	font-size: 35px;
	cursor: pointer;
	margin: 0 auto;
	display: block;
}

.button-container {
	margin-top: 20px;
	text-align: center;
}

#content-text {
	font-family: 'Freesentation-5Black', sans-serif;
	font-weight: normal;
	font-size: 15px;
	text-align: left;
	white-space: pre-wrap; /* 공백과 줄바꿈을 그대로 유지 */
}

#map {
	width: 500px;
	height: 400px;
	margin: 20px auto;
}

.address {
	text-align: center;
	font-family: 'Freesentation-9Black', sans-serif;
	font-weight: normal;
	font-size: 13px;
	margin-top: 10px;
}

.comment-container {
	margin: 20px auto;
	width: 90%;
}

.comment-input {
	width: calc(100% - 70px);
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
	padding: 10px;
	box-sizing: border-box;
	background-color: #f0f0f0;
}

.comment-edit-input {
	width: calc(100% - 70px);
	height: 50px;
	padding: 10px;
	font-size: 16px;
	border: 1px solid #ccc;
	border-radius: 5px 0 0 5px;
	box-sizing: border-box;
	display: inline-block;
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
	align-self: flex-end;
	margin-left: auto;
	margin-top: 10px;
}

.comment-update-submit:hover {
	background-color: #0056b3;
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
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
document.addEventListener('DOMContentLoaded', function() {
    const userNo = ${sessionScope.user.userNo};
    const eventNo = ${event.eventNo};
    const isAdmin = ${sessionScope.user.role == 'ADMIN'};

    console.log("userNo:", userNo);
    console.log("eventNo:", eventNo);
    console.log("isAdmin:", isAdmin);

    function confirmDeletion(event) {
        return confirm("정말로 이 이벤트를 삭제하시겠습니까? 😱") ? alert('이벤트가 삭제되었습니다.') : false;
    }

    function ajaxRequest(method, url, data, callback) {
        const xhr = new XMLHttpRequest();
        xhr.open(method, url, true);
        xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                callback(xhr);
            }
        };
        xhr.send(JSON.stringify(data));
    }

    function submitComment() {
        const content = document.getElementById('commentContent').value.trim();
        if (content === '') {
            alert('댓글을 입력해 주세요.');
            return;
        }

        console.log("Submitting comment:", content);

        ajaxRequest('POST', '${pageContext.request.contextPath}/event/addComment', {
            userNo: userNo,
            eventNo: eventNo,
            content: content,
            nickname: '${sessionScope.user.nickname}'
        }, function(xhr) {
            if (xhr.status === 200) {
                document.getElementById('commentContent').value = '';
                loadComments();
            } else {
                alert('댓글 등록에 실패했습니다.');
            }
        });
    }

    function loadComments() {
        ajaxRequest('GET', '${pageContext.request.contextPath}/event/getComments?eventNo=' + eventNo, null, function(xhr) {
            if (xhr.status === 200) {
                const comments = JSON.parse(xhr.responseText);
                const commentList = document.getElementById('commentList');
                commentList.innerHTML = '';
                comments.forEach(comment => {
                    const commentElement = document.createElement('div');
                    commentElement.className = 'comment-item';
                    commentElement.dataset.replyNo = comment.replyNo;
                    commentElement.innerHTML = `<p><strong>${comment.nickname}</strong>: <span class="comment-content">${comment.content}</span></p>
                        <div class="comment-actions">`;
                    if (comment.userNo == userNo || isAdmin) {
                        commentElement.innerHTML += `<button class="edit" onclick="enableEdit(${comment.replyNo})">수정</button>
                        <button class="comment-delete-submit" onclick="deleteComment(${comment.replyNo})">삭제</button>`;
                    }
                    commentElement.innerHTML += `</div>
                        <div class="comment-edit-container">
                        <input type="text" class="comment-edit-input" id="editContent-${comment.replyNo}" value="${comment.content}">
                        <button class="comment-update-submit" onclick="updateComment(${comment.replyNo})">수정 완료</button>
                        </div>`;
                    commentList.appendChild(commentElement);
                });
            } else {
                alert('댓글을 불러오는 데 실패했습니다.');
            }
        });
    }

    function enableEdit(replyNo) {
        const commentElement = document.querySelector(`.comment-item[data-reply-no="${replyNo}"]`);
        const contentElement = commentElement.querySelector('.comment-content');
        const editContainer = commentElement.querySelector('.comment-edit-container');
        const updateButton = commentElement.querySelector('.comment-update-submit');

        contentElement.style.display = 'none';
        editContainer.style.display = 'block';
        updateButton.style.display = 'inline-block';
        commentElement.querySelector('.comment-edit-input').focus();
    }

    function updateComment(replyNo) {
        const commentElement = document.querySelector(`.comment-item[data-reply-no="${replyNo}"]`);
        const content = commentElement.querySelector('.comment-edit-input').value.trim();

        if (content === '') {
            alert('댓글을 입력해 주세요.');
            return;
        }

        ajaxRequest('POST', '${pageContext.request.contextPath}/event/updateComment', {
            replyNo: replyNo,
            userNo: userNo,
            content: content
        }, function(xhr) {
            if (xhr.status === 200) {
                loadComments();
            } else {
                alert('댓글 수정에 실패했습니다.');
            }
        });
    }

    function deleteComment(replyNo) {
        if (confirm('정말로 이 댓글을 삭제하시겠습니까?')) {
            ajaxRequest('POST', '${pageContext.request.contextPath}/event/deleteComment', {
                replyNo: replyNo,
                userNo: userNo
            }, function(xhr) {
                if (xhr.status === 200) {
                    loadComments();
                } else {
                    alert('댓글 삭제에 실패했습니다.');
                }
            });
        }
    }

    window.submitComment = submitComment;
    window.enableEdit = enableEdit;
    window.updateComment = updateComment;
    window.deleteComment = deleteComment;
    window.onload = loadComments;
});
</script>
</head>
<body>
	<jsp:include page="../common/menubar.jsp" />
	<div class="detail-container">
		<div class="content-wrapper">
			<div class="image-container">
				<c:if test="${not empty event.imagePath}">
					<img
						src="${pageContext.request.contextPath}/resources/uploadFiles/${event.imagePath}"
						alt="Image" class="detail-image">
				</c:if>
			</div>
			<div class="content-container">
				<h1 class="event-title">&#127881; ${event.title}</h1>
				<p>
					<strong>작성일:</strong> ${event.createdDate}
				</p>
				<p>
					<strong>조회수:</strong> ${event.views}
				</p>
				<p id="content-text">${event.content}</p>
			</div>
		</div>
		<p class="address">
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
		<div class="comment-container">
			<sec:authorize access="hasRole('USER')">
				<input type="text" id="commentContent" class="comment-input"
					placeholder="댓글을 입력해 주세요." />
				<button class="comment-submit" onclick="submitComment()">등록</button>
			</sec:authorize>
		</div>
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
						onclick="updateComment(${comment.replyNo})">수정 완료</button>
				</div>
			</c:forEach>
		</div>
		<div class="button-container">
			<button class="detail-btn" onclick="history.back()">뒤로가기</button>

			<sec:authorize access="hasRole('ADMIN')">
				<button class="detail-btn1"
					onclick="location.href='${pageContext.request.contextPath}/event/editEvent?eventNo=${event.eventNo}'">수정하기</button>
				<form action="${pageContext.request.contextPath}/event/deleteEvent"
					method="post" style="display: inline;"
					onsubmit="return confirmDeletion(event)">
					<input type="hidden" name="eventNo" value="${event.eventNo}">
					<button type="submit" class="detail-btn1">삭제하기</button>
				</form>
			</sec:authorize>
			</form>
		</div>
	</div>
</body>
</html>