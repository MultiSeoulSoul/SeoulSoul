<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서울소울 SEOUL SOUL</title>
<link rel="icon" href="${pageContext.request.contextPath}/resources/img/soul_icon_favicon.png"/>
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

</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp" />
	<main>
		<jsp:include page="/WEB-INF/views/user/sideMenu.jsp" />
		<div class="content">
			<div class="main-content">		
				<jsp:include page="/WEB-INF/views/user/userBoard.jsp" />
			</div>
		</div>
	</main>
</body>
</html>
