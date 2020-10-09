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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<%@ include file = "ruEn.jsp" %>

<div class="container" >
<dic class="layout">
<form action = "controller" method="post">
	<input type="hidden" name="command" value="save_new_shopper" />
		<div class="row">
	    <div class="col">
	      <input type="text" class="form-control" name="name" placeholder=<c:out value="${name}" />>
	    </div>
	    <div class="col">
	      <input type="text" class="form-control" name="surname" placeholder=<c:out value="${surname}" />>
	    </div>
	  </div>
  
	  <div class="form-group">
	    <label for="exampleInputEmail1"><c:out value="${email}" /></label>
	    <input type="email" class="form-control" name="email" id="exampleInputEmail1" aria-describedby="emailHelp">
	  </div>
	  
	  <div class="form-group">
		    <label for="exampleInputPassword1"><c:out value="${login}" /></label>
		    <input type="text" class="form-control" name="login" >
	</div>
	
	  <div class="form-group">
	    <label for="exampleInputPassword1"><c:out value="${password}" /></label>
	    <input type="password" class="form-control" name="password" id="exampleInputPassword1">
	  </div>
	  
	  
  	<button type="submit" class="btn btn-primary"><c:out value="${send_button}" /></button>
</form>
</div>
</div>
</body>
</html>