<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!doctype html>

<html lang="en">

<jsp:include page="../_head.jsp" />

<body>

	<jsp:include page="../_nav.jsp" />

	<main>

		<div class="container about">

			<div class="row">
				<div class="col">
					<div class="col text-center">
						<h1>Register</h1>
						<img src="../logo.png" width="25%" alt="REPEAT logo"
							class="img-responsive" />
						<p></p>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col">

					<form id="register" action="register.do" method="post">


						<div class="mb-3">
							<label for="username" class="form-label">User name:</label> <input
								type="text" class="form-control" id="username" name="username">
						</div>

						<div class="mb-3">
							<label for="password" class="form-label">Password:</label> <input
								type="password" class="form-control" id="password"
								name="password">
						</div>

						<div class="mb-3">
							<!-- // TODO - In future, pull from property of list of Roles passed in, for now roles are static only 4 types. -->
							<select name="role" id="role" class="form-select"
								aria-label="Default select example">
								<option selected value="1">Pilot</option>
								<option value="2">Clerk</option>
								<option value="3">Commander</option>
								<option value="4">Admin</option>
							</select>
						</div>

						<div class="mb-3">
							<label for="dateOfBirth" class="form-label">Select your
								Date of Birth:</label> <input type="date" class="form-control"
								id="dateOfBirth" name="dateOfBirth">
						</div>

						<div class="mb-3">
							<label for="imageUrl" class="form-label">Profile Image:</label> <input
								type="text" class="form-control" id="imageUrl" name="imageUrl">
						</div>

						<div class="mb-3">
							<!-- // TODO - In future, pull from property of list of Roles passed in, for now roles are static only 4 types. -->
							<select name="organization" id="organization" class="form-select"
								aria-label="Default select example">
								<option selected value="1">......</option>
							</select>
						</div>


						<div class="mb-3">
							<button type="submit" class="btn btn-primary">Submit</button>
						</div>


					</form>


				</div>


			</div>


		</div>

	</main>

	<jsp:include page="../_tail.jsp" />

</body>

</html>








