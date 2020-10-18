<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<div class="container">
	<div class="row">
		<c:forEach var="model" items="${models}">
			<div class="card" style="width: 18rem;">
				<div class="card-body">
					<h5 class="card-title">
						<c:out value="${model.name}" />
					</h5>
					<h6 class="card-price">
						<c:out value="${model.price}" />
					</h6>
					<p class="card-text">
						<c:out value="${model.description}" />
					</p>
					<p class="card-category">
						<c:out value="${model.category}" />
					</p>
					<p class="card-text">
						<c:out value="${model.producer}" />
					</p>
					
	
					<p>
						<a href="controller?command=add_to_cart&modelId=${model.id}" class="btn btn-primary">Добавить в корзину</a>
					</p>
				
				</div>
			</div>
		</c:forEach>
	</div>
</div>