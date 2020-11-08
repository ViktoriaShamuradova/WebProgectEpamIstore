<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:message bundle="${loc}" key="menu.allOrders" var="allOrdersLoc" />
<fmt:message bundle="${loc}" key="menu.allUsers" var="allUsersLoc" />
<fmt:message bundle="${loc}" key="menu.allModels" var="allModelsLoc" />
<fmt:message bundle="${loc}" key="menu.blacklist" var="blacklistLoc" />

<nav class="col-md-2 d-none d-md-block bg-light sidebar">
	<div class="sidebar-sticky">
		<ul class="nav flex-column">

			<li class="nav-item  text-dark"><a class="nav-link text-dark bold"
				href="controller?command=all_users"><i class="fas fa-users"></i>
					<c:out value="${allUsersLoc}" />
			</a></li>
			
			<li class="nav-item"><a class="nav-link text-dark"
				href="controller?command=all_orders"><i class="fas fa-list"></i>
					<c:out value="${allOrdersLoc}" />
			</a></li>
			
			<li class="nav-item"><a class="nav-link text-dark"
				href="controller?command=ALL_MODELS_OR_BY_CATEGORY"> <i class="fas fa-keyboard"></i> 
				<c:out value="${allModelsLoc}" />
			</a></li>

			<li class="nav-item"><a class="nav-link text-dark"
				href="controller?command=black_list"> <i class="fas fa-ban"></i>
					<c:out value="${blacklistLoc}" />
			</a></li>

		</ul>
	</div>
</nav>