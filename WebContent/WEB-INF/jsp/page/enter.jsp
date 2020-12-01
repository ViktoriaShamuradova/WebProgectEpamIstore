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
<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />
<fmt:message bundle="${loc}" key="message.login" var="login" />
<fmt:message bundle="${loc}" key="message.password" var="password" />
<fmt:message bundle="${loc}" key="button.send" var="send_button" />
<fmt:message bundle="${loc}" key="button.back" var="back_button" />

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<body>

	<%@ include file="../header.jsp"%>
	
	<div class= container mt-5>
		<c:if test="${ not empty current_message }">	
			<h2><c:out value="${current_message }"></c:out></h2>				
		</c:if>	
	</div>
	
	<div class="container mt-5" >

		<form action = "controller" method="post">
			<input type="hidden" name="command" value="sign_in" />
				<div class="row">
			    <div class="col">
			      <input type="text" required class="form-control" name="login" placeholder=<c:out value="${login}" />>
			    </div>
			    <div class="col">
			      <input type="password" required class="form-control" name="password" placeholder=<c:out value="${password}" />>
			    </div>
			  </div>
			  
		<div class="container mt-3" >
		  <button type="submit"  class="btn btn-secondary bt-5"><c:out value="${send_button}" /></button>
		  <a href="controller?command=ALL_MODELS_OR_BY_CATEGORY"><button type="button" class="btn btn-light"><c:out value="${back_button}" /></button></a>
		 </div>
		</form>
	</div>
	
	<c:if test="${ not empty sessionScope.current_message }">	
		<h2><c:out value="${sessionScope.current_message }"></c:out></h2>				
	</c:if>	

</body>
</html>