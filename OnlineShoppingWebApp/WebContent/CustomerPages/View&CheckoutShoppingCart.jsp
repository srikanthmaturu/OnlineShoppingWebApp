<%@page import="onlineshoppingwebapp.models.Database"%>
<%@taglib tagdir='/WEB-INF/tags/template' prefix='t'%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:generic_page>
	<jsp:attribute name="optionalHead">
	<script>
		function updateQuantity(productId){
			var quantity = $("#"+productId.toString()+"-quantity").val();
			$.ajax({type: "PUT", url:"${context}/UpdateShoppingCart?productId="+productId.toString()+"&quantity="+quantity, data: {
				productId:productId, quantity:quantity
			}}).done(function(msg){
				alert("Update the Shopping Cart Successfully!");
				$("#shoppingCart").load("${context}/CustomerPages/View&CheckoutShoppingCart.jsp #shoppingCart");
			});
		}
		
	</script>
	</jsp:attribute>
	<jsp:body>
		<div class="container">
		<h2>Shopping Cart</h2>
		<br/>
			<div id="shoppingCart" class="shopping-cart">
			<c:forEach var="productId" items="${shoppingCart.getProductsList()}">
			<c:set var="product"
						value="${shoppingCart.getProductBean(productId) }"></c:set>
				<div class="container">
						<div class="row item">
							<div class="col-sm-3">
								<div>
									<img class="item-thumbnail"
										src="${pageContext.request.contextPath}/data/products/${product.id}/${product.productThumbNailLink}">
								</div>
							</div>
							<div class="col-sm-3">
								<div class="item-details">
									<div>
										<h4>
											<a
												href="${pageContext.request.contextPath}/ViewProductDetails?productId=${product.id}">${product.productName }</a>
										</h4>
									</div>
									<div class="row">
										<div class="col-sm-12">
											<p>Estimated Delivery days: <b><i>${product.estimatedDeliveryDays }</i></b>
											</p>
											<p style="padding-top: 5px;">Seller:<b><i>${shoppingCart.getSellerName(Integer.parseInt(product.getId())) }</i></b>
											</p>
										</div>
									</div>
								</div>
								<div>
								</div>
							</div>
							<div class="col-sm-2">
								<h3><i><span class="price">${product.price }</span> * <span class="">${shoppingCart.getQuantity(Integer.parseInt(product.id)) }</span></i>&nbsp; &nbsp; = </h3>		
							</div>
							<div class="col-sm-1">
								<h3><span class="price">${shoppingCart.getItemPrice(Integer.parseInt(product.id)) }</span></h3>
							</div>
							<div class="col-sm-3" style="padding-top: 10px;">
								<input type="number" id="${product.getId() }-quantity" name="quantity" value="${shoppingCart.getQuantity(Integer.parseInt(product.id)) }" ></input>
								<button class="btn btn-primary" onclick="updateQuantity(${product.getId()})">Update</button>
							</div>
						</div>
					</div>
					<hr />
			</c:forEach>
			<c:if test="${(shoppingCart==null) || shoppingCart.getProductsList().isEmpty()}" >
				<h4>Your shopping Cart is empty.</h4>
			</c:if>
			<c:if test="${(shoppingCart!=null) && !shoppingCart.getProductsList().isEmpty()}">
				<div class="row">
				<div class="col-sm-6">
					
				</div>
				<div class="col-sm-2">
					<h4>Total Cost: </h4>
				</div>
				<div class="col-sm-2">
					<h4><i><span class="price">${shoppingCart.getTotalCost() }</span></i></h4>
				</div>
				<div class="col-sm-2">
					<a href="${context }/CustomerTransaction" class="btn btn-primary">Checkout</a>
				</div>
				</div>
			</c:if>
			
			</div>
		</div>
	</jsp:body>
</t:generic_page>
