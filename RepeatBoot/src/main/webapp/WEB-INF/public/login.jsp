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

		<div class="container login">

			<h1 class="text-center">Login</h1>

			<div class="row">
				<div class="col text-center">
					<form method="POST" action="login.do">
						<p>Username:
						<p>
							<input type="text" name="username" placeholder="username" value="maverick"><br>
						<p>
							Password: <p>
					<input type="password" name="password"
								placeholder="password" value="topgun"><br> 
								<br>
								<input type="submit" class="btn btn-primary"
								value="Login"></form>
				</div>
			</div>

		</div>

	</main>

	<jsp:include page="../_tail.jsp" />

</body>

</html>








