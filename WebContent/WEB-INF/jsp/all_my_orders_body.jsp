<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<c:forEach var="order" items="${orders}">
			<tr>
				<td><a class="text-dark " href="controller?command=order_detailes&idOrder=${order.id}"><c:out value="${orderLoc}  ${order.id}" /></a></td>
				<td><fmt:formatDate value="${order.created}" pattern="dd-MM-yyyy" /></td>	
				<td>
				
					<fmt:message bundle="${loc}" key="order.status.${order.status}" var="statusLoc" />
					<c:out value="${statusLoc}" />
					<c:if test="${order.status.name == 'DELIVERED'}">
							<fmt:message bundle="${loc}" key="order.button.${order.status}.action" var="orderButtonLoc" />
							<a class="btn btn-secondary btn-sm" href="controller?command=change_status_order&orderId=${order.id}"><c:out value="${orderButtonLoc}" /></a>	
					</c:if>				
					
					
				</td>					
			</tr>	
</c:forEach>	