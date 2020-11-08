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

<fmt:message bundle="${loc}" key="attribute.name" var="nameLoc" />
<fmt:message bundle="${loc}" key="attribute.surname" var="surnameLoc" />
<fmt:message bundle="${loc}" key="attribute.email" var="emailLoc" />
<fmt:message bundle="${loc}" key="button.back" var="back" />

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body>

<%@ include file="header.jsp"%>

<div class="container">
		<table class="table">
			<thead>
				<tr>
					<th scope="col"><c:out value="${nameLoc}" /></th>
					<th scope="col"><c:out value="${surnameLoc}" /></th>
					<th scope="col"><c:out value="${emailLoc}" /></th>
					
				</tr>
			</thead>
			<tbody>
				
					<tr>
						<td><c:out value="${userRes.name}" /></td>
						<td><c:out value="${userRes.surname}" /></td>	
						<td><c:out value="${userRes.email}" /></td>					
					</tr>	
								
			</tbody>
		</table>
		<a class="btn btn-secondary" href="controller?command=all_orders" role="button"><c:out value="${back}" /></a>
	</div>
	
	
	
	
</body>