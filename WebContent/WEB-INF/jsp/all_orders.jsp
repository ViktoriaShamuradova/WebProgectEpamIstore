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

<fmt:message bundle="${loc}" key="menu.orders" var="oderLoc" />
<fmt:message bundle="${loc}" key="attribute.orderId" var="oderIdLoc" />
<fmt:message bundle="${loc}" key="attribute.orderDate" var="oderDateLoc" />
<fmt:message bundle="${loc}" key="message.noOrder" var="noOrderLoc" />
<fmt:message bundle="${loc}" key="button.back" var="back" />
<fmt:message bundle="${loc}" key="button.loadMore" var="loadMore" />
<fmt:message bundle="${loc}" key="message.order" var="orderLoc" />

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body>

	<%@ include file="header.jsp"%>


	<div class="container-fluid center">
		<div class="row">
		
			<h4><c:out value="${orderLoc}" /></h4>

			<div class="container">
				<table class="table table-striped">
					<thead>
						<tr>
							<th scope="col"><c:out value="${orderIdLoc}" /></th>
							<th scope="col"><c:out value="${orderDateLoc}"/></th>
						
						</tr>
					</thead>
					<tbody>
			
						<c:if test="${empty orders }">
							<tr>
								<td><c:out value="${noOrderLoc}" /></td>
							</tr>
						</c:if>
				
						<%@ include file="all_orders_body.jsp"%>							
					</tbody>
				</table>
			
				<div>	
					<c:if test="${pageCount > 1}">
						<a class="btn btn-secondary " href="controller?command=load_more_orders&pageNumber=${pageNumber+1}&pageCount=${pageCount-1}" role="button"><c:out value="${loadMore}" /></a>
					</c:if>		
					<a class="btn btn-light" href="controller?command=ALL_MODELS_OR_BY_CATEGORY" role="button"><c:out value="${back}" /></a>
				</div>
			
			</div>			
		</div>
	</div>
</body>