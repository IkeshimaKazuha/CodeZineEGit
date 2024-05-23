<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html>
	<head>
		<title>メール送信完了画面</title>
	 	<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js" type="text/javascript"></script>
		<style>

			.{ }

			html
			{
			  height: 100%;
			  margin: 0;
			  padding: 0;
			}

			body
			{
				height: 96%;
				margin: 0;
				background-color:#C0C0C0;
				font-size:large;
			}

			.background{
 				background-color:WHITE;
				width:960px;
				min-height:100%;
				margin:10px auto;
				display:table;
			}

			.container
			{
				margin:0 auto;
				display:table-cell;
				vertical-align:middle;

			}

			.button{
				border-radius:10px;
				color:#fff;
				height:40px;
				border:2px #428cf4 solid;
				background-color:#69A4D8;
				width:200px;
				margin:0 auto;
				font-size:large;
			}

			.divButton{
				width:200px;
				margin:0 auto;
			}

			.a{
				text-align:center;
				margin:0 auto;
				margin-bottom:10px;
			}
		</style>
	</head>
	<body>
		<div class="background">
			<div class="container">
				<div class="a">
					■メール送信が完了しました。
				</div>
				<div class="divButton">
					<input type="button" class="button" value="閉じる" id="btn閉じる" />
				</div>
			</div>
		</div>
		<script type="text/javascript">

			$("#btn閉じる").click(function (){
				var win = window.open("about:blank", "_self");
				win.close();
			});

		</script>
	</body>
</html>