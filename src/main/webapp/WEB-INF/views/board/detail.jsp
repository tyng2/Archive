<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <title>detail TEST</title>
</head>
<body>
<div id="sessionID" style="display: none;">${sessionID }</div>
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
			<p class="mb-0" style="margin: 15px 0;"><b>${board.userId }</b><span style="float: right;"><fmt:formatDate value="${board.bordDate }" pattern="yyyy.MM.dd HH:mm:ss"/></span></p>
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
			<p><pre style="font-family: 'Quicksand'; font-size: 15px; margin: 20px 0; min-height: 160px;">${board.bordCont }</pre></p>
			<p class="" style="text-align: right;">조회 : ${board.bordHitc }</p>
		</div>
<!-- 		<div id="CommentAn"></div> -->
		<div class="p-4 mb-3 bg-white" id="commentArea">
			<div id="comment"></div>
			
			<form action="boardComment.do" method="POST">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				
				<input type="hidden" name="bord_id" id="bord_id" value="${board.bordId }">
				<input type="hidden" name="pageNum" id="pageNum" value="${param.pageNum }">
				<input type="hidden" name="bord_catg" id="bord_catg" value="${param.bord_catg }">
		
				<c:if test="${sessionID != null }">
				<textarea name="comm_cont" id="comm_cont" cols="30" rows="4"
					class="form-control" 
					placeholder="Write your comment here..."></textarea><br>
				<button type="button" id="insertComment" class="btn btn-custom btn-md" style="float: right;">등록</button>
				</c:if>
			</form><br><br>
		</div>

		<div class="p-4 mb-3">
			<p style="text-align: right;" class="mb-0">
			<c:if test="${sessionID != null }">
				<c:if test="${sessionID.equals('admin') && !sessionID.equals(board.userId) }">
					<a href="boardDeleteProcess.do?bord_id=${board.bordId }&pageNum=${param.pageNum }&category=${param.bord_catg }" class="btn btn-custom btn-md">삭제</a>
				</c:if>
				<c:if test="${sessionID.equals(board.userId) }">
					<a href="boardModify.do?bord_id=${board.bordId }&pageNum=${param.pageNum }&category=${param.bord_catg }" class="btn btn-custom btn-md">수정</a>
					<a href="boardDeleteProcess.do?bord_id=${board.bordId }&pageNum=${param.pageNum }&category=${param.bord_catg }" class="btn btn-custom btn-md">삭제</a>
				</c:if>
<%-- 				<a href="reply.do?re_ref=${board.bord_refr }&re_lev=${board.bord_levl }&re_seq=${board.bord_seqn }&category=${board.bord_catg }&pageNum=${param.pageNum }&pcategory=${param.bord_catg }" class="btn btn-custom btn-md">답글</a> --%>
			</c:if>
			<c:choose>
			<c:when test="${param.bord_catg == null }">
				<a href="board?pageNum=${param.pageNum }&#board" class="btn btn-custom btn-md">목록</a>
			</c:when>
			<c:otherwise>
				<a href="board?category=${board.bordCatg }&pageNum=${param.pageNum }&#board" class="btn btn-custom btn-md">목록</a>
			</c:otherwise>
			</c:choose>
				
			</p>
		</div>
	</div>
	</div>
</div>
</section>
<script src="/js/Archive/board/detail.js?"></script>
</body>
