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

<fmt:message bundle="${loc}" key="attribute.good" var="goodLoc" />
<fmt:message bundle="${loc}" key="attribute.price" var="priceLoc" />
<fmt:message bundle="${loc}" key="attribute.count" var="countLoc" />
<fmt:message bundle="${loc}" key="attribute.totalsum" var="totalSumLoc" />
<fmt:message bundle="${loc}" key="attribute.totalcount" var="totalCountLoc" />
<fmt:message bundle="${loc}" key="button.back" var="back" />

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body>
	<c:if test="${current_message !=null}">
	<h1><c:out value=" ${current_message }" /></h1>
	</c:if>
	
	<%@ include file="header.jsp"%>
	
	<div class="container">
		<table class="table">
			<thead>
				<tr>
					<th scope="col"><c:out value="${goodLoc}" /></th>
					<th scope="col"><c:out value="${priceLoc}" /></th>
					<th scope="col"><c:out value="${countLoc}" /></th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach var="orderItem" items="${order.orderItems}">
					<tr>
						<td><c:out value="${orderItem.model.name}" /></td>
						<td><c:out value="${orderItem.model.price}" /></td>	
						<td><c:out value="${orderItem.count}" /></td>					
					</tr>	
				</c:forEach>				
			</tbody>
		</table>
	</div>
	
	<div class = "container">
	
		<nav aria-label="breadcrumb">
	  		<ol class="breadcrumb">
	    		<li class="breadcrumb-item"><c:out value="${totalSumLoc}" /><h5><c:out value=" ${order.totalSum}" /></h5></li>
	    		<li class="breadcrumb-item"><c:out value="${totalCountLoc}" /><h5><c:out value=" ${order.totalCount}" /></h5></li>
	    	  			
	  		</ol>
	  	</nav>
	  	<a class="btn btn-secondary" href="controller?command=my_orders" role="button"><c:out value="${back}" /></a>
	
	</div>
	
	

</body>