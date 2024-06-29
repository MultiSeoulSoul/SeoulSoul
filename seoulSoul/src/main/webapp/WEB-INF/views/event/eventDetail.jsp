<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            width: 300px;  /* 고정된 너비 */
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
    </style>
</head>
<body>
    <jsp:include page="../common/menubar.jsp" />
    <div class="detail-container">
        <h2 class="event-title">&#127881; ${event.title}</h2>
        <p><strong>작성일:</strong> ${event.createdDate}</p>
        <p><strong>조회수:</strong> ${event.views}</p>
        <p class="body-text">${event.content}</p>
       
        <c:if test="${not empty event.imagePath}">
            <div class="image-container">
                <img src="${pageContext.servletContext.contextPath}/resources/uploadFiles/${event.imagePath}" alt="Image" class="detail-image">
            </div>
        </c:if>
        <br><br>
        <p><span id="address">장소: ${event.address}</span></p>
        <div id="map"></div>
        <script type="text/javascript" src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=${kakaoApiKey}&libraries=services,clusterer,drawing"></script>
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
        <br>
        <button class="detail-btn" onclick="history.back()">뒤로가기</button>
        <button class="detail-btn1"
            onclick="location.href='${pageContext.request.contextPath}/event/editEvent?eventNo=${event.eventNo}'">수정하기</button>
        <form action="${pageContext.request.contextPath}/event/deleteEvent"
            method="post" style="display: inline;" onsubmit="return confirmDeletion(event)">
            <input type="hidden" name="eventNo" value="${event.eventNo}">
            <button type="submit" class="detail-btn1">삭제하기</button>
        </form>
        <br><br>
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

</body>
</html>