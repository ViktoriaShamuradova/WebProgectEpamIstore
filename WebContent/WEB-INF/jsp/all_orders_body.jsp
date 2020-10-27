<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>




<c:forEach var="order" items="${orders}">
						<tr>
							<td><a href="controller?command=create_order&idOrder=${order.id}"><c:out value="Order ${order.id}" /></a></td>
							<td><fmt:formatDate value="${order.created}" pattern="dd-MM-yyyy" /></td>	
												
						</tr>	
</c:forEach>	