<nav class="col-md-2 d-none d-md-block bg-light sidebar">
	<div class="sidebar-sticky">
		<ul class="nav flex-column">
			
			<li class="nav-item"><a class="nav-link"
				href="controller?command=my_orders"> <span data-feather="file"></span>
					Orders
			</a></li>

			<li class="nav-item"><a class="nav-link"
				href="controller?command=cart_page"> <span
					data-feather="shopping-cart"></span> Cart 
					<c:out value="(${sessionScope.shopcart.totalCount})" />
			</a></li>
			
			<li class="list-group-item active"><c:out value="Model catalog" /></li>
			<c:forEach var="category" items="${categories}">
				<li class="list-group-item">
				<a href="controller?command=GET_MAIN_ALL_MODELS_OR_BY_CATEGORY_PAGE&category=${category.url}"><c:out value="${category.name}" /></a></li>
			</c:forEach>
		
		</ul>
</nav>