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

<script>
	$(document).ready(function() {
	
		var isTextEntry = "<c:out value="${entryForm.textMessage}"/>" ;
		var imageURL = "<c:out value="${entryForm.imageLocation}"/>" ;
		/* var isImageEntry = "<%-- <c:out value="${entryForm.imageLocation}"/> --%>" ; */
		
		  if(${newEntry}) {
			$('#imageInput').hide();
			$('#imagePreview').hide();
		   }else{
			   if(isTextEntry != ""){
				   console.log("text is not null so display text");
				   $("#OptradioInputText").prop("checked", true);
				   $('#imageInput').hide();
				   $("imagePreview").attr("src","");
				   $('#imagePreview').hide();
				   $('#textInput').show();
			   }else{
				   console.log("image is not null so display image");
				   $("#OptradioInputImage").prop("checked", true);
				   $('#imageInput').show();
				   $('#imagePreview').show();
				   $("#imagePreview").attr("src",imageURL);
				   $('#textInput').hide();
				   
			   }
		   }
		
			$('.OptradioInput').on(
					'change',
					function() {
						var optionSelected = $(
								'input[name=optradio]:checked',
								'#entryForm').val();
						if (optionSelected == 1) {
							$('#imageInput').hide();
							$('#imagePreview').hide();
							$('#textInput').show();
							$("#imageInput").val(null);
						} else if (optionSelected == 2) {
							$('#textInput').hide();
							$('#imageInput').show();
							$('#imagePreview').show();
							$("#textInput").val(null);
						}
			});

			});
</script>

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
			action="${actionUrl }" enctype="multipart/form-data" class="form" id="entryForm">
			<span>${message}</span>
			<h2>This is Entry Form</h2>

			<div class="form-group">
				<label class="radio-inline"><input type="radio"
					name="optradio" class="OptradioInput" id="OptradioInputText"
					value="1" checked />Text Message</label> <label class="radio-inline"><input
					type="radio" name="optradio" class="OptradioInput"
					id="OptradioInputImage" value="2" />Image</label>
			</div>

			<div class="form-group">
				<form:input type="text" path="textMessage" class="form-control"
					id="textInput" placeholder="Your Text goes here"></form:input>
			</div>
			<div class="form-group">
					
					<img id="imagePreview" alt="your image" width="100" height="100" />
					<!-- <input type="file" class="form-control"
					id="imageInput" path="imageLocation" onchange="document.getElementById('imagePreview').src = window.URL.createObjectURL(this.files[0])"></input> -->
					<input type="file" class="form-control"
					id="imageInput" name="pic" onchange="document.getElementById('imagePreview').src = window.URL.createObjectURL(this.files[0])"></input>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
		</form:form>

	</div>
</body>
</html>
