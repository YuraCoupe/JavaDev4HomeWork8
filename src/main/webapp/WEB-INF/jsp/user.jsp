<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
         <c:import url="${contextPath}/WEB-INF/jsp/header.jsp"/>
    </head>

    <body>

        <c:import url="${contextPath}/WEB-INF/jsp/navibar.jsp"/>
        <div class="container">
            <div class="row">
                <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                    <div class="btn-group me-2" role="group" aria-label="Second group">
                        <a href="/users" type="button" class="btn btn-success">Back to users</a>
                    </div>
                </div>
            </div><br>
        <form:form action="/users" method="post" modelAttribute="user">
            <div class="form-group">
                <div class="row">
                    <form:label path="id">User ID:</form:label><br>
                    <form:input path="id" type="UUID" readonly="true" class="form-control" id="id" placeholder="User ID" name="id" value="${user.id}"/>
                    <br>
                    <form:label path="email">username (email address):</form:label><br>
                    <form:input path="email" type="text" class="form-control" id="email" placeholder="Enter user email" name="email" value="${user.email}"/>
                    <form:errors path="email" cssClass="error"/>
                    <br>
                    <form:label path="password">Password:</form:label><br>
                    <form:input path="password" type="text" class="form-control" id="password" placeholder="Enter password" name="password" value="${user.password}"/>
                    <form:errors path="password" cssClass="error"/>
                    <br>
                    <form:label path="firstName">First name:</form:label><br>
                    <form:input path="firstName" type="text" class="form-control" id="firstName" placeholder="Enter first name" name="firstName" value="${user.firstName}"/>
                    <form:errors path="firstName" cssClass="error"/>
                    <br>
                    <form:label path="lastName">First name:</form:label><br>
                    <form:input path="lastName" type="text" class="form-control" id="lastName" placeholder="Enter last name" name="lastName" value="${user.lastName}"/>
                    <form:errors path="lastName" cssClass="error"/>
                    <br>
                    <form:label path="roles">Roles:</form:label><br>
                    <c:forEach items="${roles}" var="role">
                        <form:checkbox path="roles" id="${roles}" label="${role.name}" value="${role}"/></td>
                    </c:forEach>
                    <br>
                    <form:errors path="roles" cssClass="error"/><br><br>
                </div>
                <div class="row">
                    <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                        <div class="btn-group me-2" role="group" aria-label="Second group">
                            <form:button type="submit" value="Submit" class="btn btn-primary">Save</form:button>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
        </div>
    </body>
</html>
