<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <form action="/manufacturers">
                <div class="form-group">
                    <label for="Id">Manufacturer name:</label><br>
                    <select class="form-control" id="id" name="id">
                        <option disabled selected value> -- select manufacturer -- </option>
                        <c:forEach items="${manufacturers}" var="manufacturer">
                            <option value="${manufacturer.id}"><c:out value="${manufacturer.name}"/></option>
                        </c:forEach>
                    </select>
                </div>
                    <input type="submit" value="Search">
            </form><br>

            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                <div class="btn-group me-2" role="group" aria-label="Second group">
                   <a href="/manufacturers/new" type="button" class="btn btn-primary">New</a>
                </div>
            </div>
            <div>
                <c:if test="${not empty errorMessage}">
                    <c:forEach items="${errorMessage.errors}" var="error">
                        <p style="color:red">${error}</p>
                    </c:forEach>
                </c:if>
            </div>

            <table class="table table-hover">
                <thead>
                    <tr>
                        <td>Manufacturer id</td>
                        <td>Manufacturer name</td>
                        <td>Manufacturer products</td>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${manufacturers}" var="manufacturer">
                    <tr>
                        <td>
                            <c:out value="${manufacturer.id}"/>
                        </td>
                        <td>
                            <c:out value="${manufacturer.name}"/>
                        </td>
                        <td>
                            <c:forEach items="${manufacturer.products}" var="product">
                                <c:out value="${product.name}"/>"/><br>
                            </c:forEach>
                        </td>
                        <td>
                            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                <div class="btn-group me-2" role="group" aria-label="Second group">
                                     <a href="/manufacturers/${manufacturer.id}" type="button" class="btn btn-warning">Edit</a>
                                     <a href="/manufacturers?deleteId=${manufacturer.id}" type="button" class="btn btn-danger">Remove</a>
                                </div>
                            </div>
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
