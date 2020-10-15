<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

</head>
<body>

<c:out value="Hello ${sessionScope.user.login}!" />

	<%@ include file="model_list.jsp"%>
	 
	<a class="btn btn-primary"
		href="controller?command=load_more_models&category=${category}"
		role="button">Load more models</a>

</body>
</html>