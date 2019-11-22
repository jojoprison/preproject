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
                <input type="text" name="email" value="<c:out value="${newUser.email}" />"/>
            </td>
        </tr>

        <tr>
            <td>Password:</td>
            <td>
                <input type="text" name="password" value="<c:out value="${newUser.password}"/>"/>
            </td>
        </tr>

        <tr>
            <td>Name:</td>
            <td>
                <input type="text" name="name" value="<c:out value="${newUser.name}"/>"/>
            </td>
        </tr>

        <tr>
            <td>Age:</td>
            <td>
                <input type="number" name="age" value="<c:out value="${newUser.age}"/>"/>
            </td>
        </tr>

        <tr>
            <td>Role:</td>
            <td>
                <input type="text" name="role" value="<c:out value="${newUser.role}"/>"/>
            </td>
        </tr>

    </table>

    <c:if test="${newUser.id != null}">
        <input type="hidden" name="id" readonly value="<c:out value="${newUser.id}"/>"/>
    </c:if>

    <input type="submit" value="Send">
</form>

</body>
</html>