<nav class="col-md-2 d-none d-md-block bg-light sidebar">
	<div class="sidebar-sticky">
		<ul class="nav flex-column">
			
			<li class="nav-item"><a class="nav-link"
				href="controller?command=all_users"> <span data-feather="file"></span>
					All users
			</a></li>
			
			<li class="nav-item"><a class="nav-link"
				href="controller?command=all_models_for_admin"> <span data-feather="file"></span>
					All models
			</a></li>
			
			<li class="nav-item"><a class="nav-link"
				href="controller?command=black_list"> <span data-feather="file"></span>
					Black list
			</a></li>
			
			<li class="nav-item"><a class="nav-link"
				href="controller?command=my_orders"> <span data-feather="file"></span>
					My Orders
			</a></li>

			<li class="nav-item"><a class="nav-link"
				href="controller?command=cart_page"> <span
					data-feather="shopping-cart"></span> My Cart 
					<c:out value="(${sessionScope.shopcart.totalCount})" />
			</a></li>
			
			
			
		</ul>
</nav>