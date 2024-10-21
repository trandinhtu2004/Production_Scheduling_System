<%-- 
    Document   : list
    Created on : Oct 20, 2024, 10:24:44 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>List of plans</h2>
        
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Plan</th>
                    <th>Workshop</th>
                    <th>Start</th>
                    <th>End</th>
                    <th>Product</th>
                    <th>Expectation quantity</th>
                    <th>Update schedule</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="plan" items="${requestScope.plans}">
            <tr>
                <td>${plan.id}</td>
                <td>${plan.name}</td>
                <td>${plan.dept.name}</td>
                <td>${plan.start}</td>
                <td>${plan.end}</td>
             <td>
                   <select>
                <c:forEach var="header" items="${plan.headers}">
                    <option value="${header.product.id}">${header.product.name}</option>
                </c:forEach>
            </select>
                </td>
                <td>
                    <c:forEach var="header" items="${plan.headers}">
                        ${header.quantity} 
                    </c:forEach>
                </td>
                <td><a href="update?id=${plan.id}">Update</a></td>
            </tr>
            
        </c:forEach>
            </tbody>
        </table>

    </body>
</html>
