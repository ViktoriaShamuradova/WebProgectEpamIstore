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

<fmt:message bundle="${loc}" key="message.allModels" var="allModelsLoc" />
<fmt:message bundle="${loc}" key="message.noModel" var="noModelLoc" />
<fmt:message bundle="${loc}" key="attribute.id" var="idLoc" />
<fmt:message bundle="${loc}" key="attribute.name" var="nameLoc" />
<fmt:message bundle="${loc}" key="attribute.price" var="priceLoc" />
<fmt:message bundle="${loc}" key="attribute.producer" var="producerLoc" />
<fmt:message bundle="${loc}" key="attribute.category" var="categoryLoc" />
<fmt:message bundle="${loc}" key="attribute.count" var="countLoc" />
<fmt:message bundle="${loc}" key="attribute.action" var="actionLoc" />
<fmt:message bundle="${loc}" key="button.addModel" var="addModelLoc" />

</head>
<body>

<%@ include file="header.jsp"%>

	<div class="container-fluid">
		<div class="row">
			<%@ include file="admin_navbar.jsp"%>


			<div class="container">
				<h2><c:out value="${allModelsLoc}" /></h2>
				<table class="table">
					<thead>
						<tr>
							<c:if test="${empty models }">
								<tr>
									<td><c:out value="${noModelLoc}" /></td>
								</tr>
							</c:if>
							<th scope="col"><c:out value="${idLoc}" /></th>
							<th scope="col"><c:out value="${nameLoc}" /></th>
							<th scope="col"><c:out value="${priceLoc}" /></th>
							<th scope="col"><c:out value="${producerLoc}" /></th>
							<th scope="col"><c:out value="${categoryLoc}" /></th>
							<th scope="col"><c:out value="${countLoc}" /></th>
							<th scope="col"><c:out value="${actionLoc}" /></th>

						</tr>
					</thead>
					<tbody>
						<%@ include file="model_list_admin_body.jsp"%>
						
					</tbody>
				</table>
					<pag:pagination currentCommand ="${command}" totalEntity="${modelsCount}" perPage="${modelsPerPage}" currentPage="${pageNumber}" />
					<a class=" badge-dark" href="controller?command=form_editing_model&modelId=${model.id}"><button type="button" class="btn btn-secondary btn-lg btn-block mt-5" ><c:out value="${addModelLoc}" /></button></a>
			</div>
			
		</div>
	</div>
</body>