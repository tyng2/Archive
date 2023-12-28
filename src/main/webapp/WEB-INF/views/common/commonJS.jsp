<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="/js/jquery-3.3.1.min.js"></script>
<script src="/js/jquery-migrate-3.0.1.min.js"></script>
<script src="/js/jquery-ui.js"></script>
<script src="/js/popper.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/owl.carousel.min.js"></script>
<script src="/js/jquery.stellar.min.js"></script>
<script src="/js/jquery.countdown.min.js"></script>
<script src="/js/jquery.magnific-popup.min.js"></script>
<script src="/js/bootstrap-datepicker.min.js"></script>
<script src="/js/aos.js"></script>

<script src="/js/typed.js"></script>
<script src="/js/main.js"></script>
<script src="/js/common.js"></script>
<spring:eval var="activeProfile" expression="@environment.getProperty('spring.profiles.active')" />
<c:choose>
	<c:when test="${activeProfile eq 'local' }">
		<script src="js/vue.js"></script>
	</c:when>
	<c:otherwise>
		<script src="js/vue.js"></script>
	</c:otherwise>
</c:choose>
<script>
var TESTDATA = 'TEST';
</script>