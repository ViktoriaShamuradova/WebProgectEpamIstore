<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
	<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
		<div class="site-logo">
			<img src="front/img/log.png" class="image">
		</div>
		
		<div class="name">
		<a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#"><h1>Ishop</h1></a> 
		</div>
		
		<!--<input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">  -->
		<ul class="navbar-nav px-3">
		
			<c:choose>
				<c:when test="${empty sessionScope.user}">
					<li class="nav-item text-nowrap"><a class="nav-link" href="controller?command=registration_page">${signUp}</a></li>
					<li class="nav-item text-nowrap"><a class="btn btn-primary" href="controller?command=enter_page" role="button"><i class="fas fa-sign-in-alt"></i> Войти</a></li>
				</c:when>
				<c:otherwise><li class="nav-item text-nowrap"><a class="nav-link" href="controller?command=sign_out">Sign out</a></li>
				</c:otherwise>			
			</c:choose>
			<li class="nav-item text-nowrap"><%@ include file="ruEn.jsp"%></li>
		
		</ul>
	</nav>

