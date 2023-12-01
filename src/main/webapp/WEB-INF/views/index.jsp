<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Archive Index page</title>
</head>
<body>
INDEX

<c:if test="${test != null }">
	<br>${test.id }
	<br>${test.val }
</c:if>
</body>
</html>