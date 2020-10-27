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

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.locale" var="loc" />


<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />
<fmt:message bundle="${loc}" key="message.login" var="login" />
<fmt:message bundle="${loc}" key="message.password" var="password" />
<fmt:message bundle="${loc}" key="message.sighUp" var="signUp" />
<fmt:message bundle="${loc}" key="button.send" var="send_button" />

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href="front/font-awesome/css/all.css" rel="stylesheet">
<link href="dashboard.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Roboto+Condensed:wght@700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="front/css/style.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="javascript/main.js" type="text/javascript"></script>
</head>
<body>

	<%@ include file="../WEB-INF/jsp/header.jsp"%>

	<!-- <div>
		<button id="showFilters">Show filters</button>
		<div id="filters" style="display: none;">
			<input type="checkbox" /> 1
			<input type="checkbox" /> 2
			<input type="checkbox" /> 3
			<input type="checkbox" /> 4
			<input type="checkbox" /> 5
		</div>
	</div> -->


	<div class="container-fluid">
		<div class="row">
			<%@ include file="../WEB-INF/jsp/category_navbar.jsp"%>
			<%@ include file="../WEB-INF/jsp/model_list.jsp"%>
			<a class="btn btn-primary"
		href="controller?command=load_more_models&category=${category}"
		role="button">Load more models</a>
		</div>
		
	</div>




	
	 
	<%-- <%@ include file="../WEB-INF/jsp/models.jsp"%> --%>
	


	
	<%-- <form action="controller" method="post">
			<input type="hidden" name="command" value="load_more_models" />
			<input type="hidden" name="category" value="${requestScope.category}" />
			<input type="submit"  value="Load more models"><br />
		</form> --%>


</body>
</html>