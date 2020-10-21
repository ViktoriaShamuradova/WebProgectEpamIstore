<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description"
	content="Пример на bootstrap 4: Базовая панель администратора с фиксированной боковой панелью и навигационной панелью. Версия v4.0.0">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href="dashboard.css" rel="stylesheet">

</head>
<body>


	<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
		<div class="site-logo">
			<img src="front/img/log.png" class="image">
		</div>
		
		<div class="name">
		<a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#"><h1>Ishop</h1></a> 
		</div>
		
		<input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">
		<ul class="navbar-nav px-3">
			<li class="nav-item text-nowrap"><a class="nav-link" href="controller?command=sign_out">Sign out</a></li>
		</ul>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<nav class="col-md-2 d-none d-md-block bg-light sidebar">
				<div class="sidebar-sticky">
					<ul class="nav flex-column">
						<li class="nav-item"><a class="nav-link active" href="#">
								<span data-feather="home"></span> My profile <span
								class="sr-only">(current)</span>
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#"> <span
								data-feather="file"></span> Orders
						</a></li>
						
						
						<li class="nav-item"><a class="nav-link" href="controller?command=cart_page"> <span
								data-feather="shopping-cart"></span> Cart <c:out value="(${sessionScope.shopcart.totalCount})" />
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#"> <span
								data-feather="bar-chart-2"></span> Reviews
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#"> <span
								data-feather="layers"></span> Saved goods
						</a></li>
					</ul>
	
				
			</nav>

			
		</div>
	</div>
	
	<%@ include file="model_list.jsp"%>

	
		

	
<!-- Icons -->
	<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
	<script>
		feather.replace()
	</script>
	
</body>
</html>