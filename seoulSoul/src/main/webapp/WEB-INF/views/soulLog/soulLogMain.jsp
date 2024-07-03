<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="${pageContext.request.contextPath}/resources/img/soul_icon_favicon.png"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>서울소울 SEOUL SOUL</title>
	
<style>
	.card {
    	cursor: pointer;
	}
</style>	

<script type="text/javascript">
		
	const filterLocationCode = "${filter.locationCode}";
	const filterCategoryCode = "${filter.categoryCode}";
	const filterSearchWord = "${filter.searchWord}";
		
	
	function selectSoulLogList(page) {
		window.location.href = "${pageContext.servletContext.contextPath}/soulLog/soulLogMain?locationCode="+filterLocationCode+"&categoryCode="+filterCategoryCode+"&searchWord="+filterSearchWord+"&page="+page;
	}
	
	
	$(function() {
			
		
		$('.card-grid').on('click', 'div.card', function() {
			// 현재 div.card 내의 input 태그의 value 속성 가져오기
			var url = $(this).find('input').attr('value');
			// url이 존재하면 해당 URL로 이동
			if (url) {
				window.location.href = url;
			}
		});

		
		$.ajax({
			url: "${pageContext.servletContext.contextPath}/soulLog/locationList",
							
			success: function(data) {
				var locationSelect = $('select[name="locationCode"]');
				// 받아온 데이터로 <select> 내용채우기
				data.forEach(function(location) {
					var option = $('<option></option>')
                    .attr('value', location.locationCode)
                    .text(location.locationName);
					
					console.log(filterLocationCode);
					
					if(location.locationCode == filterLocationCode) {
						option.attr('selected', 'selected');
					}
					
					locationSelect.append(option);
				});
			},
			error: function() {
				alert('자치구 목록을 불러오는 데 실패했습니다.');
			}
		})
			
		$.ajax({
			url: "${pageContext.servletContext.contextPath}/soulLog/categoryList",
							
			success: function(data) {
				var categorySelect = $('select[name="categoryCode"]');
				// 받아온 데이터로 <select> 내용채우기
				data.forEach(function(category) {
					var option = $('<option></option>')
                    .attr('value', category.categoryCode)
                    .text(category.categoryName);
					
					if(category.categoryCode == filterCategoryCode) {
						option.attr('selected', 'selected');
					}
					
					categorySelect.append(option);
				});
			},
			error: function() {
				alert('카테고리 목록을 불러오는 데 실패했습니다.');
			}
		})
						
	})
</script>

</head>
<body>

<jsp:include page="../common/menubar.jsp"/>

<div class="content" style="margin: 30px">

	<form id="search" action="soulLogMain" method="get">
		<div class="search-bar" style="height: 50px; background: white">
			<span style="margin-left:20px">자치구</span>
			<select name="locationCode" style="width: 140px; height: 30px; background-color: #f0f0f0; border: 1px solid #c0c0c0;">
				<option value="0">전체</option>
	    	</select>
	    	<span style="margin-left:30px">카테고리</span>
			<select name="categoryCode" style="width: 140px; height: 30px; background-color: #f0f0f0; border: 1px solid #c0c0c0;">
	    		<option value="0">전체</option>
	    	</select>
	    	<span style="margin-left:30px">제목 검색</span>
	    	<input type="text" name="searchWord" placeholder=" 검색어 입력..." style="width: 500px; height: 26px; background-color: #f0f0f0; border: 1px solid #c0c0c0;" value="${filter.searchWord}"
	    	/>
	    	<input type="hidden" name="page" value="1">
	    	<button type="submit">검색</button>
		</div> <!-- seach-bar div -->
	</form>
	
	<br>

	<div align="right">
		<a href="soulLogInsertForm">
			<button style="width: 110px; height: 35px; background: #3982BC; color: white; border: 1px solid #c0c0c0; cursor: pointer;">소울로그 작성</button>
		</a>
	</div>
	
	<div class="card-grid" style="padding-left: 0px; padding-right: 0px;">
		<c:forEach items="${soulLogList}" var="one">
			<div class="card">
	            <img src="${pageContext.servletContext.contextPath}/resources/uploadFiles/${one.files[0].savedName}" style="width: 241px; height: 241px;">
	            <div class="card-content">
	                <h3>${one.title}<input type="hidden" value="soulLogDetail?soulLogNo=${one.soulLogNo}"></h3>
	                <span style="margin-right:10px">${one.location.locationName}</span><span>|</span><span style="margin-left:10px">${one.category.categoryName}</span>
	                <br>
	                <br>
	                <span style="margin-right:10px">&#128153; &nbsp;${one.likesCount}</span>
	                <span style="margin-right:10px">&#128064; &nbsp;${one.views}</span>
	                <span>&#128172; &nbsp;${one.repliesCount}</span>
	            </div>
        	</div>
		</c:forEach>
    </div> <!-- card-grid div -->
    
    <br>
    
    <div class="pagination">
    	<%
    	int pages = (int) request.getAttribute("pages");
    	for(int p = 1; p <= pages; p++) {
    	%>
    	<button class="pages" onclick="selectSoulLogList(<%=p%>)" style="width: 30px; height: 30px; background: #3982BC; color: white; border: 1px solid #c0c0c0; cursor: pointer;"><%=p%></button>
    	<% 
    	}
    	%>
    </div>
	
	
</div> <!-- content div -->
</body>
</html>