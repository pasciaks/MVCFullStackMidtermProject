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

<jsp:include page="nav.jsp"/>


<h1>Home</h1>

<form method="POST" action="login.do">

<input type="text" name="username" placeholder="username"><br>
<input type="password" name="password" placeholder="password"><br>
<input type="submit" value="Login">

</form>

</body>
</html>