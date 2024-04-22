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
					<h1>Profile</h1>
					<c:if test="${! empty sessionScope.loggedInUser}">
						<p>${sessionScope.loggedInUser.username}</p>


						<form action="update_profile.do" method="post">

							<jsp:include page="../public/_profile_form.jsp" />

							<div class="mb-3">
								<button type="submit" class="btn btn-primary">Update Profile</button>
							</div>

						</form>


					</c:if>
				</div>
			</div>
		</div>

	</main>

	<jsp:include page="../_tail.jsp" />

</body>

</html>








