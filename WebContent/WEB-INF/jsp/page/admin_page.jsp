<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Пример на bootstrap 4: Базовая панель администратора с фиксированной боковой панелью и навигационной панелью. Версия v4.0.0">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href="front/font-awesome/css/all.css" rel="stylesheet">
<link href="dashboard.css" rel="stylesheet">
<link rel="stylesheet" href="front/css/style.css">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="javascript/main.js" type="text/javascript"></script>


<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.locale" var="loc" />


</head>
<body>

<%@ include file="../header.jsp"%>

<c:out value="Hello ${sessionScope.user.login}!" />

<div class="container-fluid">
		<div class="row">
			<%@ include file="../admin_navbar.jsp"%>		
		</div>		
</div>
	
</body>
</html>