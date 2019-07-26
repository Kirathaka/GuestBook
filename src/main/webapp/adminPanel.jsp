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
<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">

		<div class="page-header float-right" style="float:right">
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<form id="logoutForm" method="POST" action="${contextPath}/logout">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>

				<h2 class="custom-header">
					Welcome, ${pageContext.request.userPrincipal.name} | <a
						class="btn btn-info" role="button"
						onclick="document.forms['logoutForm'].submit()">Logout</a>
				</h2>
			</c:if>
		</div>
		<br /> <br /> <br />



		<h2>Entries List</h2>
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="row">User</th>
					<th scope="row">Text Message</th>
					<th scope="row">Image</th>
					<th scope="row">Approve</th>
					<th scope="row">Update</th>
					<th scope="row">Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${entryList }" var="entry">
					<tr>
						<td>${entry.user.username }</td>
						<td>${entry.textMessage}</td>
						<td><img id="imagePreview" alt="your image" src="${entry.imageLocation}" width="100" height="100" /></td>

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
