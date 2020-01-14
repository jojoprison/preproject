<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Users List</title>
</head>

<body>

<div>

    <%@include file="admin/auth_header.jsp" %>

    <div>

        <div>
            <span>List of Users:</span>
        </div>
        <br/>

        <table>

            <thead>
            <tr>
                <th>Name</th>
                <th>Age</th>
                <th>Email</th>
                <th>SSO ID</th>
                <sec:authorize access="hasRole('ADMIN')">
                    <th width="100"></th>
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN')">
                    <th width="100"></th>
                </sec:authorize>
            </tr>
            </thead>

            <tbody>

            <c:forEach items="${users}" var="user">

                <tr>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                    <td>${user.email}</td>
                    <td>${user.ssoId}</td>
                    <sec:authorize access="hasRole('ADMIN')">
                        <td>
                            <a href="<c:url value='/admin/edit_user_${user.ssoId}' />">edit</a>
                        </td>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN')">
                        <td>
                            <a href="<c:url value='/admin/delete_user_${user.ssoId}' />">delete</a>
                        </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>
    <br/>

    <sec:authorize access="hasRole('ADMIN')">
        <div>
            <a href="<c:url value='/admin/new_user'/>">New User</a>
        </div>
    </sec:authorize>
</div>

</body>
</html>