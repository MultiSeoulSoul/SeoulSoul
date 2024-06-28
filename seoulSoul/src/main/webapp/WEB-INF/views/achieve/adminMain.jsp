<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainDesign.css">

<style>
    .grid-container {
        display: grid;
        grid-template-columns: 1fr 1fr 1fr;
        gap: 20px;
        padding: 20px;
    }

    .grid-container section {
        background-color: #ffffff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    .half-section {
        margin-top: 20px;
    }

    .tab-container {
        display: flex;
        justify-content: flex-start;
        margin-bottom: 20px;
    }

    .tab-container button {
        background-color: #f1f1f1;
        border: none;
        padding: 10px 20px;
        cursor: pointer;
        margin-right: 10px;
    }

    .tab-container button.active {
        background-color: #ddd;
        font-weight: bold;
    }

    .tab-content {
        display: none;
    }

    .tab-content.active {
        display: block;
    }

    .button-group {
        display: inline-block;
    }

    .pagination {
        display: flex;
        justify-content: center;
        margin-top: 20px;
    }

    .pagination a {
        margin: 0 5px;
        text-decoration: none;
        color: #333;
    }

    .pagination a.active {
        font-weight: bold;
    }
</style>

<title>관리자 메인 페이지</title>
</head>
<body>

    <jsp:include page="../common/menubar.jsp"/>
    
    <main class="grid-container">
        <section class="left-column">
            <h2>전체 회원 리스트</h2>
            <input type="text" placeholder="검색어를 입력하세요">
            <button>검색</button>
            <br><hr>
            <c:forEach items="${userList}" var="bag">
                No.${bag.userNo} | name : ${bag.nickname} | 
                <button>강등</button> 
                <button>블랙</button> 
                <button>탈퇴</button>
                <hr>
            </c:forEach>
            <div class="pagination">
                <c:forEach begin="1" end="${totalPages}" var="page">
                    <a href="?page=${page}" class="${currentPage == page ? 'active' : ''}">${page}</a>
                </c:forEach>
            </div>
        </section>
        <section class="center-column">
            <div class="full-section">
                <h2>신고 현황</h2>
                <div id="report" class="report-content active">
                    <c:forEach items="${reportList}" var="bag">
                        <a href="${pageContext.request.contextPath}/report/reportDetail?reportNo=${bag.reportNo}">
                            No.${bag.reportNo} | 신고제목 : ${bag.title} | 신고자 : ${bag.reporter.nickname} | 게시글 : ${bag.reportedSoulLog.title}
                        </a>
                        <hr>
                    </c:forEach>
                </div>
                <div class="pagination">
                    <c:forEach begin="1" end="${totalReportPages}" var="page">
                        <a href="?reportPage=${page}" class="${currentReportPage == page ? 'active' : ''}">${page}</a>
                    </c:forEach>
                </div>
            </div>
            <div class="half-section">
                <h2>블랙리스트</h2>
                <c:forEach items="${blacklist}" var="bag">
                    ${bag.nickname} | 블랙리스트에 등록된 날짜: ${bag.blacklistDate} 
                    <button>해제</button>
                    <hr>
                </c:forEach>
                <div class="pagination">
                    <c:forEach begin="1" end="${totalBlacklistPages}" var="page">
                        <a href="?blacklistPage=${page}" class="${currentBlacklistPage == page ? 'active' : ''}">${page}</a>
                    </c:forEach>
                </div>
            </div>
        </section>
        <section class="right-column">
            <h2>업적 관리</h2>
            <div class="tab-container">
                <button class="tab-button active" onclick="showTabContent('loca')">자치구 업적</button>
                <button class="tab-button" onclick="showTabContent('cate')">카테고리 업적</button>
            </div>
            <div id="loca" class="tab-content active">
                <c:forEach items="${achieveLocaList}" var="bag">
                    No.${bag.achNo} | 자치구 : ${bag.locationCode} | 횟수 : ${bag.maxCount} | 타이틀명 : ${bag.title}
                    <div class="button-group">
                        <a href="${pageContext.request.contextPath}/admin/achLocaUpdateForm"><button>수정</button></a>
                        <form action="${pageContext.request.contextPath}/admin/deleteLoca" method="get" style="display:inline;">
                            <input type="hidden" name="achNo" value="${bag.achNo}">
                            <input type="hidden" name="locationCode" value="${bag.locationCode}">
                            <button type="submit">삭제</button>
                        </form>
                    </div>
                    <hr>
                </c:forEach>
            	<a href="${pageContext.request.contextPath}/admin/achLocaInsertForm"><button>업적 생성</button></a>
            </div>
            <div id="cate" class="tab-content">
                <c:forEach items="${achieveCateList}" var="bag">
                    No.${bag.achNo} | 카테고리 : ${bag.categoryCode} | 횟수 : ${bag.maxCount} | 타이틀명 : ${bag.title}
                    <div class="button-group">
                        <a href="${pageContext.request.contextPath}/admin/achCateUpdateForm"><button>수정</button></a>
                        <form action="${pageContext.request.contextPath}/admin/deleteCate" method="get" style="display:inline;">
                            <input type="hidden" name="achNo" value="${bag.achNo}">
                            <input type="hidden" name="categoryCode" value="${bag.categoryCode}">
                            <button type="submit">삭제</button>
                        </form>
                    </div>
                    <hr>
                </c:forEach>
            	<a href="${pageContext.request.contextPath}/admin/achCateInsertForm"><button>업적 생성</button></a>
            </div>
        </section>
    </main>

    <script>
        function showTabContent(tab) {
            var tabs = document.getElementsByClassName('tab-content');
            for (var i = 0; i < tabs.length; i++) {
                tabs[i].classList.remove('active');
            }
            document.getElementById(tab).classList.add('active');

            var buttons = document.getElementsByClassName('tab-button');
            for (var i = 0; i < buttons.length; i++) {
                buttons[i].classList.remove('active');
            }
            document.querySelector('[onclick="showTabContent(\'' + tab + '\')"]').classList.add('active');
        }
    </script>

</body>
</html>

