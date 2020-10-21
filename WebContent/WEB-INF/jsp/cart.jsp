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
					
				</tr>
			</thead>
			<tbody>
				<c:forEach var="shopCartItem" items="${sessionScope.shopcart.shopCartItems}">
					<tr>
						<td><c:out value="${shopCartItem.model.name}" /></td>
						<td><a class="btn btn-primary btn-sm" href="controller?command=reduce_count_of_goods_per_unit&idModel=${shopCartItem.model.id}" role="button">-</a>
							<c:out value="${shopCartItem.count}" />
							<a class="btn btn-primary btn-sm" href="controller?command=increase_count_of_goods_per_unit&idModel=${shopCartItem.model.id}" role="button">+</a>
							</td>					
					</tr>	
				</c:forEach>				
			</tbody>
		</table>
	</div>
	
	
	<div class = "container">
	
		<nav aria-label="breadcrumb">
	  		<ol class="breadcrumb">
	    		<li class="breadcrumb-item">Total sum <h5><c:out value=" ${sessionScope.shopcart.totalSum}" /></h5></li>
	    		<li class="breadcrumb-item">Total count <h5><c:out value="${sessionScope.shopcart.totalCount}" /></h5></li>
	    		<li class="breadcrumb-item"><a href="controller?command=form_order" class="btn btn-primary">Оформить заказ</a></li>
	    		
	   			
	  		</ol>
	  </nav>
	
	
	</div>
	
	



</body>
</html>