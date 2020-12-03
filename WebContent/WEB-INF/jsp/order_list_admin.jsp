<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/GeneralPagination.tld" prefix="pag"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.locale" var="loc" />

<fmt:message bundle="${loc}" key="message.allOrders" var="allOrdersLoc" />
<fmt:message bundle="${loc}" key="message.noOrder" var="noOrderLoc" />
<fmt:message bundle="${loc}" key="attribute.orderId" var="orderIdLoc" />
<fmt:message bundle="${loc}" key="attribute.userId" var="userIdLoc" />
<fmt:message bundle="${loc}" key="attribute.orderDate" var="dateLoc" />
<fmt:message bundle="${loc}" key="attribute.order.status" var="statusLoc" />
<fmt:message bundle="${loc}" key="attribute.action" var="actionLoc" />

</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="container-fluid">
		<div class="row">
			<%@ include file="admin_navbar.jsp"%>


			<div class="container">
				<h2><c:out value="${allOrdersLoc}" /></h2>
				<table class="table">
					<thead>
						<tr>
							<c:if test="${empty orders }">
								<tr>
									<td><c:out value="${noOrderLoc}" /></td>
								</tr>
							</c:if>
							<th scope="col"><c:out value="${orderIdLoc} " /></th>
							<th scope="col"><c:out value="${userIdLoc} " /></th>
							<th scope="col"><c:out value="${dateLoc}" /></th>
							<th scope="col">
								<div class="btn-group">
  									<button type="button" class="btn btn-default dropdown-toggle font-weight-bold" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
   											<c:out value="${statusLoc}" />
 									 </button>
 									 <div class="dropdown-menu">
 									 	<c:forEach var="status" items="${statuses}">
 									 		<a class="dropdown-item font-weight-bold" href="controller?command=ALL_ORDERS&status=${status}"><c:out value="${status}" /></a>
 									 	
 									 	</c:forEach>
																		 
										    <div class="dropdown-divider font-weight-bold"></div>
										    <a class="dropdown-item font-weight-bold" href="controller?command=all_orders"><c:out value="${allOrdersLoc}" /></a>
									 </div>
								</div>
							
							</th>
							<th scope="col"><c:out value="${actionLoc}" /></th>							

						</tr>
					</thead>
					<tbody>
						<%@ include file="order_list_admin_body.jsp"%>
						
					</tbody>
				</table>
					<pag:pagination currentCommand ="${command}" totalEntity="${ordersCount}" perPage="${ordersPerPage}" currentPage="${pageNumber}" status="${status}" />
					
			</div>			
		</div>
	</div>
</body>