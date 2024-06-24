<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            width: 200px; /* 사이드 메뉴 박스 너비 */
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
            padding: 20px;
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
</head>
<body>
    <!-- 메뉴바 포함 -->
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
                            <h2>{title_title} ({user_nickname})</h2>
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
                        <li><a href="#">소울로그</a></li>
                        <li><a href="#">소울로그 댓글</a></li>
                        <li><a href="#">이벤트 댓글</a></li>
                        <li><a href="#">문의내역</a></li>
                        <li><a href="#">좋아요</a></li>
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
                            <tr>
                                <td>1</td>
                                <td>{Location}</td>
                                <td>{Category}</td>
                                <td>{Title}</td>
                                <td>{Reply_count}</td>
                                <td>{Created_date}</td>
                                <td>{Count}</td>
                            </tr>
                            <!-- 추가적인 게시물 -->
                        </tbody>
                    </table>
                    <div class="pagination">
                        <a href="#">1</a>
                        <a href="#">2</a>
                        <a href="#">3</a>
                        <a href="#">4</a>
                        <a href="#">next</a>
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
