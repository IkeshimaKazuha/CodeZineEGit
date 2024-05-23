<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html>
	<head>
		<title>メール送信画面</title>
	 	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
	 	<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js" type="text/javascript"></script>
		<style>
			body
			{
				/* background-color:#C0C0C0; */
				font-size:large;
			}

			.background{
				background-color:WHITE;
				width:960px;
				margin:0px auto;
				padding-bottom:20px;
				margin-bottom:20px;
 				margin-top: 10px;
			}

			.container1
			{
				width:80%;
				margin:0px auto;
				margin-bottom:10px;
				padding-top:10px;
			}

			.container2{
				border:3px solid #69A4D8;
				padding:20px;
				margin-bottom:10px;

			}


			.row{
				margin-bottom:5px;
				margin-left:0px;
			}

			.textbox{
				border:2px #69A4D8 solid;
				border-radius:10px;
			}

			.button1,.button2{
				border-radius:10px;
				color:#fff;
				height:40px;
				border:2px #428cf4 solid;
				background-color:#69A4D8;
				display:table-cell;
				width:200px;
			}

			.button1{
				margin-right:5px;
			}


			.宛先,.件名{
				padding-right:20px;
			}

			.メール内容{
				border-top:3px solid #69A4D8;
				border-bottom:3px solid #69A4D8;
				padding-top:10px;
				padding-bottom:100px;
				height:180px;
				margin-bottom:10px
			}



			.添付row{
				display:table;
			}

			.添付ファイル1,.添付ファイル2,.添付ファイル3{
				display:table-cell;
				vertical-align:middle;
			}

			.input添付div{
				display:table-cell;
				vertical-align:middle;
			}


			.input添付{
				outline: 0;
				border: 0px;
			}

			.buttonClass{
				margin:0px auto;
			}

			.textarea{
				width:100%;
				resize:none;
				border:none;
			}

			.btnCancel{
				display:none;
			}
		</style>
	</head>
	<body>
		<div id="headerTitle" hidden="true">メール送信</div>
		<div class="background">
			<div class="container1">
				<div class="container2">
					<form method="post" action="/OfficeSystem_Hibernate/SendMail" id="Form1" enctype="multipart/form-data">
						<div class="row 宛先">
							<div class="col-xs-2">宛先</div>
							<input type="text" class="col-xs-10 textbox" id="destination" name="destination" value="" />
						</div>
						<div class="row 件名">
							<div class="col-xs-2">件名</div>
							<input type="text" class="col-xs-10 textbox" id="subject" name="subject" />
						</div>
						<div class="メール内容">
							メール内容
							<textarea class="col-xs-4 textarea" rows="5" id="mailContent" name="mailContent" ></textarea>
						</div>
					</form>
					<form method="post" action="/OfficeSystem_Hibernate/SendMailFileUpload" id="Form2" enctype="multipart/form-data">
						<div class="row 添付row">
							<input type="file" name="file1" id="file1" style="display:none;" onchange="setLink('file1',$(this).prop('files')[0].name)"  required >
							<input type="button" class="col-xs-3 button1" value="添付ファイル1" id="id添付ファイル1btn"  onClick="$('#file1').click()" />
							<div class="input添付div">
								<a herf = "" id="fake_input_file1" class="input添付"></a>
							</div>
							<div class="input添付div">
								<input type="button" class="添付ファイル1 btnCancel" value="X" id="btnCancelfile1" onclick="clearValue('file1');"/>
							</div>
						</div>
						<div class="row 添付row">
							<input type="file" id="file2" style="display:none;" onchange="setLink('file2',$(this).prop('files')[0].name)" required>
							<input type="button" class="col-xs-3 button1" value="添付ファイル2" id="id添付ファイル2btn"  onClick="$('#file2').click()" />
							<div class="input添付div">
								<a href = "" id ="fake_input_file2" class="input添付"></a>
							</div>
							<div class="input添付div">
								<input type="button" class="添付ファイル2 btnCancel" value="X" id="btnCancelfile2" onclick="clearValue('file2');" />
							</div>
						</div>
						<div class="row 添付row">
							<input type="file" id="file3" style="display:none;" onchange="setLink('file3',$(this).prop('files')[0].name)" required>
							<input type="button" class="col-xs-3 button1" value="添付ファイル3" id="id添付ファイル3btn"  onClick="$('#file3').click()" />
							<div class="input添付div">
								<a href="" id="fake_input_file3" class="input添付"></a>
							</div>
							<div class="input添付div">
								<input type="button" class="添付ファイル3 btnCancel" value="X" id="btnCancelfile3" onclick="clearValue('file3');" />
							</div>
						</div>
					</form>
				</div>
				<div class="row buttonClass">
					<input type="button" class="col-xs-offset-2 col-xs-3 button2" value="送信" id="btn送信" />
					<input type="button" class="col-xs-offset-1 col-xs-3 button2" value="閉じる" id="btn閉じる" />
				</div>
			</div>
		</div>
		<script type="text/javascript">

			window.onload=function(){
				//document.getElementById( "destination" ).value = "ZZZZZZZZ";
				//document.getElementById( "destination" ).defaultValue = "ZZZZZZZZ";
				//alert(document.getElementById( "destination" ).value);

 				var query=window.location.search.substring(1);
				var parms=query.split('&');
				for (var i=0;i<parms.length;i++){
					if(parms[i].match(/(\w+)=(.+)/))document.forms[0].elements[RegExp.$1].defaultValue=(RegExp.$2);
				}
			};


			window.onpageshow = function(){
				document.getElementById("Form1").reset();
				document.getElementById("Form2").reset();
				document.getElementById("Form3").reset();
	        };


			$(function(){
				$("#file1,#file2,#file3").change(proc);
				function proc() {
					//alert($(this).attr("id"));
			    	fileSetted($(this).attr("id"))
			        if (window.FormData){             //　FormDataにブラウザが対応しているかチェック
			            var fileObj = $('#'+ $(this).attr("id"))[0].files[0];    // ファイルオブジェクトの取り出し
			            if ( fileObj != null ){
			                fileupload(fileObj,$(this).attr("id"));
			            }
			        }else{
			            alert("このブラウザはFormDataに対応していません。");
			            return null;
			        }
				}

			});

		    function fileupload(fileObj,number){
		        var fd = new FormData();            // FormDataオブジェクト生成
		        fd.append('file', fileObj);
		      	fd.append('number',number);
		        $.ajax({
					url  : "${pageContext.request.contextPath}/SendMailFileUpload",
					type : "post",
					data : fd,
					cache       : false,
					contentType: false,
					processData : false
		        })
		        .done( function(text){
		            console.log(text);
		        });
		    }

			function fileSetted(id){
				 $("#btnCancel" + id).show();
			}

			function clearValue(id){
				$("#"+id).val("");
			    $("#fake_input_" + id).html("");
			    $("#btnCancel" + id).attr("href","");
				$("btnCancel" + id).hide();

				deleteFile(id);
			}

		    function deleteFile(id){
		        $.ajax({
					url  : "${pageContext.request.contextPath}/SendMailDeleteFile",
					type : "post",
					data:{"id":id}
		        })
				.done(function(data, textStatus, jqXHR){
					//console.error(jqXHR.responseText);
				})
				.fail(function(jqXHR, textStatus, errorThrown){
					console.error(jqXHR.responseText);
				});
		    }


			//入力データをチェック
			$("#btn送信").click(function (){

 				if ($("#destination").val() == "") {
					alert("宛先を入力してください");
					return false;
				}

				if ($("#subject").val() == "") {
					alert("件名を入力してください");
					return false;
				}

				if ($("#mailContent").val() == "") {
					alert("件名を入力してください");
					return false;
				}

 			    // フォームデータを取得
			    var form = $('#Form1').get()[0]; // You need to use standard javascript object here
 			    var formdata = new FormData(form);

				 /* POSTでアップロード
				Webサーバーにリクエストとメールのデータを送信する*/
				$.ajax({
					url  : "${pageContext.request.contextPath}/SendMail",
					type : "post",
					data : formdata,
					cache       : false,
					contentType: false,
					processData : false
				})
				/*メール送信結果は成功の結果なら"SentMailFinish"の画面に遷移
				間違っていれば、エラーメッセージを表示*/
				.done(function(data, textStatus, jqXHR){
					console.error(jqXHR.responseText);
					window.location.href = "SendMailFinish";
					//alert(data);

				})
				.fail(function(jqXHR, textStatus, errorThrown){
					console.error(jqXHR.responseText);
					alert(jqXHR.responseText);
				});

			});

			$("#btn閉じる").click(function (){
				//document.getElementById( "destination" ).value = "a";
				var win = window.open("about:blank", "_self");
				win.close();
			});


			/*リンクを設定する*/
			function setLink(id, filename){
				$("#fake_input_" + id).html(filename);
				$("#fake_input_" + id).attr("href",
		"${pageContext.request.contextPath}/DownloadFile?id="+id+"&name="+filename
				);
			}

		</script>
	</body>
</html>