<%@tag description="Main Layout" pageEncoding="UTF-8" %>

<%@attribute name="header" fragment="true" %>
<%@attribute name="navigation" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="optionalHead" fragment="true" %>
<html lang="en">
	<head>
		<title>Online Shopping</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, intial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="${context}/css/custom.css">
		<link rel="shortcut icon" href="${context}/favicon.ico" type="image/ico"/>
		<jsp:invoke fragment="optionalHead"/>
	</head>
	<body>
		<div id="header">
			<jsp:invoke fragment="header"/>
		</div>
		<div id="navigation">
			<jsp:invoke fragment="navigation"/>
		</div>
		<div id="body">
			<jsp:doBody/>
		</div>
		<div id="footer">
			<jsp:invoke fragment="footer"/>
		</div>
	</body>
</html>
