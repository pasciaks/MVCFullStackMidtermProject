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
					<h1>Users List For Clerk</h1>
			
					<c:if test="${! empty message}">
						<div class="alert alert-success">${message}</div>
					</c:if>


					<p>${users.size()}<span> user(s) found.</span>
					</p>


					<table class="table table-bordered">
						<c:forEach var="user" items="${users}">
							<tr>
								<td class="text-center">${user.id}<br>
								<img onerror="this.style.display='none';" src="${user.imageUrl}"
									alt="Image Url for user with id : ${user.id}"
									title="Image Url for user with id : ${user.id}" width="100"></td>
								<td>${user.username}</td>
								<td>${user.enabled == true ? '<span class="text-success">enabled</span>' :  '<span class="text-danger">disabled</span>' }</td>

								<td>${user.organization.name}</td>

								<td>
									<form method="GET" action="show_user.do">
										<input type="hidden" name="id" value="${user.id}" />
										<button type="submit" class="btn btn-success">Show</button>
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








