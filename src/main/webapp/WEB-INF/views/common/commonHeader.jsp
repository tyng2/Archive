<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="isLogin" scope="request" value="${(not empty sessionScope.loginSession) ? true : false }" />
<c:if test="${isLogin }">
	<c:set var="loginSessionVo" scope="request" value="${sessionScope.loginSession }" />
</c:if>
