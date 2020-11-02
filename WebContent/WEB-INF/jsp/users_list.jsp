<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/GeneralPagination.tld" prefix="userPag"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>


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
									<td>No users here</td>
								</tr>
							</c:if>
							<th scope="col"><c:out value="User id" /></th>
							<th scope="col"><c:out value="Name" /></th>
							<th scope="col"><c:out value="Surname" /></th>
							<th scope="col"><c:out value="Login" /></th>
							<th scope="col"><c:out value="Email" /></th>
							<th scope="col">
								<div class="btn-group">
  									<button type="button" class="btn btn-default dropdown-toggle font-weight-bold" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
   											<c:out value="Role" />
 									 </button>
 									 <div class="dropdown-menu">
										    <a class="dropdown-item font-weight-bold" href="controller?command=users_by_role&roleId=1">Admin</a>
										    <a class="dropdown-item font-weight-bold" href="controller?command=users_by_role&roleId=2">Shopper</a>									 
										    <div class="dropdown-divider font-weight-bold"></div>
										    <a class="dropdown-item font-weight-bold" href="controller?command=all_users">All users</a>
									 </div>
								</div>
								
							
							
							</th>
							<th scope="col"><c:out value="Action" /></th>

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