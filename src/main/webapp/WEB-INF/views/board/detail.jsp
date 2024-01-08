<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <title>detail TEST</title>
    <jsp:include page="/WEB-INF/views/common/commonHeader.jsp" />
    <style type="text/css">
    	.bordCont {
    		font-family	: 'Quicksand';
    		font-size	: 15px;
    		margin		: 20px 0;
    		min-height	: 160px;
    	}
    </style>
</head>
<body id="detail">
<div class="site-blocks-cover inner-page-cover overlay"
	style="background-image: url(images/hero_1.jpg);" data-aos="fade"
	data-stellar-background-ratio="0.5">
	<div class="container">
	<div class="row align-items-center justify-content-center text-center">

		<div class="col-md-12" data-aos="fade-up" data-aos-delay="400">

			<div class="row justify-content-center mb-4">
				<div class="col-md-8 text-center">
					<h1>Board</h1>
					<p class="lead mb-5">Free board for everyone</p>
				</div>
			</div>

		</div>
	</div>
	</div>
</div>

<!-- content -->
<section class="site-section bg-light">
<div id="view" class="container">
	<div class="row">
	<div style="min-width: 100%;">
		<div class="p-4 mb-3 bg-white" style="margin: 0;">
			<p class="mb-0 font-weight-bold h2">${board.bordTitl } <span style="font-size: 18px"><span style="color: gray;">|</span> ${board.catgName }</span>
			<span style="float: right; font-size: 15px;">No. ${board.bordId }</span></p>
			<p class="mb-0" style="margin: 15px 0;"><b>${board.nickname }</b><span style="float: right;"><fmt:formatDate value="${board.bordDate }" pattern="yyyy.MM.dd HH:mm:ss"/></span></p>
		</div>
		
		<c:if test="${files.size() != 0 }">
			<div class="p-4 mb-3 bg-white">
				<p class="mb-0">
				<c:forEach items="${files }" var="item" >
					<a href="javascript:void(0);" class="download" data-file_id="${item.fileId }">
						<button type="button" class="file themeBtn4 mb-2">${item.fileOlnm }</button>
					</a>
				</c:forEach>
				</p>
			</div>
		</c:if>
					
		<div class="p-4 mb-3 bg-white">
			<pre class="bordCont">${board.bordCont }</pre>
			<p class="" style="text-align: right;">조회 : ${board.bordHitc }</p>
		</div>
<!-- 		<div id="CommentAn"></div> -->
		<div class="p-4 mb-3 bg-white" id="commentArea">
			<div id="comment"></div>
			<div id="commentPageBlock" class="p-4" style="display: none;"><div class="custom-pagination text-center"></div></div>
			<form action="boardComment" method="POST">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				
				<input type="hidden" name="bordId"		id="bordId"		value="${board.bordId }">
				<input type="hidden" name="userId"		id="userId"		value="${board.userId }">
				<input type="hidden" name="pageNum"		id="pageNum"	value="${pageNum }">
				<input type="hidden" name="category"	id="category"	value="${category }">
				<input type="hidden" name="search"		id="search"		value="${search }">
		
				<c:if test="${isLogin }">
				<textarea name="commCont" id="commCont" cols="30" rows="4" class="form-control" 
					placeholder="Write your comment here..."></textarea><br>
				<button type="button" id="insertComment" class="btn btn-custom btn-md" style="float: right;">등록</button><br><br>
				</c:if>
			</form>
		</div>

		<div class="p-4 mb-3">
			<p style="text-align: right;" class="mb-0">
			<c:if test="${isLogin }">
<%-- 				<c:if test="${sessionID.equals('admin') && !sessionID.equals(board.userId) }"> --%>
				<c:if test="${loginSessionVo.authId eq 1 && loginSessionVo.userId ne board.userId }">
					<a href="javascript:void(0);" class="btn btn-custom btn-md delete">삭제</a>
				</c:if>
<%-- 				<c:if test="${sessionID.equals(board.userId) }"> --%>
				<c:if test="${loginSessionVo.userId eq board.userId }">
					<a href="javascript:void(0);" class="btn btn-custom btn-md update">수정</a>
					<a href="javascript:void(0);" class="btn btn-custom btn-md delete">삭제</a>
				</c:if>
<%-- 				<a href="reply?re_ref=${board.bord_refr }&re_lev=${board.bord_levl }&re_seq=${board.bord_seqn }&category=${board.bordCatg }&pageNum=${param.pageNum }&pcategory=${param.bordCatg }" class="btn btn-custom btn-md">답글</a> --%>
			</c:if>
			<a href="javascript:void(0);" class="btn btn-custom btn-md listBtn">목록</a>
<%-- 			<c:choose> --%>
<%-- 			<c:when test="${param.bordCatg == null }"> --%>
<%-- 				<a href="board?pageNum=${param.pageNum }&#board" class="btn btn-custom btn-md">목록</a> --%>
<%-- 			</c:when> --%>
<%-- 			<c:otherwise> --%>
<%-- 				<a href="board?category=${board.bordCatg }&pageNum=${param.pageNum }&#board" class="btn btn-custom btn-md">목록</a> --%>
<%-- 			</c:otherwise> --%>
<%-- 			</c:choose> --%>
			</p>
		</div>
	</div>
	</div>
</div>
</section>
<script src="/js/Archive/board/detail.js?"></script>
</body>
