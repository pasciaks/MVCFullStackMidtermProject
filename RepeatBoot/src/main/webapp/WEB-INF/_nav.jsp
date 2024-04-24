<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<nav class="navbar navbar-expand-lg bg-light">
	<div class="container-fluid">
		<a class="navbar-brand" href="/">REPEAT</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNavAltMarkup"
			aria-controls="navbarNavAltMarkup" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav">

				<a class="nav-link" aria-current="page" href="home.do">Home</a> <a
					class="nav-link" href="about.do">About</a>

				<c:if test="${empty loggedInUser}">
					<li><a class="nav-link" href="login.do">Login</a></li>
					<li><a class="nav-link" href="register.do">Register</a></li>
				</c:if>

				<c:if test="${! empty loggedInUser}">


					<c:if test="${loggedInUser.role.name eq 'pilot'}">

						<li><a class="nav-link" href="add_log_entry.do">Add
								Flight Log</a></li>
						<li><a class="nav-link" href="list_experience.do">See
								Flight Logs</a></li>
						<li><a class="nav-link" href="choose_aircraft_type.do">Evaluate
								Flight Logs</a></li>

					</c:if>


					<c:if test="${loggedInUser.role.name eq 'clerk'}">

						<li><a class="nav-link" href="list_users_for_certification.do">List
								Users For Certification</a></li>

					</c:if>


					<c:if test="${loggedInUser.role.name eq 'commander'}">

						<li><a class="nav-link" href="clerk_one">Commander
								Implemented</a></li>

					</c:if>


					<c:if test="${loggedInUser.role.name eq 'admin'}">

						<li><a class="nav-link" href="list_user.do">Users</a></li>

					</c:if>
					
					
					<li><a class="nav-link" href="logout.do">Logout</a></li>
					<li><a class="nav-link" href="profile.do">Profile</a></li>

				</c:if>

			</div>
		</div>
	</div>
</nav>

<div class="container bg-light bordered">
	<c:if test="${! empty sessionScope.loggedInUser}">
		<div class="row">
			<div class="col text-center">
				<img onerror="this.style.display='none';"
					src="${sessionScope.loggedInUser.imageUrl}" alt="Image Url"
					height="100">
			</div>
			<div class="col text-center">
				<p>Username: ${sessionScope.loggedInUser.username}</p>
				<p>Role:${sessionScope.loggedInUser.role.getName()}</p>
			</div>
		</div>
		<hr>
	</c:if>
</div>

