<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <title>Login</title>
</head>
<body>

<form action="login" method="POST">

    <h2>Login</h2>

    <div>
        <label for="email">Email: </label>
        <input id="email" type="text" name="email"/>

        <label for="password">Password: </label>
        <input id="password" type="password" name="password"/>
    </div>
    <br/>

    <div>
        <input type="submit" value="Login">
    </div>
</form>
<br/>

<div>
    <span style="color: red"><c:out value="${message}"/></span>
</div>

</body>
</html>
