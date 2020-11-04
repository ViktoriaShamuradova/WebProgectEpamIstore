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
<fmt:message bundle="${loc}" key="message.name" var="name" />
<fmt:message bundle="${loc}" key="message.surname" var="surname" />
<fmt:message bundle="${loc}" key="message.email" var="email" />
<fmt:message bundle="${loc}" key="button.send" var="send_button" />
<fmt:message bundle="${loc}" key="button.back" var="back_button" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<%@ include file="header.jsp"%>

<div class="container mt-5" >

		<form action = "controller" method="post">
			<input type="hidden" name="command" value="save_new_shopper" />
				<div class="row">
			    <div class="col">
			      <input type="text" required class="form-control" name="name" placeholder=<c:out value="${name}" />>
			    </div>
			    <div class="col">
			      <input type="text" required class="form-control" name="surname" placeholder=<c:out value="${surname}" />>
			    </div>
			  </div>
		  
			  <div class="form-group mt-3">
			    <input type="email" required class="form-control" name="email" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder=<c:out value="${email}"/>>
			  </div>
			  
			  <div class="form-group">
				    <input type="text" required  class="form-control" name="login" placeholder=<c:out value="${login}"/>>
			</div>
			
			  <div class="form-group">
			    <input type="password" required class="form-control" name="password" id="exampleInputPassword1" placeholder=<c:out value="${password}"/>>
			  </div>
			  
			  
		  	<button type="submit" required class="btn btn-secondary"><c:out value="${send_button}" /></button>
		  	<div class="row mx-md-n5" style="margin-top: 20px;">
		  							<div class="col px-md-5"><a href="controller?command=ALL_MODELS_OR_BY_CATEGORY"><button type="button" class="btn btn-light"><c:out value="${back_button}" /></button></a></div>
		  							
			</div>
		</form>
</div>

</body>
</html>