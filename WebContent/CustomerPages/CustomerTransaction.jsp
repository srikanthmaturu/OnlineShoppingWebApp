<%@page import="onlineshoppingwebapp.models.Database"%>
<%@taglib tagdir='/WEB-INF/tags/template' prefix='t'%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:generic_page>
	<jsp:attribute name="optionalHead">
	<script>
	function verifyCredit(){
		    shippingName = $("#shippingName").val(),
			shippingAddressLine1 = $("#shippingAddress1").val(),
			shippingAddressLine2 = $("#shippingAddress2").val(),
			shippingCity = $("#shippingCity").val(),
			shippingState = $("#shippingState").val(),
			shippingPostalCode = $("#shippingPostalCode").val(),
			shippingCountry = $("#shippingCountry").val(),
			shippingPhoneNumber = $("#shippingPhoneNumber").val(),
			billingName = $("#billingName").val(),
			billingAddressLine1 = $("#billingAddress1").val(),
			billingAddressLine2 = $("#billingAddress2").val(),
			billingCity = $("#billingCity").val(),
			billingState = $("#billingState").val(),
			billingPostalCode = $("#billingPostalCode").val(),
			billingCountry = $("#billingCountry").val(),
			billingPhoneNumber = $("#billingPhoneNumber").val(),
			cardHolderName = $("#cardHolderName").val(),
			creditCardNumber = $("#creditCardNumber").val(),
			cardType = $("#cardType").val(),
			cvv = $("#cvv").val(),
			expirationDate = $("#expirationDate").val(),
			userId = "${loggedUserBean.getId()}";
		
		var completeShippingAddress = shippingName + "\n" + shippingAddressLine1 + "\n" + shippingAddressLine2 + "\n" + shippingCity + "\n" + shippingState
									  + "-" + shippingPostalCode + "\n"  + billingCountry+ "Ph: " + shippingPhoneNumber,
			completeBillingAddress = billingName + "\n" + billingAddressLine1 + "\n" + billingAddressLine2 + "\n" + billingCity + "\n" + billingState
			  + "-" + billingPostalCode + "\n"  + shippingCountry + "\n"+ "Ph: " + billingPhoneNumber;
		
		alert("ShippingAddress: " + completeShippingAddress);
		alert("BillingAddress: " + completeBillingAddress);
			
		$.ajax({
			method: "POST",
			url: "${context}/BankServlet",
			data: {
				"cardHolderName":cardHolderName,
				"creditCardNumber":creditCardNumber,
				"cardType":cardType,
				"userId":userId,
				"cvv":cvv,
				"expirationDate":expirationDate,
				"transactionAmount": "${shoppingCart.getTotalCost() }",
				"transactionType":"deduct"
			}
		}).done(function(msg){
			alert("Transaction Status:" + msg);
			if(msg.includes("Success")){
				placeOrderJS();
			}
		});
		}
	
	function placeOrderJS() {
		var orderData = {
				"saAddressLine1": shippingAddressLine1,
				"saAddressLine2": shippingAddressLine2,
				"saCity": shippingCity,
				"saZipcode": shippingPostalCode,
				"saState": shippingState,
				"saCountry": shippingCountry,
				"saPhoneNumber": shippingPhoneNumber, 
				"baAddressLine1": billingAddressLine1,
				"baAddressLine2": billingAddressLine2,
				"baCity": billingCity,
				"baZipcode": billingPostalCode,
				"baState": billingState,
				"baCountry": billingCountry,
				"baPhoneNumber": billingPhoneNumber,
				"creditCardNumber": creditCardNumber,
				"customerId":userId
			};
		
		$.ajax({
			method: "POST",
			url: "${context}/PlaceOrder",
			data: {purchaseData : JSON.stringify(orderData)}
		}).done(function(orderStatus){
			var result = JSON.parse(orderStatus);
			if(result.status == "success"){
				alert("Order Placed:" + result.orderId);
				window.location = "${context}/CustomerTransactionConfirmation?orderId="+result.orderId+"&status="+result.status;
			}else{
				
			}
			
		});
		
	}
	</script>

	</jsp:attribute>
	<jsp:body>
		<div class="container">
		<div>
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
											<p style="padding-top: 5px;">Seller:<b><i>${shoppingCart.getSellerName(Integer.parseInt(product.getSellerId())) }</i></b>
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
						</div>
					</div>
					<hr />
			</c:forEach>
			<div class="row">
				<div class="col-sm-6">
					
				</div>
				<div class="col-sm-2">
					<h4>Total Cost: </h4>
				</div>
				<div class="col-sm-2">
					<h4><i><span class="price">${shoppingCart.getTotalCost() }</span></i></h4>
				</div>
			</div>
			</div>
		</div>
		<hr/>
		<div>
			<form style="" action="javascript:verifyCredit()">
			<div class="row">
				<div class="col-sm-6">
					<h3>Shipping Address:</h3>
			<div class="shipping-address-section">
				<div class="form-group row">
				<label for="shippingName" class="col-sm-2">Name: </label>
				<input type="text" class="col-sm-8" id="shippingName"
										name="shippingName" required>
			</div>
			<div class="form-group row">
				<label for="shippingAddress" class="col-sm-2">Address Line 1: </label>
				<input type="text" class="col-sm-8" id="shippingAddress1"
										name="shippingAddress" required>
			</div>
			<div class="form-group row">
				<label for="shippingAddress2" class="col-sm-2">Address Line 2: </label>
				<input type="text" class="col-sm-8" id="shippingAddress2"
										name="shippingAddress2" required>
			</div>
			<div class="form-group row">
				<label for="shippingCity" class="col-sm-2">City: </label>
				<input type="text" class="col-sm-8" id="shippingCity"
										name="shippingCity" required>
			</div>
			<div class="form-group row">
				<label for="shippingState" class="col-sm-2">State: </label>
				<input type="text" class="col-sm-8" id="shippingState"
										name="shippingState" required>
			</div>
			<div class="form-group row">
				<label for="shippingPostalCode" class="col-sm-2">Postal Code: </label>
				<input type="number" class="col-sm-8" id="shippingPostalCode"
										name="shippingPostalCode" required>
			</div>
			<div class="form-group row">
				<label for="shippingCountry" class="col-sm-2">Country: </label>
				<input type="text" class="col-sm-8" id="shippingCountry"
										name="shippingCountry" required>
			</div>
			<div class="form-group row">
				<label for="shippingPhoneNumber" class="col-sm-2">Phone Number: </label>
				<input type="tel" class="col-sm-8" id="shippingPhoneNumber"
										name="shippingPhoneNumber" required>
			</div>
			</div>	
				</div>
				<div class="col-sm-6">
					<h3>Billing Address:</h3>
			<div class="billing-address-section">
				<div class="form-group row">
				<label for="billingName" class="col-sm-2">Name: </label>
				<input type="text" class="col-sm-8" id="billingName"
										name="billingName" required>
			</div>
			<div class="form-group row">
				<label for="billingAddress1" class="col-sm-2">Address Line 1: </label>
				<input type="text" class="col-sm-8" id="billingAddress1"
										name="billingAddress1" required>
			</div>
			<div class="form-group row">
				<label for="billingAddress2" class="col-sm-2">Address Line 2: </label>
				<input type="text" class="col-sm-8" id="billingAddress2"
										name="billingAddress2" required>
			</div>
			<div class="form-group row">
				<label for="billingCity" class="col-sm-2">City: </label>
				<input type="text" class="col-sm-8" id="billingCity"
										name="billingCity" required>
			</div>
			<div class="form-group row">
				<label for="billingState" class="col-sm-2">State: </label>
				<input type="text" class="col-sm-8" id="billingState"
										name="billingState" required>
			</div>
			<div class="form-group row">
				<label for="billingPostalCode" class="col-sm-2">Postal Code: </label>
				<input type="number" class="col-sm-8" id="billingPostalCode"
										name="billingPostalCode" required>
			</div>
			<div class="form-group row">
				<label for="billingCountry" class="col-sm-2">Country: </label>
				<input type="text" class="col-sm-8" id="billingCountry"
										name="billingCountry" required>
			</div>
			<div class="form-group row">
				<label for="billingPhoneNumber" class="col-sm-2">Phone Number: </label>
				<input type="tel" class="col-sm-8" id="billingPhoneNumber"
										name="billingPhoneNumber" required>
			</div>
			</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-5">
					<h3>Credit Card Information:</h3>
			<div class="credit-card-section">
			<div class="form-group row">
				<label for="cardHolderName" class="col-sm-3">CardHolder Name: </label>
				<input type="text" class="col-sm-8" id="cardHolderName"
										name="cardHolderName" required>
			</div>
			<div class="form-group row">
				<label for="creditCardNumber" class="col-sm-3">Credit Card Number: </label>
				<input type="number" class="col-sm-8" id="creditCardNumber"
										name="creditCardNumber" required>
			</div>
			<div class="form-group row">
				<label for="cardType" class="col-sm-3">Cart Type: </label>
				<select class="col-sm-8" id="cardType" name="cardType" required>
					<option value="visa">Visa</option>
					<option value="amex">AMEX</option>
					<option value="discover">Discover</option>
				</select>
			</div>
			<div class="form-group row">
				<label for="cvv" class="col-sm-3">CVV: </label>
				<input type="number" class="col-sm-8" id="cvv" name="cvv" required>
			</div>
			<div class="form-group row">
				<label for="expirationDate" class="col-sm-3">Expiration Date: </label>
				<input type="date" class="col-sm-8" id="expirationDate"
										name="expirationDate" required>
			</div>
			</div>
				</div>
			</div>
			
			<div class="col-sm-2">
				<button type="submit" class="btn btn-primary">Place Order</button>
			</div>
			</form>
		</div>
		
		</div>
	</jsp:body>
</t:generic_page>
