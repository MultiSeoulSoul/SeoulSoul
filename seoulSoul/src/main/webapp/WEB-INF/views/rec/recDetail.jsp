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
	margin: 50px auto; 
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
   font-family: 'Freesentation-6Black', sans-serif;
    font-weight: normal;
    font-size: 12px;
    text-align: left;
     white-space: pre-wrap;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
    // 로그인된 사용자 ID를 하드코딩된 값으로 설정 (로그인 기능이 구현되면 이를 동적으로 받아와야 함)
    var userNo = 1; // 이 값을 로그인된 사용자의 ID로 변경해야 합니다.

    function confirmDeletion(event) {
        if (confirm("정말로 이 추천을 삭제하시겠습니까? 😱")) {
            return true;
        } else {
            return false;
        }
    }

    function toggleHeart(userNo, recommendationNo) {
        $.post('${pageContext.request.contextPath}/rec/toggleHeart', {
            userNo: userNo,
            recommendationNo: recommendationNo
        }, function(response) {
            if (response.success) {
                if (response.isHearted) {
                    $('#heart-btn').html('&#128153;');
                    alert('추천 게시물이 찜! 되었습니다.');
                } else {
                    $('#heart-btn').html('&#129654;');
                    alert('추천 게시물의 찜이 취소되었습니다.');
                }
            } else {
                alert('Error: ' + response.error);
            }
        });
    }
    function replaceNewlines(text) {
        return text.replace(/\n/g, '<br />');
    }

    $(document).ready(function() {
        var content = $("#content-text").html();
        $("#content-text").html(replaceNewlines(content));
    });
</script>
</head>
<body>
	<jsp:include page="../common/menubar.jsp" />
	<div class="detail-container">
        <div class="content-wrapper">
            <div class="image-container">
                <c:if test="${not empty rec.imagePath}">
                    <img
                        src="${pageContext.request.contextPath}/resources/uploadFiles/${rec.imagePath}"
                        alt="Image" class="detail-image">
                </c:if>
            </div>
            <div class="content-container">
                <h1>${rec.title}</h1>
                <p>
                    <strong>작성일:</strong> ${rec.createdDate}
                </p>
                <p>
                    <strong>조회수:</strong> ${rec.views}
                </p>
                  <p id="content-text">${rec.content}</p>
            </div>
        </div>
        <div class="button-container">
            <span id="heart-btn" class="heart-btn"
                onclick="toggleHeart(userNo, ${rec.recommendationNo})">&#129654;</span>
            <br><br>
            <button class="detail-btn" onclick="history.back()">뒤로가기</button>
            <button class="detail-btn1"
                onclick="location.href='${pageContext.request.contextPath}/rec/editRec?recommendationNo=${rec.recommendationNo}'">수정하기</button>
            <form action="${pageContext.request.contextPath}/rec/deleteRecommend"
                method="post" style="display: inline;"
                onsubmit="return confirmDeletion(event)">
                <input type="hidden" name="recommendationNo"
                    value="${rec.recommendationNo}">
                <button type="submit" class="detail-btn1">삭제하기</button>
            </form>
        </div>
    </div>
</body>
</html>