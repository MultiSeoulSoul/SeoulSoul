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

        .detail-image {
            width: 100%;
            max-width: 300px;
            height: auto;
            max-height: 400px; /* 필요에 따라 최대 높이를 설정 */
            object-fit: contain; /* 이미지를 컨테이너에 맞추어 조정 */
        }

        .detail-btn {
            background-color: #4382A6;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 5px 10px;
            cursor: pointer;
        }

        .detail-btn:hover {
            background-color: #0056b3;
        }

        #map {
            width: 500px; 
            height: 400px; 
            margin: 20px auto;
        }
    </style>
    <script type="text/javascript" src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=0e2c2d679898678e6c157d1de02b14a4&libraries=services"></script>
    <script type="text/javascript">
        document.addEventListener("DOMContentLoaded", function() {
            var address = document.getElementById('address').textContent.replace('장소: ', '');
            console.log('Address:', address);  // 디버깅을 위해 콘솔에 주소 출력

            // 카카오 지도 API가 로드된 후 initMap 함수 호출
            if (typeof kakao === 'undefined' || typeof kakao.maps === 'undefined') {
                console.error('Kakao maps library not loaded properly.');
                return;
            }
            console.log('Kakao map script loaded');
            var mapContainer = document.getElementById('map'); // 지도를 표시할 div
            var mapOption = {
                center: new kakao.maps.LatLng(37.5665, 126.9780), // 기본 중심좌표 (서울 시청)
                level: 3
            };

            var map = new kakao.maps.Map(mapContainer, mapOption);
            var geocoder = new kakao.maps.services.Geocoder();

            // 주소 검색
            geocoder.addressSearch(address, function(result, status) {
                if (status === kakao.maps.services.Status.OK) {
                    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                    var marker = new kakao.maps.Marker({
                        map: map,
                        position: coords
                    });
                    map.setCenter(coords);
                    console.log('Map center set to:', coords);
                } else {
                    console.error('주소를 찾을 수 없습니다.', status);  // 에러 메시지 출력
                    alert('주소를 찾을 수 없습니다.');
                }
            });
        });
    </script>
</head>
<body>
    <jsp:include page="../common/menubar.jsp" />
    <div class="detail-container">
        <h2>${event.title}</h2>
        <p><strong>작성일:</strong> ${event.createdDate}</p>
        <p><strong>조회수:</strong> ${event.views}</p>
        <p>${event.content}</p>
        <p><span id="address">장소: ${event.address}</span></p>
        <c:if test="${not empty event.imagePath}">
            <img src="${pageContext.servletContext.contextPath}/resources/uploadFiles/${event.imagePath}" alt="Image" class="detail-image">
        </c:if>
        <div id="map"></div>
        <br>
        <button class="detail-btn" onclick="history.back()">뒤로가기</button>
    </div>
</body>
</html>