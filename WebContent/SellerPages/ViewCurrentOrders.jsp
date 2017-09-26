<%@page import="onlineshoppingwebapp.models.Database" %>
<%@taglib tagdir='/WEB-INF/tags/template' prefix='t' %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:generic_page>
	<jsp:attribute name="optionalHead">
	<script>
	function updateShippingRef(orderItemId){
		var shippingRefNo = $("#order-item-"+orderItemId.toString()).val();
		$.ajax({type: "PUT", url:"${context}/UpdateOrderItems?orderItemId="+orderItemId.toString()+"&shippingRefNo="+shippingRefNo, data: {
			orderItemId:orderItemId, shippingRefNo:shippingRefNo
		}}).done(function(msg){
			alert("Updated the Shipping Reference Number");
			$("#currentOrderItems").load("${context}/ViewOrderItems #currentOrderItems");
		});
	}
	
	function cancelOrderItem(orderItemId){
		$.ajax({type: "Delete", url:"${context}/UpdateOrderItems?orderItemId="+orderItemId.toString(), data: {
			orderItemId:orderItemId
		}}).done(function(msg){
			alert("Cancelled Order Item.");
			$("#currentOrderItems").load("${context}/ViewOrderItems #currentOrderItems");
		});
	}
	</script>
	</jsp:attribute>
	<jsp:body>
		<div class="container">
			<div>
				<div>
					<h3>Your current Orders</h3>
				</div>
				
				<div id="currentOrderItems">
					<c:choose>
						<c:when test="${orderItems.isEmpty()}">
							<h3>Your order-items list is empty.</h3>
						</c:when>
						<c:when test="${!orderItems.isEmpty()}">
							<div class="order-items-list-heading row">
							<div class="col-sm-3">
							</div>
							<div class="col-sm-9"> 
								<div class="row">
									<div class="col-sm-4">
										<h4>Order Details</h4>
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
							<hr/>
						</c:when>
					</c:choose>
					
					<c:forEach var="orderItem" items="${orderItems}">
					<c:set var="product" value="${productsModel.getProduct(Integer.parseInt(orderItem.getProductId()))}"></c:set>
						<c:set var="order" value="${ordersModel.getOrderById(Integer.parseInt(orderItem.getOrderId()))}"></c:set>
					<div class="container">
						<div class="row item">
							<div class="col-sm-2">
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
											<h4>Customer: ${usersModel.getUser(Integer.parseInt(order.getCustomerId())).getFirstName() }</h4>
											<h4>Order Date: ${order.getOrderDate() }</h4>
										</div>
										<div class="col-sm-4">
											<h4><i><span class="price">${product.price }</span> * <span class="">${orderItem.getQuantity() }</span></i>&nbsp; = 
												<span class="price">${Double.parseDouble(product.price) * Integer.parseInt(orderItem.getQuantity()) }</span>
											</h4>		
										</div>
										<div class="col-sm-4">
											<p>Order Status - 
												<c:if test="${!orderItem.getStatus()}"><i>Cancelled</i></c:if>
												<c:if test="${orderItem.getStatus() && !orderItem.getShippingStatus() }"> <i>Not Shipped</i>
													<!--<label>Shipping Ref </label>  -->
													<input id="order-item-${orderItem.getId()}" type="text" name="${orderItem.getId()}-" value=""></input>
													<button class="btn btn-primary" onclick="updateShippingRef(${orderItem.getId()})">Update Shipping Ref</button>
													<button onclick="cancelOrderItem(${orderItem.getId()})" class="btn btn-danger" style="margin-top:10px; margin-left:10px;">Cancel Item</button>
												</c:if>
												<c:if test="${orderItem.getShippingStatus() }"> <i>Shipped</i> 
													<p>Shipping Reference number: ${orderItem.getShippingRefNo()}</p>
												</c:if>
											</p>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-4">
											<h4> Billing Address: </h4> ${order.getBillingAddress() }
										</div>
										<div class="col-sm-4">
											<h4> Shipping Address: </h4> ${order.getShippingAddress() }
										</div>
										<div class="">
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
		</div>
	</jsp:body>
</t:generic_page>
