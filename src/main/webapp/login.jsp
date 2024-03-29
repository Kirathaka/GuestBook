<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Log in with your account</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>

<body>

	<div class="container">

		<div class="row">
			<div class="col-md-8" style="background-color: lavender;">
				<div class="jumbotron">
					<h1>Guest Book</h1>
					<p>This is a logging system that allows visitors of the WebSite
						to leave a Feedback (either in text/image format) about the
						WebSite or its subject.</p>
				</div>
			</div>

			<div class="col-sm-4 login-dialogue"
				style="background-color: lavenderblush;">
				<form method="POST" action="${contextPath}/login"
					class="form-signin">
					<h2 class="form-heading">Log in</h2>

					<div class="form-group ${error != null ? 'has-error' : ''}">
						<span>${message}</span> <input name="username" type="text"
							class="form-control" placeholder="Username" autofocus="true" />
						<input name="password" type="password" class="form-control"
							placeholder="Password" /> <span>${error}</span> <input
							type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />

						<button class="btn btn-lg btn-primary btn-block" type="submit">Log
							In</button>
						<h4 class="text-center">
							<a href="${contextPath}/registration">Create an account</a>
						</h4>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
