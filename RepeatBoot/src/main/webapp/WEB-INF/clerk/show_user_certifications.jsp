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
					<h1>User Certifications</h1>



					<c:if test="${! empty message}">
						<div class="alert alert-success">${message}</div>
					</c:if>


					<p>${certifications.size()}<span> certification(s)
							found.</span>
					</p>

					<form method="GET" action="add_certification.do">
						<input type="hidden" name="pilotId" value="${currentPilotId}" />
						<button type="submit" class="btn btn-success">Add
							Certification</button>
					</form>

					<br>

					<table class="table table-bordered">

						<tr>
							<th class="text-center">ID</th>
							<th class="text-center">Description</th>
							<th class="text-center">Details</th>
							<th class="text-center">Effective Date</th>
							<th class="text-center">Expiration Date</th>
							<th class="text-center">Edit</th>
							<th class="text-center">Delete</th>
						</tr>

						<c:forEach var="certification" items="${certifications}">
							<tr>
								<td class="text-center">${certification.id}</td>
								<td class="text-center">${certification.certification.description}</td>
								<td class="text-center">${certification.details}</td>
								<td class="text-center">${certification.effectiveDate ==
									null ? '_____' : certification.effectiveDate }</td>
								<td class="text-center">${certification.expirationDate == null ? '_____' : certification.expirationDate }</td>

								<td>
									<form method="GET" action="edit_certification.do">
										<input type="hidden" name="id" value="${certification.id}" />
										<input type="hidden" name="pilotId" value="${currentPilotId}" />
										<button type="submit" class="btn btn-success">Edit</button>
									</form>
								</td>

								<td>
									<form method="POST" action="delete_certification.do"
										onsubmit="return confirm('Are you sure?');">
										<input type="hidden" name="id" value="${certification.id}" />
										<input type="hidden" name="pilotId" value="${currentPilotId}" />
										<button type="submit" class="btn btn-danger">Delete</button>
									</form>
								</td>
							</tr>
						</c:forEach>
						
					</table>

				</div>
			</div>





		</div>

	</main>

	<jsp:include page="../_tail.jsp" />

</body>

</html>








