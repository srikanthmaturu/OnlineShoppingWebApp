<%@page import="onlineshoppingwebapp.models.Database"%>
<%@taglib tagdir='/WEB-INF/tags/template' prefix='t'%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:generic_page>
	<jsp:attribute name="optionalHead">
	</jsp:attribute>
	<jsp:body>
		<div class="container">
			<h2>Add New Credit Card</h2>
			<hr/>
			<form method="post" style="padding-left: 50px" action="${context }/UpdateCreditCardServlet">
			<div class="form-group row">
				<label for="cardHolderName" class="col-sm-2">CardHolder Name: </label>
				<input type="text" class="col-sm-3" id="cardHolderName" name="cardHolderName"
						required>
			</div>
			<div class="form-group row">
				<label for="creditCardNumber" class="col-sm-2">Credit Card Number: </label>
				<input type="number" class="col-sm-3" id="creditCardNumber" name="creditCardNumber"
						required>
			</div>
			<div class="form-group row">
				<label for="balance" class="col-sm-2">Balance: </label>
				<input type="number" class="col-sm-3" id="balance" name="balance"
						required>
			</div>
			<div class="form-group row">
				<label for="cardType" class="col-sm-2">Cart Type: </label>
				<select class="col-sm-3" id="cardType" name="cardType" required>
					<option value="visa">Visa</option>
					<option value="amex">AMEX</option>
					<option value="discover">Discover</option>
				</select>
			</div>
			<div class="form-group row">
				<label for="cvv" class="col-sm-2">CVV: </label>
				<input type="number" class="col-sm-3" id="cv" name="cvv" required>
			</div>
			<div class="form-group row">
				<label for="expirationDate" class="col-sm-2">Expiration Date: </label>
				<input type="date" class="col-sm-3" id="expirationDate"
						name="expirationDate" required>
			</div>
			
			<div class="col-sm-2">
				<button type="submit" class="btn btn-primary">Create</button>
			</div>
		</form>		
		</div>
	</jsp:body>
</t:generic_page>