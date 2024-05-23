<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>




<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>ユーザー基本情報変更完了</title>
	 	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
	 	<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js" type="text/javascript"></script>
		<style>

			body
			{
				font-size:large;
			}

			.background{
				background-color:WHITE;
				width:90%;
				margin:0 auto;
				min-height:100%;
				padding-bottom:20px;
				margin-bottom:10px;
			}

			.h1{
				border-bottom:3px solid #69A4D8;
			}

			.container1
			{
				width:90%;
				margin:0 auto;
				padding-top:10px;
			}

			.container2{
				height:100%;
				width:90%;
				margin:0 auto;
				margin-bottom:10px;
				padding:10px;
			}

			input[type=button]{
				border-radius:10px;
				color:#fff;
				height:40px;
				border:2px #69A4D8 solid;
				background-color:#69A4D8;
			}

			.textbox{
				border:2px #69A4D8 solid;
				border-radius:10px;
			}

			.row{
				margin-bottom:5px;
			}

		</style>
	</head>
	<body>
		<div id="headerTitle" hidden>ユーザー基本情報変更完了</div>
		<div class="background">
			<div class="container1">
				<div class="h1"><h1 id="h2">基本情報変更完了</h1></div>
			</div>
			<div class="container2">
			　　■ユーザー情報新規登録が完了しました。再ログインしてください。
			</div>
			<div class="row rowbutton">
				<input type="button" class="col-xs-offset-3 col-xs-3 button2" value="ログイン画面に戻る" id="btnログイン画面に戻る" />
			</div>
		</div>
		<script type="text/ecmascript">

			$("#btnログイン画面に戻る").click(function (){
				window.location.href = "${pageContext.request.contextPath}/UserBasicInfoChangeCompletion";
			});

		</script>
	</body>

</html>
