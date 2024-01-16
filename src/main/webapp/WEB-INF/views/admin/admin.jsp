<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
	<title>Admin &mdash; Archive</title>
	<jsp:include page="/WEB-INF/views/common/commonHeader.jsp" />
</head>
<body id="admin">
<div class="site-blocks-cover inner-page-cover overlay"
	style="background-image: url(images/hero_1.jpg);" data-aos="fade"
	data-stellar-background-ratio="0.5">
	<div class="container">
		<div
			class="row align-items-center justify-content-center text-center">

			<div class="col-md-12" data-aos="fade-up" data-aos-delay="400">

				<div class="row justify-content-center mb-4">
					<div class="col-md-8 text-center">
						<h1>Administrator Menu</h1>
						<p class="lead mb-5">관리자 메뉴</p>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>

<div class="site-section bg-light">
	<div class="container">
		<div class="row">
			<div style="width: 100%;">
			<!-- bootstrapBoard -->
				<div class="panel-body">
				<table class="table table-striped table-bordered table-list" style="float: none;">
					<tr>
					<th width="50%">User List</th>
					<th width="50%">Menu</th>
<!-- 					<th width="50%">Admin Chart</th> -->
					<!-- <th width="16px"></th> -->
					</tr>
					<tr>
					<td><a href="userList"><img src="images/admin.png" style="height: 200px;"></a></td>
					<td><a href="menuList"><img src="images/menu.png" style="height: 200px;"></a></td>
<!-- 					<td><a href="adminChartForm.do"><img src="images/chart.png" style="height: 200px;"></a></td> -->
					</tr>
				</table>
	
				</div>
	
				<!-- bootstrapBoard -->
				
				<!--  boardpage -->
				
	
				
				<!--  boardpage -->
			</div>
	
		</div>
	</div>
</div>
<script>
$(document).ready(function() {
	$('#chAll').click(function() {
		if ($('#chAll').is(':checked')){
			$('input:checkbox[name=chBoxId]').prop('checked', true);
		} else {
			$('input:checkbox[name=chBoxId]').prop('checked', false);
		}
	});
});
</script>
</body>
