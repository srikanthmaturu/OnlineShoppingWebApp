<%@page import="onlineshoppingwebapp.models.Database" %>
<%@taglib tagdir='/WEB-INF/tags/template' prefix='t' %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<jsp:useBean id="productsModel" class="onlineshoppingwebapp.models.Products" scope="page" >
</jsp:useBean>


<t:generic_page>
	<div class="container">
		<h2>Add a new product</h2>
		<br/>
		<br/>
		<form action="${context}/AddNewProduct"  method="post" enctype="multipart/form-data">
			<div class="form-group row">
				<label for="productName" class="col-sm-2">Product Name </label>
				<input type="text" class="col-sm-5" id="productName" name="productName" required>
			</div>
			<div class="form-group row">
				<label for="productCategoryIndex" class="col-sm-2">Product Category </label>
				<select class="" id="productCategoryIndex" name="productCategoryIndex" required>
				<c:forEach var="productCategory" items = "${productsModel.getProductCategories() }">
					<option value="${productCategory.id }"> ${productCategory.productCategory}</option>
				</c:forEach>
				</select>
			</div>
			<div class="form-group row">
				<label for="price" class="col-sm-2">Price </label>
				<input type="number" class="" id="price" name="price" required>
			</div>
			<div class="form-group row">
				<label for="productThumbNail" class="col-sm-2">Product Thumbnail</label>
				<input type="file" class="" id="productThumbnail" name="productThumbnail" required>
			</div>
			<div class="form-group row">
				<label for="productImages" class="col-sm-2">Product Images</label>
				<input type="file" class="" id="productImages" name="productImages" multiple>
			</div>
			<div class="form-group row">
				<label for="productVideos" class="col-sm-2">Product Videos</label>
				<input type="file" class="" id="productVideos" name="productVideos" required multiple>
			</div>
			<div class="form-group row">
				<label for="productDescription" class="col-sm-2">Product Description</label>
				<textarea rows="4" cols="50" class="" id="productDescription" name="productDescription" required></textarea>
			</div>
			<div class="form-group row">
				<label for="availableQuantity" class="col-sm-2">Available Quantity</label>
				<input type="number" class="" id="availableQuantity" name="availableQuantity" required/>
			</div>
			<div class="form-group row">
				<label for="estimatedDeliveryDays" class="col-sm-2">Estimated Delivery Days</label>
				<input type="number" class="" id="estimatedDeliveryDays" name="estimatedDeliveryDays" required>
			</div>
			<button type="submit" class="btn btn-success col-sm-1">Add</button>
		</form>	
		
	</div>
</t:generic_page>