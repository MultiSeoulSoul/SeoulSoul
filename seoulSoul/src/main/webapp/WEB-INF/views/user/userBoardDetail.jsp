<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.js"></script>
<style>
	main {
		display: flex;
		flex: 1;
		overflow: hidden;
	}
	.content {
		flex: none;
		width: 800px;
		flex: 1;
		overflow: hidden;
	}
	.main-content {
		flex: 1;
		padding: 0px 10px 0px 10px;
		overflow: auto;
	}
    .board {
        background-color: white;
        padding: 20px;
        border: 1px solid #ddd;
        border-radius: 8px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        margin-bottom: 20px;
        text-align: center;
    }
    .board h2 {
        margin-bottom: 20px;
    }
    .board p {
        margin-bottom: 20px;
        color: #666;
    }
    .board-tabs {
        display: flex;
        gap: 10px;
        padding: 0;
        margin: 0;
        list-style: none;
        justify-content: center;
        margin-bottom: 20px;
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
        transition: background-color 0.3s, color 0.3s;
        display: block;
        border: 1px solid #ddd;
    }
    .board-tabs li a:hover, .board-tabs li a.active {
        background-color: #b2ebf2;
        color: #00796b;
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
    .pagination {
        margin-top: 20px;
        display: flex;
        justify-content: center;
        gap: 5px;
    }
    .pagination a {
        text-decoration: none;
        color: #333;
        padding: 5px 10px;
        border: 1px solid #ddd;
        border-radius: 4px;
        transition: background-color 0.3s;
    }
    .pagination a:hover {
        background-color: #b2ebf2;
    }
</style>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        showBoard('soul-log', 1);
        activateTab('soul-log');
    });

    function activateTab(boardType) {
        const tabs = document.querySelectorAll('.board-tabs li a');
        tabs.forEach(tab => {
            if (tab.getAttribute('onclick').includes(boardType)) {
                tab.classList.add('active');
            } else {
                tab.classList.remove('active');
            }
        });
    }

    function showBoard(boardType, page = 1) {
        const url = `${pageContext.request.contextPath}/user/` + boardType + "Page?page=" + page;
        $.ajax({
            url: url,
            method: 'GET',
            dataType: 'json',
            success: function(data) {
                const tbody = document.querySelector('.board-list tbody');
                tbody.innerHTML = '';

                if (boardType === 'soul-log') {
                    renderSoulLog(data.slBoard, tbody);
                } else if (boardType === 'soul-reply') {
                    renderSoulLogReply(data.slReply, tbody);
                } else if (boardType === 'event-reply') {
                    renderEventReply(data.eventReply, tbody);
                } else if (boardType === 'likes') {
                    renderLikes(data.likes, tbody);
                } else if (boardType === 'heart-btn') {
                    renderHeartBtn(data.heartBtn, tbody);
                } else if (boardType === 'cs-question') {
                    renderCsQuestion(data.csQuestion, tbody);
                } else if (boardType === 'report') {
                    renderReport(data.report, tbody);
                }

                updateTableHeader(boardType);

                const pages = data.pages || 1;
                updatePagination(pages, boardType);
                activateTab(boardType);
            },
            error: function(xhr, status, error) {
                console.error('Error fetching data:', error);
            }
        });
    }

    function renderSoulLog(data, tbody) {
        data.forEach(item => {
            const row = document.createElement('tr');

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

            const soulLogNo = document.createElement('td');
            soulLogNo.textContent = item.title;
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

    function renderEventReply(data, tbody) {
        data.forEach(item => {
            const row = document.createElement('tr');

            const title = document.createElement('td');
            title.textContent = item.title;
            row.appendChild(title);

            const content = document.createElement('td');
            content.textContent = item.content;
            row.appendChild(content);

            tbody.appendChild(row);
        });
    }

    function renderLikes(data, tbody) {
        data.forEach(item => {
            const row = document.createElement('tr');

            const locationName = document.createElement('td');
            locationName.textContent = item.locationName;
            row.appendChild(locationName);

            const categoryName = document.createElement('td');
            categoryName.textContent = item.categoryName;
            row.appendChild(categoryName);

            const title = document.createElement('td');
            title.textContent = item.title;
            row.appendChild(title);

            const likedDate = document.createElement('td');
            likedDate.textContent = item.likedDate;
            row.appendChild(likedDate);

            tbody.appendChild(row);
        });
    }

    function renderHeartBtn(data, tbody) {
        data.forEach(item => {
            const row = document.createElement('tr');

            const title = document.createElement('td');
            title.textContent = item.title;
            row.appendChild(title);

            const createdDate = document.createElement('td');
            createdDate.textContent = item.createdDate;
            row.appendChild(createdDate);

            tbody.appendChild(row);
        });
    }

    function renderCsQuestion(data, tbody) {
        data.forEach(item => {
            const row = document.createElement('tr');

            const categoryName = document.createElement('td');
            categoryName.textContent = item.categoryName;
            row.appendChild(categoryName);

            const title = document.createElement('td');
            title.textContent = item.title;
            row.appendChild(title);

            const createdDate = document.createElement('td');
            createdDate.textContent = item.createdDate;
            row.appendChild(createdDate);

            const isAnswered = document.createElement('td');
            isAnswered.textContent = item.isAnswered;
            row.appendChild(isAnswered);

            tbody.appendChild(row);
        });
    }

    function renderReport(data, tbody) {
        data.forEach(item => {
            const row = document.createElement('tr');

            const reason = document.createElement('td');
            reason.textContent = item.reason;
            row.appendChild(reason);

            const title = document.createElement('td');
            title.textContent = item.title;
            row.appendChild(title);

            const createdDate = document.createElement('td');
            createdDate.textContent = item.createdDate;
            row.appendChild(createdDate);

            const reportReply = document.createElement('td');
            reportReply.textContent = item.reportReply;
            row.appendChild(reportReply);

            tbody.appendChild(row);
        });
    }

    function updateTableHeader(boardType) {
        const headerRow = document.querySelector('.board-list thead tr');
        headerRow.innerHTML = '';

        let headers = [];
        switch (boardType) {
            case 'soul-log':
                headers = ['지역', '카테고리', '소울로그 제목', '댓글수', '작성시간', '조회수'];
                break;
            case 'soul-reply':
                headers = ['소울로그 제목', '댓글 내용', '작성시간'];
                break;
            case 'event-reply':
                headers = ['이벤트 제목', '댓글 내용'];
                break;
            case 'likes':
                headers = ['지역', '카테고리', '좋아요한 소울로그 제목', '작성시간'];
                break;
            case 'heart-btn':
                headers = ['찜한 이벤트 제목', '작성시간'];
                break;
            case 'cs-question':
                headers = ['카테고리', '문의 제목', '작성시간', '답변 여부'];
                break;
            case 'report':
                headers = ['신고 사유', '신고 제목', '신고 시간', '답변 여부'];
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
            pageLink.href = 'javascript:void(0);';
            pageLink.textContent = page;
            pageLink.onclick = () => showBoard(boardType, page);
            pagination.appendChild(pageLink);
        }
    }
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp" />
	<main>
		<jsp:include page="/WEB-INF/views/user/sideMenu.jsp" />
		<div class="content">
			<div class="main-content">

				<div class="board">
					<h2>나의 활동</h2>
					<p>여기서 당신의 활동을 확인해보세요!</p>
					<ul class="board-tabs">
						<li><a href="javascript:void(0);"
							onclick="showBoard('soul-log', 1);">소울로그</a></li>
						<li><a href="javascript:void(0);"
							onclick="showBoard('soul-reply', 1);">소울로그 댓글</a></li>
						<li><a href="javascript:void(0);"
							onclick="showBoard('event-reply', 1);">이벤트 댓글</a></li>
						<li><a href="javascript:void(0);"
							onclick="showBoard('likes', 1);">좋아요</a></li>
						<li><a href="javascript:void(0);"
							onclick="showBoard('heart-btn', 1);">찜</a></li>
						<li><a href="javascript:void(0);"
							onclick="showBoard('cs-question', 1);">문의내역</a></li>
						<li><a href="javascript:void(0);"
							onclick="showBoard('report', 1);">신고내역</a></li>
					</ul>
					<table class="board-list">
						<thead>
							<tr></tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<div class="pagination"></div>
				</div>
			</div>
		</div>
	</main>
</body>
</html>
