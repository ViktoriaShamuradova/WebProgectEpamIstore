<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/GeneralPagination.tld" prefix="userPag"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>


<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.locale" var="loc" />

<fmt:message bundle="${loc}" key="message.noUser" var="noUserLoc" />
<fmt:message bundle="${loc}" key="attribute.userId" var="userIdLoc" />
<fmt:message bundle="${loc}" key="attribute.name" var="nameLoc" />
<fmt:message bundle="${loc}" key="attribute.surname" var="surnameLoc" />
<fmt:message bundle="${loc}" key="attribute.login" var="loginLoc" />
<fmt:message bundle="${loc}" key="attribute.email" var="emailLoc" />
<fmt:message bundle="${loc}" key="attribute.role" var="roleLoc" />
<fmt:message bundle="${loc}" key="attribute.admin" var="adminLoc" />
<fmt:message bundle="${loc}" key="attribute.shopper" var="shopperLoc" />
<fmt:message bundle="${loc}" key="attribute.allUsers" var="allUsersLoc" />
<fmt:message bundle="${loc}" key="attribute.action" var="actionLoc" />

</head>
<body>

	<%@ include file="header.jsp"%>

	<div class="container-fluid">
		<div class="row">
			<%@ include file="admin_navbar.jsp"%>

			<div class="container">
				
				<table class="table">
					<thead>
						<tr>
							<c:if test="${empty users }">
								<tr>
									<td><c:out value="${noUserLoc}" /></td>
								</tr>
							</c:if>
							<th scope="col"><c:out value="${userIdLoc}" /></th>
							<th scope="col"><c:out value="${nameLoc}" /></th>
							<th scope="col"><c:out value="${surnameLoc}" /></th>
							<th scope="col"><c:out value="${loginLoc}" /></th>
							<th scope="col"><c:out value="${emailLoc}" /></th>
							<th scope="col">
								<div class="btn-group">
  									<button type="button" class="btn btn-default dropdown-toggle font-weight-bold" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
   											<c:out value="${roleLoc}" />
 									 </button>
 									 <div class="dropdown-menu">
										    <a class="dropdown-item font-weight-bold" href="controller?command=users_by_role&roleId=1"><c:out value="${adminLoc}" /></a>
										    <a class="dropdown-item font-weight-bold" href="controller?command=users_by_role&roleId=2"><c:out value="${shopperLoc}" /></a>									 
										    <div class="dropdown-divider font-weight-bold"></div>
										    <a class="dropdown-item font-weight-bold" href="controller?command=all_users"><c:out value="${allUsersLoc}" /></a>
									 </div>
								</div>								
							</th>
							<th scope="col"><c:out value="${actionLoc}" /></th>

						</tr>
					</thead>
					<tbody>
						<%@ include file="users_list_body.jsp"%>
						
					</tbody>
				</table>
					<userPag:userPagination currentCommand ="${command}" totalEntity="${userCount}" perPage="${perPage}" currentPage="${pageNumber}" roleId="${roleId }" />
			</div>
		</div>
	</div>
</body>