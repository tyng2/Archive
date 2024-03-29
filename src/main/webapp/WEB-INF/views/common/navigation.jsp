<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- <c:set var="currentTime" value="<%=System.currentTimeMillis() %>"/> --%>
<%-- <c:if test="${cookie.cookieID != null && sessionID == null }"> --%>
<%-- <c:set var="sessionID" scope="session" value="${cookie.cookieID.value }"/> --%>
<%-- </c:if> --%>
<script>
// $(document).ready(function() {
// 	$('li').click(function() {
// 		$('li').each(function() {
// 			$(this).removeClass("active");
// 		});
// 		$(this).addClass('active');
// 	});
// });
$(document).ready(function() {
	$('#loginBtn').on('click', function(){
		let url = window.location.pathname + window.location.search;
		let param = {
			redirectURI : url.substr(1)
		};
		CMJS.submit('/login', param);
	});
});
</script>
<div class="site-wrap">
	<div class="site-mobile-menu">
		<div class="site-mobile-menu-header">
			<div class="site-mobile-menu-close mt-3">
				<span class="icon-close2 js-menu-toggle"></span>
			</div>
		</div>
		<div class="site-mobile-menu-body"></div>
	</div>

	<div class="border-bottom top-bar py-2">
		<div class="container">
			<div class="row">
				<div class="col-2">
					<p align="left" class="mb-0">
					<c:if test="${login.authId eq 1 }">
						<img src="images/adminIcon.png" width="25px;">
					</c:if>
					</p>
				</div>
				<div class="col-10">
					<p class="mb-0 social-media">
					<c:choose>
					<c:when test="${!isLogin }">
						<a href="javascript:void(0);" id="loginBtn"><button class="log">로그인</button></a>
					</c:when>
					<c:otherwise>
						<span class="mr-3" style=""><a href="javascript:void(0);"><b>${login.nickname }</b></a>님 로그인 중</span>
						<a href="logout"><button class="log">로그아웃</button></a>
					</c:otherwise>
					</c:choose>
					<!-- <span class="mr-3"><strong>Phone:</strong><a href="tel://#">+1 292 3293 4238</a></span>
						<span><strong>Email:</strong><a href="#">info@yourdomain.com</a></span>
					</p>
				</div>
				<!-- <div class="col-md-6">
					<ul class="social-media">
						<li><a href="#" class="p-2"><span class="icon-facebook"></span></a></li>
						<li><a href="#" class="p-2"><span class="icon-twitter"></span></a></li>
						<li><a href="#" class="p-2"><span class="icon-instagram"></span></a></li>
						<li><a href="#" class="p-2"><span class="icon-linkedin"></span></a></li>
					</ul>
				</div> -->
			</div>
		</div>
	</div>
	<hr style="margin: 8px;">
	
	<header class="site-navbar py-4 bg-white" role="banner">
	<div class="container">
		<div class="row align-items-center">

			<div class="col-11 col-xl-2">
				<h1 class="mb-0 site-logo">
					<a href="/" class="text-black h2 mb-0">Archive</a>
				</h1>
			</div>
			<div class="col-12 col-md-10 d-none d-xl-block">
			<nav class="site-navigation position-relative text-right" role="navigation">

				<ul class="site-menu js-clone-nav mr-auto d-none d-lg-block">
					<li><a href="/">Home</a></li>
					<c:if test="${not empty menuList }">
					<c:forEach var="menu" items="${menuList }" varStatus="vs">
						<fmt:parseNumber var="childCnt" value="${menu.childCnt }" type="number"/>
						<c:choose>
						<c:when test="${menu.childCnt gt 0 }">
							<c:set var="menuChildCnt" value="${childCnt }" />
							<fmt:parseNumber var="index" value="0" integerOnly="true"/>
							<li class="has-children"><a href="${menu.menuLink }">${menu.menuName }</a>
								<ul class="dropdown">
						</c:when>
						<c:otherwise>
							<li><a href="${menu.menuLink }">${menu.menuName }</a></li>
						</c:otherwise>
						</c:choose>
						
						<c:if test="${menu.depth gt 1 }">
							<c:set var="index" value="${index + 1 }" />
						</c:if>
						<c:if test="${index eq menuChildCnt and index gt 0 }">
							</ul></li>
						</c:if>
					</c:forEach>
					</c:if>
<!--  					<li><a href="notice">Notice</a></li> -->
<!-- 					<li class="has-children"><a href="board">Board</a> -->
<!-- 						<ul class="dropdown"> -->
<!-- 							<li><a href="board?bord_catg=C">C</a></li> -->
<!-- 							<li><a href="board?bord_catg=Java">Java</a></li> -->
<!-- 							<li><a href="board?bord_catg=Python">Python</a></li> -->
<!-- 							<li><a href="board?bord_catg=SQL">SQL</a></li> -->
<!-- 							<li><a href="board?bord_catg=Web">Web</a></li> -->
<!-- 							<li><a href="board?category=1">기타</a></li> -->
<!-- 							<li><a href="board?category=2">잡담</a></li> -->
<!-- 						</ul></li> -->
<!-- 					<li><a href="boardChartForm.do">Stat</a></li> -->
<%-- 					<c:forEach var="" items="" > --%>
<!-- 						<li><a href="board">Board</a> -->
<%-- 					</c:forEach> --%>
<%-- 					<c:if test="${login.userAuth == 'admin' }"> --%>
<!-- 					<li class="has-children"><a href="admin.do">Admin</a> -->
<!-- 						<ul class="dropdown"> -->
<!-- 							<li><a href="memberList.do">Member</a></li> -->
<!-- 							<li><a href="adminChartForm.do">Stat</a></li> -->
<!-- 						</ul></li> -->
<%-- 					</c:if> --%>
				</ul>
			</nav>
			</div>

			<div class="d-inline-block d-xl-none ml-md-0 mr-auto py-3" style="position: relative; top: 3px;">
				<a href="#" class="site-menu-toggle js-menu-toggle text-black">
				<span class="icon-menu h3"></span></a>
			</div>
		</div>
	</div>
	</header>
</div>
</div>