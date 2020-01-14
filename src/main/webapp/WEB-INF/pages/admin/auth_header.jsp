<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Auth Header</title>
</head>
<body>

<div>
    <span>Pretty <strong>${logged_in_user}</strong>, Welcome to CRUD 5 (SS).</span>
    <span>
        <a href="<c:url value="/logout"/>">Logout</a>
    </span>
</div>
<br/>

</body>
</html>
