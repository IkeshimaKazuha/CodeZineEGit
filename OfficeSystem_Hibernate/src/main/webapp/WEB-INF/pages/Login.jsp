<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="G_T.OfficeSystem.controller.LoginController"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
	<head>
		<title>ログイン画面</title>


		<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js" type="text/javascript"></script>



		<style type="text/css">
			.{ }

			.form
			{
				margin:0;
			}

			.LABEL
			{
				color:red;
				margin:0 auto;
				display:block;
				text-align:center;
			}

			body {
				/* background-color:#C0C0C0; */
				margin:0;
			}

			.BACKGROUND
			{
				background-color:WHITE;
				width:1012px;
				min-height:100%;
				margin:0 auto;
			}


			.TEXTBOX
			{
				width:100%;
				height:30px;
				margin-bottom:5px;
				border-color:#69A4D8;
				border:2px #69A4D8 solid;
				border-radius:5px;
			}



			.LEFT
			{
				float:left;
			}

			.RIGHT
			{
				float:right;
			}

			.BUTTON
			{
				background-color:#69A4D8;
				border:2px #69A4D8 solid;
		        width:100%;
				color:#fff;
				border-radius:5px;
			}

			.TEXTAREA
			{
				resize:none;
				width:500px;
				height:250px;
				margin:0 auto;
				display:block;
		        border-color:#69A4D8;
			}

			.CONTAINER1
			{
				padding-top:100px;
				width:100%
			}

			.CONTAINER2
			{
				width:250px;
				margin:0 auto;
			}

			.CONTAINER3{
				width:100%;
			}

			.CHECKBOX{
			}

			#h2{
				display:none;
			}


		</style>


	</head>
	<body>

		<div id="headerTitle" hidden>ログイン画面</div>
		<div class="h1"><h1 id="h2">ログイン画面</h1></div>
<%-- 		<spring:url value="/Login" var="url" htmlEscape="true" /> --%>
		<form method="post" action="/OfficeSystem_Hibernate/Login" class="form">
<%-- 		<form class = "form"name="f" action="<c:url value="/Login"/>" method="post">
		<form action="${url}" method="post" class="form"> --%>

			<div class="BACKGROUND">
				<div class="CONTAINER1">
					<label class="LABEL"> ${error}</label>
					<div class="CONTAINER2">
						<input type="text" class="TEXTBOX" id="userId" name="userId" placeholder="ユーザーID"/>
						<input type="password" class="TEXTBOX" id="password" name="password" placeholder="パスワード"/>
						<div class="CONTAINER3">
							<input type="checkbox" id="checkbox" name="checkbox" value="CHECKBOX">
							IDを保存する
						</div>
<%-- 						<input type="submit" value="ログイン" class="BUTTON"> --%>
						<input type="button" class="BUTTON" value="ログイン" />
						<a href="" class="LEFT" >パスワード忘れ</a>
						<a href="" class="RIGHT" >新規申請</a>
					</div>
					<textarea class="TEXTAREA" >お知らせ</textarea>
				</div>
		    </div>
		</form>


		<script type="text/ecmascript">
 			$(".BUTTON").click(function () {
				if ($("#userId").val() == "") {
					alert("ユーザーIDを入力してください");
					return false;
				}

				if ($("#password").val() == "") {
					alert("パスワードを入力してください");
					return false;
				}

				$.ajax({
					url: "/OfficeSystem_Hibernate/Login",
					type: "post",
					data: { userId:$("#userId").val(), password: $("#password").val() },
					success: function (data) {
 						if (data != "") {
							setCookie();
							window.location.href = "${pageContext.request.contextPath}/Find";
						}
						else {
							alert("ユーザーIDまたはパスワードが存在しません");
						}
					},
					error: function(XMLHttpRequest,textStatus,errorThrown,data){
						alert(XMLHttpRequest.responseText);
/* 						alert("XMLHttpRequest : " + XMLHttpRequest.status
								+"\ntextStatus     : " + textStatus
								+"\nerrorThrown    : " + errorThrown.message
								+"\n"+XMLHttpRequest.responseText); */
					}
				});
			});
		</script>

				<!-- cookie -->
		<script type="text/ecmascript">
			window.onload = function setId(){
				if(getCookie('checkbox')=='true'){
					//document.getElementById("checkbox").checked = true;
					userId.value = getCookie('id');
				}else{
					//document.getElementById("checkbox").checked = false;
				}

			}

			function setCookie() {
				var date1,date2;  //日付データを格納する変数
				var kigen = 30;   //cookieの期限（今回は30日）

				//現在の日付データを取得
				date1 = new Date();

				//30日後の日付データを作成
				//date1.setTime(date1.getTime() + kigen*24*60*60*1000);
				date1.setTime(date1.getTime() + 30000);

				//GMT形式に変換して変数date2に格納する
				date2 = date1.toGMTString();

				if (document.getElementById("checkbox").checked) {
					document.cookie = 'id=' + document.getElementById("userId").value + ";expires=" + date2;
					document.cookie = 'checkbox=true;expires=' + date2;
				} else {
					document.cookie = 'checkbox=false;expires=' + date2;
				}
			}


			function getCookie( name )
			{
			    var result = null;

			 	//"key="
			    var cookieName = name + '=';
			 	//"key=value; key=value;..."
			    var allcookies = document.cookie;

			    var position = allcookies.indexOf( cookieName );
			    if( position != -1 )
			    {
			        var startIndex = position + cookieName.length;

			        //indexOf( 検索したい文字, 検索開始位置 )
			        var endIndex = allcookies.indexOf( ';', startIndex );
			        //最後のcookieは；がない　-1になってる
			        if( endIndex == -1 )
			        {
			            endIndex = allcookies.length;
			        }

			        result = decodeURIComponent(
			        	//indexStart から indexEnd 未満の文字を取り出します
			            allcookies.substring( startIndex, endIndex ) );
			    }

			    return result;
			}
		</script>
	</body>
</html>