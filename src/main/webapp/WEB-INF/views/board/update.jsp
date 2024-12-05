<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>Update TEST</title>
    <jsp:include page="/WEB-INF/views/common/commonHeader.jsp" />
</head>
<body id="update">
<div class="site-blocks-cover inner-page-cover overlay" style="background-image: url(images/img_2.jpg);"
	data-aos="fade" data-stellar-background-ratio="0.5">
	<div class="container">
		<div class="row align-items-center justify-content-center text-center">
			<div class="col-md-12" data-aos="fade-up" data-aos-delay="400">
				<div class="row justify-content-center mb-4">
					<div class="col-md-8 text-center">
						<h1>Update</h1>
						<p class="lead mb-5">Update your contents</p>
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
	<form action="updateProcess" class="p-5 bg-white" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<input type="hidden" id="bordId"	name="bordId" value="${board.bordId }">
		<input type="hidden" id="userId"	name="userId" value="${board.userId }">
		
		<h2 class="h4 text-black mb-5">Update</h2>

		<input type="hidden" name="category" value="${param.category }">
		<div class="row form-group">
			<div class="col-md-6 mb-3 mb-md-0">
				<label class="text-black" for="fname">Category</label><br>
				<select class="form-control" name="categy">
				<c:if test="${not empty categoryList }">
				<c:forEach var="c" items="${categoryList }">
					<c:choose>
					<c:when test="${not empty board.bordCatg }">
						<option value="${c.catgId }" <c:if test="${board.bordCatg eq c.catgId }">selected</c:if> >${c.catgName }</option>
					</c:when>
					<c:otherwise>
						<option value="${c.catgId }">${c.catgName }</option>
					</c:otherwise>
					</c:choose>
				</c:forEach>
				</c:if>
				</select>
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
				<input type="text" id="title" class="form-control" name="title" value="${board.bordTitl }" required>
			</div>
		</div>
		
		<div class="row form-group" id="fileArea">
			<div class="col-md-12">
				<label class="text-black" for="title">File</label>
				<input type="hidden" id="file_size" name="file_size" value="${files.size() }">
				<input type="hidden" id="file_cnt"	name="file_cnt" value="1">
				<button type="button" class="btn btn-none fileAdd"><img src="images/add.png" width="22px" height="22px"></button>
				<button type="button" class="btn btn-none fileDel"><img src="images/minus.png" width="22px" height="22px"></button>
				<c:if test="${files.size() != 0 }">
				<div class="bg-white">
					<p class="mb-0">
					<c:forEach items="${files }" var="item">
						<a href="javascript:void(0);" class="delete" id="fileA${item.fileId }" data-file_id="${item.fileId }">
							<button type="button" class="file themeBtn4 mb-2">${item.fileOlnm }</button>
						</a>
					</c:forEach>
					</p>
				</div>
				</c:if>
				<div id="file_add_form" >
					<input type="file" name='mFile' class="form-control" id="file1">
<%-- 					<c:forEach items="${files }" var="item" varStatus="s"> --%>
<%-- 						<c:if test="${not s.first }"><br id="br${s.count }"></c:if> --%>
<%-- 						<input type="file" name='mFile' class="form-control" id="file${s.count }" data-file_id="${item.fileId }" value="file/${item.fileOlnm }"> --%>
<%-- <%-- 						<label for="file${s.count }" class="form-control">${item.fileOlnm }</label> --%>
<%-- 					</c:forEach> --%>
				</div>
			</div>
		</div>

		<div class="row form-group">
			<div class="col-md-12">
				<label class="text-black" for="content">Content</label>
				<textarea name="content" id="content" cols="30" rows="8" class="form-control"
					placeholder="Write your contents here...">${board.bordCont }</textarea>
			</div>
		</div>

		<div class="row form-group">
			<div class="col-md-12">
				<input type="submit" value="작성" class="btn btn-primary btn-md" >
				<a href="board" class="btn btn-custom btn-md" style="float: right;">목록</a>
			</div>
		</div>

	</form>
	</div>

</div>
</div>
</section>
<script src="/js/Archive/board/update.js?"></script>
</body>
