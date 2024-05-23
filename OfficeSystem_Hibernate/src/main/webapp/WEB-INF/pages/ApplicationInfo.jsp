<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>




<!DOCTYPE html>
<html>
	<head>
		<title>申請内容</title>
	 	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
	 	<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js" type="text/javascript"></script>
		<style>

			html{
				height:100%;
			}

			body
			{
				/* background-color:#C0C0C0; */
				font-size:large;
				height:100%;
				/* padding:20px 0px; */
			}

			.background{
				background-color:WHITE;
				width:90%;
				margin:0 auto;
				min-height:100%;
				padding-bottom:20px;
				margin-bottom:10px;
			}

			input[type=button]{
				border-radius:10px;
				color:#fff;
				height:40px;
				border:2px #69A4D8 solid;
				background-color:#69A4D8;
			}

			.row{
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
				border-style:solid;
				margin-bottom:10px;
				border-color:#69A4D8;
				padding:10px;
			}

			.borderDiv{
				border-top:3px solid #69A4D8;
				width:90%;
				margin:0 auto;
				margin-bottom:10px;
			}

			.textarea{
				resize:none;
				height:150px;
				width:100%;
				margin:0 auto;
 				border:2px #69A4D8 solid;
				border-radius:10px;
/* 				border:none; */
			}

			.textAreaDiv{
				margin:0 auto;
				width:90%
			}

			#divLink{
			color:blue;
			}

		</style>
	</head>
	<body>
		<div id="headerTitle" hidden>申請内容</div>
		<div class="background">
			<div class="container1">
				<div class="h1"><h1 id="h2">申請内容</h1></div>
			</div>
			<div class="container2">
				<div class="row">
					<div class="col-xs-offset-1 col-xs-2">申請状態</div>
					<div class="col-xs-offset-1 col-xs-2" id="status"></div>
				</div>
				<div class="row">
					<div class="col-xs-offset-1 col-xs-2">申請書類</div>
					<div class="col-xs-offset-1 col-xs-2">${applicationInfoModel.getApplyFile()}</div>
				</div>
				<div class="row">
					<div class="col-xs-offset-1 col-xs-2">タイトル</div>
					<div class="col-xs-offset-1 col-xs-2">${applicationInfoModel.getTitle()}</div>
				</div>
				<div class="row">
					<div class="col-xs-offset-1 col-xs-2">申請ファイル</div>
					<!-- <div class="col-xs-offset-1 col-xs-2" id="divLink"><a href="a" id="fileLink">申請ファイル</a></div> -->
					<div class="col-xs-offset-1 col-xs-2" id="divLink">申請ファイル</div>

				</div>
				<div class="borderDiv"></div>
				<div class="row 連絡事項">
					<div class="col-xs-offset-1 col-xs-2">連絡事項</div>
				</div>
				<div class="textAreaDiv" >
					<textarea class="textarea" id="連絡事項"></textarea>
				</div>
			</div>
			<div class="row">
				<input type="button" class="col-xs-offset-1 col-xs-1 button" value="削除" id="btn削除" />
				<input type="button" class="col-xs-offset-1 col-xs-1 button" value="差し戻し" id="btn差し戻し" />
				<input type="button" class="col-xs-offset-4 col-xs-2 button" value="申請一覧に戻る" id="btn申請一覧に戻る" />
			</div>
		</div>
		<script type="text/ecmascript">

			window.onload=function(){
				var link = "";
				//document.getElementbyId("fileLink").href="http://localhost:8080/OfficeSystem_Hibernate/Download?link="+"C:\\Users\\USER\\Desktop\\test.txt";
				//"http://localhost:8080/OfficeSystem_Hibernate/Download?link="+"C:\Users\USER\Desktop\test.txt"
				var applyId = ${applicationInfoModel.getApplyId()};

				switch(${applicationInfoModel.getApplyStatus()}){

					case 0:
						document.getElementById("status").innerHTML = "未承認";
						$("#btn差し戻し").hide();
					 	break;
					case 1:
						document.getElementById("status").innerHTML = "承認済";
						$("#btn削除").hide();
						break;
					case 2:
						document.getElementById("status").innerHTML = "自分差し戻し";
						$("#btn差し戻し").hide();
						break;
					case 3:
						document.getElementById("status").innerHTML = "差し戻し";
						$("#btn差し戻し").hide();
						break;
					case 4:
						document.getElementById("status").innerHTML = "削除済";
						$("#btn削除").hide();
						break;
					default:
						break;
				}
			};


			$("#divLink").click(function (){
				var link ="C:\\Users\\USER\\Desktop\\test.txt"
				location.href = "http://localhost:8080/OfficeSystem_Hibernate/Download?link="+ link;
			});



			$("#btn差し戻し").click(function (){
				if (document.getElementById('連絡事項').value == "" )  {
				    alert('差し戻し理由を入力してください。');
				}else{
					if(window.confirm('申請を差し戻しますが、よろしいでしょうか？')){

						var applyId = ${applicationInfoModel.getApplyId()};
						$.ajax({
							url: "${pageContext.request.contextPath}/ChangeApplyStatus",
							type: "post",
							data: {applyId:applyId,applyStatus:"2"},
							success: function (data) {
								document.getElementById("status").innerHTML = "自分差し戻し";
								window.alert('申請の差し戻しが正常に完了しました。');
								sendEmailAddress = "gtyoshiaki@yahoo.co.jp";
								var emailContent = document.getElementById('連絡事項').value;

								$.ajax({
									url: "${pageContext.request.contextPath}/UpdateApplyList",
									type: "post",
									data:"",
									success: function (data) {
										//alert(data);
										//console.error(data);
										window.opener.$("#container").html(data);
										//window.parent.$("#container").html(data);
									},
									error: function (XMLHttpRequest, textStatus, errorThrown) {
										alert("申請一覧の更新が異常に完了しました。原因は"
												+"\nXMLHttpRequest : " + XMLHttpRequest.status
												+"\ntextStatus     : " + textStatus
												+"\nerrorThrown    : " + errorThrown.message);
									}
								});
								$.ajax({
									url: "${pageContext.request.contextPath}/NoticeMail",
									type: "post",
									data: {sendEmailAddress:sendEmailAddress,applyId:applyId,emailContent:emailContent},
									success: function (data) {
										window.alert('通知メールの送信が正常に完了しました。');
									},
									error: function (XMLHttpRequest, textStatus, errorThrown) {
										alert("通知メールの送信が異常に完了しました。原因は"
												+"\nXMLHttpRequest : " + XMLHttpRequest.status
												+"\ntextStatus     : " + textStatus
												+"\nerrorThrown    : " + errorThrown.message);
									}
								});
							},
							error: function (XMLHttpRequest, textStatus, errorThrown) {
								alert("申請の差し戻しが異常に完了しました。原因は"
										+"\nXMLHttpRequest : " + XMLHttpRequest.status
										+"\ntextStatus     : " + textStatus
										+"\nerrorThrown    : " + errorThrown.message);
							}
						});
						var btn = document.getElementById("btn差し戻し");
						btn.disabled = "disabled";
						btn.style.backgroundColor = "gray";
						btn.style.borderColor = "gray";
					}
					else{
						window.alert('キャンセルされました'); // 警告ダイアログを表示
/* 						var win = window.open("about:blank", "_self");
						win.close(); */
					}
				}

			});

			$("#btn申請一覧に戻る").click(function (){
				var win = window.open("about:blank", "_self");
				win.close();
			});

			var btn削除 = document.getElementById('btn削除');

			btn削除.addEventListener('click', function() {
				// 「OK」時の処理開始 ＋ 確認ダイアログの表示
				if(window.confirm('申請を削除しますが、よろしいでしょうか？')){

					/* location.href = "example_confirm.html"; // example_confirm.html へジャンプ */
					var applyId = ${applicationInfoModel.getApplyId()};
					$.ajax({
						url: "${pageContext.request.contextPath}/ChangeApplyStatus",
						type: "post",
						data: {applyId:applyId,applyStatus:"4"},
						success: function (data) {
							document.getElementById("status").innerHTML = "削除済み";
							window.alert('申請の削除が正常に完了しました。');
							$.ajax({
								url: "${pageContext.request.contextPath}/UpdateApplyList",
								type: "post",
								data:"",
								success: function (data) {
									//alert(data);
									//console.error(data);
									window.opener.$("#container").html(data);
									//window.parent.$("#container").html(data);
								},
								error: function (XMLHttpRequest, textStatus, errorThrown) {
									alert("申請一覧の更新が異常に完了しました。原因は"
											+"\nXMLHttpRequest : " + XMLHttpRequest.status
											+"\ntextStatus     : " + textStatus
											+"\nerrorThrown    : " + errorThrown.message);
								}
							});
						},
						error: function (XMLHttpRequest, textStatus, errorThrown) {
							alert("申請の削除が異常に完了しました。原因は"
									+"\nXMLHttpRequest : " + XMLHttpRequest.status
									+"\ntextStatus     : " + textStatus
									+"\nerrorThrown    : " + errorThrown.message);
						}
					});
					var btn = document.getElementById("btn削除");
					btn.disabled = "disabled";
					btn.style.backgroundColor = "gray";
					btn.style.borderColor = "gray";
				}
				else{

					window.alert('キャンセルされました'); // 警告ダイアログを表示

				}

			})



		</script>
	</body>

</html>
