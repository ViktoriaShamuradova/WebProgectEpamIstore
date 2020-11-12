<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:message bundle="${loc}" key="category.allModel" var="allModelLoc" />
<fmt:message bundle="${loc}" key="category.catalog" var="catalogLoc" />


<nav class="col-md-2 d-none d-md-block bg-light sidebar secondary">
	<div class="sidebar-sticky">
		<ul class="nav flex-column">
			<li class="list-group-item list-group-item-dark"><p class="font-weight-bold large"><c:out value="${catalogLoc}" /></p></li>
			<c:if test="${not empty category }">
					<li class="list-group-item"><a class="text-dark font-weight-bold large" href="controller?command=ALL_MODELS_OR_BY_CATEGORY"><c:out value="${allModelLoc}"/></a></li>
			</c:if>
			<c:forEach var="category" items="${categories}">
				<li class="list-group-item">
				<a class="text-dark " href="controller?command=ALL_MODELS_OR_BY_CATEGORY&category=${category.id}"><c:out value="${category.name}" /></a></li>
			</c:forEach>
		</ul>
	</div>
</nav>