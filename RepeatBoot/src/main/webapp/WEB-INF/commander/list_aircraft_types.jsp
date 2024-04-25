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
					<h1>Aircraft Types</h1>
				</div>
			</div>
		</div>


		<div class="container">

			<c:if test="${! empty aircraftTypeId}">
				<div class="alert alert-success" role="alert">
					<p>Selected Aircraft Type ID: ${aircraftTypeId}</p>
				</div>

			</c:if>

			<c:if test="${empty aircraftTypeId}">
				<div class="alert alert-success" role="alert">
					<p>Click an Image to Learn More About the Aircraft Type.</p>
				</div>
			</c:if>

			<div class="row row-cols-1 row-cols-md-2 row-cols-lg-4 g-2">

				<c:forEach var="aircraftType" items="${aircraftTypes}">
					<div class="col text-center">
						<div class="card">
							<a class="nav-link"
								href="experience_type_requirements.do?aircraftTypeId=${aircraftType.id}">
								<div class="card-body">
									<h5 class="card-title">ID:(${aircraftType.id})&nbsp;${aircraftType.aircraftType}</h5>
									<p class="card-text">
										<img class="aircraft-type img-responsive shadow hover-zoom"
											src="${aircraftType.imageUrl}" width="100" />
									</p>

									<!-- Show experience requirements for selected aircraft type. -->

									<c:if test="${aircraftType.id == aircraftTypeId}">

										<div class="alert alert-info" role="alert">

											<p>Experience Requirements</p>

											<c:forEach var="experienceTypeRequirement"
												items="${aircraftType.getExperienceTypeRequirements()}">

												<c:forEach var="experienceType"
													items="${experienceTypeRequirement.getExperienceTypes()}">
													<p>${experienceTypeRequirement.minutesRequired}&nbsp;Minutes
														of ${experienceType.description}</p>
												</c:forEach>


											</c:forEach>

										</div>


										<form action="edit_aircraft_type.do" method="post"
											onsubmit=" confirm('Not implemented yet, stretch goal!');return false;">
											<input type="hidden" name="id" value="${aircraftType.id}" />
											<button type="submit" class="btn btn-primary">Edit</button>
										</form>
										<form action="delete_aircraft_type.do" method="post"
											onsubmit=" confirm('Not implemented yet, stretch goal!');return false;">
											<input type="hidden" name="id" value="${aircraftType.id}" />
											<button type="submit" class="btn btn-danger">Delete</button>
										</form>

									</c:if>


								</div>
							</a>
						</div>
					</div>
				</c:forEach>


			</div>



		</div>
	</main>

	<jsp:include page="../_tail.jsp" />

</body>

</html>








