<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<style>
	.header{
		height:50px;
		width:100%;
		background-color:#C0C0C0;
		color:#fff;
		display:table;

	}
	.title1{
		font-size: xx-large;
		float:left;
		display:table-cell;
		vertical-align: middle;
		height:50px;
		line-height:50px;
		margin-left:20px;
	}

	.right1{
		text-align: right;
		vertical-align: middle;
		height:50px;
		line-height:50px;
		margin-right:30px;
	}
</style>
<body>
	<div class="header">
		<div class="title1" id="title1">
		</div>
		<div class="right1">
			<a class="">
				ニックネーム
			</a>
			<a class="">
				権限
			</a>
		</div>
	</div>

</body>