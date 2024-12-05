<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>board TEST</title>
    <jsp:include page="/WEB-INF/views/common/commonHeader.jsp" />
</head>
<body id="write">
<div class="site-blocks-cover inner-page-cover overlay" style="background-image: url(images/img_2.jpg);"
	data-aos="fade" data-stellar-background-ratio="0.5">
	<div class="container">
		<div class="row align-items-center justify-content-center text-center">
			<div class="col-md-12" data-aos="fade-up" data-aos-delay="400">
				<div class="row justify-content-center mb-4">
					<div class="col-md-8 text-center">
						<h1>Write</h1>
						<p class="lead mb-5">Write your contents</p>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>

<section class="site-section bg-light">

<!-- content -->
<div class="container">
<div class="row">

	<div style="width: 100%">
	<form action="write" class="p-5 bg-white" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		
		<h2 class="h4 text-black mb-5">Write</h2>

		<input type="hidden" id="pageNum"	name="pageNum"	value="${pageNum }">
		<input type="hidden" id="search"	name="search"	value="${search }">
		<input type="hidden" id="category"	name="category"	value="${category }">
		<div class="row form-group">
			<div class="col-md-6 mb-3 mb-md-0">
				<c:if test="${not empty categoryList }">
				<label class="text-black" for="fname">Category</label><br>
					<select class="form-control" name="categy">
					<c:forEach var="c" items="${categoryList }">
						<c:choose>
						<c:when test="${not empty category }">
	<%-- 						<option value="${c.catg_id }" <% if ("".equals(category)) {%>selected<%} %>>${c.catg_name }</option> --%>
							<option value="${c.catgId }" <c:if test="${category eq c.catgId }">selected</c:if> >${c.catgName }</option>
						</c:when>
						<c:otherwise>
							<option value="${c.catgId }">${c.catgName }</option>
						</c:otherwise>
						</c:choose>
					</c:forEach>
					</select>
				</c:if>
				<!-- <input type="text" id="fname" class="form-control"> -->
			</div>
			<div class="col-md-6">
				<!-- <label class="text-black" for="lname">Last Name</label> <input
					type="text" id="lname" class="form-control"> -->
			</div>
		</div>
		
		<div class="row form-group">
			<div class="col-md-12">
				<label class="text-black" for="title">Title</label>
				<input type="text" id="title" class="form-control" name="title" required>
			</div>
		</div>
		
		<div class="row form-group" id="fileArea">
			<div class="col-md-12">
				<label class="text-black" for="title">File</label>
				<input type="hidden" id="file_cnt" name="file_cnt" value="1">
				<button type="button" class="btn btn-none fileAdd"><img src="images/add.png" width="22px" height="22px"></button>
				<button type="button" class="btn btn-none fileDel"><img src="images/minus.png" width="22px" height="22px"></button>
				<div>
				
				</div>
				<div id="file_add_form" >
					<input type="file" name='mFile' class="form-control" id="file1">
<!-- 					<input type="file" name='bbs_file1' class="form-control" > -->
				</div>
			</div>
		</div>

		<div class="row form-group">
			<div class="col-md-12">
				<label class="text-black" for="content">Content</label>
				<textarea name="content" id="content" cols="30" rows="8" class="form-control"
					placeholder="Write your contents here..."></textarea>
			</div>
		</div>

		<div class="row form-group">
			<div class="col-md-12">
				<input type="submit" value="작성" class="btn btn-primary btn-md" >
				<a href="javascript:void(0);" class="btn btn-custom btn-md listBtn" style="float: right;">목록</a>
			</div>
		</div>

	</form>
	</div>

</div>
</div>
</section>
<script src="/js/Archive/board/write.js?"></script>
</body>
