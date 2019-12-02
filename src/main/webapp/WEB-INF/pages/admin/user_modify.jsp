<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>

    <c:if test="${empty user.id}">
        <title>Add Page</title>
    </c:if>
    <c:if test="${!empty user.id}">
        <title>Update Page</title>
    </c:if>

</head>
<body>

<c:if test="${empty user.id}">
    <c:url value="/admin/add" var="var"/>
</c:if>
<c:if test="${!empty user.id}">
    <c:url value="/admin/update" var="var"/>
</c:if>

<form method="POST" action="${var}">

    <h2>User</h2>

    <c:if test="${!empty user.id}">
        <input type="hidden" name="id" value="${user.id}">
    </c:if>

    <label for="email">Email:</label>
    <br/>
    <input type="text" name="email" id="email" value="<c:out value="${user.email}" />">
    <br/>

    <label for="password">Password:</label>
    <br/>
    <input type="text" name="password" id="password" value="<c:out value="${user.password}" />">
    <br/>

    <label for="name">Name:</label>
    <br/>
    <input type="text" name="name" id="name" value="<c:out value="${user.name}" />">
    <br/>

    <label for="age">Age:</label>
    <br/>
    <input type="number" name="age" id="age" value="<c:out value="${user.age}" />">
    <br/>

    <label for="role">Role:</label>
    <br/>
    <input type="text" name="role" id="role" value="<c:out value="${user.role}" />">
    <br/>
    <br/>

    <c:if test="${empty user.id}">
        <input type="submit" value="Add new user">
    </c:if>
    <c:if test="${!empty user.id}">
        <input type="submit" value="Update user">
    </c:if>

</form>

</body>
</html>