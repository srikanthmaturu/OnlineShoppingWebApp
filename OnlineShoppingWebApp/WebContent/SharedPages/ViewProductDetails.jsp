<%@page import="onlineshoppingwebapp.models.Database"%>
<%@taglib tagdir='/WEB-INF/tags/template' prefix='t'%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:generic_page>
	<jsp:attribute name="optionalHead">
		<script>
		function uploadQuestion() {
			var inputQuestion = $("#inputQuestion").val();
			var productId = ${productBean.id};
			var userId = ${loggedUserBean.id}
			$.post("${context}/ProductQA", {
				productQuestion:inputQuestion, productId:productId, userId:userId
			}).done(function(data){
				alert("Success!");
				$("#proudctQASection").load("${context}/ViewProductDetails?productId=" + productId + " #proudctQASection");
			});
		}
		
		function uploadReview(){
			var review = $("#review").val();
			var rating = $("#rating").val();
			var reviewDate = $("#reviewDate").val();
			var productId = ${productBean.id};
			var userId = ${loggedUserBean.id};
			//alert(review + " " + rating + " " + reviewDate + " " + productId + " " + userId);
			$.post("${context}/AddCustomerReview", {
				review:review, rating:rating, reviewDate:reviewDate, productId: productId, customerId: userId
			}).done(function(msg){
				alert("Your review posted!");
				$("#productReviewsSection").load("${context}/ViewProductDetails?productId=" + productId + " #productReviewsSection");
			});
		}
		
		function deleteProduct(productId){
			$.ajax({method: "delete", url:"${context}/ManageProducts?productId="+productId.toString(), data: {}}).done(function(msg){
				alert("Product deleted");
				window.location.href = "${context}/SellerPages/ManageProducts.jsp";
			});
		}
		
		function addToShoppingCart(){
			var quantity = $("#quantity").val();
			var productId = ${productBean.id};
			$.post("${context}/UpdateShoppingCart", {
				quantity:quantity, productId:productId
			}).done(function(msg){
				alert("Added to the Shopping Cart Successfully!");
			});
		}
		
		function updateAnswer(questionId){
			var answer = $("#answer-"+questionId.toString()).val();
			var productId = ${productBean.id};
			var userId = ${loggedUserBean.id}
			$.ajax({ url: "${context}/ProductQA?questionId="+questionId.toString()+"&answer="+answer,
				type: "PUT",
				data: {
					questionId:questionId, answer: answer
				}
			}).done(function(data){
				alert("Answer Updated!");
				$("#proudctQASection").load("${context}/ViewProductDetails?productId=" + productId + " #proudctQASection");
			});
		}
		</script>	
	</jsp:attribute>
	<jsp:body>
	<div class="container">
		<c:if
				test="${user_type == 'seller' && loggedUserBean.getId() == productBean.getSellerId()}">
			<div style="float: right">
				<a
						href="${context }/SellerPages/EditProductDetails.jsp?productId=${productBean.id}"
						class="btn btn-primary">Edit</a>
				<button onclick="deleteProduct(${productBean.id})"
						class="btn btn-danger">Delete</button>
			</div>
		</c:if>
		<div class="row">
			<div id="imagesCarousel" class="carousel slide col-sm-4"
					data-ride="carousel">
				<ol class="carousel-indicators">
				<c:forEach var="i" begin="0"
							end="${productPhotosLinks.size() + productVideosLinks.size() - 1}">
					<li data-target="#imagesCarousel" data-slide-to="${i}"></li>
				</c:forEach>
				</ol>
				<div class="carousel-inner" role="listbox">
				<c:forEach var="productPhotoLink" items="${productPhotosLinks }"
							varStatus="status">
					<div class="item ${status.first ? 'active' : ''}">
						<img src="${productPhotoLink}" alt="ProductImage"></img>
					</div>
				</c:forEach>
				<c:forEach var="productVideoLink" items="${productVideosLinks }">
					<div class="item">
						<video src="${productVideoLink}"></video>
					</div>
				</c:forEach>
				</div>
				<a class="left carousel-control" href="#imagesCarousel"
						role="button" data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left" aria-hidden="true">
				</span> <span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#imagesCarousel"
						role="button" data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>
			<div id="productInfo" class="product-info col-sm-6">
				<div>
					<h2> ${productBean.productName} </h2>
				</div>
				<hr />
				<div class="container">
					<div class="product-info-sections row">
						<div class="product-info-section col-xs-3">
							<div>
								Price: <h3 class="price">${productBean.price }</h3>
							</div>
							<div>
								<p>Only <span
											style="font-size: 15px; color: red; padding: 5px;">${productBean.availableQuantity }</span> left on stock!</p>
							</div>
						</div>
						<div class="product-info-section col-xs-3">
							<div>
								<p style="">Seller: ${sellerBean.firstName}</p>
							</div>
							<div>
								<p>Estimated Delivery Days: ${productBean.estimatedDeliveryDays}</p>
							</div>
						</div>
					</div>				
				</div>
				<c:if test="${user_type == 'customer' }">
					Quantity: <input id="quantity" type="number"></input> &nbsp;&nbsp;&nbsp;&nbsp;
					<button onclick="addToShoppingCart()" class="btn btn-info">Add to Cart</button>
				</c:if>
			</div>
			<div>
				
			</div>
		</div>
		<hr />
		<div class="container">
			<div id="productReviewsSection" class="row product-reviews-section">
				<div class="product-reviews">
					<h2>Customer Reviews</h2>
					<c:forEach var="customerReviewBean" items="${customerReviewBeans}">
						<div class="product-review">
							<div class="row">
								<div class="col-sm-1">
									<h4>Date:</h4>
								</div>
								<div class="col-sm-4">
									<h4>${customerReviewBean.reviewDate }</h4>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-1">
									<h4>Rating:</h4>
								</div>
								<div class="col-sm-4">
									<h4>
							<c:forEach var="i" begin="1" end="5" step="1">
								<c:if test="${ i le customerReviewBean.rating}">
								<span class="glyphicon glyphicon-star"></span>
								</c:if>
								<c:if test="${ i > customerReviewBean.rating }">
									<span class="glyphicon glyphicon-star-empty"></span>
								</c:if>								
							</c:forEach>
									</h4>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-1">
									<h4>Review:</h4>
								</div>
								<div class="col-sm-8">
									<h4>${customerReviewBean.review }</h4>
								</div>
							</div>
							 </div>
						<hr />
					</c:forEach>
				</div>
				<h3>Write a Review</h3>
				<br/>
				<div class="product-review-form">
					<form action="javascript:uploadReview()">
					<div class="form-group row">
						<label for="reviewDate" class="col-sm-1">ReviewDate:</label>
						<input type="date" class="col-sm-2" id="reviewDate" name="">
					</div>
					<div class="form-group row">
						<label for="rating" class="col-sm-1">Rating:</label>
						<input type="text" class="col-sm-1" id="rating" name="">
					</div>
					<div class="form-group row">
						<label for="review" class="col-sm-1">Review:</label>
						<textarea type="text" class="col-sm-8" id="review" name="" rows="2" ></textarea>
					</div>
					<input type="submit" class="btn btn-info" value="Submit"></input>
					</form>
				</div>
			</div>
		</div>
		<hr />
		<div class="container">
			<div id="proudctQASection" class="product-qas-section">
				<h2>Customer Questions and Answers</h2>
					<div class="product-qas">
					<c:forEach var="productQABean" items="${productQABeans}">
						<div class="product-qa">
							<div class="row">
								<div class="col-sm-1">
									<h5 style="padding-left:0px"><b>Question:</b></h5>
								</div>
								<div class="col-sm-6">
									<h4 class="">${productQABean.question}</h4>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-1">
									<h5 style="padding-left:0px"><b>Answer:</b></h5>
								</div>
								<div class="col-sm-8">
									<c:choose>
										<c:when test="${productQABean.answer != \"null\"}">
											<h5 class="">${productQABean.answer}</h5>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${loggedUserBean.getId().contains(productBean.getSellerId()) }">
														<textarea rows="2" col="5" id="answer-${productQABean.getId() }" class="col-sm-6" name="question-${productQABean.getId() }"></textarea>
														<div class="col-sm-2">
															<button onclick="updateAnswer(${productQABean.getId()})"class="btn btn-primary">Submit</button>
														</div>
												</c:when>
												<c:otherwise>
													<p class=""> Yet to Answer! </p>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
						<hr/>
					</c:forEach>
				</div>
			</div>
			<h4>Add New Question</h4>
			<div class="product-qa-form">
				<form action="javascript:uploadQuestion()">
					<div class="form-group row">
						<label for="inputQuestion" class="col-sm-1">Question:</label>
						<input type="text" class="col-sm-6" id="inputQuestion"
								name="inputQuestion">
					</div>
					<input type="submit" class="btn btn-info" value="Submit"></input>
				</form>
			</div>
		</div>
	</div>
	</jsp:body>
</t:generic_page>