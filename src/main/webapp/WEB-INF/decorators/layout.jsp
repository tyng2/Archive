<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta http-equiv="x-ua-compatible" content="ie=edge" />
	<title><sitemesh:write property="title" /></title>
	<!-- Favicon-->
	<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
	<!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css" />
    <!-- Google Fonts Roboto -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" />
    <!-- MDB -->
    <link rel="stylesheet" href="css/mdb.min.css" />
    <!-- Custom styles -->
    <link rel="stylesheet" href="css/style.css" />
	
	<!-- MDB -->
    <script type="text/javascript" src="js/mdb.min.js"></script>
	<script src="js/jquery-3.3.1.min.js"></script>
    <!-- Custom scripts -->
    <script type="text/javascript" src="js/scripts.js"></script>
	<spring:eval var="activeProfile" expression="@environment.getProperty('spring.profiles.active')" />
	<c:choose>
		<c:when test="${activeProfile eq 'local' }">
			<script src="js/vue.js"></script>
		</c:when>
		<c:otherwise>
			<script src="js/vue.js"></script>
<!-- 				<script src="js/vue.min.js"></script> -->
		</c:otherwise>
	</c:choose>
</head>
<body class="d-flex flex-column h-100">
    
	<jsp:include page="/WEB-INF/views/common/navigation.jsp" />
   	
	<sitemesh:write property="body" />
    
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>
