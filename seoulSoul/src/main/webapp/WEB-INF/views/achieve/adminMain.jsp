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
</style>


<title>관리자 메인 페이지</title>
</head>
<body>

	<jsp:include page="../common/menubar.jsp"/>
	
	<main class="grid-container">
        <section class="left-column">
            <h2>전체 회원 리스트</h2>
        </section>
        <section class="center-column">
            <h2>신고 현황</h2>
        </section>
        <section class="right-column">
            <h2>업적 관리</h2>
            <c:forEach items="${achieveLocaList}" var="bag">
				No.${bag.achNo} | 자치구 : ${bag.locationCode} | 횟수 : ${bag.maxCount} | 타이틀명 : ${bag.title}
				<a href="#"><button>수정</button></a>
				<form action="${pageContext.request.contextPath}/admin/delete" method="get">
		            <input type="hidden" name="achNo" value="${bag.achNo}">
		            <input type="hidden" name="locationCode" value="${bag.locationCode}">
		            <button type="submit">삭제</button>
		        </form>
				<hr>
			</c:forEach>
			<c:forEach items="${achieveCateList}" var="bag">
				No.${bag.achNo} | 카테고리 : ${bag.categoryCode} | 횟수 : ${bag.maxCount} | 타이틀명 : ${bag.title}
				<a href="#"><button>수정</button></a>
				<form action="${pageContext.request.contextPath}/admin/delete" method="get">
		            <input type="hidden" name="achNo" value="${bag.achNo}">
		            <input type="hidden" name="categoryCode" value="${bag.categoryCode}">
		            <button type="submit">삭제</button>
		        </form>
				<hr>
			</c:forEach>
            <a href="${pageContext.request.contextPath}/admin/achieveInsertForm"><button>업적 생성</button></a>
        </section>
    </main>

</body>
</html>