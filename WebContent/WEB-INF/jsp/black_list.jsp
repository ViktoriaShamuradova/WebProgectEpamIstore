<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/GeneralPagination.tld" prefix="pag"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

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
							<c:if test="${empty blackList }">
								<tr>
									<td>No users here</td>
								</tr>
							</c:if>
							<th scope="col"><c:out value="User id" /></th>
							<th scope="col"><c:out value="Name" /></th>
							<th scope="col"><c:out value="Surname" /></th>
							<th scope="col"><c:out value="Login" /></th>
							<th scope="col"><c:out value="Email" /></th>
							<th scope="col"><c:out value="Action" /></th>

						</tr>
					</thead>
					<tbody>
						<%@ include file="black_list_body.jsp"%>
						
					</tbody>
				</table>
					<pag:pagination totalEntity="${userCount}" perPage="${perPage}" currentPage="${pageNumber}" />
			</div>
		</div>
	</div>
</body>