<%@tag description="Customer Navigation" pageEncoding="UTF-8" %>

<nav class="navbar navbar-default">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Online Shopping</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li class="active"><a href="${context}/CustomerPages/CustomerHomePage.jsp"><span class="glyphicon glyphicon-home"></span> Home</a></li>
				<li><a href="${context}/ViewOrders">View Orders</a></li>
				<!-- li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">My Orders
					<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">Orders</a></li>
						<li><a href="#">My Orders</a></li>
					</ul>
				</li-->
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${context}/CustomerPages/View&CheckoutShoppingCart.jsp"><span class="glyphicon glyphicon-shopping-cart"></span>   My Cart</a></li>
				<li><a href="#"><span class="glyphicon glyphicon-user"></span>   Logged In</a></li>
				<li><a href="${context}/Logout"><span class="glyphicon glyphicon-log-out"></span>   Logout</a></li>
			</ul>
		</div>
	</div>
</nav>
