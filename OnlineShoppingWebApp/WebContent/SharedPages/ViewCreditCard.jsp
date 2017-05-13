<%@page import="onlineshoppingwebapp.models.Database" %>
<%@taglib tagdir='/WEB-INF/tags/template' prefix='t' %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:generic_page>
	<jsp:attribute name="optionalHead">
		<script>
			function updateCreditCardBalance(){
				var credit = $("#credit").val();
				var creditCardNumber = $("#creditCardNumber").val();
				$.ajax("${context}/UpdateCreditCardServlet?credit="+credit+"&creditCardNumber="+creditCardNumber,{method:"PUT", data: { credit: credit, creditCardNumber: creditCardNumber}},function(msg){
					alert("Credit Card Updated!");
				});
			}			
		</script>
	</jsp:attribute>
	<jsp:body>
		<div class="container">
			<a class="btn btn-primary" style="float:right" href="${context}/SharedPages/AddCreditCard.jsp">Create New Credit Card</a>
			<h2>Update Balance</h2>
			<hr/>
			<div class="row">
				<label for="creditCardNumber" class="col-sm-2">Credit Card Number: </label>
				<input type="number" class="col-sm-3" id="creditCardNumber" name="creditCardNumber"
						required>
			</div>
			<br/>
			<div class="row">
				<label for="credit" class="col-sm-2">Credit: </label>
				<input type="number" class="col-sm-3" id="credit" name="credit"
						required>
			</div>
			<br/>
			<div class="col-sm-2">
				<button type="submit" onclick="javascript:updateCreditCardBalance()" class="btn btn-primary">Update</button>
			</div>
		</div>
	</jsp:body>
</t:generic_page>