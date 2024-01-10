<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
	<title>Archive &mdash; Template</title>
	<jsp:include page="/WEB-INF/views/common/commonHeader.jsp" />
</head>
<body id="notice">
<div class="site-blocks-cover inner-page-cover overlay" style="background-image: url(images/hero_1.jpg);" 
	data-aos="fade" data-stellar-background-ratio="0.5">
	<div class="container">
		<div class="row align-items-center justify-content-center text-center">

			<div class="col-md-12" data-aos="fade-up" data-aos-delay="400">
				<div class="row justify-content-center mb-4">
					<div class="col-md-8 text-center">
						<h1>Notice</h1>
						<p class="lead mb-5">공지사항</p>
					</div>
				</div>
			</div>
			
		</div>
	</div>
</div>

<section class="site-section bg-light">
<div class="container">

	<div class="row">
		<div style="width: 100%;">
		<!-- bootstrapBoard -->
		<c:choose>
		<c:when test="${not empty noticeList }">
			<c:forEach items="${noticeList }" var="n">
			<div class="panel-body">
				<div class="p-4 mb-3 bg-white" style="margin: 0;">
					<p class="mb-0 font-weight-bold h2">${n.notiTitle }
					<span style="float: right; font-size: 15px;">No. ${n.notiId }</span></p>
					<p class="mb-4" style="margin: 15px 0;"><span style="float: right;"><fmt:formatDate value="${n.createdDate }" pattern="yyyy.MM.dd HH:mm:ss"/></span></p>
				</div>
					
				<div class="p-4 mb-3 bg-white">
					<p><pre style="font-family: 'Quicksand'; font-size: 15px; margin: 20px 0; min-height: 160px;">${n.notiCont }</pre></p>
				</div>
			</div><br><br><br>
			</c:forEach>
				<!-- bootstrapBoard -->
				
			<!--  boardpage -->
			<div class="row">
			<div class="col-12">
			<div class="custom-pagination text-center">
				<c:if test="${pageInfo.allRowCount > 0 }">

				<c:if test="${pageInfo.startPage > pageInfo.pageBlockSize }">
					<c:if test="${pageInfo.startPage > (pageInfo.pageBlockSize * 2) }">
						<a href="notice?pageNum=1&search=${search }&#board">1</a><span class="more-page">...</span>
					</c:if>
					<a href="notice?pageNum=${pageInfo.startPage - 1 }&search=${search }&#board"><span class="pt"><img src="images/left-arrow.png" width="18px" height="18px"></span></a>
				</c:if>

				<c:forEach begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1" varStatus="s">
				
				<c:choose>
				<c:when test="${s.current eq pageInfo.pageNum }">
					<span>${s.current }</span>
				</c:when>
				<c:otherwise>
					<a href="notice?pageNum=${s.current }&#board">${s.current }</a>
				</c:otherwise>
				</c:choose>
				</c:forEach>

				<c:if test="${pageInfo.endPage < pageInfo.maxPage }">
					<a href="notice?pageNum=${pageInfo.endPage + 1 }&#board"><span class="pt"><img src="images/right-arrow.png" width="18px" height="18px"></span></a>
					<c:if test="${(pageInfo.endPage + pageInfo.pageBlockSize) < pageInfo.maxPage }">
						<span class="more-page">...</span>
						<a href="notice?pageNum=${pageInfo.maxPage }&#board">${pageInfo.maxPage }</a>
					</c:if>
				</c:if>

				</c:if>
			</div>
			</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="panel-body"><div class="p-5 mb-3 bg-white text-center">No contents</div></div>
		</c:otherwise>
		</c:choose>
			
		<p style="text-align: right;" class="mt-4">
			<c:if test="${isLogin and login.authId le curMenu.wrAuth }">
				<a href="noticeWrite" class="btn btn-custom btn-md">글쓰기</a>
			</c:if>
		</p>
		
		</div>

	</div>
</div>
</section>
<script>
</script>
</body>
