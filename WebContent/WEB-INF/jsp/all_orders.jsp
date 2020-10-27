<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body>

	<%@ include file="header.jsp"%>


	<div class="container-fluid">
		<div class="row">
			<%@ include file="shopper_navbar.jsp"%>
			
			<h4>My orders</h4>

			<div class="container">
				<table class="table">
					<thead>
						<tr>
							<th scope="col"><c:out value="Order id" /></th>
							<th scope="col"><c:out value="Date" /></th>
						
						</tr>
					</thead>
					<tbody>
			
						<c:if test="${empty orders }">
							<tr>
								<td>No orders here</td>
							</tr>
						</c:if>
				
						<%@ include file="all_orders_body.jsp"%>							
					</tbody>
				</table>
			
				<div>	
					<c:if test="${pageCount > 1}">
						<a class="btn btn-primary" href="controller?command=load_more_orders&pageNumber=${pageNumber+1}&pageCount=${pageCount-1}" role="button">Load more orders</a>
					</c:if>		
					<a class="btn btn-primary" href="controller?command=GET_SHOPPER_PAGE" role="button">Назад</a>
				</div>
			
		`	</div>			
		</div>
	</div>
</body>