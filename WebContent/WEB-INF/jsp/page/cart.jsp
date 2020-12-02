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
<fmt:message bundle="${loc}" key="attribute.count" var="countLoc" />
<fmt:message bundle="${loc}" key="button.back" var="back" />
<fmt:message bundle="${loc}" key="button.formOrder" var="formOrderLoc" />

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body>

	<%@ include file="../header.jsp"%>
	
	<div class= container mt-5>
		<c:if test="${ not empty current_message }">	
			<h2><c:out value="${current_message }"></c:out></h2>				
		</c:if>	
	</div>
	
	<div class="container">
		<table class="table">
			<thead>
				<tr>
					<th scope="col"><c:out value="${goodLoc}" /></th>
					<th scope="col"><c:out value="${countLoc}" /></th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach var="shopCartItem" items="${sessionScope.shopcart.shopCartItems}">
					<tr>
						<td><c:out value="${shopCartItem.model.name}" /></td>
						<td><a class="btn btn-secondary btn-sm" href="controller?command=reduce_count_of_goods_per_unit&modelId=${shopCartItem.model.id}" role="button">-</a>
							<c:out value="${shopCartItem.count}" />
							<a class="btn btn-secondary btn-sm" href="controller?command=increase_count_of_goods_per_unit&modelId=${shopCartItem.model.id}" role="button">+</a>
							</td>					
					</tr>	
				</c:forEach>				
			</tbody>
		</table>
	</div>

	
	<div class = "container">
	
	<c:if test="${ not empty sessionScope.shopcart.shopCartItems }">
		<nav aria-label="breadcrumb">
	  		<ol class="breadcrumb">
	    		<li class="breadcrumb-item">Total sum <h5><c:out value=" ${sessionScope.shopcart.totalSum}" /></h5></li>
	    		<li class="breadcrumb-item">Total count <h5><c:out value="${sessionScope.shopcart.totalCount}" /></h5></li>
	    		<li class="breadcrumb-item"><a href="controller?command=form_order" class="btn btn-light"><c:out value="${formOrderLoc}" /></a></li>		  			
	  		</ol>
	  	</nav>
	  	
	</c:if>
		<a class="btn btn-secondary " href="controller?command=ALL_MODELS_OR_BY_CATEGORY" role="button"><c:out value="${back}" /></a>
	</div>
	
</body>
</html>