<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>




<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>ユーザー基本情報変更</title>
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
		<div id="headerTitle" hidden>ユーザー基本情報変更</div>
		<div class="background">
			<div class="container1">
				<div class="h1"><h1 id="h2">基本情報変更</h1></div>
			</div>
			<div class="container2">
				<div class="row">
					<div class="col-xs-offset-1 col-xs-2 ユーザーID">ユーザーID</div>
					<div class="col-xs-offset-1 col-xs-2" id="userId">${userBasicInfoModel.getUserId()}</div>
				</div>
				<form method="post" action="Find" id="Form1">
			 		<div class="row">
						<div class="col-xs-offset-1 col-xs-3">メールアドレス</div>
						<input type="text" class="col-xs-6 textbox" name="userId" id="email"/>
					</div>
					<div class="row">
						<div class="col-xs-offset-1 col-xs-3">旧パスワード</div>
						<input type="password" class="col-xs-6 textbox password" name="userId" id="oldPassword"/>
					</div>
					<div class="row">
						<div class="col-xs-offset-1 col-xs-3">新パスワード</div>
						<input type="password" class="col-xs-6 textbox password" name="userId" id="newPassword" />
					</div>
					<div class="row">
						<div class="col-xs-offset-1 col-xs-3">新パスワード確認</div>
						<input type="password" class="col-xs-6 textbox password" name="userId" id="newPasswordConfirm" />
					</div>
					<div class="row">
						<div  class="col-xs-offset-4 col-xs-8">
						<input type="checkbox" id="checkbox" name="checkbox" value="CHECKBOX">
							パスワード表示
						</div>
					</div>
				</form>
			</div>
			<div class="row rowbutton">
						<input type="button" class="col-xs-offset-2 col-xs-2 button2" value="変更" id="btn変更" />
						<input type="button" class="col-xs-offset-2 col-xs-2  button3" value="キャンセル" id="btnキャンセル" />
			</div>
		</div>
		<script type="text/ecmascript">
			$(function() {
			    $('#checkbox').change(function(){
			        if ( $(this).prop('checked') ) {
			            $('.password').attr('type','text');
			        } else {
			            $('.password').attr('type','password');
			        }
			    });
			});

			$("#btn変更").click(function (){
				if ($("#email").val() == "") {
					alert("メールアドレスを入力してください");
					return false;
				}

				if ($("#oldPassword").val() == "") {
					alert("旧パスワードを入力してください");
					return false;
				}
				if ($("#newPassword").val() == "") {
					alert("新パスワードを入力してください");
					return false;
				}
				if ($("#newPasswordConfirm").val() == "") {
					alert("新パスワード確認を入力してください");
					return false;
				}
				if($("#newPassword").val() != $("#newPasswordConfirm").val() ){
					alert("新パスワードと新パスワード確認が一致しません");
					return false;
				}
				if($("#oldPassword").val() == $("#newPassword").val() ){
					alert("旧パスワードと新パスワードは違う文字列にしてください");
					return false;
				}

				var userId = document.getElementById("userId").innerHTML;
				var oldPassword = $("#oldPassword").val();
				var newPassword = $("#newPassword").val();
				var email = $("#email").val();

				$.ajax({
					url: "${pageContext.request.contextPath}/CheckRegistration",
					type: "post",
					data: { userId:userId, oldPassword: oldPassword, email:email, newPassword:newPassword},
					success: function (data) {
						//alert(data);
 						if (data != "ng") {
 							window.location.href = "${pageContext.request.contextPath}/UserBasicInfoChangeConfirmation";
						}else{
							alert("ユーザー未登録または「ユーザーID」と「旧パスワード」が間違っています。");
						}
					},
					error: function (err) {
						alert(err.responseText);
						alert("システムエラーが発生しました");
					}
				});
			});

			$("#btnキャンセル").click(function (){
				window.location.href = "${pageContext.request.contextPath}/UserBasicInfo";
			});


		</script>
	</body>

</html>
