<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!doctype html>

<html lang="en">

<jsp:include page="_head.jsp" />

<body>

	<jsp:include page="_nav.jsp" />

	<main>

		<div class="container">

			<div class="row">
				<div class="col text-center">
					<h1>Error</h1>

				</div>
			</div>

			<div class="row">
				<div class="col text-center">
					<p>${error}</p>
				</div>
			</div>

		</div>
		
	</main>

	<jsp:include page="_tail.jsp" />

</body>

</html>