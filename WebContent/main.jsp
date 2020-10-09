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
<fmt:message bundle="${loc}" key="message.sighUp" var="signUp" />
<fmt:message bundle="${loc}" key="button.send" var="send_button" />

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body> 

<%@ include file = "../WEB-INF/jsp/ruEn.jsp" %> 

	<form action="controller" method="post">
			<input type="hidden" name="command" value="sign_in" />
			<c:out value="${login}" /> <br /> 
			<input type="login" name="login" value="" /><br />
	
			<c:out value="${password}" /> <br /> 
			<input type="password" name="password" value="" /><br />
			<input type="submit"  value="${send_button}"><br />
	</form>

		<h2>
			<a href="controller?command=registration_page"><c:out value="${signUp}" /></a>
		</h2>
		
	
		
	<div id="modelList">
		<!--<%@ include file = "../WEB-INF/jsp/model_list.jsp" %>   -->
		
		<div class="container">
		<div class="row">
			<c:forEach var="model" items="${models}">
				<div class="card" style="width: 18rem;">
					<div class="card-body">
						<h5 class="card-title">
							<c:out value="${model.name}" />
						</h5>
						<h6 class="card-price">
							<c:out value="${model.price}" />
						</h6>
						<p class="card-text">
							<c:out value="${model.description}" />
						</p>
						<p class="card-category">
							<c:out value="${model.category}" />
						</p>
						<p class="card-text">
							<c:out value="${model.producer}" />
						</p>
						<a href="#" class="btn btn-primary">Добавить в корзину</a>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
		
		<form action="controller" method="post">
			<input type="hidden" name="command" value="load_more_models" />		
			<input type="submit"  value="Load more models"><br />
		</form>
	</div>
	
	
</body>
</html>