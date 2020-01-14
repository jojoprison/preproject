<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Access Denied page</title>
</head>

<body>

<div>
    <div>
        <span>Pretty <strong>${logged_in_user}</strong>, you are not authorized to access this page.</span>
        <span><a href="<c:url value="/logout" />">Logout</a></span>
    </div>
</div>

</body>
</html>