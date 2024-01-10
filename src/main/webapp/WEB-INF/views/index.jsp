<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>INDEX</title>
    <jsp:include page="/WEB-INF/views/common/commonHeader.jsp" />
</head>
<body id="index">
<div class="site-blocks-cover overlay" style="background-image: url(images/hero_1.jpg);" data-aos="fade" data-stellar-background-ratio="0.5">
<div class="container">
	<div class="row align-items-center justify-content-center text-center">

		<div class="col-md-12" data-aos="fade-up" data-aos-delay="400">

			<div class="row justify-content-center mb-4">
				<div class="col-md-8 text-center">
					<h1>
						We Are Expert in <span class="typed-words"></span>
					</h1>
					<p class="lead mb-5">Community - Potal</p>
					<!-- Search form -->
					<div id="searchNAVER">
						<a id="google" class="searchSwitch">Google로 검색</a>
						<form action="https://search.naver.com/search.naver" method="get" target="_blank">
							<input class="form-control" type="text" placeholder="NAVER" aria-label="Search" name="query"><br>
							<button class="btn btn-custom1 btn-md">Search</button>
						</form>
					</div>
					<div id="searchGoogle" style="display: none;">
						<a id="naver" class="searchSwitch">NAVER로 검색</a>
						<form action="https://www.google.co.kr/search" method="get" target="_blank">
							<input class="form-control" type="text" placeholder="Google" aria-label="Search" name="q"><br>
							<button class="btn btn-custom1 btn-md">Search</button>
						</form>
					</div>
				</div>
			</div>
			
		</div>
		
	</div>
</div>
</div>

<section class="section ft-feature-1">
<div class="container">
	<div class="row align-items-stretch">
	<div class="col-12 bg-black w-100 ft-feature-1-content">
		<c:if test="${!isLogin }">
			<br><h2 style="text-align: center;">로그인 후 사용가능합니다.</h2>
		</c:if>
		<div class="row align-items-center">
			
			<div class="col-lg-5">
				<div class="mb-5 h-100">
					<div class="d-flex align-items-center">
						<div>
<!-- 								<a href="https://vimeo.com/317571768" -->
<!-- 									class="popup-vimeo d-block play"><span class="icon-play"></span></a> -->
						</div>
<!-- 							<h2>Welcome To Chimper An Awward Winning Web Agency</h2> -->
					</div>
					
					<div id="siteList"></div>
<!-- 						<img src="images/about_1.jpg" alt="Image" -->
<!-- 							class="img-feature img-fluid"> -->
				</div>
			</div>
			<div class="col-lg-3 ml-auto">
			<c:if test="${isLogin }">
				<div class="mb-5">
					<h3 class="mb-4 d-flex align-items-center">
						<span class="icon icon-phonelink mr-2"></span><span>Insert your Favorite Site</span>
					</h3>
					<p><label class="text-white" for="name">Name</label>
					<input type="text" id="name" class="form-control"><br>
					<label class="text-white" for="url">URL</label>
					<input type="text" id="url" class="form-control"><br>
					</p>
					<p>
						<button type="button" id="addSite" style="float: right;" class="btn btn-primary btn-md">Add</button>
					</p>
				</div>
			</c:if>

				<!-- <div>
					<h3 class="d-flex align-items-center">
						<span class="icon icon-extension mr-2"></span><span>Branding
							&amp; Identity</span>
					</h3>
					<p>Lorem ipsum dolor sit amet consectetur adipisicing elit.
						Cumque ab nihil quam nesciunt.</p>
					<p>
						<a href="#">Read More</a>
					</p>
				</div> -->

			</div>
			<!-- <div class="col-lg-3">
				<div class="mb-5">
					<h3 class="d-flex align-items-center">
						<span class="icon icon-format_paint mr-2"></span><span>Art
							Direction</span>
					</h3>
					<p>Lorem ipsum dolor sit amet consectetur adipisicing elit.
						Cumque ab nihil quam nesciunt.</p>
					<p>
						<a href="#">Read More</a>
					</p>
				</div>

				<div>
					<h3 class="d-flex align-items-center">
						<span class="icon icon-question_answer mr-2"></span><span>Copywriting</span>
					</h3>
					<p>Lorem ipsum dolor sit amet consectetur adipisicing elit.
						Cumque ab nihil quam nesciunt.</p>
					<p>
						<a href="#">Read More</a>
					</p>
				</div>
			</div> -->
		</div>
	</div>
	</div>
</div>
</section>

<c:if test="${not empty noticeList }">
	<section class="site-section noticeList">
	<div class="container">
		<div class="row">
		<c:forEach items="${noticeList }" var="n">
			<div class="col-md-6 col-lg-4" onclick="location.href='notice';" style="cursor: pointer;">
				<div class="p-3 box-with-humber">
					<div class="number-behind">${n.notiId }.</div>
					<h2>${n.notiTitle }</h2>
					<p>${n.notiCont }</p>
				</div>
			</div>
		</c:forEach>
		</div>
	</div>
	</section>
</c:if>

<c:if test="${not empty boardList }">
	<section class="site-section testimonial-wrap">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-8 text-center">
				<h2 class="text-black h1 site-section-heading text-center">최근 게시글</h2>
			</div>
		</div>
	</div>
	
	<div class="slide-one-item home-slider owl-carousel">
	
	<c:forEach items="${boardList }" var="b">
	
		<div style="cursor: pointer;" onclick="location.href='/detail?bordId=${b.bordId }&pageNum=1';">
			<div class="testimonial">
		
				<blockquote class="mb-5">
					<h3>${b.bordTitl }</h3>
					<p>&ldquo;${b.bordCont }&rdquo;</p>
				</blockquote>
		
				<figure class="mb-4 d-flex align-items-center justify-content-center">
					<div>
					</div>
					<p>${b.userId }</p>
				</figure>
			</div>
		</div>
		
	</c:forEach>
	
	</div>
	</section>
</c:if>

<script src="/js/Archive/index.js?"></script>
</body>