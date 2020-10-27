<nav class="col-md-2 d-none d-md-block bg-light sidebar">
	<div class="sidebar-sticky">
		<ul class="nav flex-column">
			<li class="list-group-item active"><c:out value="Model catalog" /></li>
			<c:forEach var="category" items="${categories}">
			<li class="list-group-item">
			<a href="controller?command=models_by_category&category=${category.url}"><c:out value="${category.name}" /></a></li>
			</c:forEach>
		</ul>
	</div>
</nav>