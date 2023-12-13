<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>board TEST</title>
</head>
<body>
	<div class="site-blocks-cover inner-page-cover overlay"
	style="background-image: url(images/hero_1.jpg);" data-aos="fade"
	data-stellar-background-ratio="0.5">
	<div class="container">
		<div
			class="row align-items-center justify-content-center text-center">

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
<%
// String category = (String) request.getParameter("bord_catg");
%>
<div class="container">
<div class="row">

	<div style="width: 100%">
	<form action="write" class="p-5 bg-white" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		
		<h2 class="h4 text-black mb-5">Write</h2>

		<input type="hidden" name="category" value="${param.category }">
		<div class="row form-group">
			<div class="col-md-6 mb-3 mb-md-0">
				<label class="text-black" for="fname">Category</label><br>
				<select class="form-control" name="categy">
				<c:if test="${not empty categoryList }">
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
				<input type="text" id="title" class="form-control" name="title" required>
			</div>
		</div>
		
		<div class="row form-group">
			<div class="col-md-12">
				<label class="text-black" for="title">File</label>
				<input type="hidden" id="file_cnt" name="file_cnt" value="1">
				<button type="button" class="btn btn-none" onclick="file_add(50, 'class=input_write');"><img src="images/add.png" width="22px" height="22px"></button>
				<button type="button" class="btn btn-none" onclick="file_delete();"><img src="images/minus.png" width="22px" height="22px"></button>
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
				<a href="board" class="btn btn-custom btn-md" style="float: right;">목록</a>
			</div>
		</div>

	</form>
	</div>

</div>
</div>
</section>
<script>
var uf = "";
function file_add(size, ext) {
	var filecountTemp = parseInt(document.getElementById("file_cnt").value);
	var parents = document.getElementById("file_add_form");
	var br = document.createElement("br");
	br.setAttribute("id", "br" + (filecountTemp + 1));
	parents.appendChild(br);
	if (filecountTemp == 10) {
		alert("파일 업로드는 최대 10개까지 가능합니다.");
		return;
	} else {
		var obj = document.createElement("input");
		obj.setAttribute("type", "file");
		//obj.setAttribute("size", size);
		obj.setAttribute("name", "mFile");
		obj.setAttribute("class", "form-control");
		obj.setAttribute("id", "file" + (filecountTemp + 1));
		parents.appendChild(obj);
	}
	document.getElementById("file_cnt").value = filecountTemp + 1;
}

function file_delete() {
	var filecountTemp = parseInt(document.getElementById("file_cnt").value);
	if (filecountTemp > 1) {
		var parents = document.getElementById("file_add_form");
		var obj = document.getElementById("file" + filecountTemp);
		var br = document.getElementById('br' + filecountTemp);
		parents.removeChild(obj);
		parents.removeChild(br);
		document.getElementById("file_cnt").value = filecountTemp - 1;
	}
}
</script>
</body>
