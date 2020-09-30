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
	
	
</body>
</html>