<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Registration Confirmation Page</title>
</head>

<body>

<div>

    <%@include file="auth_header.jsp" %>

    <div>
        ${success}
    </div>
    <br/>

    <span>
        Go to <a href="<c:url value='/admin/list'/>">Users List</a>
    </span>

</div>

</body>
</html>