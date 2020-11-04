<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<c:forEach var="model" items="${models}">
						<tr>
							<td><c:out value="${model.id}"></c:out></td>
							<td><c:out value="${model.name}"></c:out></td>
							<td><c:out value="${model.price}"></c:out></td>
							<td><c:out value="${model.producer}"></c:out></td>
							<td><c:out value="${model.category}"></c:out></td>
							<td><c:out value="${model.count}"></c:out></td>
							<td><a class="btn btn-secondary btn-sm" href="controller?command=form_editing_model&modelId=${model.id}"> Edit</a></td>
											
						</tr>	
</c:forEach>	