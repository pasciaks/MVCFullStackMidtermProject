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
					<h1>Add Log Entry</h1>
					<c:if test="${! empty sessionScope.loggedInUser}">
						<p>${sessionScope.loggedInUser.username}</p>
					</c:if>
				</div>
			</div>
		</div>


		<div class="container">
			<form action="add_log_entry.do" method="post">

				<input type="hidden" id="id" name="id"
					value="${sessionScope.loggedInUser.id}">

				<div class="mb-3">
					<label for="startTime" class="form-label">Start time:</label> <input
						type="text" class="form-control" id="startTime" name="startTime"
						value="">
				</div>

				<div class="mb-3">
					<label for="stopTime" class="form-label">Stop time:</label> <input
						type="text" class="form-control" id="stopTime" name="stopTime"
						value="">
				</div>

				<div class="mb-3">
					<!-- // TODO - In future, pull from property of list of all Organizations in the system for now roles are static only 2 organizations.... -->
					<label for="experienceType" class="form-label">Select your
						experience type:</label> <select name="experienceType" id="experienceType"
						class="form-select" aria-label="Default select example" required>

						<option value="">Choose one</option>
						<c:forEach var="experienceType" items="${experienceTypes}"
							varStatus="loop">
							<option value="${experienceType.getId()}">${experienceType.getDescription()}</option>
						</c:forEach>
					</select>
				</div>
				<button type="submit" class="btn btn-primary">Add Log Entry</button>
			</form>
		</div>
	</main>

	<jsp:include page="../_tail.jsp" />

</body>

</html>








