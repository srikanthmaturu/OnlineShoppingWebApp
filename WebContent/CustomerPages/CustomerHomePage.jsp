<%@page import="onlineshoppingwebapp.models.Database"%>
<%@taglib tagdir='/WEB-INF/tags/template' prefix='t'%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:generic_page>
	<jsp:attribute name="optionalHead">
		
	</jsp:attribute>
	<jsp:body>
	<div class="container">
		<div>
			<h2>Search Products</h2>
		</div>
		<br/>
		<br/>
		<div>
			<form action="${context }/SearchProducts">
				<div class="form-group row">
					<label for="searchbox" class="col-sm-2">Enter search query:</label>
					<input type="text" class="col-sm-8" id="searchbox"
							name="searchquery">
				</div>
				<input type="submit" class="btn btn-info" value="Search"></input>
			</form>
		</div>
	</div>
	</jsp:body>
</t:generic_page>