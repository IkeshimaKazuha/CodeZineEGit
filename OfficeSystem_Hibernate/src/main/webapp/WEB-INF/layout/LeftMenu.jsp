<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	.ニックネーム{
		border-bottom:3px solid #69A4D8;
	}
	.情報{
		border-bottom:3px solid #69A4D8;
	}
	.container3{
		background-color:white;
		height:1000px;
	}

	.画像class{
		zoom: 2;
	}
</style>
<div class="container3">
	<div class="ニックネーム">
		<div class="ニックネーム1">
			ニックネーム
		</div>
		<div class="画像1">
			<img src="img/left_triangle.png" class="画像class">
		</div>
	</div>
	<div class="情報">
		<div class="情報1">
			情報
		</div>
		<div class="情報中身">
			　星座
		</div>
		<div class="情報中身">
			　役職
		</div>
		<div class="情報中身">
			　電話番号
		</div>
	</div>
	<div class="その他">
		<div class="その他中身">
		マイページ
		</div>
		<div class="情報中身">
		掲示板
		</div>
		<div class="情報中身">
		書類申請
		</div>
		<div class="情報中身">
			<a href="${pageContext.request.contextPath}/Find">ユーザー検索</a>

		</div>
		<div class="情報中身">
		E-ラーニング
		</div>
	</div>
</div>