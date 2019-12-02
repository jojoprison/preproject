<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Users</title>
</head>
<body>

<h2 align="center">Users:</h2>

<table align="center" border="1" width="100%">

    <tr>
        <th>ID</th>
        <th>E-Mail</th>
        <th>Password</th>
        <th>Name</th>
        <th>Age</th>
        <th>Role</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>

    <c:forEach var="user" items="${userList}">

        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.password}"/></td>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.age}"/></td>
            <td><c:out value="${user.role}"/></td>
            <td align="center">
                <form method="GET" action="admin/update">
                    <input type="submit" value="Update" name="update">
                    <input type="hidden" name="id" value="${user.id}">
                </form>
            </td>
            <td align="center">
                <form method="POST" action="admin/delete">
                    <input type="submit" value="Delete" name="delete">
                    <input type="hidden" name="id" value="${user.id}">
                </form>
            </td>

        </tr>
    </c:forEach>

</table>
<br/>

<form action="admin/add" align="center">
    <button>Add User</button>
</form>
<br/>

<div align="center">
    <a href="user">Visit Login Page</a>
</div>
<br/>

<form action="logout" method="POST" align="center">
    <input type="submit" value="Logout">
</form>

</body>
</html>