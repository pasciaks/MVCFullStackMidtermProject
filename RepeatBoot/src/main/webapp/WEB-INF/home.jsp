<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!doctype html>

<html lang="en">

<jsp:include page="_head.jsp" />

<body>

	<jsp:include page="_nav.jsp" />

	<main>

		<c:if test="${! empty error}">
			<div class="alert alert-danger" role="alert">${error}</div>
		</c:if>


		<c:if test="${! empty message}">
			<div class="alert alert-success" role="alert">${message}</div>
		</c:if>

		<div class="container">

			<div class="row">
				<div class="col text-center">
					<h1>Home</h1>
					<img src="logo.png" width="25%" alt="REPEAT logo"
						class="img-responsive shadow hover-zoom" /> <br> <br>
					<hr>
					<h2>
						<strong>R</strong>ecursive <strong>E</strong>ternity <strong>P</strong>ilot
						<strong>E</strong>ducation <strong>A</strong>nd <strong>T</strong>racking
					</h2>
				</div>
			</div>



		</div>

	</main>

	<jsp:include page="_tail.jsp" />

</body>

</html>