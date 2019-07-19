<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Create an account</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="container">
		<div class="page-header">
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<form id="logoutForm" method="POST" action="${contextPath}/logout">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>

				<h2 class="custom-header">
					Welcome ${pageContext.request.userPrincipal.name} | <a
						class="btn btn-info" role="button"
						onclick="document.forms['logoutForm'].submit()">Logout</a>
				</h2>
			</c:if>
		</div>
		<br /> <br /> <br />
		<spring:url value="/entry" var="saveURL" />
		<spring:url value="/entry/updateEntry/${entryForm.id}" var="updateURL" />


		<c:choose>
			<c:when test="${isAdminUser}">
				<c:set var="actionUrl" scope="page" value="${updateURL}" />
			</c:when>
			<c:otherwise>
				<c:set var="actionUrl" scope="page" value="${saveURL}" />
			</c:otherwise>
		</c:choose>

		<form:form method="POST" modelAttribute="entryForm"
			action="${actionUrl }" class="form">
			<span>${message}</span>
			<h2>This is Entry Form</h2>

			<div class="form-group">
				<form:input type="text" path="textMessage" class="form-control"
					placeholder="Your Text goes here"></form:input>
			</div>
			<div class="form-group">
				<form:input type="text" path="imageLocation" class="form-control"
					placeholder="Your Image Location goes here"></form:input>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
		</form:form>

	</div>
</body>
</html>
