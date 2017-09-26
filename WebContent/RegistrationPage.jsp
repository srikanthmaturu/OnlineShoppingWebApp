<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir='/WEB-INF/tags/template' prefix='t' %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="login_reg_pages" scope="session" value="${true}"/>

<t:generic_page>
	<div class="container">
		<h2>Register</h2>
		<br/>
		<form style="padding-left:50px" action="Registration">
			<div class="form-group row">
				<label for="firstName" class="col-sm-2">First Name: </label>
				<input type="text" class="col-sm-3" id = "firstName" name = "firstName" required>
			</div>
			<div class="form-group row">
				<label for="lastName" class="col-sm-2">Last Name: </label>
				<input type="text" class="col-sm-3" id = "lastName" name = "lastName" required>
			</div>
			<div class="form-group row">
				<label for="address" class="col-sm-2">Address: </label>
				<input type="text" class="col-sm-3" id = "address" name = "address" required>
			</div>
			<div class="form-group row">
				<label for="city" class="col-sm-2">City: </label>
				<input type="text" class="col-sm-3" id = "city" name = "city" required>
			</div>
			<div class="form-group row">
				<label for="state" class="col-sm-2">State: </label>
				<input type="text" class="col-sm-3" id = state name = "state" required>
			</div>
			<div class="form-group row">
				<label for="postalCode" class="col-sm-2">Postal Code: </label>
				<input type="number" class="col-sm-3" id = "postalCode" name = "postalCode" required>
			</div>
			<div class="form-group row">
				<label for="phoneNumber" class="col-sm-2">Phone Number: </label>
				<input type="tel" class="col-sm-3" id = "phoneNumber" name = "phoneNumber" required>
			</div>
			<div class="form-group row">
				<label for="birthday" class="col-sm-2">Birthday: </label>
				<input type="date" class="col-sm-3" id = "birthday" name = "birthday" required>
			</div>
			<div class="form-group row">
				<label for="emailAddress" class="col-sm-2">Email:</label>
				<input type="email" class="col-sm-3" id = "emailAddress" name = "emailAddress" required>
			</div>
			<div class="form-group row">
				<label for="username" class="col-sm-2">UserName: </label>
				<input type="text" class="col-sm-3" id="username" name = "username" required>
			</div>
			<div class="form-group row">
				<label for="password" class="col-sm-2">Password: </label>
				<input type="password" class="col-sm-3" id = "password" name = "password" required>
			</div>
			<div class="form-group row">
				<label for="user" class="col-sm-2">User Type: </label>
				<div class="col-sm-3">
					<div class="radio">
						<label> <input type="radio" name="type" value="0" required>Customer</label>
					</div>
					<div class="radio">
						<label> <input type="radio" name="type" value="1">Seller</label>
					</div>				
				</div>
			</div>
			<div class="col-sm-2">
				<button type="submit" class="btn btn-default">Register</button>
			</div>
		</form>		
		<br/><br/><br/>
	</div>
</t:generic_page>