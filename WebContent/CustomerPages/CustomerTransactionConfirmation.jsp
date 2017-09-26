<%@page import="onlineshoppingwebapp.models.Database"%>
<%@taglib tagdir='/WEB-INF/tags/template' prefix='t'%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:generic_page>
	<jsp:attribute name="optionalHead">
	</jsp:attribute>
	<jsp:body>
		<div class="container">
		<div>
			<c:if test="${!orderStatus}">
					<h4>There is a problem while order.</h4>
			</c:if>
			<c:if test="${orderStatus}">
				<div id="">
				<div>
					<h3>Order Details <i># ${orderId}</i>
					</h3>
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
				</div>
				
				<div>
				<h3>Order Items</h3>
				<hr/>
				<div class="order-items-list-heading row">
					<div class="col-sm-3">
					</div>
					<div class="col-sm-9"> 
						<div class="row">
							<div class="col-sm-4">
								<h4>Item Details</h4>
							</div>
							<div class="col-sm-4">
								<h4>Price * Quantity = Total</h4>
							</div>
							<div class="col-sm-4">
								<h4>Shipping Details</h4>
							</div>
						</div>
					</div>
				</div>			
				<hr />
					<c:forEach var="orderItem" items="${orderItems}">
					<c:set var="product"
							value="${productsModel.getProduct(Integer.parseInt(orderItem.getProductId()))}"></c:set>
					<div class="container">
						<div class="row item">
							<div class="col-sm-3">
								<div>
									<img class="item-thumbnail"
											src="${pageContext.request.contextPath}/data/products/${product.id}/${product.productThumbNailLink}">
								</div>
							</div>
							<div class="col-sm-9">
								<div class="item-details">
									<div>
										<h4>
											<a
													href="${pageContext.request.contextPath}/ViewProductDetails?productId=${product.id}">${product.productName }</a>
										</h4>
									</div>
									<div class="row">
										<div class="col-sm-4">
											<p style="padding-top: 5px;">Seller:<b><i>${usersModel.getUser(Integer.parseInt(product.getSellerId())).getFirstName() }</i></b>
												</p>
											<p>Estimated Delivery days: <b><i>${product.estimatedDeliveryDays }</i></b>
												</p>
										</div>
										<div class="col-sm-4">
											<h3>
													<i><span class="price">${product.price }</span> * <span
														class="">${orderItem.getQuantity() }</span></i>&nbsp; &nbsp; = 
												<span class="price">${Double.parseDouble(product.price) * Integer.parseInt(orderItem.getQuantity()) }</span>
											</h3>		
										</div>
										<div class="col-sm-4">
											<p>Order Status - 
												<c:if test="${!orderItem.getStatus()}">
														<i>Cancelled</i>
													</c:if>
												<c:if
														test="${orderItem.getStatus() && !orderItem.getShippingStatus() }"> <i>Not Shipped</i>
													<button onclick="cancelOrderItem(${orderItem.getId()})"
															class="btn btn-danger"
															style="margin-top: 10px; margin-left: 10px;">Cancel Item</button>
												</c:if>
												<c:if test="${orderItem.getShippingStatus() }"> <i>Shipped</i> 
													<p>Shipping Reference number: ${orderItem.getShippingRefNo()}</p>
												</c:if>
											</p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<hr />
				</c:forEach>
				</div>
			</div>
			</c:if>
		</div>
			
		</div>
	</jsp:body>
</t:generic_page>
