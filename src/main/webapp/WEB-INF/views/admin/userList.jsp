<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
	<title>USER LIST &mdash; Archive</title>
	<jsp:include page="/WEB-INF/views/common/commonHeader.jsp" />
	<style>
		input.disabled {
			border: 0;
		}
	</style>
</head>
<body id="userList">
<!-- <a href="javascript:void(0);" class="btn btn-primary" data-toggle="modal" data-target=".userDetailModal">Large modal</a> -->
<div class="modal fade" id="userDetailModal" tabindex="-1" role="dialog" aria-labelledby="userModalLabel" aria-hidden="true">
	<input type="hidden" id="authFlag" value="">
	<div class="modal-dialog modal-dialog-custom" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="userModalLabel">USER</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
			<form id="userModalForm">
				<input type="hidden" id="user_id_modal" name="userId" disabled="disabled">
				<div class="row">
					<div class="form-group col-6">
						<label for="user_txid_modal" class="col-form-label">UserTxId</label>
						<input type="text" class="form-control disabled" id="user_txid_modal" name="userTxId" disabled="disabled">
					</div>
					<div class="form-group col-6">
						<label for="username_modal" class="col-form-label">UserName</label>
						<input type="text" class="form-control disabled" id="username_modal" name="userName" disabled="disabled">
					</div>
					<div class="form-group col-6">
						<label for="email_modal" class="col-form-label">Email</label>
						<input type="text" class="form-control disabled" id="email_modal" name="email" disabled="disabled">
					</div>
					<div class="form-group col-6">
						<label for="sns_type_modal" class="col-form-label">TYPE</label>
						<input type="text" class="form-control disabled" id="sns_type_modal" name="snsType" disabled="disabled">
					</div>
					<div class="form-group col-6">
						<label for="nickname_modal" class="col-form-label">NickName</label>
						<input type="text" class="form-control editable" id="nickname_modal" name="nickname" disabled="disabled">
					</div>
					<div class="form-group col-6">
						<label for="auth_modal" class="col-form-label">Auth</label>
<!-- 						<input type="text" class="form-control editable" id="auth_modal" disabled="disabled"> -->
						<select class="form-control editable" id="auth_modal" name="authId" disabled="disabled"></select>
					</div>
					<div class="form-group col-6">
						<label for="create_date_modal" class="col-form-label">CRDT</label>
						<input type="text" class="form-control disabled" id="create_date_modal" name="createDate" disabled="disabled">
					</div>
					<div class="form-group col-6">
						<label for="modify_date_modal" class="col-form-label">MODT</label>
						<input type="text" class="form-control disabled" id="modify_date_modal" name="modifyDate" disabled="disabled">
					</div>
				</div>
<%--
				<div class="form-group">
					<label for="recipient-name" class="col-form-label">Recipient:</label>
					<input type="text" class="form-control" id="recipient-name">
				</div>
				<div class="form-group">
					<label for="message-text" class="col-form-label">Message:</label>
					<textarea class="form-control" id="message-text"></textarea>
				</div>
--%>
			</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" id="dropOutUser">DropOut</button>
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary" id="modifyUser">Modify</button>
				<button type="button" class="btn btn-success" id="modifyUserApply" style="display: none;">Apply</button>
			</div>
		</div>
	</div>
</div>


<div class="site-blocks-cover inner-page-cover overlay"
	style="background-image: url(images/hero_1.jpg);" data-aos="fade"
	data-stellar-background-ratio="0.5">
	<div class="container">
		<div class="row align-items-center justify-content-center text-center">
			<div class="col-md-12" data-aos="fade-up" data-aos-delay="400">

				<div class="row justify-content-center mb-4">
					<div class="col-md-8 text-center">
						<h1>Administrator Menu</h1>
						<p class="lead mb-5">User List</p>
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
	<h2 class="text-black site-section-heading text-center" id="lst">User List</h2>
	<div class="container">
		<div class="row">
			<div style="width: 100%;">
			<!-- bootstrapBoard -->
			<form action="memberDelProcess.do" method="POST">
				<div class="panel-body">
				<table class="table table-hover table-list">
				<thead>
					<tr>
					<th width="16px"><input type="checkbox" id="chAll"></th>
					<th width="10%">ID</th>
					<th>TYPE</th>
<!-- 					<th>NAME</th> -->
					<th width="10%">NICKNAME</th>
					<th>E-MAIL</th>
					<th width="30px">AUTH</th>
					<th width="170px">REG_DATE</th>
<!-- 					<th width="16px"></th> -->
					</tr>
				</thead>
				<tbody>
				<c:choose>
				<c:when test="${empty userList }">
					<tr><td colspan="8" style="text-align: center;">No user.</td></tr>
				</c:when>
				<c:otherwise>
				<c:forEach items="${userList }" var="v">
					<tr data-user_id="${v.userId }" data-toggle="modal" data-target="#userDetailModal">
					<td><input type="checkbox" name="chBoxId" value="${v.userId }" ></td>
					<td class="title1">${v.userId }</td>
					<td>${fn:toUpperCase(v.snsType) }</td>
<%-- 					<td>${v.userName }</td> --%>
					<td>${v.nickname }</td>
					<td>${v.email }</td>
					<td>${v.authName }</td>
					<td><fmt:formatDate value="${v.createDate }" pattern="yyyy.MM.dd HH:mm:ss"/></td>
					</tr>
				</c:forEach>
				</c:otherwise>
				</c:choose>
				</tbody>
				</table>

				</div>

				<!-- bootstrapBoard -->
				
				<!--  boardpage -->
				
				<div class="row">
				<div class="col-12">
				<div class="custom-pagination text-center">
					<c:if test="${pageInfoMap.allRowCount > 0 }">

					<c:if test="${pageInfoMap.startPage > pageInfoMap.pageBlockSize }">
					<a href="memberList.do?pageNum=1&search=${search }&#lst">1</a><span class="more-page">...</span>
					<a href="memberList.do?pageNum=${pageInfoMap.startPage - 1 }&search=${search }&#lst"><span class="pt"><img src="images/left-arrow.png" width="18px" height="18px"></span></a>
					</c:if>

					<c:forEach begin="${pageInfoMap.startPage }" end="${pageInfoMap.endPage }" step="1" varStatus="s">
					
					<c:choose>
					<c:when test="${s.current == pageInfoMap.pageNum }">
					<span>${s.current }</span>
					</c:when>
					<c:otherwise>
						<a href="memberList.do?pageNum=${s.current }&search=${search }&#lst">${s.current }</a>
					</c:otherwise>
					</c:choose>
					</c:forEach>

					<c:if test="${pageInfoMap.endPage < pageInfoMap.maxPage }">
					<a href="memberList.do?pageNum=${pageInfoMap.endPage + 1 }&search=${search }&#lst"><span class="pt"><img src="images/right-arrow.png" width="18px" height="18px"></span></a>
					<span class="more-page">...</span>
					<a href="memberList.do?&pageNum=${pageInfoMap.maxPage }&search=${search }&#lst">${pageInfoMap.maxPage }</a>
					</c:if>

					</c:if>
				</div>
				</div>
				</div>
				
				<p style="text-align: right;" class="mt-4">
					<button class="btn btn-custom btn-md">강제 탈퇴</button>
					<%-- <a href="javascript:delMember('${v.id }')" class="btn btn-custom btn-md">강제 탈퇴</a> --%>
				</p>
				</form>
				
				<h3 class="h5 text-black mb-3">Search</h3>
				<form action="userList" method="get">
					<%-- <input type="hidden" name="category" value="${param.category }"> --%>
					<div class="form-group d-flex">
						<input type="text" class="form-control" name="search" placeholder="ID로 검색" value="${search }">
					</div>
				</form>
				
				<!--  boardpage -->
			</div>

		</div>
	</div>
</section>
<script src="/js/Archive/admin/userList.js?"></script>
</body>
