<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/GeneralPagination.tld" prefix="pag"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />

<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.locale" var="loc" />


</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="container-fluid">
		<div class="row">
			<%@ include file="admin_navbar.jsp"%>


			<div class="container">
				<h2><c:out value="Все заказы" /></h2>
				<table class="table">
					<thead>
						<tr>
							<c:if test="${empty orders }">
								<tr>
									<td><c:out value="Нет заказов" /></td>
								</tr>
							</c:if>
							<th scope="col"><c:out value="Номер заказа" /></th>
							<th scope="col"><c:out value="Номер пользователя" /></th>
							<th scope="col"><c:out value="Дата" /></th>
							<th scope="col"><c:out value="Статус" /></th>
							<th scope="col"><c:out value="Действие" /></th>							

						</tr>
					</thead>
					<tbody>
						<%@ include file="order_list_admin_body.jsp"%>
						
					</tbody>
				</table>
					<pag:pagination currentCommand ="${command}" totalEntity="${ordersCount}" perPage="${ordersPerPage}" currentPage="${pageNumber}" />
					
			</div>			
		</div>
	</div>
</body>