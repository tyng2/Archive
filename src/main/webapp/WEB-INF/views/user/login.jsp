<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<title>Login / Potal &mdash; Web Community</title>
	<jsp:include page="/WEB-INF/views/common/commonHeader.jsp" />
</head>
<body id="login">
<div class="site-blocks-cover inner-page-cover overlay"
	style="background-image: url(images/img_2.jpg);" data-aos="fade"
	data-stellar-background-ratio="0.5">
	<div class="container">
		<div class="row align-items-center justify-content-center text-center">
			<div class="col-md-12" data-aos="fade-up" data-aos-delay="400">

				<div class="row justify-content-center mb-4">
					<div class="col-md-8 text-center">
						<h1>Login</h1>
						<p class="lead mb-5"></p>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>

<div class="site-section bg-light">
<div class="container">
	<div class="row" style="margin: 0 auto; max-width: 600px;">
	<div style="width: 100%;">

		<form action="/loginProcess" class="p-5 bg-white" method="POST">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			
			<h2 class="h4 text-black mb-5">Login Form</h2>

			<div class="row form-group">
				<div class="col-md-12">
					<label class="text-black" for="email">ID</label>
					<input type="text" name="id" class="form-control" required>
				</div>
			</div>

			<div class="row form-group">
				<div class="col-md-12">
					<label class="text-black" for="subject">Password</label>
					<input type="password" name="pw" class="form-control" required>
				</div>
			</div>
			
			<div class="row form-group">
				<div class="col-md-12"><br>
<!-- 				<a href="/naver-login" class=""> -->
				<a href="javascript:void(0);" id="naverLogin" class="">
					<img src="images/btnW_compl.png" width="50%" height="" alt="네이버 로그인" >
				</a>
				<!-- 
				<span class="button-checkbox">
      					<button type="button" class="btn btn-lg" data-color="primary">로그인 상태 유지</button>
			        <input type="checkbox" value="true" name="loginChk" class="hidden" style="display: none;" />
			        <a href="passwordForm#f" class="btn btn-custom btn-md" style="float: right;">비밀번호 찾기</a>
			    </span>
				<!-- <label>로그인 상태 유지</label>
					<input type="checkbox" name="loginChk" value="true" class="form-control"> -->
				</div>
			</div>

			<div class="row form-group">
				<div class="col-md-12"><br>
				<button class="btn btn-custom btn-md">Sign In</button>
				<a href="join#f" class="btn btn-custom btn-md" style="float: right;">Sign Up</a>
				</div>
			</div>

		</form>
		
	</div>
	</div>
</div>
</div>

<script src="/js/Archive/user/login.js"></script>
</body>
