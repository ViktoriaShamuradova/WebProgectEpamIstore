<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<c:out value="Hello ${sessionScope.user.login}!" />
<c:out value="${sessionScope.user.email}" />

<div class="container">
		<table class="table table-bordered">
			<thead class="thead-dark">
				<tr>
					<th>
						<c:out value="Name" />
					</th>
					<th>
						<c:out value="Price" />
					</th>
					<th>
						<c:out value="Description" />
					</th>
					<th>
						<c:out value="Category" />
					</th>
					<th>
						<c:out value="Producer" />
					</th>
					<th>
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="model" items="${requestScope.models}">
					<tr>
						<td>
							<c:out value="${model.name}" />
						</td>
						<td>
							<c:out value="${model.price}" />
						</td>
						<td>
							<c:out value="${model.description}" />
						</td>
						<td>
							<c:out value="${model.category}" />
						</td>
						<td>
							<c:out value="${model.producer}" />
						</td>
						<td>
							<button class="btn btn-danger btn-sm">Удалить</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<table class="table table-hover">
									<thead>
										<tr class="success">
											<td><strong> <c:out
														value="${appointmentTypeMessage}"></c:out>
											</strong></td>
											<td><strong> <c:out
														value="${appointmentNameMessage}"></c:out>
											</strong></td>
											<td><strong> <c:out
														value="${appointmentTimeMessage}"></c:out>
											</strong></td>
											<td><strong> <c:out
														value="${executionPeriodMessage}"></c:out>
											</strong></td>
											<td></td>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="appointment"
											items="${requestScope.appointments}">
											<tr>
												<td><c:out value="${appointment.type}"></c:out></td>
												<td><c:out value="${appointment.name}"></c:out></td>
												<td><c:out value="${appointment.appointmentDate}">
													</c:out></td>
												<td><c:out value="${appointment.appointmentTerm}">
													</c:out></td>
												<td></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
	</div>

</body>
</html>