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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script>
	$(document).ready(function() {
	
		var isTextEntry = "<c:out value="${entryForm.textMessage}"/>" ;
		var imageURL = "<c:out value="${entryForm.imageLocation}"/>" ;
		
		  if(${newEntry}) {
			$('.imageBlock').hide();
		   }else{
			   if(isTextEntry != ""){
				   $("#OptradioInputText").prop("checked", true);
				   $('.imageBlock').hide();
				   $("#imagePreview").attr("src","");
				   $('.textBlock').show();
			   }else{
				   $("#OptradioInputImage").prop("checked", true);
				   $('.imageBlock').show();
				   $("#imagePreview").attr("src",imageURL);
				   $('.textBlock').hide();
				   
			   }
		   }
		
			$('.OptradioInput').on(
					'change',
					function() {
						var optionSelected = $(
								'input[name=optradio]:checked',
								'#entryForm').val();
						if (optionSelected == 1) {
							$('.imageBlock').hide();
							$('.textBlock').show();
							$("#imageInput").val(null);
						} else if (optionSelected == 2) {
							$('.textBlock').hide();
							$('.imageBlock').show();
							$("#textInput").val(null);
						}
			});

			$('.info').on(
					'change',
					function() {
						if($('.info').val()){
							$('.info').show();
						}else{
							$('.info').hide();
						}
					});
			
			});
</script>

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
			action="${actionUrl }" enctype="multipart/form-data" class="form"
			id="entryForm">
			<div>
				<span class="error info alert-info" delimiter="&lt;p/&gt;"
					style="bottom: 10px;">${message}</span>
			</div>

			<form:errors cssClass="error alert alert-warning"
				delimiter="&lt;p/&gt;" style="bottom: 10px;" />

			<h2>Please select the type of feedback:</h2>

			<div class="form-group">
				<label class="radio-inline"><input type="radio"
					name="optradio" class="OptradioInput" id="OptradioInputText"
					value="1" checked />Text Feedback</label> <label class="radio-inline"><input
					type="radio" name="optradio" class="OptradioInput"
					id="OptradioInputImage" value="2" />Image Feedback</label>
			</div>

			<div class="form-group textBlock ${error ? 'has-error' : ''}">
				<form:textarea path="textMessage" class="form-control"
					id="textInput" placeholder="Your Text goes here"></form:textarea>
				<small class="text-muted">Character length should be between
					2 to 144.</small>
				<form:errors path="textMessage"></form:errors>
			</div>
			<div class="form-group imageBlock ${error ? 'has-error' : ''}">

				<img id="imagePreview"
					alt="Browse your image to see the preview here" width="100"
					height="100" />
				<!-- <input type="file" class="form-control"
					id="imageInput" path="imageLocation" onchange="document.getElementById('imagePreview').src = window.URL.createObjectURL(this.files[0])"></input> -->
				<input type="file" class="form-control" id="imageInput" name="pic"
					onchange="document.getElementById('imagePreview').src = window.URL.createObjectURL(this.files[0])"></input>
				<small class="text-muted">Allowed formats: jpg/jpeg/png,
					Allowed Dimensions: 300X300, Allowed Max size: 300MB</small>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
		</form:form>


	</div>
</body>
</html>
