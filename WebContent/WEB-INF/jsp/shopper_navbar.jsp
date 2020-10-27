<nav class="col-md-2 d-none d-md-block bg-light sidebar">
	<div class="sidebar-sticky">
		<ul class="nav flex-column">
			<li class="nav-item"><a class="nav-link active" href="#"> <span
					data-feather="home"></span> My profile <span class="sr-only">(current)</span>
			</a></li>
			<li class="nav-item"><a class="nav-link"
				href="controller?command=my_orders"> <span data-feather="file"></span>
					Orders
			</a></li>


			<li class="nav-item"><a class="nav-link"
				href="controller?command=cart_page"> <span
					data-feather="shopping-cart"></span> Cart 
					<c:out value="(${sessionScope.shopcart.totalCount})" />
			</a></li>
			<li class="nav-item"><a class="nav-link" href="#"> <span
					data-feather="bar-chart-2"></span> Reviews
			</a></li>
			<li class="nav-item"><a class="nav-link" href="#"> <span
					data-feather="layers"></span> Saved goods
			</a></li>
		</ul>
</nav>