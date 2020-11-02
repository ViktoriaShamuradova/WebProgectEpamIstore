<nav class="col-md-2 d-none d-md-block bg-light sidebar secondary">
	<div class="sidebar-sticky">
		<ul class="nav flex-column">
			<li class="list-group-item list-group-item-dark"><p class="font-weight-bold large"><c:out value="Model catalog" /></p></li>
			<c:if test="${not empty category }">
					<li class="list-group-item"><a class="text-dark font-weight-bold large" href="controller?command=GET_MAIN_ALL_MODELS_OR_BY_CATEGORY_PAGE"><c:out value="All models"/></a></li>
			</c:if>
			<c:forEach var="category" items="${categories}">
				<li class="list-group-item">
				<a class="text-dark " href="controller?command=GET_MAIN_ALL_MODELS_OR_BY_CATEGORY_PAGE&category=${category.url}"><c:out value="${category.name}" /></a></li>
			</c:forEach>
			
			
		</ul>
	</div>
</nav>