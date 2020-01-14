<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login Page</title>
</head>

<body>

<div>

    <c:url var="loginUrl" value="/login"/>

    <form action="${loginUrl}" method="POST">

        <c:if test="${param.error != null}">
            <div>
                <p>Invalid username and password.</p>
            </div>
        </c:if>

        <c:if test="${param.logout != null}">
            <div>
                <p>You have been logged out successfully.</p>
            </div>
        </c:if>

        <div>
            <label for="username"></label>
            <input type="text" id="username" name="ssoId" placeholder="Enter Username"
                   required>
        </div>

        <div>
<%--            TODO i--%>
            <label for="password"><i></i></label>
            <input type="password" id="password" name="password"
                   placeholder="Enter Password" required>
        </div>
        <br/>

        <div>
            <div>
                <label><input type="checkbox" id="remember_me" name="remember_me"> Remember Me</label>
            </div>
        </div>
        <br/>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <div>
            <input type="submit" value="Log in">
        </div>
    </form>
</div>

</body>
</html>