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
						<img src="../logo.png" width="25%" alt="REPEAT logo"
							class="img-responsive" />
						<p></p>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col">
					${roles.size()}
					${organizations.size()}
					<form id="register" action="register.do" method="post">

						<jsp:include page="_profile_form.jsp" />

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








