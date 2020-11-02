<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:message bundle="${loc}" key="menu.orders" var="orders" />
<fmt:message bundle="${loc}" key="menu.cart" var="cart" />

<nav class="col-md-2 d-none d-md-block bg-light sidebar">
	<div class="sidebar-sticky">
		<ul class="nav flex-column">
			
			<li class="nav-item"><a class="nav-link text-dark"
				href="controller?command=my_orders"> <span data-feather="file"></span>
					<c:out value="${orders}" />
			</a></li>

			<li class="nav-item"><a class="nav-link text-dark"
				href="controller?command=cart_page"> <span
					data-feather="shopping-cart"></span> <c:out value="${cart}" />
					<c:out value="(${sessionScope.shopcart.totalCount})" />
			</a></li>
			
			<li class="list-group-item list-group-item-dark"><p class="font-weight-bold large"><c:out value="Model catalog" /></p></li>
			<c:if test="${not empty category }">
					<li class="list-group-item"><a class="text-dark font-weight-bold large" href="controller?command=ALL_MODELS_OR_BY_CATEGORY"><c:out value="All models"/></a></li>
			</c:if>
			
			<c:forEach var="category" items="${categories}">
				<li class="list-group-item">
				<a class="text-dark " href="controller?command=ALL_MODELS_OR_BY_CATEGORY&category=${category.url}"><c:out value="${category.name}" /></a></li>
			</c:forEach>
			
		</ul>
	</div>
</nav>