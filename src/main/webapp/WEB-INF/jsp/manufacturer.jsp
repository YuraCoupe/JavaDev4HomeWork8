<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
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
                        <a href="/manufacturers" type="button" class="btn btn-success">Back to manufacturers</a>
                    </div>
                </div>
            </div><br>
        <form:form action="/manufacturers" method="post" modelAttribute="manufacturer">
            <div class="form-group">
                <div class="row">
                    <form:label path="id">Manufacturer ID:</form:label><br>
                    <form:input path="id" type="UUID" readonly="true" class="form-control" id="id" placeholder="Manufacturer ID" name="id" value="${manufacturer.id}"/><br>
                    <form:label path="name">Company name:</form:label><br>
                    <form:input path="name" type="text" class="form-control" id="name" placeholder="Enter Manufacturer name" name="name" value="${manufacturer.name}"/>
                    <form:errors path="name" cssClass="error"/><br><br>
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

        <c:if test = "${fn:length(manufacturer.products) > 0}">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <td>Product name</td>
                        <td>Product price</td>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${manufacturer.products}" var="product">
                    <tr>
                        <td>
                            <c:out value="${product.name}"/>
                        </td>
                        <td>
                            <c:out value="${product.price}"/>
                        </td>
                        <td>
                            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                <div class="btn-group me-2" role="group" aria-label="Second group">
                                    <a href="/products/edit/${product.id}" type="button" class="btn btn-warning">Edit</a>
                                    <a href="/manufacturers/edit/${manufacturer.id}/delete/${product.id}" type="button" class="btn btn-danger">Remove</a>
                                </div>
                            </div>
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        </div>
    </body>
</html>
