<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
<title>Insert title here</title>

	<script>
    	function updateParameters() {
    		
            var location = document.getElementById('location');
            var locationCode = location.options[location.selectedIndex].value;

            var category = document.getElementById('category');
            var categoryCode = category.options[category.selectedIndex].value;

            var searchInput = document.getElementById('searchInput').value;
            
            var searchSoulLog = document.getElementById('searchSoulLog');

            // 선택된 값과 검색어에 따라 다른 파라미터를 설정
            var actionURL = 'searchSoulLog?';
            
            if (locationCode) {
                actionURL += 'locationCode=' + locationCode + '&';
            }
            if (categoryCode) {
                actionURL += 'categoryCode=' + categoryCode + '&';
            }
            if (searchInput) {
                actionURL += 'searchWord=' + encodeURIComponent(searchInput);
            }
            
            searchSoulLog.action = actionURL;
        }
	</script>

</head>
<body>

<jsp:include page="../common/menubar.jsp"/>

<div class="content" style="margin: 30px">

	<form id="searchSoulLog" onsubmit="updateParameters()">
		<div class="search-bar" style="height: 50px; background: white">
			<span style="margin-left:20px">자치구</span>
			<select id="location" onchange="updateParameters()" style="width: 140px; height: 30px; background-color: #f0f0f0; border: 1px solid #c0c0c0;">
				<option value="0">전체</option>
				<option value="101">강남구</option>
	        	<option value="102">강동구</option>
	        	<option value="103">강북구</option>
	    	</select>
	    	<span style="margin-left:30px">카테고리</span>
			<select id="category" onchange="updateParameters()" style="width: 140px; height: 30px; background-color: #f0f0f0; border: 1px solid #c0c0c0;">
				<option value="0">전체</option>
				<option value="100">영화</option>
	        	<option value="200">연극</option>
	        	<option value="300">뮤지컬</option>
	    	</select>
	    	<span style="margin-left:30px">검색어</span>
	    	<input type="text" id="searchInput" placeholder=" 검색어 입력..." oninput="updateParameters()" style="width: 500px; height: 26px; background-color: #f0f0f0; border: 1px solid #c0c0c0;"/>
	    	<button type="submit">검색</button>
		</div> <!-- seach-bar div -->
	</form>
	
	
	<div class="card-grid" style="padding-left: 0px; padding-right: 0px;">
		<c:forEach items="${soulLogList}" var="one">
			<div class="card">
	            <img src="${pageContext.servletContext.contextPath}/resources/uploadFiles/${one.files[0].savedName}">
	            <div class="card-content">
	                <h3>${one.title}</h3>
	                <span style="margin-right:10px">${one.location.locationName}</span><span>|</span><span style="margin-left:10px">${one.category.categoryName}</span>
	                <br>
	                <br>
	                <span style="margin-right:10px">&#128153; &nbsp;${one.likes[0].likesCount}</span>
	                <span style="margin-right:10px">&#128064; &nbsp;${one.views}</span>
	                <span>&#128172; &nbsp;${one.replies[0].repliesCount}</span>
	            </div>
        	</div>
		</c:forEach>
    </div> <!-- card-grid div -->
	
	
</div> <!-- content div -->
</body>
</html>