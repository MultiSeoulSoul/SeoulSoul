<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    margin: 20px auto; /* 메뉴바와의 여백을 위해 상단 여백 추가 */
    display: flex;
    flex-direction: column; /* 중앙 아래에 위치한 버튼을 위해 수직 정렬 */
    align-items: center; /* 수직 중앙 정렬 */
}

.content-wrapper {
    display: flex;
    width: 80%; /* 중앙 정렬을 위한 너비 설정 */
    margin-bottom: 20px; /* 하단 여백 추가 */
}

.image-container {
    flex: 0 0 40%; /* 가로 공간의 40% 차지 */
    max-height: 400px; /* 필요에 따라 최대 높이를 설정 */
    overflow: hidden; /* 이미지가 컨테이너를 넘지 않도록 숨김 */
    margin-right: 20px; /* 이미지와 컨텐츠 사이의 여백 */
    margin-left: 20px; /* 이미지와 왼쪽 사이의 여백 추가 */
    display: flex;
    align-items: center;
    justify-content: center;
}

.detail-image {
    width: 100%;
    height: 100%; /* 이미지가 컨테이너의 높이를 채우도록 설정 */
    object-fit: contain; /* 이미지를 컨테이너에 맞추어 조정하고 비율 유지 */
}

.content-container {
    flex: 1; /* 나머지 공간을 차지하도록 */
    margin-right: 20px; /* 컨텐츠와 오른쪽 사이의 여백 추가 */
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
    margin: 0 auto; /* 중앙 정렬 */
    display: block; /* 블록 요소로 설정하여 중앙 정렬 */
}

.button-container {
    margin-top: 20px; /* 버튼들과 본문 사이의 여백 */
    text-align: center; /* 버튼들을 중앙 정렬 */
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
    margin-top: 10px; /* 지도 위에 여백 추가 */
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
    padding: 10px;
    box-sizing: border-box;
    background-color: #f0f0f0;
}

.comment-edit-input {
     width: calc(100% - 70px); /* 버튼을 제외한 너비 */
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
    margin-left: auto; /* 왼쪽 여백 자동 */
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
    var userNo = 1; // 테스트를 위한 사용자 번호
    var nickname = '테스트닉네임'; // 실제 구현 시 서버에서 가져온 닉네임 사용

    function confirmDeletion(event) {
        if (confirm("정말로 이 이벤트를 삭제하시겠습니까? 😱")) {
            alert('이벤트가 삭제되었습니다.');
            return true;
        } else {
            return false;
        }
    }

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
                            '<div class="comment-edit-container">' +
                            '<input type="text" class="comment-edit-input" id="editContent-' + comment.replyNo + '" value="' + comment.content + '">' +
                            '<button class="comment-update-submit" onclick="updateComment(' + comment.replyNo + ')">수정 완료</button>' +
                            '</div>';
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
        var editContainer = commentElement.querySelector('.comment-edit-container');
        var updateButton = commentElement.querySelector('.comment-update-submit');

        contentElement.style.display = 'none';
        editContainer.style.display = 'block';
        updateButton.style.display = 'inline-block';
        var editInput = commentElement.querySelector('.comment-edit-input');
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
</head>
<body>
    <jsp:include page="../common/menubar.jsp" />
    <div class="detail-container">
        <div class="content-wrapper">
            <div class="image-container">
                <c:if test="${not empty event.imagePath}">
                    <img src="${pageContext.request.contextPath}/resources/uploadFiles/${event.imagePath}" alt="Image" class="detail-image">
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
            <input type="text" id="commentContent" class="comment-input" placeholder="댓글을 입력해 주세요." />
            <button class="comment-submit" onclick="submitComment()">등록</button>
        </div>
        <div id="commentList" class="comment-list">
            <c:forEach var="comment" items="${comments}">
                <div class="comment-item" data-reply-no="${comment.replyNo}">
                    <p>
                        <strong>${comment.nickname}</strong>: <span class="comment-content">${comment.content}</span>
                    </p>
                    <div class="comment-actions">
                        <button class="edit" onclick="enableEdit(${comment.replyNo})">수정</button>
                        <button class="comment-delete-submit" onclick="deleteComment(${comment.replyNo})">삭제</button>
                    </div>
                    <input type="text" class="comment-edit-input" id="editContent-${comment.replyNo}" value="${comment.content}">
                    <button class="comment-update-submit" onclick="updateComment(${comment.replyNo})">수정 완료</button>
                </div>
            </c:forEach>
        </div>
        <div class="button-container">
            <button class="detail-btn" onclick="history.back()">뒤로가기</button>
            <button class="detail-btn1" onclick="location.href='${pageContext.request.contextPath}/event/editEvent?eventNo=${event.eventNo}'">수정하기</button>
            <form action="${pageContext.request.contextPath}/event/deleteEvent" method="post" style="display: inline;" onsubmit="return confirmDeletion(event)">
                <input type="hidden" name="eventNo" value="${event.eventNo}">
                <button type="submit" class="detail-btn1">삭제하기</button>
            </form>
        </div>
    </div>
</body>
</html>