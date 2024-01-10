<%@page import="com.main.comm.SessionUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="LOGIN_SESSION" value="<%= SessionUtil.LOGIN_SESSION %>"/>
<c:set var="isLogin" scope="request" value="${(not empty sessionScope[LOGIN_SESSION] ) ? true : false }" />
<c:if test="${isLogin }">
	<c:set var="login" scope="request" value="${sessionScope[LOGIN_SESSION] }" />
</c:if>
<script>
var TESTDATA = 'TEST1';
</script>