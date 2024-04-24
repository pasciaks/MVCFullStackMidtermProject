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

		<div class="container about">

			<div class="row">
				<div class="col">
					<div class="col text-center">
						<h1>Register</h1>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col">

					<form id="register" action="register.do" method="post">

						<input type="hidden" id="id" name="id"
							value="${sessionScope.loggedInUser.id}">

						<div class="mb-3">
							<label for="username" class="form-label">User name:</label> <input
								type="text" class="form-control" id="username" name="username"
								required value="${sessionScope.loggedInUser.username}">
						</div>

						<div class="mb-3">
							<label for="password" class="form-label">Password:</label> <input
								type="password" class="form-control" id="password"
								name="password" required
								value="${sessionScope.loggedInUser.password}">
						</div>

						<div class="mb-3">
							<!-- // TODO - In future, pull from property of list of Roles passed in, for now roles are static only 4 types. -->
							<select name="roleId" id="roleId" class="form-select"
								aria-label="Select"
								required ${ (sessionScope.loggedInUser.getId()> 0 ) ?
								'disabled' : ''}>


								<c:forEach var="role" items="${roles}" varStatus="loop">
									<option
										${sessionScope.loggedInUser.role.getId() == role.getId() ? 'selected' : ''}
										value="${role.getId()}">${role.getName()}</option>
								</c:forEach>


								<%-- 
		<option value="">Choose one</option>
			<option
			${sessionScope.loggedInUser.role.getId() == 1 ? 'selected' : ''}
			value="1">Pilot</option>
		<option
			${sessionScope.loggedInUser.role.getId() == 2 ? 'selected' : ''}
			value="2">Clerk</option>
		<option
			${sessionScope.loggedInUser.role.getId() == 3 ? 'selected' : ''}
			value="3">Commander</option>
		<option
			${sessionScope.loggedInUser.role.getId() == 4 ? 'selected' : ''}
			value="4">Admin</option>  --%>


							</select>
						</div>

						<div class="mb-3">
							<label for="dateOfBirth" class="form-label">Select your
								Date of Birth:</label> <input type="date" class="form-control"
								id="dateOfBirth" name="dateOfBirth" required
								value="${sessionScope.loggedInUser.getDateOfBirth()}">
						</div>

						<div class="mb-3">
							<label for="imageUrl" class="form-label">Profile Image:</label> <input
								type="text" class="form-control" id="imageUrl" name="imageUrl"
								value="${sessionScope.loggedInUser.getImageUrl()}">
						</div>

						<div class="mb-3">
							<!-- // TODO - In future, pull from property of list of all Organizations in the system for now roles are static only 2 organizations.... -->
							<label for="organizationId" class="form-label">Select
								your Organization:</label> <select name="organizationId"
								id="organizationId" class="form-select"
								aria-label="Default select example"
								required ${sessionScope.loggedInUser.getId()> 0 ?
								'disabled' : ''}>
								<option value="">Choose one</option>

								<c:forEach var="organization" items="${organizations}"
									varStatus="loop">
									<option
										${sessionScope.loggedInUser.organization.getId() == organization.getId() ? 'selected' : ''}
										value="${organization.getId()}">${organization.getName()}</option>
								</c:forEach>


								<%-- 			<option
									${sessionScope.loggedInUser.getOrganization().getId() == 1 ? 'selected' : ''}
									value="1">1-501AB</option>
								<option
									${sessionScope.loggedInUser.getOrganization().getId() == 2 ? 'selected' : ''}
									value="2">1-110AB</option> --%>


							</select>
						</div>


						<div class="mb-3">
							<button type="submit" class="btn btn-primary">Register</button>
						</div>

					</form>


				</div>


			</div>


		</div>

	</main>

	<jsp:include page="../_tail.jsp" />

</body>

</html>








