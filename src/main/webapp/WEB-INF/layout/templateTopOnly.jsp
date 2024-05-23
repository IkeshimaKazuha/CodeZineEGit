<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html class="no-js">
	<head>
		<style>
			body{
				background-color:white;
			}
			#body {
			    width: 100%;
			}
		</style>
	</head>
	<body>
	    <div id="header">
	        <tiles:insertAttribute name="header" /> <!-- (4) -->
	    </div>
	    <div id="body">
	        <tiles:insertAttribute name="body" /> <!-- (5) -->
	    </div>

	    <script>
	    	var myh2 = document.getElementById("headerTitle");
	    	document.getElementById("title1").innerHTML = myh2.innerHTML;

		</script>
	</body>
</html>