<%@tag description="Generic Page" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/template/" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${loggedin == null}">
	<c:set var="loggedin" scope="session" value="${false}" />
</c:if>

<c:if test="${login_reg_pages == null}">
	<c:set var="login_reg_pages" scope="session" value="${false}" />
</c:if>

<c:if test="${loggedin == false && login_reg_pages == false}">
	<c:redirect url="/LoginPage.jsp"></c:redirect>
</c:if>

<c:if test="${loggedin == true && login_reg_pages == true}">
	<% request.getSession().invalidate(); %>
	<c:redirect url="/LoginPage.jsp"></c:redirect>
</c:if>

<c:if test="${login_reg_pages == true && loggedin == false}">
	<c:set var="user_type" scope="session" value="${'guest'}" />
	<c:set var="login_reg_pages" scope="session" value="${false}"/>
</c:if>

<c:set var="context" value="${pageContext.request.contextPath}" scope="request"/>

<%@attribute name="optionalHead" fragment="true" %>

<t:main_layout>
	<jsp:attribute name="optionalHead">
		<jsp:invoke fragment="optionalHead"/>
	</jsp:attribute>
	<jsp:attribute name="header">
		<div class="container">
			<h1>Online Shopping Portal</h1>
		</div>
	</jsp:attribute>
	<jsp:attribute name="navigation">
		<c:choose>
			<c:when test="${user_type == 'customer'}">
				<t:customer_navigation/>
			</c:when>
			<c:when test="${user_type == 'seller' }">
				<t:seller_navigation/>
			</c:when>
			<c:when test="${user_type == 'guest' }">
				<t:guest_navigation/>
			</c:when>
		</c:choose>
	</jsp:attribute>
		<jsp:attribute name="footer">
		<div class="container">
			<div class="row">
				<div class="col-sm-12 text-center">
					<p> Web Developer - Srikanth Maturu </p>
					<p id="copyright"> Copyright 2017 </p>
				</div>
			</div>
		</div>
	</jsp:attribute>
	<jsp:body>
		<div id="main-content">
			<jsp:doBody/>
		</div>
	</jsp:body>
</t:main_layout>
