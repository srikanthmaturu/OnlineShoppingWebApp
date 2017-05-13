<%@page import="onlineshoppingwebapp.models.Database" %>
<%@taglib tagdir='/WEB-INF/tags/template' prefix='t' %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:generic_page>
	<jsp:attribute name="optionalHead">
	</jsp:attribute>
	<jsp:body>
		<div class="container">
		<h2>Your Orders</h2>
		<div class="orders-list">
			<c:choose>
				<c:when test="${customerOrders.isEmpty()}">
					<h3>Your orders list is empty.</h3>
				</c:when>
				<c:when test="${!customerOrders.isEmpty()}">
					<div class="orders-list-heading row">
					<div class="col-sm-2">
						<h3>Order Number</h3>
					</div>
					<div class="col-sm-2">
						<h3>Order Date</h3>
					</div>
					<div class="col-sm-3">
						<h3>Shipping Address</h3>
					</div>
					<div class="col-sm-3">
						<h3>Billing Address</h3>
					</div>
					<div class="col-sm-2">
						<h3>Total Cost</h3>
					</div>
					</div>
				</c:when>
			</c:choose>
			
			<c:forEach var="order" items="${ customerOrders}" >
			<hr/>
				<div class="orders-list-item row">
					<div class="col-sm-2">
						<a href="${context }/ViewOrderItems?orderId=${order.getId()}">${order.getId()}</a>
					</div>
					<div class="col-sm-2">
						<p>${order.getOrderDate()}</p>
					</div>
					<div class="col-sm-3">
						<p>${order.getShippingAddress()}</p>
					</div>
					<div class="col-sm-3">
						<p>${order.getBillingAddress()}</p>
					</div>
					<div class="col-sm-2">
						<p>${order.getTotalCost()}</p>
					</div>
				</div>
			</c:forEach>
		</div>
		</div>
	</jsp:body>
</t:generic_page>