<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Modify User</title>
</head>
<body>

<form method="POST" action="modify">

    <h2>User</h2>

    <table>

        <tr>
            <td>E-Mail:</td>
            <td>
                <input type="text" name="email" value="<c:out value="${user.email}" />"/>
            </td>
        </tr>

        <tr>
            <td>Password:</td>
            <td>
                <input type="text" name="password" value="<c:out value="${user.password}"/>"/>
            </td>
        </tr>

        <tr>
            <td>Name:</td>
            <td>
                <input type="text" name="name" value="<c:out value="${user.name}"/>"/>
            </td>
        </tr>

        <tr>
            <td>Age:</td>
            <td>
                <input type="number" name="age" value="<c:out value="${user.age}"/>"/>
            </td>
        </tr>

    </table>

    <c:if test="${user.id != null}">
        <input type="hidden" name="id" readonly value="<c:out value="${user.id}"/>"/>
    </c:if>

    <input type="submit" value="Send">
</form>

</body>
</html>