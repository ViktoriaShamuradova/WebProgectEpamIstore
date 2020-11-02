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

<%@ include file="header.jsp"%>

<div class="container-fluid">
		<div class="row">
			<%@ include file="admin_navbar.jsp"%>
			
		</div>
		
	</div>
</body>
</html>