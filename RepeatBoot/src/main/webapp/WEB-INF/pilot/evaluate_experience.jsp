<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
					<th>Start time</th>
					<th>Stop time</th>
					<th>Description</th>
					<th>User</th>
					<th>Edit</th>
				</tr>
			</table>

			<c:forEach var="experienceType" items="${experienceTypes}"
				varStatus="loop">

			
				<table class="table table-striped table-bordered">

				<c:forEach var="pilotLogEntry" items="${pilotLogEntries}"
					varStatus="loop">

					<c:if
						test="${pilotLogEntry.experienceType.id eq experienceType.id}">

						<tr>
							<td>(ID: ${pilotLogEntry.getId()}) ${pilotLogEntry.getStartTime()}</td>
							<td>${pilotLogEntry.getStopTime()}</td>
							<td>${pilotLogEntry.getExperienceType().getDescription()}</td>
							<td>${pilotLogEntry.getUser().getUsername()}</td>
							<td></td>
						</tr>

					</c:if>

				</c:forEach>
				
				</table>
			
				<c:set var="index" value="${pilotLogEntry.getExperienceType().getId()}" />

				<c:set var="index" value="${experienceType.id}" />
				
				<c:set var="specificValue" value="${sum[index]}" />
				
				<c:if test="${specificValue >= experienceType.experienceTypeRequirement.minutesRequired}">
	
					<div class="alert alert-success" role="alert">${experienceType.description} [ ${experienceType.experienceTypeRequirement.minutesRequired} total minutes required. Qualified! ]</div>
				
				</c:if>
				
				<c:if test="${specificValue < experienceType.experienceTypeRequirement.minutesRequired}">
	
					<div class="alert alert-danger" role="alert">${experienceType.description} [ ${experienceType.experienceTypeRequirement.minutesRequired} total minutes required. NOT Qualified. ]</div>
				
				</c:if>
				
				<p>The sum for experience type # ${index} is: ${specificValue} minutes</p>
				<p>The sum for experience type # ${index} is: <fmt:formatNumber value="${specificValue/60}" pattern="#,##0.00" /> hours</p>
				
		
			</c:forEach>

		</div>
	</main>

	<jsp:include page="../_tail.jsp" />

</body>

</html>








