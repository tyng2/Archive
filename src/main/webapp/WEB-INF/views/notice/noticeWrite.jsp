<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
	<title>Archive &mdash; Template</title>
	<jsp:include page="/WEB-INF/views/common/commonHeader.jsp" />
</head>
<body id="noticeWrite">
<div class="site-blocks-cover inner-page-cover overlay" style="background-image: url(images/hero_1.jpg);" 
	data-aos="fade" data-stellar-background-ratio="0.5">
	<div class="container">
		<div class="row align-items-center justify-content-center text-center">

			<div class="col-md-12" data-aos="fade-up" data-aos-delay="400">

				<div class="row justify-content-center mb-4">
					<div class="col-md-8 text-center">
						<h1>Notice Write</h1>
						<p class="lead mb-5">공지사항 작성</p>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>

<section class="site-section bg-light">
<div class="container">
	<div class="row">
	
		<div style="width: 100%">
			<form action="insertNotice" class="p-5 bg-white" method="POST">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				
				<h2 class="h4 text-black mb-5">Write</h2>
				<div class="row form-group">
					<div class="col-md-12">
						<label class="text-black" for="notiTitle">Title</label>
						<input type="text" id="notiTitle" class="form-control" name="notiTitle" required>
					</div>
				</div>
				
				<div class="row form-group">
					<div class="col-md-12">
						<label class="text-black" for="notiCont">Content</label>
						<textarea name="notiCont" id="notiCont" cols="30" rows="8" class="form-control" required
							placeholder="Write your contents here..."></textarea>
					</div>
				</div>

				<div class="row form-group">
					<div class="col-md-12">
						<input type="submit" value="등록" class="btn btn-primary btn-md">
						<a href="notice" class="btn btn-custom btn-md" style="float: right;">목록</a>
					</div>
				</div>

			</form>
		</div>
	
	</div>
</div>
</section>
<script>
</script>
</body>
