<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Main</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
<style>
    /* 페이지 레이아웃 설정 */
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
    .side-menu {
        width: 100px;
        background-color: #e0f7fa; /* 배경 색상 */
        border-right: 1px solid #ddd; /* 오른쪽 경계 */
        padding: 20px;
    }
    .side-menu ul {
        list-style: none;
        padding: 0;
        margin: 0;
    }
    .side-menu ul li {
        margin: 10px 0;
    }
    .side-menu ul li a {
        text-decoration: none;
        color: #333;
        font-weight: bold;
        display: block;
        padding: 10px;
        border-radius: 4px;
        transition: background-color 0.3s;
    }
    .side-menu ul li a:hover {
        background-color: #b2ebf2; /* 호버 시 배경 색상 */
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
    }
    .profile-info {
        display: flex;
        align-items: center;
        gap: 15px;
    }
    .profile-img {
        width: 80px;
        height: 80px;
        border-radius: 50%;
        border: 1px solid #ddd;
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
        background-color: #b2ebf2; /* 호버 시 배경 색상 */
    }
    .edit-btn {
        background-color: #5c9eaf;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
    .chart, .board, .achievement {
        background-color: white;
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
    .pagination {
        display: flex;
        justify-content: center;
        gap: 5px;
    }
    .pagination a {
        text-decoration: none;
        color: #5c9eaf;
        padding: 8px 12px;
        border: 1px solid #5c9eaf;
        border-radius: 4px;
    }
    .pagination a.active {
        background-color: #5c9eaf;
        color: white;
    }
</style>
<script>
    function showBoard(boardType, url) {
        const boardList = document.querySelectorAll('.board-list tbody tr');
        boardList.forEach((row) => {
            row.style.display = (row.classList.contains(boardType)) ? '' : 'none';
        });

        // Ajax 요청으로 게시글 가져오기
        if (url) {
            fetch(url)
                .then(response => response.json())
                .then(data => {
                    const tbody = document.querySelector('.board-list tbody');
                    tbody.innerHTML = ''; // 기존 내용 제거
                    data.forEach(item => {
                        const tr = document.createElement('tr');
                        tr.classList.add(boardType);
                        tr.innerHTML = `
                            <td>${item.soulLogNo}</td>
                            <td>${item.locationCode}</td>
                            <td>${item.categoryCode}</td>
                            <td>${item.title}</td>
                            <td>${item.content}</td>
                            <td>${item.createdDate}</td>
                            <td>${item.views}</td>
                        `;
                        tbody.appendChild(tr);
                    });
                });
        }
    }
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/menubar.jsp" />
<main>
    <div class="side-menu">
        <ul>
            <li><a href="#">회원정보 수정</a></li>
            <li><a href="#">소울 보기</a></li>
            <li><a href="#">업적 보기</a></li>
            <li><a href="#">소울로그 보기</a></li>
            <li><a href="#">댓글 보기</a></li>
            <li><a href="#">좋아요 보기</a></li>
            <li><a href="#">문의내역 보기</a></li>
            <li><a href="#">회원 탈퇴</a></li>
        </ul>
    </div>
    <div class="content">
        <div class="main-content">
            <div class="profile">
                <div class="profile-info">
                    <img src="profile.jpg" alt="프로필 사진" class="profile-img">
                    <div>
                        <h2>{achievement_title} <sec:authentication property="name"/></h2>
                        <p>{user_description}</p>
                    </div>
                </div>
                <button class="edit-btn" onclick="changeProfilePicture()">수정하기</button>
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
                <!-- 추가적인 차트 아이템들 -->
            </div>
            <div class="board">
                <ul class="board-tabs">
                    <li><a href="javascript:void(0);" onclick="showBoard('soul-log', '${pageContext.request.contextPath}/user/SLBoardPage');">소울로그</a></li>
                    <li><a href="javascript:void(0);" onclick="showBoard('soul-log-comment')">소울로그 댓글</a></li>
                    <li><a href="javascript:void(0);" onclick="showBoard('event-comment')">이벤트 댓글</a></li>
                    <li><a href="javascript:void(0);" onclick="showBoard('inquiry')">문의내역</a></li>
                    <li><a href="javascript:void(0);" onclick="showBoard('like')">좋아요</a></li>
                    <li><a href="javascript:void(0);" onclick="showBoard('favorite')">찜</a></li>
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
                    	<c:forEach items="${list}" var="sl_board">
	                        <tr class="soul-log">
	                            <td>${sl_board.soulLogNo}</td>
	                            <td>${sl_board.locationCode}</td>
	                            <td>${sl_board.categoryCode}</td>
	                            <td>${sl_board.title}</td>
	                            <td>${sl_board.content}</td>
	                            <td>${sl_board.createdDate}</td>
	                            <td>${sl_board.views}</td>
	                        </tr>
                        </c:forEach>
                        <tr class="soul-log-comment" style="display:none;">
                            <td>2</td>
                            <td>서울</td>
                            <td>댓글</td>
                            <td>소울로그 댓글 제목1</td>
                            <td>5</td>
                            <td>2023-06-01</td>
                            <td>50</td>
                        </tr>
                        <tr class="event-comment" style="display:none;">
                            <td>3</td>
                            <td>서울</td>
                            <td>이벤트</td>
                            <td>이벤트 댓글 제목1</td>
                            <td>3</td>
                            <td>2023-06-01</td>
                            <td>30</td>
                        </tr>
                        <tr class="inquiry" style="display:none;">
                            <td>4</td>
                            <td>서울</td>
                            <td>문의</td>
                            <td>문의 제목1</td>
                            <td>1</td>
                            <td>2023-06-01</td>
                            <td>10</td>
                        </tr>
                        <tr class="like" style="display:none;">
                            <td>5</td>
                            <td>서울</td>
                            <td>좋아요</td>
                            <td>좋아요 제목1</td>
                            <td>20</td>
                            <td>2023-06-01</td>
                            <td>200</td>
                        </tr>
                        <tr class="favorite" style="display:none;">
                            <td>6</td>
                            <td>서울</td>
                            <td>찜</td>
                            <td>찜 제목1</td>
                            <td>2</td>
                            <td>2023-06-01</td>
                            <td>20</td>
                        </tr>
                    </tbody>
                </table>
                <div class="pagination">
	               	<%int slPage = 1;
	               	  slPage = Integer.parseInt(request.getParameter("page"));
	               	  if (slPage == 1) {%>
                    	<a href="SLBoardPage?page=<%=slPage-1%>">이전</a>
                    <%} else {%>
                    	<a href="SLBoardPage?page=<%=slPage-1%>">이전</a> <!-- disabled 알아보기 -->
                    <%}%>
                    <%int slPages = (int) request.getAttribute("pages");
                      for (int i = 1; i <= slPages; i++) {%>
                   		<a href="SLBoardPage?page=<%=i%>"><%=i%></a>
                    <%}%>
                    	<a href="SLBoardPage?page=<%=slPage + 1%>">다음</a> <!-- disabled 추가하기 -->
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
