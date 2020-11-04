<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/GeneralPagination.tld" prefix="pag"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description"
	content="Пример на bootstrap 4: Базовая панель администратора с фиксированной боковой панелью и навигационной панелью. Версия v4.0.0">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href="dashboard.css" rel="stylesheet">

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.locale" var="loc" />


</head>
<body>

<%@ include file="header.jsp"%>

	<div class= container mt-5>
		<c:if test="${ not empty current_message }">	
			<h2><c:out value="${current_message }"></c:out></h2>				
		</c:if>	
	</div>

	<div class="container-fluid">
		<div class="row">
			<%@ include file="shopper_navbar.jsp"%>
			
			<pag:modelPagination totalModels="${modelsCount}" modelsPerPage="${modelsPerPage}" 
				currentPage="${pageNumber}" models="${models}" category="${category}"/>	
		</div>
		
	</div>
	
<!-- Icons -->
	<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
	<script>
		feather.replace()
	</script>
	
</body>
</html>