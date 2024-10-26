<%-- 
    Document   : update
    Created on : Oct 23, 2024, 5:29:21 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Update Plan: ${plan.name}</h2>
<form action="update" method="POST">
    <input type="hidden" name="planId" value="${plan.id}" />

    <!-- Products and Progress Table -->
    <h3>Products</h3>
    <table border="1">
        <tr>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Progress (%)</th>
        </tr>
        <c:forEach var="p" items="${plan.headers}">
            <tr>
                <td>${p.product.name}</td>
                <td>${p.product.quantity}</td>
<!--                <td><input type="number" name="progress_${p.id}" value="${product.progress}" min="0" max="100" /></td>-->
            </tr>
        </c:forEach>
    </table>

    <!-- Shift Schedule -->
    <h3>Shift Schedule</h3>
    <table border="1">
        <tr>
            <th>Date</th>
            <th>Shift 1 (7h-11h)</th>
            <th>Shift 2 (14h-18h)</th>
            <th>Shift 3 (19h-23h)</th>
        </tr>
        <c:forEach var="shift" items="${shifts}">
            <tr>
                <td>${shift.shiftDate}</td>
                <td><input type="checkbox" name="shift1_${shift.shiftDate}" ${shift.shift1 ? "checked" : ""} /></td>
                <td><input type="checkbox" name="shift2_${shift.shiftDate}" ${shift.shift2 ? "checked" : ""} /></td>
                <td><input type="checkbox" name="shift3_${shift.shiftDate}" ${shift.shift3 ? "checked" : ""} /></td>
            </tr>
        </c:forEach>
    </table>

    <input type="submit" value="Update" />
</form>

    </body>
</html>
