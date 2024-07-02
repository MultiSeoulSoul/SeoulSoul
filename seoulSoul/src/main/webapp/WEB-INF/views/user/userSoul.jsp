<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .soul h2 {
        margin-bottom: 20px;
    }
    .soul p {
        margin-bottom: 20px;
        color: #666;
    }
    .soul {
        background-color: white;
        padding: 20px;
        border: 1px solid #ddd;
        border-radius: 8px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        margin-bottom: 20px;
        text-align: center;
    }
    .soul-container {
        display: flex;
        flex-wrap: wrap;
        gap: 20px;
        justify-content: center;
        width: 100%;
    }
    .soul-info {
        background-color: white;
        padding: 20px;
        border: 1px solid #ddd;
        border-radius: 8px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        margin-bottom: 20px;
        text-align: center;
        width: 200px;
    }
    .experience-bar-container {
        background-color: #f3f3f3;
        border-radius: 10px;
        overflow: hidden;
        height: 30px;
        margin: 10px 0;
    }
    .experience-bar {
        background-color: #4caf50;
        height: 100%;
        text-align: center;
        color: white;
        line-height: 30px;
    }
    .soul-icon {
        display: inline-block;
        background: url('${pageContext.request.contextPath}/resources/img/soul_icon.png') no-repeat center center;
        background-size: contain;
    }
</style>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.js"></script>
<script>
	$(document).ready(function() {
	    // AJAX 호출로 데이터 가져오기
	    $.ajax({
	        url: '${pageContext.request.contextPath}/user/soul',
	        method: 'GET',
	        success: function(data) {
	        	
	        	console.log("data:", data);
	        	
	            data.slice(0, 6).forEach(function(item) {
	            	var exp = item.exp;
	                var level;

	                if (exp == 0) {
	                    level = 0;
	                } else if (exp > 0 && exp < 100) {
	                    level = 1;
	                } else if (exp >= 100 && exp < 200) {
	                    level = 2;
	                } else if (exp >= 200 && exp < 300) {
	                    level = 3;
	                } else if (exp >= 300 && exp < 400) {
	                    level = 4;
	                } else if (exp >= 400 && exp < 500) {
	                    level = 5;
	                } else {
	                    level = "Max";
	                }
	            	var expBarWidth = exp % 100;
	            	var iconSize = Math.min(level * 20, 100);

	                var content = '<div class="soul-info">';
	                content += '<h2>' + item.locationName + '</h2>';
	                content += '<p style="height:50px">Lv . ' + level + ' <span class="soul-icon" style="width:' + iconSize + 'px; height:' + iconSize + 'px;"></span></p>';
	                content += '<div class="experience-bar-container">';
	                content += '<div class="experience-bar" style="width:' + expBarWidth + '%;"></div>';
	                content += '</div>';
	                content += '<p>소울 : ' + expBarWidth + '</p>';
	                content += '</div>';
	                
	                $('.soul-container').append(content);
	            });
	        },
	        error: function(error) {
	            console.error("Error fetching data:", error);
	        }
	    });
	});
</script>
</head>
<body>
    <div class="soul">
        <h2>나의 소울</h2>
        <p>여기서 당신의 소울을 확인해보세요!</p>
        <div class="soul-container">
        </div>
    </div>
</body>
</html>
