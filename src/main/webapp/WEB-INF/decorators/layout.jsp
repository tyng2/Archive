<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8" content="no-cache" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta http-equiv="x-ua-compatible" content="ie=edge" />
	<title><sitemesh:write property="title" /></title>
	<!-- Favicon-->
<!-- 	<link rel="icon" type="image/x-icon" href="assets/favicon.ico" /> -->
	<link rel="shortcut icon" href="/images/a.png">
	
	<jsp:include page="/WEB-INF/views/common/commonCSS.jsp" />
	<c:set var="isLogin" scope="session" value="${(not empty sessionScope.loginSession) ? true : false }" />
	<c:if test="${isLogin }">
		<c:set var="loginSessionVo" scope="session" value="${sessionScope.loginSession }" />
	</c:if>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/commonJS.jsp" />
	<jsp:include page="/WEB-INF/views/common/navigation.jsp" />
   	
	<sitemesh:write property="body" />
    
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>
