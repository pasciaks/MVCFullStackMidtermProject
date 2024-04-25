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
					<c:if test="${! empty sessionScope.loggedInUser}">
						<p>${sessionScope.loggedInUser.username}</p>
					</c:if>
				</div>
			</div>
		</div>


		<div class="container">

			<c:forEach var="aircraftType" items="${aircraftTypes}">
				<div class="card">
					<a class="nav-link"
						href="evaluate_experience.do?aircraftTypeId=${aircraftType.id}">
						<div class="card-body">
							<h5 class="card-title">ID:(${aircraftType.id})&nbsp;${aircraftType.aircraftType}</h5>
							<p class="card-text">
								<img class="aircraft-type img-responsive shadow hover-zoom"
									src="${aircraftType.imageUrl}" width="100" />
							</p>

						</div>
					</a>
				</div>
			</c:forEach>




		</div>
	</main>

	<jsp:include page="../_tail.jsp" />

</body>

</html>








