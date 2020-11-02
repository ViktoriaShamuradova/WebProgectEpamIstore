<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<fmt:message bundle="${loc}" key="message.signIn" var="signIn" />	
<fmt:message bundle="${loc}" key="message.sighUp" var="signUp" />
<fmt:message bundle="${loc}" key="message.signOut" var="signOut" />

	<nav class="navbar sticky-top navbar-expand-md navbar-dark bg-dark">
		<div class="site-logo">
			<img src="front/img/log.png" class="img-fluid" width="50px" height="50px">
		</div>
		
		<div class="name">
		<a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#"><h3>Ishop</h3></a> 
		</div>
		
	
		<ul class="navbar-nav px-3">
		
			<c:choose>
				<c:when test="${empty sessionScope.user}">
					<li class="nav-item text-nowrap"><a class="nav-link" href="controller?command=registration_page">${signUp}</a></li>
					<li class="nav-item text-nowrap"><a class="nav-link" href="controller?command=enter_page">${signIn}</a></li>
				</c:when>
				<c:otherwise><li class="nav-item text-nowrap"><a class="nav-link" href="controller?command=sign_out">${signOut}</a></li>
				</c:otherwise>			
			</c:choose>
			<li class="nav-item text-nowrap" style="margin-left:1000px"><%@ include file="ruEn.jsp"%></li>
		
		</ul>
	</nav>

