<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <title>board Archive</title>
    <jsp:include page="/WEB-INF/views/common/commonHeader.jsp" />
</head>
<body id="board">
<div class="site-blocks-cover inner-page-cover overlay" style="background-image: url(images/hero_1.jpg);" 
	data-aos="fade" data-stellar-background-ratio="0.5">
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

<section class="site-section">
<h2 class="text-black site-section-heading text-center" id="title">
	<c:choose>
	<c:when test="${not empty search }">
		키워드 "${search }" 검색
	</c:when>
	<c:when test="${not empty category }">
		카테고리 "${category.catgName }"
	</c:when>
	<c:otherwise>
		Every
	</c:otherwise>
	</c:choose>
</h2>

<div class="container">
	<div class="row">
		<div class="col-md-8">
		<!-- bootstrapBoard -->
	
			<div class="panel-body">
			<table class="table table-striped table-bordered table-list">
			<thead>
				<tr>
				<!-- <th width="10%">게시판</th> -->
				<th colspan="2">제목</th>
				<th width="20%">작성자</th>
				<th width="80px">작성일</th>
				<th width="60px">조회</th>
				</tr>
			</thead>
			<tbody id="boardContent">
			<c:choose>
			<c:when test="${empty boardList }">
			<tr><td colspan="5" style="text-align: center;">작성된 게시글이 없습니다.</td></tr>
			</c:when>
			<c:otherwise>
			<c:forEach items="${boardList }" var="v">
				<tr>
				<td width="60px" class="category"><a href="javascript:void(0);" class="catgBtn" data-catg="${v.bordCatg }">${v.catgName }</a></td>
				<td class="title1 left">
				<%--
				<c:if test="${v.bord_levl > 0 }">
					<c:forEach begin="1" end="${v.bord_levl }">
						&nbsp;
					</c:forEach>
					<span id="reply">└</span>
				</c:if>
				--%>
					<a href="javascript:void(0);" class="detailBtn" data-bord_id="${v.bordId }">${v.bordTitl }</a>
				<c:if test="${v.fileCnt > 0 }">
					<img src="images/disk.png" style="width: 14px;">
				</c:if>
				<c:if test="${v.commentCnt > 0 }">
					<b style="color: red;">[${v.commentCnt }]</b>
				</c:if>
				</td>
				<td>${v.nickname }</td>
				<td>
				<jsp:useBean id="today" class="java.util.Date"></jsp:useBean>
				<fmt:parseNumber value="${today.time / (1000 * 60 * 60 * 24)}" 		var="nowDays" integerOnly="true" />
				<fmt:parseNumber value="${v.bordDate.time / (1000 * 60 * 60 * 24)}" var="regDays" integerOnly="true" />
				<c:set value="${nowDays - regDays }" var="dayDiff" />
				<c:choose>
				<c:when test="${dayDiff == 0 }">
					<fmt:formatDate value="${v.bordDate }" pattern="HH:mm:ss"/>
				</c:when>
				<c:otherwise>
					<fmt:formatDate value="${v.bordDate }" pattern="yy.MM.dd"/>
				</c:otherwise>
				</c:choose>
				</td>
				<td>${v.bordHitc }</td>
				</tr>
			</c:forEach>
			</c:otherwise>
			</c:choose>
			</tbody>
			</table>
	
			</div>
	
			<!-- bootstrapBoard -->
			
			<div class="row">
			<div class="col-12">
			<div class="custom-pagination text-center">
				<input type="hidden" id="search"	value="${search }">
				<input type="hidden" id="category"	value="${category.catgId }">
				<input type="hidden" id="pageNum"	value="${param.pageNum }">
				<c:if test="${pageInfo.allRowCount > 0 }">
	
				<c:if test="${pageInfo.startPage > pageInfo.pageBlockSize }">
					<c:if test="${pageInfo.startPage > (pageInfo.pageBlockSize * 2) }">
						<a href="javascript:void(0);" class="pageBtn" data-page="1">1</a>
						<span class="more-page">...</span>
					</c:if>
					<a href="javascript:void(0);" class="pageBtn" data-page="${pageInfo.startPage - 1 }"><span class="pt"><img src="images/left-arrow.png" width="18px" height="18px"></span></a>
				</c:if>
	
				<c:forEach begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1" varStatus="s">
				
				<c:choose>
				<c:when test="${s.current eq pageInfo.pageNum }">
					<span>${s.current }</span>
				</c:when>
				<c:otherwise>
					<a href="javascript:void(0);" class="pageBtn" data-page="${s.current }">${s.current }</a>
				</c:otherwise>
				</c:choose>
				
				</c:forEach>
	
				<c:if test="${pageInfo.endPage < pageInfo.maxPage }">
					<a href="javascript:void(0);" class="pageBtn" data-page="${pageInfo.endPage + 1 }"><span class="pt"><img src="images/right-arrow.png" width="18px" height="18px"></span></a>
					<c:if test="${(pageInfo.endPage + pageInfo.pageBlockSize) < pageInfo.maxPage }">
						<span class="more-page">...</span>
						<a href="javascript:void(0);" class="pageBtn" data-page="${pageInfo.maxPage }">${pageInfo.maxPage }</a>
					</c:if>
				</c:if>
	
				</c:if>
			</div>
			</div>
			</div>
			
			<p style="text-align: right;" class="mt-4">
				<c:if test="${isLogin and login.authId le curMenu.wrAuth }">
					<a href="javascript:void(0);" class="btn btn-primary btn-md write">글쓰기</a>
				</c:if>
			</p>
		</div>
	
	
		<div class="col-md-3 ml-auto" ><!-- style="max-width: 25%;" -->
			<div class="mb-5">
				<h3 class="h5 text-black mb-3">Search</h3>
				<form action="board" method="get">
					<div class="form-group d-flex">
						<input type="text" class="form-control" name="search"
							placeholder="Search keyword and hit enter..." value="${search }">
					</div>
				</form>
			</div>
	
			<c:if test="${not empty hitList }">
				<div class="mb-5">
					<h3 class="h5 text-black mb-3">Popular Posts</h3>
					<ul class="list-unstyled">
						<c:forEach items="${hitList }" var="hl">
							<li class="mb-2"><a href="detail?bordId=${hl.bordId }&#view">${hl.bordTitl }</a></li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
	
			<c:if test="${not empty commentList }">
				<div class="mb-5">
					<h3 class="h5 text-black mb-3">Recent Comments</h3>
					<ul class="list-unstyled">
						<c:forEach items="${commentList }" var="cl">
							<li class="mb-2"><a href="detail?bordId=${cl.bordId }&#CommentArea">${cl.commCont }</a></li>
						</c:forEach>
						<!-- <li class="mb-2"><a href="#">Joefrey</a> <em>in</em> <a
							href="#">Lorem ipsum dolor sit amet</a></li>
						<li class="mb-2"><a href="#">Joefrey</a> <em>in</em> <a
							href="#">Quaerat rerum voluptatibus veritatis</a></li>
						<li class="mb-2"><a href="#">Joefrey</a> <em>in</em> <a
							href="#">Maiores sapiente veritatis reprehenderit</a></li>
						<li class="mb-2"><a href="#">Joefrey</a> <em>in</em> <a
							href="#">Natus eligendi nobis</a></li> -->
					</ul>
				</div>
			</c:if>
	
		</div>
	
	</div>
</div>

</section>
<script src="/js/Archive/board/board.js?"></script>
</body>
