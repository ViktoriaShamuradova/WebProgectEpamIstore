<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:message bundle="${loc}" key="button.delete" var="deleteLoc" />

<c:forEach var="order" items="${orders}">
						<tr>
							<td><a class="text-dark " href="controller?command=ORDER_DETAILES&idOrder=${order.id}"><c:out value="${order.id}"></c:out></a></td>
							<td><a class="text-dark " href="controller?command=user_detailes&userId=${order.idUser}"><c:out value="${order.idUser}"></c:out></a></td>
							<td><fmt:formatDate value="${order.created}" pattern="dd-MM-yyyy" /></td>
							<td><c:out value="${order.status}"></c:out></td>
							<td>
								<c:choose>
									<c:when test="${order.status.name != 'EXECUTED' and order.status.name != 'DELIVERED'}">
										<fmt:message bundle="${loc}" key="order.button.${order.status}.action" var="orderButton" />
										<a class="btn btn-secondary btn-sm" href="controller?command=change_status_order&orderId=${order.id}"><c:out value="${orderButton}" /></a>	
									</c:when >
									<c:when test="${order.status.name == 'DELIVERED'}">
										<c:out value="Ожидается подтверждение получения заказа от покупаеля" /></a>
									</c:when>
									<c:when test="${order.status.name == 'EXECUTED'}">
										<p class="text-success"><c:out value="Заказ получен" /></p>
									</c:when>
								</c:choose>
							</td>
											
						</tr>	
</c:forEach>