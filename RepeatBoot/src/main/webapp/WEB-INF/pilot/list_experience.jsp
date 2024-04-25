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
					<h1>Flight Logs</h1>
					<c:if test="${! empty sessionScope.loggedInUser}">
						<p>${sessionScope.loggedInUser.username}</p>
					</c:if>
				</div>
			</div>
		</div>


		<div class="container">
			<table class="table table-bordered w-100">
				<tr class="bg-primary">
					<th class="text-light">Start time</th>
					<th class="text-light">Stop time</th>
					<th class="text-light d-none d-md-block">Description</th>
					<!-- <th class="text-light">User</th> -->
					<th class="text-light">Edit</th>
					<th class="text-light">Delete</th>
				</tr>

				<c:forEach var="pilotLogEntry" items="${pilotLogEntries}"
					varStatus="loop">
					<tr>
						<td>(ID: ${pilotLogEntry.getId()}) ${pilotLogEntry.getStartTime()}</td>
						<td>${pilotLogEntry.getStopTime()}</td>
						<td class="d-none d-md-block">${pilotLogEntry.getExperienceType().getDescription()}</td>
						<%-- <td>${pilotLogEntry.getUser().getUsername()}</td> --%>
						<td>
			
						
							<form method="get" action="edit_experience.do">
								<input type="hidden" name="id" value="${pilotLogEntry.getId()}">
								<div class="mb-3">
									<button type="submit" class="btn btn-primary">Edit log
										entry</button>
								</div>
							</form>
							
										
							<span class="d-block d-md-none">
								<br>${pilotLogEntry.getExperienceType().getDescription()}
							</span>

						</td>
						<td>
							<form method="post" action="delete_experience.do" onsubmit="return confirm('Are you sure?');">
								<input type="hidden" name="id" value="${pilotLogEntry.getId()}">
								<div class="mb-3">
									<button type="submit" class="btn btn-danger">Delete
										log entry</button>
								</div>
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</main>

	<jsp:include page="../_tail.jsp" />

</body>

</html>
