<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir='/WEB-INF/tags/template' prefix='t' %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="login_reg_pages" scope="session" value="${true}"/>

<t:generic_page>
	<div  class="container">
		<h1>Login</h1>
		<br/>
		<form action="Login" method="post">
			<div class="form-group">
				<label for="username">Username:</label>
				<input type="text" class="" id="username" name="username" required>
			</div>
			<div class="form-group">
				<label for="password">Password:</label>
				<input type="password" class="" id="password" name="password" required>
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>		
	</div>
</t:generic_page>