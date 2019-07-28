<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Create an account</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">

		<div class="page-header">
			<span style="float: left; margin-top: 10px;"> <c:if
					test="${pageContext.request.userPrincipal.name eq 'admin'}">
					<a class="btn btn-success" role="button"
						href="${pageContext.request.contextPath}/adminPanel">Home</a>
				</c:if> <c:if test="${pageContext.request.userPrincipal.name != 'admin'}">
					<a class="btn btn-success" role="button"
						href="${pageContext.request.contextPath}/entry">Home</a>
				</c:if>

			</span> <span style="float: right"> <c:if
					test="${pageContext.request.userPrincipal.name != null}">
					<form id="logoutForm" method="POST" action="${contextPath}/logout">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>

					<h2 class="custom-header">
						Welcome, ${pageContext.request.userPrincipal.name} | <a
							class="btn btn-danger" role="button"
							onclick="document.forms['logoutForm'].submit()">Logout</a>
					</h2>
				</c:if>
			</span>
		</div>
		<br /> <br /> <br />



		<h2>Entries List</h2>
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="row">User</th>
					<th scope="row" style="width: 50%">Feedback</th>
					<th scope="row"></th>
					<th scope="row"></th>
					<th scope="row"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${entryList }" var="entry">
					<tr>
						<td>${entry.user.username }</td>
						<td><c:if test="${not empty entry.textMessage}">
									${entry.textMessage}
								</c:if> <c:if test="${empty entry.textMessage}">
								<img id="imagePreview" alt="your image"
									src="${entry.imageLocation}" width="100" height="100" />
							</c:if></td>
						<td><c:choose>
								<c:when test="${entry.approved}">
									<spring:url value="/entry/approveEntry/${entry.id }"
										var="updateURL" />
									<a class="btn btn-primary disabled" href="${updateURL}"
										role="button">Approved</a>
								</c:when>
								<c:otherwise>
									<spring:url value="/entry/approveEntry/${entry.id }"
										var="updateURL" />
									<a class="btn btn-primary" href="${updateURL}" role="button">Approve</a>
								</c:otherwise>
							</c:choose></td>
						<td><spring:url value="/entry/updateEntry/${entry.id }"
								var="deleteURL" /> <a class="btn btn-primary"
							href="${deleteURL }" role="button">Update</a></td>
						<td><spring:url value="/entry/deleteEntry/${entry.id }"
								var="deleteURL" /> <a class="btn btn-primary"
							href="${deleteURL }" role="button">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>

</body>
</html>
