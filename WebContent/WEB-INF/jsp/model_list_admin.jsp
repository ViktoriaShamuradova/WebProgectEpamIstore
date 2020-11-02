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
				<h2>All Models</h2>
				<table class="table">
					<thead>
						<tr>
							<c:if test="${empty models }">
								<tr>
									<td>No models here</td>
								</tr>
							</c:if>
							<th scope="col"><c:out value="Id" /></th>
							<th scope="col"><c:out value="Name" /></th>
							<th scope="col"><c:out value="Price" /></th>
							<th scope="col"><c:out value="Producer" /></th>
							<th scope="col"><c:out value="Category" /></th>
							<th scope="col"><c:out value="Count" /></th>
							<th scope="col"><c:out value="Action" /></th>

						</tr>
					</thead>
					<tbody>
						<%@ include file="model_list_admin_body.jsp"%>
						
					</tbody>
				</table>
					<pag:pagination currentCommand ="${command}" totalEntity="${modelCount}" perPage="${perPage}" currentPage="${pageNumber}" />
					<a class=" badge-dark" href="controller?command=form_editing_model&modelId=${model.id}"><button type="button" class="btn btn-secondary btn-lg btn-block mt-5" >Add new model</button></a>
			</div>
			
		</div>
	</div>
</body>