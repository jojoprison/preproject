<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Login Success Page</title>
</head>
<body>

<h3>User with email "<c:out value="${user.email}"/>" successful logged in.</h3>

<div>
    <span>User Role = <c:out value="${user.role}"/></span>
</div>
<br/>

<form action="logout" method="POST">
    <input type="submit" value="Logout">
</form>

</body>
</html>