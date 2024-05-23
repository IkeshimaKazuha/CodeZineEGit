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
			#LeftMenu{
				float:left;
				width:12%;
				padding-left:10px;
			}
			#body {
			    float: right;
			    width: 88%;
			}

 			#test1{
				display:none;
			}
		</style>
		<script type="text/javascript">

		</script>

	</head>
	<body>
	    <div id="header">
	        <tiles:insertAttribute name="header" /> <!-- (4) -->
	    </div>
	    <div id="body">
	        <tiles:insertAttribute name="body" /> <!-- (5) -->
	    </div>
<!-- 	    <div id="divdiv">
	    </div> -->
 	    <div id="LeftMenu"><tiles:insertAttribute name="LeftMenu" /></div>


<%-- 	<div id="test1">
	    	<div id="LeftMenu"><tiles:insertAttribute name="LeftMenu" /></div>
	    </div> --%>



<%-- 	 <c:if test="request.getContextPath == ${pageContext.request.contextPath}/Find">		</c:if> --%>

	    <script>
	    	var myh2 = document.getElementById("headerTitle");
	    	//alert(myh2.innerHTML);
	    	document.getElementById("title1").innerHTML = myh2.innerHTML;

<%--  			window.onload=function(){
				var url = location.href ;
				/* alert(url.indexOf("${pageContext.request.contextPath}/Find")); */
				if(url.indexOf("${pageContext.request.contextPath}/Find") > 0){
					/* $('#divdiv').prepend('<div id="LeftMenu"><tiles:insertAttribute name="LeftMenu" /></div>'); */
					/* divdiv.insertAdjacentHTML('afterbegin','<div id="LeftMenu"><tiles:insertAttribute name="LeftMenu" /></div>'); */
					/* divdiv.insertAdjacentHTML('afterbegin','<div>AfterBegin</div>'); */
					/* alert(url +"*"+"${pageContext.request.contextPath}/Find"); */
					document.getElementById("test1").style.display="block";
				}
			};--%>
		</script>
	</body>
</html>