<%@page import="onlineshoppingwebapp.models.Database"%>
<%@taglib tagdir='/WEB-INF/tags/template' prefix='t'%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:generic_page>
	<jsp:attribute name="optionalHead">
		
	</jsp:attribute>
	<jsp:body>
	<div class="container">
		<div>
			<div>
				<h2>Search Results</h2>
			</div>
			<hr />
			<div class="item-list">
				<c:forEach var="product" items="${resultProducts}">
					<div class="container">
						<div class="row item">
							<div class="col-sm-4">
								<div>
									<img class="item-thumbnail"
											src="${pageContext.request.contextPath}/data/products/${product.id}/${product.productThumbNailLink}">
								</div>
							</div>
							<div class="col-sm-8">
								<div class="item-details">
									<div>
										<h4>
											<a
													href="${pageContext.request.contextPath}/ViewProductDetails?productId=${product.id}">${product.productName }</a>
										</h4>
									</div>
									<div class="row">
										<div class="col-sm-3">
											<h3 class="price">${product.price }</h3>
											<p style="padding-top:5px;">Seller:<b><i>${usersModel.getUser(Integer.parseInt(product.getSellerId())).getFirstName() }</i></b></p>
										</div>
										<div class="col-sm-4">
											<p>Available Quantity: <b><i>${product.availableQuantity }</i></b> </p>
											<p>Estimated Delivery days: <b><i>${product.estimatedDeliveryDays }</i></b></p>
										</div>
									</div>
								</div>
								<div>
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