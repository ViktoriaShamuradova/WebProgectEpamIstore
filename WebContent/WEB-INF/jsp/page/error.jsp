<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.locale" var="loc" />

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body>
	<%@ include file="../header.jsp"%>
	<div class="alert alert-danger hidden print" role="alert">
		<h1>${statusCode}</h1>

		<c:out value="${statusCode }"></c:out>


		<c:choose>
			<c:when test="${statusCode == 403 }">You don't have permissions to view this resource</c:when>
			<c:when test="${statusCode == 404 }">Requested resource not found</c:when>
			<c:when test="${statusCode == 500 }">Sorry, internal application error. Come back later </c:when>

			<c:otherwise>Sorry! Something went wrong. Come back later</c:otherwise>
		</c:choose>
	</div>
</body>
</html>