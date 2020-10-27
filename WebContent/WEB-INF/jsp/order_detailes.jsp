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
	<c:if test="${current_message !=null}">
	<h1><c:out value=" ${current_message }" /></h1>
	</c:if>
	
	<div class="container">
		<table class="table">
			<thead>
				<tr>
					<th scope="col"><c:out value="Товар" /></th>
					<th scope="col"><c:out value="Цена" /></th>
					<th scope="col"><c:out value="Количество" /></th>
					
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
	    		<li class="breadcrumb-item">Total sum <h5><c:out value=" ${order.totalSum}" /></h5></li>
	    		<li class="breadcrumb-item">Total count <h5><c:out value="${order.totalCount}" /></h5></li>
	    	
	    		  			
	  		</ol>
	  	</nav>
	
	</div>
	
	<a class="btn btn-primary btn-sm" href="controller?command=my_orders" role="button">Назад</a>

</body>