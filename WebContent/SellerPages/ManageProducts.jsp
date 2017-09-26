<%@page import="onlineshoppingwebapp.models.Database"%>
<%@taglib tagdir='/WEB-INF/tags/template' prefix='t'%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:useBean id="productsModel"
	class="onlineshoppingwebapp.models.Products" scope="page">
</jsp:useBean>

<c:set var="sellerId" scope="request"
	value="${ loggedUserDBO.getFieldSQLStringValue(\"Id\") }" />

<t:generic_page>
	<div class="container">

		<div>
			<a style="float:right" href="AddNewProduct.jsp" class="btn btn-info" role="button">Add
				new product</a>
		</div>
		<div >
			<h3 >Currently Listed Products</h3>
		</div>
		<hr />
		<div class="item-list">
			<c:forEach var="sellerProduct"
				items="${productsModel.getProductsLists(Integer.parseInt(sellerId))}">
				<div class="container">
					<div class="row item">
						<div class="col-sm-4">
							<div>
								<img class="item-thumbnail"
									src="${pageContext.request.contextPath}/data/products/${sellerProduct.id}/${sellerProduct.productThumbNailLink}">
							</div>
						</div>
						<div class="col-sm-8">
							<div class="item-details">
								<div>
									<h4>
										<a
											href="${pageContext.request.contextPath}/ViewProductDetails?productId=${sellerProduct.id}">${sellerProduct.productName }</a>
									</h4>
								</div>
								<div class="row">
									<div class="col-sm-3">
										<h3 class="price">${sellerProduct.price }</h3>
									</div>
									<div class="col-sm-4">
										<p>Available Quantity: <b><i>${sellerProduct.availableQuantity }</i></b> </p>
										<p>Estimated Delivery days: <b><i>${sellerProduct.estimatedDeliveryDays }</i></b></p>
									</div>
								</div>
							</div>

							<div></div>
						</div>
					</div>
				</div>
				<hr />
			</c:forEach>
		</div>
	</div>
</t:generic_page>