<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>R.E.P.E.A.T.</title>
</head>
<body>

<h1>Profile</h1>

	<c:if test="${! empty sessionScope.loggedInUser}">
	<p>
	${sessionScope.loggedInUser.username}
	</p>
	</c:if>
	
</body>
</html>