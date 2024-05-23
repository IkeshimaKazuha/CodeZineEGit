<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>




<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>検索画面</title>
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
				margin:0 auto;
				padding-bottom:20px;
				margin-bottom:20px;
			}

			.container1
			{
				width:95%;
				margin:0 auto;
				margin-bottom:10px;
				padding-top:10px;
			}

			.h1{
				border-bottom:3px solid #69A4D8;
			}

			.row{
				margin-bottom:5px;
			}

			.textbox{
				border:2px #69A4D8 solid;
				border-radius:10px;
			}

			.textarea{
				resize:none;
				height:100px;
				border:2px #69A4D8 solid;
				border-radius:10px;
			}

			select{
				border:2px #69A4D8 solid;
				border-radius:5px;
				width:150px;
				height:40px;
			}

			input[type=button]{
				border-radius:10px;
				color:#fff;
				height:40px;
			}

			.button1,.button2,.button3{
				border:2px #69A4D8 solid;
				background-color:#69A4D8;
			}

			.button4{
				border:2px #c0c0c0 solid;
				background-color:#c0c0c0;
			}

			input[type=text]{
			}

			.radiobox{
				padding-left:0px;
			}

			.man,.woman{
				display:inline;
				padding-left:0px;
			}

			.radioyouso{
				display:inline;
			}

			.option1{
				width:100px;
				margin-right:5px;
			}

			.option2,.option3{
				width:80px;
				margin-right:5px;
			}

			.option4{
				width:150px;
				margin-right:5px;
			}


			.rowbutton{
				padding:0 15;
			}

			.table,#searchList{
				width:95%;
				border:2px solid #69A4D8;
				margin:0 auto;
				table-layout:fixed;
			}

			.table th,td{
				border:2px solid #69A4D8;
				word-wrap: break-word; /* IE11用 */
				overflow-wrap: break-word;
			 }


			.tr1{
				border-bottom:4px solid #69A4D8;
			}

			.tablebox1{
 				display:table;
				width:100%;
			}

			.SOUKENSU{
				display:table-cell;
			}

			.tablebox2{
				display:table-cell;
				text-align:right;
			}

			.HYOJI{
				display:inline-block;
			}

			.HYOJIOPTION{
			}

			.a{
				width:50px;
			}

			.b{
				width:50px;
			}

			.c{
				width:50px;
			}

			.d{
				width:50px;
			}

			.e{
				width:50px;
			}

			.f{
				width:50px;
			}

			.g{
				width:250px;
			}


		</style>
	</head>
	<body>
		<div id="headerTitle" hidden>ユーザー検索</div>
		<div class="background">
			<div class="container1">
				<div class="h1"><h1 id="h2">検索条件</h1></div>
				<form method="post" action="Find" id="Form1">
		 			<div class="row">
						<div class="col-xs-offset-1 col-xs-2">ユーザーID</div>
						<input type="text" class="col-xs-4 textbox" name="userId" />
					</div>
					<div class="row">
						<div class="col-xs-offset-1 col-xs-2">メールアドレス</div>
						<input type="text" class="col-xs-4 textbox" name="email" />
					</div>
				 	<div class="row">
						<div class="col-xs-offset-1 col-xs-2">ニックネーム</div>
						<input type="text" class="col-xs-4 textbox" name="nickName"/>
				 	</div>
				 	<div class="row">
						<div class="col-xs-offset-1 col-xs-2">氏名</div>
						<input type="text" class="col-xs-4 textbox" name="userName"/>
				 	</div>
				 	<div class="row">
						<div class="col-xs-offset-1 col-xs-2">性別</div>
					 	<div class="col-xs-5 radiobox">
							<div class="col-xs-2 man">
								<input type="radio"  class="radioyouso" name="sex" value="1" id="man"/>
								<div class="radioyouso" >男</div>
							</div>
							<div class="col-xs-2 woman">
								<input type="radio"  class="radioyouso"  name="sex" value="2" id="woman"/>
								<div class="radioyouso">女</div>
							</div>
						 </div>
					</div>
					<div class="row">
						<div class="col-xs-offset-1 col-xs-2">生年月日</div>
						<select class="col-xs-1 option1"	name="birthday1">
							<option value="">  </option>
							<option value="1948">1948</option>
							<option value="1949">1949</option>
							<option value="1950">1950</option>
							<option value="1951">1951</option>
							<option value="1952">1952</option>
							<option value="1953">1953</option>
							<option value="1954">1954</option>
							<option value="1955">1955</option>
							<option value="1956">1956</option>
							<option value="1957">1957</option>
							<option value="1958">1958</option>
							<option value="1959">1959</option>
							<option value="1960">1960</option>
							<option value="1961">1961</option>
							<option value="1962">1962</option>
							<option value="1963">1963</option>
							<option value="1964">1964</option>
							<option value="1965">1965</option>
							<option value="1966">1966</option>
							<option value="1967">1967</option>
							<option value="1968">1968</option>
							<option value="1969">1969</option>
							<option value="1970">1970</option>
							<option value="1971">1971</option>
							<option value="1972">1972</option>
							<option value="1973">1973</option>
							<option value="1974">1974</option>
							<option value="1975">1975</option>
							<option value="1976">1976</option>
							<option value="1977">1977</option>
							<option value="1978">1978</option>
							<option value="1979">1979</option>
							<option value="1980">1980</option>
							<option value="1981">1981</option>
							<option value="1982">1982</option>
							<option value="1983">1983</option>
							<option value="1984">1984</option>
							<option value="1985">1985</option>
							<option value="1986">1986</option>
							<option value="1987">1987</option>
							<option value="1988">1988</option>
							<option value="1989">1989</option>
							<option value="1990">1990</option>
							<option value="1991">1991</option>
							<option value="1992">1992</option>
							<option value="1993">1993</option>
							<option value="1994">1994</option>
							<option value="1995">1995</option>
							<option value="1996">1996</option>
							<option value="1997">1997</option>
							<option value="1998">1998</option>
							<option value="1999">1999</option>
							<option value="2000">2000</option>
							<option value="2001">2001</option>
							<option value="2002">2002</option>
							<option value="2003">2003</option>
							<option value="2004">2004</option>
							<option value="2005">2005</option>
							<option value="2006">2006</option>
							<option value="2007">2007</option>
							<option value="2008">2008</option>
							<option value="2009">2009</option>
							<option value="2010">2010</option>
							<option value="2011">2011</option>
							<option value="2012">2012</option>
							<option value="2013">2013</option>
							<option value="2014">2014</option>
							<option value="2015">2015</option>
							<option value="2016">2016</option>
							<option value="2017">2017</option>
							<option value="2018">2018</option>
						</select>
						<select class="col-xs-1 option2"	name="birthday2">
							<option value="">  </option>
							<option value="01">1</option>
							<option value="02">2</option>
							<option value="03">3</option>
							<option value="04">4</option>
							<option value="05">5</option>
							<option value="06">6</option>
							<option value="07">7</option>
							<option value="08">8</option>
							<option value="09">9</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
						</select>
						<select class="col-xs-1 option3"	name="birthday3">
							<option value="">  </option>
							<option value="01">1</option>
							<option value="02">2</option>
							<option value="03">3</option>
							<option value="04">4</option>
							<option value="05">5</option>
							<option value="06">6</option>
							<option value="07">7</option>
							<option value="08">8</option>
							<option value="09">9</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
							<option value="13">13</option>
							<option value="14">14</option>
							<option value="15">15</option>
							<option value="16">16</option>
							<option value="17">17</option>
							<option value="18">18</option>
							<option value="19">19</option>
							<option value="20">20</option>
							<option value="21">21</option>
							<option value="22">22</option>
							<option value="23">23</option>
							<option value="24">24</option>
							<option value="25">25</option>
							<option value="26">26</option>
							<option value="27">27</option>
							<option value="28">28</option>
							<option value="29">29</option>
							<option value="30">30</option>
							<option value="31">31</option>
						</select>
					</div>
					<div class="row">
						<div class="col-xs-offset-1 col-xs-2">年齢</div>
						<input type="text" class="col-xs-1 textbox" name="age" />
					</div>
					<div class="row">
						<div class="col-xs-offset-1 col-xs-2">電話番号</div>
						<input type="text" class="col-xs-4 textbox" name="tel" />
					</div>
					<div class="row">
						<div class="col-xs-offset-1 col-xs-2">郵便番号</div>
						<input type="text" class="col-xs-4 textbox" name="postcode" />
					</div>
					<div class="row">
						<div class="col-xs-offset-1 col-xs-2">住所</div>
						<input type="text" class="col-xs-4 textbox" name="address" />
					</div>

					<div class="row">
						<div class="col-xs-offset-1 col-xs-2">入社日</div>
						<select class="col-xs-1 option1"	name="hireDate1">
							<option value="">  </option>
							<option value="1948">1948</option>
							<option value="1949">1949</option>
							<option value="1950">1950</option>
							<option value="1951">1951</option>
							<option value="1952">1952</option>
							<option value="1953">1953</option>
							<option value="1954">1954</option>
							<option value="1955">1955</option>
							<option value="1956">1956</option>
							<option value="1957">1957</option>
							<option value="1958">1958</option>
							<option value="1959">1959</option>
							<option value="1960">1960</option>
							<option value="1961">1961</option>
							<option value="1962">1962</option>
							<option value="1963">1963</option>
							<option value="1964">1964</option>
							<option value="1965">1965</option>
							<option value="1966">1966</option>
							<option value="1967">1967</option>
							<option value="1968">1968</option>
							<option value="1969">1969</option>
							<option value="1970">1970</option>
							<option value="1971">1971</option>
							<option value="1972">1972</option>
							<option value="1973">1973</option>
							<option value="1974">1974</option>
							<option value="1975">1975</option>
							<option value="1976">1976</option>
							<option value="1977">1977</option>
							<option value="1978">1978</option>
							<option value="1979">1979</option>
							<option value="1980">1980</option>
							<option value="1981">1981</option>
							<option value="1982">1982</option>
							<option value="1983">1983</option>
							<option value="1984">1984</option>
							<option value="1985">1985</option>
							<option value="1986">1986</option>
							<option value="1987">1987</option>
							<option value="1988">1988</option>
							<option value="1989">1989</option>
							<option value="1990">1990</option>
							<option value="1991">1991</option>
							<option value="1992">1992</option>
							<option value="1993">1993</option>
							<option value="1994">1994</option>
							<option value="1995">1995</option>
							<option value="1996">1996</option>
							<option value="1997">1997</option>
							<option value="1998">1998</option>
							<option value="1999">1999</option>
							<option value="2000">2000</option>
							<option value="2001">2001</option>
							<option value="2002">2002</option>
							<option value="2003">2003</option>
							<option value="2004">2004</option>
							<option value="2005">2005</option>
							<option value="2006">2006</option>
							<option value="2007">2007</option>
							<option value="2008">2008</option>
							<option value="2009">2009</option>
							<option value="2010">2010</option>
							<option value="2011">2011</option>
							<option value="2012">2012</option>
							<option value="2013">2013</option>
							<option value="2014">2014</option>
							<option value="2015">2015</option>
							<option value="2016">2016</option>
							<option value="2017">2017</option>
							<option value="2018">2018</option>
						</select>
						<select class="col-xs-1 option2"	name="hireDate2">
							<option value="">  </option>
							<option value="01">1</option>
							<option value="02">2</option>
							<option value="03">3</option>
							<option value="04">4</option>
							<option value="05">5</option>
							<option value="06">6</option>
							<option value="07">7</option>
							<option value="08">8</option>
							<option value="09">9</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
						</select>
						<select class="col-xs-1 option3"	name="hireDate3">
							<option value="">  </option>
							<option value="01">1</option>
							<option value="02">2</option>
							<option value="03">3</option>
							<option value="04">4</option>
							<option value="05">5</option>
							<option value="06">6</option>
							<option value="07">7</option>
							<option value="08">8</option>
							<option value="09">9</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
							<option value="13">13</option>
							<option value="14">14</option>
							<option value="15">15</option>
							<option value="16">16</option>
							<option value="17">17</option>
							<option value="18">18</option>
							<option value="19">19</option>
							<option value="20">20</option>
							<option value="21">21</option>
							<option value="22">22</option>
							<option value="23">23</option>
							<option value="24">24</option>
							<option value="25">25</option>
							<option value="26">26</option>
							<option value="27">27</option>
							<option value="28">28</option>
							<option value="29">29</option>
							<option value="30">30</option>
							<option value="31">31</option>
						</select>
					</div>
					<div class="row">
						<div class="col-xs-offset-1 col-xs-2">所属</div>
						<select class="col-xs-12 option4"	name="affiliation">
							<option value="">  </option>
							<option value="営業部">営業部</option>
							<option value="技術部">技術部</option>
							<option value="社長">社長</option>
						</select>
					</div>
					<div class="row">
						<div class="col-xs-offset-1 col-xs-2">役職</div>
						<input type="text" class="col-xs-4 textbox" name="position" />
					</div>
					<div class="row">
						<div class="col-xs-offset-1 col-xs-2">趣味</div>
						<input type="text" class="col-xs-4 textbox" name="hobby" />
					</div>
					<div class="row">
						<div class="col-xs-offset-1 col-xs-2">特技</div>
						<input type="text" class="col-xs-4 textbox" name="specialSkill" />
					</div>
					<div class="row">
						<div class="col-xs-offset-1 col-xs-2">座右銘</div>
						<textarea class="col-xs-4 textarea" rows="3" name="comment" ></textarea>
					</div>
					<div class="row rowbutton">
						<input type="button" class="col-xs-2 button1" value="検索" id="id検索btn" />
						<div class="col-xs-5 col-xs-offset-5">
							<div class="row">
								<input type="button" class="col-xs-4 button2" value="メール送信" id="btnメール送信" />
								<input type="button" class="col-xs-4 col-xs-offset-1 button3" value="申請確認" id="btn申請確認" />
								<input type="button" class="col-xs-2 col-xs-offset-1 button4" value="△" />
							</div>
						</div>
					</div>
				</form>
			</div>
			<div id="container">
				<tiles:insertAttribute name="_FindResult" />
			</div>
		</div>
	<script type="text/ecmascript">




		$("#id検索btn").click(function (){
/* 			$.ajax({
				url: "${pageContext.request.contextPath}/CheckSession",
				type: "post",
				success: function (data) {
					if(data == "SessionTimeout"){
						alert("セッションがタイムアウトしました");
					}else{ */
						$.ajax({
							url: "${pageContext.request.contextPath}/Find",
							type: "post",
							data: $("#Form1").serialize(),
							success: function (data) {
								//alert(data);
								//console.log(data);
								$("#container").html(data);
							},
							error: function(XMLHttpRequest,textStatus,errorThrown,data){
								alert(XMLHttpRequest.responseText);
							}
						});
/* 					}
				},
				error: function (err) {
					alert(err.responseText);
					alert("システムエラーが発生しました");
				}
			}); */
		});

		$(document).on('change', "[id^='showNumber']", function () {
			if ($('#totalCount').text() == "0") {
				return;
			}
			$.ajax({
				url: "${pageContext.request.contextPath}/GetPage",
				type: "post",
				data: { showNumber:$("#showNumber").val(), currentPage: 1 },
				success: function (data) {
					$("#container").html(data);
				},
				error: function (err) {
					alert(err.responseText);
					alert("システムエラーが発生しました");
				}
			});
		});

		$(document).on('change', "[id='currentPage']", function () {
			if ($('#totalCount').text() == "0") {
				return;
			}
			$.ajax({
				url: "${pageContext.request.contextPath}/GetPage",
				type: "post",
				data: { showNumber:$("#showNumber").val(), currentPage: $('#currentPage').val() },
				success: function (data) {
					$("#container").html(data);
				},
				error: function () {
					alert("システムエラーが発生しました");
				}
			});
		});


		$(document).on('click', "[id='previousPage']", function () {
			event.preventDefault();
			var selecting = $('#currentPage').val();

			if (selecting == 1) {
				return;
			}

			$('#currentPage').val(Number(selecting) - 1);
			$('#nextPage').attr("src", "${pageContext.request.contextPath}/img/right_triangle.png");


			$.ajax({
				url: "${pageContext.request.contextPath}/GetPage",
				type: "post",
				data: { showNumber:$("#showNumber").val(), currentPage: (Number(selecting) - 1) },
				success: function (data) {
					$("#container").html(data);
				},
				error: function () {
					alert("システムエラーが発生しました");
				}
			});
		});


		$(document).on('click', "[id='nextPage']", function () {
			event.preventDefault();
			var selecting = $('#currentPage').val();
			var maxvalue = $('#currentPage option:last-child').val();

			if (selecting == maxvalue) {
				return;
			}

			$('#currentPage').val(Number(selecting) + 1);
			$('#previousPage').attr("src", "${pageContext.request.contextPath}/img/left_triangle.png");


			$.ajax({
				url: "${pageContext.request.contextPath}/GetPage",
				type: "post",
				data: { showNumber:$("#showNumber").val(), currentPage: (Number(selecting) + 1) },
				success: function (data) {
					$("#container").html(data);
				},
				error: function () {
					alert("システムエラーが発生しました");
				}
			});
		});

		function sort(obj) {
			if ($('#totalCount').text() == "0") {
				return;
			}
			var span = $(obj).find("span");//spanの要素
			var sortOrder = span.text().trim();//今の▲の状態
			var sortColumn = $(obj).text().replace("▲", "").replace("▼", "").trim();//今のユーザーIDとか

			//タイトルからソート順のマークを削除する　更新前に前を消しておく
			$("#searchList").find("tr:nth-child(1) td").each(function () {
				$(this).find("span").text("");
			});

			if (sortOrder == "" || sortOrder == "▼") {
				sortOrder = "▲";
			}
			else {
				sortOrder = "▼";
			}

			$('#previousPage').attr("src", "${pageContext.request.contextPath}/img/left_triangle_disable.png");
			$('#currentPage').val(1);
			$('#nextPage').attr("src", "${pageContext.request.contextPath}/img/right_triangle.png");


			$.ajax({
				url: "${pageContext.request.contextPath}/Sort",
				type: "post",
				data: {sortColumn:sortColumn,sortOrder:sortOrder},
				success: function (data) {
					$("#container").html(data);//containerの中身更新？
				},
				error: function () {
					alert("システムエラーが発生しました");
				}
			});
		}

		var manChecked = false;
		$("#man").click(function(){
			if(manChecked){
				$(this).prop('checked',false);
				manChecked = false;
			}else{
				manChecked = true;
				womanChecked = false;
			}
		});

		var womanChecked = false;
		$("#woman").click(function(){
			if(womanChecked){
				$(this).prop('checked',false);
				womanChecked = false;
			}else{
				womanChecked = true;
				manChecked = false;
			}
		});


		$("#btn申請確認").click(function (){
			//alert("btn申請確認click!");
			var checkbox = document.Form1.checkbox;
			var count = 0;
			var userId = "";
			//alert(checkbox);
			//alert(checkbox.length);
			if(checkbox.checked){
				count++;
			}
			for (let i = 0; i < checkbox.length; i++){
				//alert(checkbox.length);
				if(checkbox[i].checked){
					count++;
					//alert("count++");
				}
			}

			if(count==0 || count>1){
				alert("１ユーザーのみ選択してください。count="+count);
			}else{

	 			//alert("ok");
				for (let i = 0; i < checkbox.length; i++){
					if(checkbox[i].checked){
						//alert(document.getElementsByClassName("userId")[i].innerHTML);
						userId = document.getElementsByClassName("userId")[i].innerHTML;
					}
				}
				if(checkbox.checked){
					//alert(document.getElementsByClassName("userId")[0].innerHTML);
					userId = document.getElementsByClassName("userId")[0].innerHTML;
				}

/* 				var userName = findModel.getUserName();
				alert(userName); */

				x = (screen.width) / 1;
				y = (screen.height) / 1;
				var ApplicationManageWindow = window.open( "ApplicationManageWithUserId?userId="+userId,"a","screenX=0,screenY=0,left=0,top=0,width="+(x-10)+",height="+(y-90)+",scrollbars=1,toolbar=1,menubar=1,staus=1,resizable=1");
	 		}

		});
/* 		var array = [];
		function saveCheckboxIsChecked(userId,ischecked){
			alert(userId)

			var

			if(ischecked){
				array.push(userId);
				alert($("#selectUser_${item.getUserId()}").val());

				//$('#selectUser_${item.getUserId()}').val('value-a');
			}else{
				var idx = array.indexOf(userId);
				if(idx >= 0){
				 array.splice(idx, 1);
				}
			}
			alert(array);
			alert(array[0])
		} */
	</script>
	</body>

</html>
