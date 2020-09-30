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

</head>
<body>

<%@ include file = "ruEn.jsp" %>
	
<form action = "controller" method="post">
		<input type="hidden" name="command" value="save_new_shopper" />
		<c:out value="${name}" /> <br />
		<input type="text" name="name" value="" /><br />
		<c:out value="${surname}" /> <br />
		<input type="text" name="surname" value="" /><br />
		<c:out value="${login}" /> <br />
		<input type="text" name="login" value="" /><br />
		<c:out value="${email}" /><br />
		<input type="email" name="email" value="" /><br />
		<c:out value="${password}" /><br />
		<input type="password" name="password" value="" /><br />
		
		<input type="submit" value="${send_button}" /><br />
	
	</form>


</body>
</html>