<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/GeneralPagination.tld" prefix="pag"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.locale" var="loc" />

<fmt:message bundle="${loc}" key="message.noUser" var="noUserLoc" />
<fmt:message bundle="${loc}" key="menu.blacklist" var="blacklistLoc" />
<fmt:message bundle="${loc}" key="attribute.userId" var="userIdLoc" />
<fmt:message bundle="${loc}" key="attribute.name" var="nameLoc" />
<fmt:message bundle="${loc}" key="attribute.surname" var="surnameLoc" />
<fmt:message bundle="${loc}" key="attribute.login" var="loginLoc" />
<fmt:message bundle="${loc}" key="attribute.email" var="emailLoc" />
<fmt:message bundle="${loc}" key="attribute.action" var="actionLoc" />
<fmt:message bundle="${loc}" key="button.delete" var="deleteLoc" />

</head>
<body>

	<%@ include file="header.jsp"%>

	<div class="container-fluid">
		<div class="row">
			<%@ include file="admin_navbar.jsp"%>


			<div class="container">
				<h2><c:out value="${blacklistLoc}" /></h2>
				<table class="table">
					<thead>
						<tr>
							<c:if test="${empty blackList }">
								<tr>
									<td><c:out value="${noUserLoc}" /></td>
								</tr>
							</c:if>
							<th scope="col"><c:out value="${userIdLoc}" /></th>
							<th scope="col"><c:out value="${nameLoc}" /></th>
							<th scope="col"><c:out value="${surnameLoc}" /></th>
							<th scope="col"><c:out value="${loginLoc}" /></th>
							<th scope="col"><c:out value="${emailLoc}" /></th>
							<th scope="col"><c:out value="${actionLoc}" /></th>

						</tr>
					</thead>
					<tbody>
						<%@ include file="black_list_body.jsp"%>
						
					</tbody>
				</table>
					<pag:pagination currentCommand ="${command}" totalEntity="${userCount}" perPage="${perPage}" currentPage="${pageNumber}" />
			</div>
		</div>
	</div>
</body>