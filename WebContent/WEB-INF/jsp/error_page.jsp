<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

</head>
<body>

<h1>${statusCode}</h1>
<c:choose>
	<c:when test="${statusCode ==401 }">You don't have permissions to view this resource</c:when>
	<c:when test="${statusCode ==404 }">Requested resource not found</c:when>
	<c:otherwise>Sorry! Can't process this request! Try again later...</c:otherwise>
</c:choose>


</body>
</html>