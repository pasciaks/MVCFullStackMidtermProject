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
					<h1>Edit Log Entry</h1>
					<c:if test="${! empty sessionScope.loggedInUser}">
						<p>${sessionScope.loggedInUser.username}</p>
					</c:if>
				</div>
			</div>
		</div>


		<div class="container">
			<form action="edit_experience.do" method="post">

				<input type="hidden" id="id" name="id"
					value="${sessionScope.loggedInUser.id}">
					
					<!-- NOTE: This was a flaw that needed fixed, both the userId and the pilot log entry id mismatch, etc. -->
					
				<input type="hidden" id="pilogLogEntryId" name="pilogLogEntryId"
					value="${pilotLogEntry.id}">

				<div class="mb-3">
					<label for="startTime" class="form-label">Start time:</label> 
					
					<input class="form-control" type="datetime-local" id="startTime" name="startTime" required
						value="${pilotLogEntry.getStartTime()}">
					
<%-- 					<input
						type="text" class="form-control" id="startTime" name="startTime" required
						value="${pilotLogEntry.getStartTime()}"> --%>
						
				</div>

				<div class="mb-3">
					<label for="stopTime" class="form-label">Stop time:</label>
					
					
					<input class="form-control" type="datetime-local" id="stopTime" name="stopTime" 
						value="${pilotLogEntry.getStopTime()}"> 
						
<%-- 					 <input
						type="text" class="form-control" id="stopTime" name="stopTime"
						value="${pilotLogEntry.getStopTime()}"> --%>
						
				</div>

				<div class="mb-3">
					<!-- // TODO - In future, pull from property of list of all Organizations in the system for now roles are static only 2 organizations.... -->
					<label for="experienceType" class="form-label">Select your
						experience type:</label> <select name="experienceType" id="experienceType"
						class="form-select" aria-label="Default select example" required>

						<option value="">Choose one</option>
						<c:forEach var="experienceType" items="${experienceTypes}"
							varStatus="loop">
							<option ${pilotLogEntry.getExperienceType().getId() == experienceType.getId() ? 'selected' : '' }
							 value="${experienceType.getId()}">${experienceType.getDescription()}</option>
						</c:forEach>
					</select>
				</div>
				<button type="submit" class="btn btn-primary">Update Log Entry</button>
			</form>
		</div>
	</main>

	<jsp:include page="../_tail.jsp" />

</body>

</html>








