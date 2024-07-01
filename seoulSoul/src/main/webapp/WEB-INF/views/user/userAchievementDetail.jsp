<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Achievement</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<style>
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
    .achievement {
        background-color: white;
        justify-content: space-between;
        padding: 20px;
        border: 1px solid #ddd;
        border-radius: 8px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        margin-bottom: 20px;
    }
    .achievement h2 {
        margin-bottom: 20px;
    }
    .achievement p {
        margin-bottom: 20px;
        color: #666;
    }
    .achievement-icons {
        display: flex;
        justify-content: flex-start;
        gap: 10px;
        flex-wrap: wrap; /* 가로방향으로 나열하고 공간이 부족할 경우 줄바꿈 */
    }
	.achievement-icons-notach {
        display: flex;
        justify-content: flex-start;
        gap: 10px;
        flex-wrap: wrap; /* 가로방향으로 나열하고 공간이 부족할 경우 줄바꿈 */
    }
    .achievement-icons-notach img {
	    filter: grayscale(100%); /* 이미지 흑백 필터 적용 */
	}
    .achievement-icon {
        display: inline-block;
        width: 100px;
        height: 100px;
        background-color: #f0f0f0;
        border-radius: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 50px;
        color: #555;
        cursor: pointer;
   		border: 2px solid #000
    }
    .achievement-icon img {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }
    .achievement-item {
	    display: flex;
	    flex-direction: column;
	    align-items: center;
	    margin-bottom: 10px;
	}
	
	.achievement-title {
	    margin-top: 5px;
	    font-size: 14px;
	    text-align: center;
	}
    #modalImage {
        width: 200px;
        height: 200px;
        border-radius: 50%;
        animation: glow 2s infinite; /* 애니메이션 적용 */
        border: 3px solid #000
    }
    .modal-body {
        display: flex;
        align-items: center;
    }
    .modal-body .info {
    	flex: 1;
        text-align: center;
    }
    .modal-body .info h5 {
        margin: 0 0 10px;
    }
    .modal-body .info p {
        margin: 5px 0;
    }
    
    @keyframes glow {
        0% {
            box-shadow: 0 0 10px rgba(255, 255, 255, 0.6); /* 더 큰 그림자와 강한 색상 대비 */
        }
        50% {
            box-shadow: 0 0 30px rgba(255, 255, 255, 1); /* 더 큰 그림자와 강한 색상 대비 */
        }
        100% {
            box-shadow: 0 0 10px rgba(255, 255, 255, 0.6); /* 더 큰 그림자와 강한 색상 대비 */
        }
    }
</style>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        showAchievements();
    });
    
    function showAchievements() {
        const url = `${pageContext.request.contextPath}/user/achievement`;
        $.ajax({
            url: url,
            method: 'GET',
            success: function(data) {
                const achievementContainer = $('.achievement-icons');
                const notAchievementContainer = $('.achievement-icons-notach');
                achievementContainer.empty();
                notAchievementContainer.empty();
                data.forEach(item => {
                    const savedName = item.savedName;
                    const imgPath = `${pageContext.request.contextPath}/resources/uploadFiles/` + savedName;
                    const curCount = item.curCount;
                    const maxCount = item.maxCount;
                    const conditionName = item.conditionName;
                    const img = $('<img>').attr('src', imgPath).attr('alt', item.title).addClass('achievement-icon'); // 업적 아이콘 이미지 경로와 이름 설정
                    
                    const imgContainer = $('<div>').addClass('achievement-item');
                    imgContainer.append(img);
                    imgContainer.append($('<div>').text(item.title).addClass('achievement-title'));
                    
                    if(item.status === 'Y') {
                        achievementContainer.append(imgContainer);
                    } else {
                        notAchievementContainer.append(imgContainer);
                    }
                    img.on('click', function() {
                        $('#modalImage').attr('src', imgPath);
                        $('#modalTitle').text(item.title);
                        $('#modalDescription').text(conditionName + " 소울로그 작성 " + curCount + "/" + maxCount);
                        $('#achievementModal').modal('show');
                    });
                });
            },
            error: function(xhr, status, error) {
                console.error('Error fetching achievements:', error);
            }
        });
    }
</script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/menubar.jsp" />
<main>
	<jsp:include page="/WEB-INF/views/user/sideMenu.jsp" />
	<div class="content">
		<div class="main-content">
			<div class="achievement">
			    <h2>나의 업적</h2>
  				<p>여기서 당신의 업적을 확인하고 성취도를 확인해보세요!</p>
				<h3 style="text-align: left;">달성 업적</h3>
				<div class="achievement-icons"></div>
				<br>
				<h3 style="text-align: left;">미달성 업적</h3>
				<div class="achievement-icons-notach"></div>
			</div>
		</div>
	</div>
</main>

<!-- 모달 -->
<div class="modal fade" id="achievementModal" tabindex="-1"
	role="dialog" aria-labelledby="achievementModalLabel"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="achievementModalLabel">업적 정보</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

			<div class="modal-body">
				<img id="modalImage" />
				<div class="info">
					<h4 id="modalTitle"></h4>
					<p id="modalDescription"></p>
				</div>
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

</body>
</html>
