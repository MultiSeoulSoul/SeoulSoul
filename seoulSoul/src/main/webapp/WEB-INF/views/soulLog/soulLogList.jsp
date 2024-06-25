<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
		$(function() {
			
	        $(document).ready(function(){
	            $(".card").css("cursor", "pointer");
	        });
			
		})
</script>

<c:forEach items="${soulLogList}" var="one">
	<div class="card">
		<img src="${pageContext.servletContext.contextPath}/resources/uploadFiles/${one.files[0].savedName}">
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

    
