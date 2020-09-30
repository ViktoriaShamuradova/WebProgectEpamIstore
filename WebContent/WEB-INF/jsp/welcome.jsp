<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
</head>
<body>

	Hello World
	<br />
	<c:out value="Hello ${sessionScope.user.login}! SS" />
	<jsp:useBean id="user" class="by.epamtc.shamuradova.ishop.bean.User"></jsp:useBean>
	<jsp:setProperty property="*" name="user"></jsp:setProperty>
	<jsp:getProperty property="name" name="user"></jsp:getProperty>
	<jsp:getProperty property="surname" name="user"></jsp:getProperty>
	<jsp:getProperty property="login" name="user"></jsp:getProperty>
	
</body>
</html>