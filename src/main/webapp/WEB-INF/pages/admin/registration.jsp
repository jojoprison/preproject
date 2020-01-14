<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>User Registration Form</title>
</head>

<body>

<div>

    <%@include file="auth_header.jsp" %>

    <div>User Registration Form</div>
    <br/>

    <form:form method="POST" modelAttribute="user">

        <form:input type="hidden" path="id" id="id"/>

        <div>
            <label for="ssoId">SSO ID:</label>

            <div>
                <c:choose>

                    <c:when test="${edit}">
                        <form:input type="text" path="ssoId" id="ssoId" disabled="true"/>
                    </c:when>

                    <c:otherwise>
                        <form:input type="text" path="ssoId" id="ssoId"/>
                        <div>
                            <form:errors path="ssoId"/>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <div>
            <label for="password">Password:</label>

            <div>

                <form:input type="password" path="password" id="password"/>

                <div>
                    <form:errors path="password"/>
                </div>
            </div>
        </div>

        <div>
            <label for="email">Email:</label>

            <div>

                <form:input type="text" path="email" id="email"/>

                <div>
                    <form:errors path="email"/>
                </div>
            </div>
        </div>

        <div>
            <label for="name">Name:</label>

            <div>
                <form:input type="text" path="name" id="name"/>
                <div>
                    <form:errors path="name"/>
                </div>
            </div>
        </div>

        <div>
            <label for="age">Age:</label>

            <div>
                <form:input type="text" path="age" id="age"/>
                <div>
                    <form:errors path="age"/>
                </div>
            </div>
        </div>

        <div>
            <label for="userProfiles">Roles:</label>

            <div>

                <form:select path="userProfiles" items="${roles}" multiple="true" itemValue="id" itemLabel="type"/>

                <div>
                    <form:errors path="userProfiles"/>
                </div>
            </div>
        </div>
        <br/>

        <div>
            <c:choose>

                <c:when test="${edit}">
                    <input type="submit" value="Update"/> or
                    <a href="<c:url value='/admin/list'/>">Cancel</a>
                </c:when>

                <c:otherwise>
                    <input type="submit" value="Register"/> or
                    <a href="<c:url value='/admin/list'/>">Cancel</a>
                </c:otherwise>
            </c:choose>
        </div>

    </form:form>
</div>

</body>
</html>