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
			<table class="table table-bordered">
			<tr> 
			<th> Start time </th>
			<th> Stop time </th>
			<th>Description</th>
			<th>User</th>
			<th>Edit</th>
			</tr>
			
				<c:forEach var="pilotLogEntry" items="${pilotLogEntries}"
					varStatus="loop">
					<tr>
						<td>${pilotLogEntry.getStartTime()}</td>
						<td>${pilotLogEntry.getStopTime()}</td>
						<td>${pilotLogEntry.getExperienceType().getDescription()}${pilotLogEntry.getExperienceType().getId()}</td>
						<td>${pilotLogEntry.getUser().getUsername()}</td>
						<td>

						
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</main>

	<jsp:include page="../_tail.jsp" />

</body>

</html>








