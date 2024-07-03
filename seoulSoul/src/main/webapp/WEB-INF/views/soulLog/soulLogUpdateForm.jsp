<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %> 
<%@ page import="com.multi.seoulsoul.soulLog.model.dto.SoulLogDTO" %>
<%@ page import="com.multi.seoulsoul.soulLog.model.dto.FilesDTO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="${pageContext.request.contextPath}/resources/img/soul_icon_favicon.png"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>서울소울 SEOUL SOUL</title>

<script type="text/javascript">
	
	$(document).ready(function() {	
		
		var beforeLocationCode = "${updateDetail.location.locationCode}";
        var beforeCategoryCode = "${updateDetail.category.categoryCode}";
		
		$.ajax({
			url: "${pageContext.servletContext.contextPath}/soulLog/locationList",
						
			success: function(data) {
				var locationSelect = $('select[name="locationCode"]');
				// 받아온 데이터로 <select> 내용채우기
				data.forEach(function(location) {
					var option = $('<option></option>')
                    .attr('value', location.locationCode)
                    .text(location.locationName);
                	// 원래 게시글의 자치구 값과 일치하면 선택 상태로 설정
                	if (location.locationCode == beforeLocationCode) {
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
                	// 원래 게시글의 카테고리 값과 일치하면 선택 상태로 설정
                	if (category.categoryCode == beforeCategoryCode) {
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


<script>
			
	function loadImg(value, num) {
		if (value.files && value.files[0]) {
			const reader = new FileReader();
			reader.onload = function(e) {
				
				switch(num){
					case 1:
						document.getElementById("img1").src = e.target.result;
						break;
					case 2:
						document.getElementById("img2").src = e.target.result;
						break;
					case 3:
						document.getElementById("img3").src = e.target.result;
						break;
					case 4:
						document.getElementById("img4").src = e.target.result;
						break;
					case 5:
						document.getElementById("img5").src = e.target.result;
						break;
				}
			}
			
			reader.readAsDataURL(value.files[0]);
			document.getElementById('status' + num).value = '-2';
			
		}
	}
			
</script>


<script>
function deleteImage(index) {
	var imgElement = document.getElementById('img' + index);
    imgElement.removeAttribute('src');
    document.getElementById('status' + index).value = '-3';
    document.getElementById('deleteBtn' + index).style.display = 'none';
}
</script>


<script>
function validateForm() {
	const formElement = document.getElementById('updateSoulLog');
    const imageTags = formElement.querySelectorAll('img');
    var imageCount = 0;

    for (var i = 0; i < imageTags.length; i++) {
        if (imageTags[i].getAttribute('src') !== null && img.getAttribute('src') !== '') {
            imageCount++;
        }
    }
    
    if (imageCount == 0) {
    	alert('소울로그에는 최소 1개의 이미지가 업로드돼야 합니다.');
        return false;
    }
    
    return true;        

}
</script>


</head>
<body>

<jsp:include page="../common/menubar.jsp"/>

<div class="content" style="margin: 30px">

	<form id="updateSoulLog" action="updateSoulLog" method="post" encType="multipart/form-data" onsubmit="return validateForm()">
		
		<div class="insert-group" style="height: 50px; background: white">
			<input type="hidden" name="soulLogNo" value="${updateDetail.soulLogNo}">
			<span style="margin-left:20px">제목</span>
			<input type="text" id="title" name="title" style="margin-top: 10px; margin-left: 15px; width: 400px; height: 25px; background-color: #f0f0f0; border: 1px solid #c0c0c0;" maxlength="20" value="${updateDetail.title}" required>
			<span style="margin-left:50px">작성자</span>
			<input type="text" style="margin-top: 10px; margin-left: 15px; width: 150px; height: 25px; background-color: #f0f0f0; border: 1px solid #c0c0c0;" value="${updateDetail.writer.nickname}" disabled>
			<span style="margin-left:50px">자치구</span>
			<select name="locationCode" style="margin-left: 15px; width: 140px; height: 30px; background-color: #f0f0f0; border: 1px solid #c0c0c0;">
			</select>
			<span style="margin-left:50px">카테고리</span>
			<select name="categoryCode" style="margin-left: 15px; width: 140px; height: 30px; background-color: #f0f0f0; border: 1px solid #c0c0c0;">
			</select>
		</div>
		
		<br>
	
		<%
    	// updateDetail을 EL에서 직접 접근하는 대신, 스크립틀릿에서 변수를 선언하여 사용
    	SoulLogDTO updateDetail = (SoulLogDTO) request.getAttribute("updateDetail");
		%>
	
		<table>
			<tr>
				
				<%
				
				List<FilesDTO> files = updateDetail.getFiles();
				
				for(int i = 0; i < files.size(); i++) {
					String savedName = files.get(i).getSavedName();
					int fileNo = files.get(i).getFileNo();
					
					%>
						<td>
							<div id="imgArea<%= (i+1) %>" style="margin-right:18px; width: 240px; height: 240px; background: white">
								<img src="${pageContext.servletContext.contextPath}/resources/uploadFiles/<%= savedName %>" id="img<%= (i+1) %>" width="240px" height="240px">
								<input type="file" id="imgInput<%= (i+1) %>" name="imgList" onchange="loadImg(this,<%= (i+1) %>)">
								<input type="hidden" id="filedNo<%= (i+1) %>" name="fileNo[]" value="<%= fileNo %>">
								<input type="hidden" id="status<%= (i+1) %>" name="status[]" value="-1">
								<div id="deleteBtn<%= (i+1) %>">
									<button type="button" onclick="deleteImage(<%= (i+1) %>)">삭제</button>
								</div>
							</div>
						</td>
					<%
				
					}
				
				for(int i = files.size(); i < 5; i++) {
				%>
					<td>
						<div id="imgArea<%= (i+1) %>" style="margin-right:18px; width: 240px; height: 240px; background: white">
							<img id="img<%= (i+1) %>" width="240px" height="240px">
							<input type="file" id="imgInput<%= (i+1) %>" name="imgList" onchange="loadImg(this, <%= (i+1) %>)">
							<input type="hidden" id="filedNo<%= (i+1) %>" name="fileNo[]" value="0">
							<input type="hidden" id="status<%= (i+1) %>" name="status[]" value="-1">
						</div>
					</td>
				<%
				}
				%>
				
			</tr>
		</table>
		
		<br>
		<br>
		<br>
		<br>
		
		<div class="insert-group" style="height: 270px; background: white">
			<br>
			<span style="margin-left:20px">내용</span>
			<br>
			<textarea id="content" name="content" style="margin-top: 10px; margin-left: 15px; width: 1230px; height: 200px; background-color: #f0f0f0; border: 1px solid #c0c0c0; resize: none;" maxlength="500" required>${updateDetail.content}</textarea>
		</div>
		
		<br>
		<br>
		
		<div align="center">
			<button class="btns" type="submit" style="width: 110px; height: 35px; background: #3982BC; color: white; border: 1px solid #c0c0c0;">로그 수정</button>
		</div>
		
		
	</form>
	
</div> <!-- content div -->

</body>
</html>