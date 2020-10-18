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

	<div class="container">

		<table class="table">
			<thead>
				<tr>
					<th scope="col">Товар</th>
					<th scope="col">Количество</th>
					<th scope="col">Действие</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="shopCartItem" items="${sessionScope.shopcart.shopCartItems}">
					<tr>
					 	<th scope="row">1</th>
						<td><c:out value="${shopCartItem.model.name}" /></td>
					</tr>
					
					<tr>
						<th scope="row">2</th>
						<td><td><c:out value="${shopCartItem.count}" /></td></td>
					</tr>
					
					<tr>	
					 <th scope="row">3</th>			
						<td><button type="button" class="btn btn-warning">Удалить</button></td>		
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
	
	<c:out value="${sessionScope.shopcart.totalSum}" />
	
	<c:out value="${sessionScope.shopcart.totalCount}" />




</body>
</html>