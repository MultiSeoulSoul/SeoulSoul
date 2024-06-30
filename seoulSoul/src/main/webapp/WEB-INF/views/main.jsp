<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SeoulSoul</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
    <script src="https://d3js.org/d3.v7.min.js"></script>
    <script src="https://unpkg.com/topojson@3"></script>
    <style>
        .district {
            stroke: #D0D0D0;
            stroke-width: 0.4px;
            cursor: pointer;
        }
        .district:hover {
            opacity: 0.8;
        }
        
        #soulLevel {
		    position: absolute;
		    bottom: 1px;
		    right: 1px;
		    margin-bottom: 80px;
		    margin-right: 240px;
        }

    </style>
</head>
<body>
<jsp:include page="common/menubar.jsp"/>
    <div id="map" align="center" style="margin-top: 20px; margin-bottom: 30px;">
    
    <div id="soulLevel">
    	<img src="${pageContext.request.contextPath}/resources/img/soulLevel.png" style="width: 80px">
    </div>
    
    </div>
    <script>
        const width = 800;
        const height = 550;

        const svg = d3.select("#map")
            .append("svg")
            .attr("width", width)
            .attr("height", height);

        const projection = d3.geoMercator()
            .center([126.9895, 37.5651])
            .scale(90000)
            .translate([width / 2, height / 2]);

        const path = d3.geoPath().projection(projection);

        var userStats = [
            <c:forEach var="item" items="${userStats}" varStatus="status">
                {
                    name: "${item.locationName}",
                    exp: ${item.exp}
                   
                }<c:if test="${!status.last}">,</c:if>
            </c:forEach>
        ];
 
        var locationList = [
            <c:forEach var="item" items="${locationList}" varStatus="status">
                {
                    name: "${item.locationName}",
                    code: ${item.locationCode}
                   
                }<c:if test="${!status.last}">,</c:if>
            </c:forEach>
        ];
        
        
        const colorScale = d3.scaleOrdinal(d3.schemeSet3);

        d3.json("https://raw.githubusercontent.com/southkorea/seoul-maps/master/kostat/2013/json/seoul_municipalities_topo_simple.json")
            .then(function(topology) {
                const geojson = topojson.feature(topology, topology.objects.seoul_municipalities_geo);

                svg.selectAll("path")
                    .data(geojson.features)
                    .enter()
                    .append("path")
                    .attr("d", path)
                    .attr("class", "district")
                    .attr("fill", function(d, i) {
                    	var stats = userStats.find(function(item){
                    		return item.name === d.properties.name;
                    	})
                    	var colorCode = '#FFFFFF';
 
                    	if(stats == undefined){
                    		return colorCode;
                    	}
                    	

                    	//color code 배정
                    	if(stats.exp > 0 && stats.exp <= 100) {
                    		colorCode='#CBF7FC';
                    	}
                    	else if(stats.exp > 100 && stats.exp <= 200) {
                    		colorCode='#80DEEA';
                    	}
						else if(stats.exp > 200 && stats.exp <= 300) {
							colorCode='#48C5D5';
                    	}
						else if(stats.exp > 300 && stats.exp <= 400) {
							colorCode='#2999A8';
                    	}
						else if(stats.exp > 400) {
                    		colorCode='#3A828B';
                    	}
                    	
                    	return colorCode;
     
                    })
                    .on("click", function(event, d) {
                    	var location = locationList.find(function(item){
                    		return item.name === d.properties.name;
                    	})
                    	window.location.href = "${pageContext.servletContext.contextPath}/soulLog/soulLogMain?locationCode="+location.code+"&page=1";
                    });

                // 구 이름 추가
                svg.selectAll("text")
                    .data(geojson.features)
                    .enter()
                    .append("text")
                    .attr("transform", function(d) {
                    	var xy = path.centroid(d);
                    	xy[0]-=5;
                    	xy[1]+=3;
                        return "translate(" + xy + ")";
                    })
                    .attr("dy", ".35em")
                    .attr("text-anchor", "middle")
                    .style("font-size", "10px")
                    .style("fill", "black")
                    .text(function(d) {
                        return d.properties.name;
                    });
            });
    </script>
</body>
</html>
