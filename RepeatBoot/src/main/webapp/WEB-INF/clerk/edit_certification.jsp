<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!doctype html>

<html lang="en">

<jsp:include page="../_head.jsp" />

<body>

	<jsp:include page="../_nav.jsp" />

	<main>


		<c:if test="${! empty error}">
			<div class="alert alert-danger" role="alert">${error}</div>
		</c:if>


		<c:if test="${! empty message}">
			<div class="alert alert-success" role="alert">${message}</div>
		</c:if>


		<div class="container">

			<div class="row">
				<div class="col">
					<h1>Edit Certification</h1>
				</div>
			</div>
		</div>


		<div class="container">

			<form action="edit_certification.do" method="post">

				<c:set var="certTypeId" value="${certification.certification.id}" />

				<input type="hidden" id="pilotId" name="pilotId"
					value="${currentPilotId}"> 
					
				<input type="hidden" id="id"
					name="id" value="${certId}">


				<div class="mb-3">

					<label for="effectiveDate" class="form-label">effectiveDate</label>

					<input class="form-control" type="date" id="effectiveDate"
						name="effectiveDate" required
						value="${certification.effectiveDate}">

				</div>



				<div class="mb-3">

					<label for="expirationDate" class="form-label">expirationDate</label>

					<input class="form-control" type="date" id="expirationDate"
						name="expirationDate" required
						value="${certification.expirationDate}">

				</div>


				<div class="mb-3">

					<label for="expirationDate" class="form-label">details</label> <input
						class="form-control" type="text" id="details" name="details"
						required value="${certification.details}">

				</div>



				<div class="mb-3">


					${certification.passed} 
					
					<label for="passed" class="form-label">Passed
						or Failed</label> <select name="passed" id="passed" class="form-select"
						aria-label="passed" required>

						<option value="">Choose One</option>
						<option ${certification.passed eq false ? 'selected' : ''}
							value="0">Failed</option>
						<option ${certification.passed eq true ? 'selected' : ''}
							value="1">Passed</option>

					</select>


				</div>


				<div class="mb-3">


					<label for="certification" class="form-label">Select your
						certification type:</label> <select name="certificationId"
						id="certificationId" class="form-select"
						aria-label="Certification" required>

						<option value="">Choose one</option>

						<c:forEach var="certificationType" items="${certifications}"
							varStatus="loop">
							<option
								${certificationType.getId() == certTypeId ? "selected" : "" }
								value="${certificationType.getId()}">${certificationType.getDescription()}</option>
						</c:forEach>

					</select>

				</div>

				<button type="submit" class="btn btn-primary">Update
					Certification</button>

			</form>
		</div>
	</main>

	<jsp:include page="../_tail.jsp" />

</body>

</html>








