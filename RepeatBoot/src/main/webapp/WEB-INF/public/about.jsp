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
						<h1>About</h1>
						<img src="../logo.png" width="30%" alt="REPEAT logo"
							class="img-responsive" /> <br>

						<p>
							<strong>EPIC:</strong> Military pilots are tasked with
							maintaining a constant state of readiness. This includes their
							academic, medical and clearance certifications of good standing
							as well as the certification that they are up to date with
							required flight training/experience. By logging each flight a
							pilot completes along with the details of the training/practice
							that occurred on these flights, the sum of all experiences can be
							compared to the total number of time requirements that are
							determined necessary. When the pilot meets or exceeds these
							requirements, they are regarded as combat ready and sufficiently
							trained to for the specific types of aircrafts the pilot is
							tasked with operating.
						</p>
						<p>
							<strong>INITIATIVE:</strong> Create a web based, mobile friendly
							website that allows for the tracking of pilot qualifications for
							various aircraft. The site should be able to easily identify any
							shortcoming in pilot experience needed to be regarded as fully
							qualified/combat ready.
						</p>
					</div>
				</div>
			</div>

			<hr>

			<h3 class="text-center text-success">Created by the following Veteran Developers thanks to VET
				TEC and Skill Distillery!</h3>

			<hr>

			<div class="row">
				<div class="col">
					<h3 class="text-center">Bryan</h3>

					<p>SCRUM Master</p>
					<p>DBA</p>
					<p>Programmer</p>

				</div>
				<div class="col">
					<h3 class="text-center">Sheldon</h3>

					<p>REPO Owner</p>
					<p>Programmer</p>

				</div>
			</div>


		</div>

	</main>

	<jsp:include page="../_tail.jsp" />

</body>

</html>








