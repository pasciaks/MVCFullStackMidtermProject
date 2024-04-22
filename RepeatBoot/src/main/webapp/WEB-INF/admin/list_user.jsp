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
					<h1>Users</h1>
					<c:if test="${! empty sessionScope.loggedInUser}">
						<p>${sessionScope.loggedInUser.username}</p>
					</c:if>

					<c:if test="${! empty message}">
						<div class="alert alert-success">${message}</div>
					</c:if>


					<p>${users.size()}<span> user(s) found.</span>
					</p>


					<table class="table table-bordered">
						<c:forEach var="user" items="${users}">
							<tr>
								<td>${user.id}</td>
								<td>${user.username}</td>
								<td>${user.enabled == true ? '<span class="text-success">enabled</span>' :  '<span class="text-danger">disabled</span>' }</td>

								<td>
									<form method="post" action="enable_user.do">
										<input type="hidden" name="id" value="${user.id}" />
										<button type="submit" class="btn btn-success">Enable</button>
									</form>
								</td>


								<td>
									<form method="post" action="disable_user.do">
										<input type="hidden" name="id" value="${user.id}" />
										<button type="submit" class="btn btn-danger">Disable</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</table>

				</div>
			</div>





		</div>

	</main>

	<jsp:include page="../_tail.jsp" />

</body>

</html>








