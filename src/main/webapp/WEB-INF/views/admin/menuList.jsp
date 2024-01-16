<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
	<title>MENU LIST &mdash; Archive</title>
	<jsp:include page="/WEB-INF/views/common/commonHeader.jsp" />
</head>
<body id="menuList">
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
						<p class="lead mb-5">메뉴 목록</p>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>

<!-- <div class="site-section bg-light"> -->
<!-- 	<div class="container"> -->
<!-- 	</div> -->
<!-- </div> -->
<section class="site-section">
<h2 class="text-black site-section-heading text-center" id="lst">MENU List</h2>
	<div class="container">
		<div class="row">
			<div style="width: 100%;">

				<div class="panel-body">
				<input type="hidden" name="menuId" id="menuId" value="">
				<table class="table table-striped table-list">
				<thead>
					<tr>
<!-- 					<th width="16px"><input type="checkbox" id="chAll"></th> -->
<!-- 					<th width="20px">ID</th> -->
					<th width="150px">NAME</th>
					<th>LINK</th>
					<th width="30px">RD</th>
					<th width="30px">WR</th>
					<th width="122px">MANAGE</th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
				<c:when test="${empty menuList }">
					<tr><td colspan="8" style="text-align: center;">등록된 메뉴가 없습니다.</td></tr>
				</c:when>
				<c:otherwise>
				<c:forEach items="${menuList }" var="v">
					<tr data-menu_id="${v.menuId }">
<%-- 					<td><input type="radio" name="chBoxId" value="${v.menuId }" ></td> --%>
<%-- 					<td class="title1"><a href="memberModifyForm.do?id=${v.menuId }">${v.menuId }</a></td> --%>
					<td class="text-left"><c:if test="${v.depth gt 1 }">└&nbsp;</c:if>${v.menuName }</td>
					<td class="text-left">${v.menuLink }</td>
					<td>${v.rdAuth }</td>
					<td>${v.wrAuth }</td>
					<td>
						<c:if test="${v.depth le 1 }">
							<button type="button" class="btn btn-success btn-sm addMenu">ADD</button>
						</c:if>
						<button type="button" class="btn btn-custom btn-sm delMenu" style="float:right;">DEL</button>
					</td>
					</tr>
				</c:forEach>
				</c:otherwise>
				</c:choose>
				</tbody>
				</table>
				</div>

				<p style="text-align: right;" class="mt-4">
					<button type="button" id="addMenu" class="btn btn-success btn-md">ADD MENU</button>
					<button type="button" id="apply" class="btn btn-primary btn-md">Apply</button>
				</p>
				
<%--
				<h3 class="h5 text-black mb-3">Search</h3>
				<form action="memberList.do" method="get">
					<input type="hidden" name="category" value="${param.category }">
					<div class="form-group d-flex">
						<input type="text" class="form-control" name="search" placeholder="ID로 검색" value="${search }">
					</div>
				</form>
--%>		
			</div>

		</div>
	</div>
</section>
<script src="/js/Archive/admin/menuList.js?"></script>
</body>
