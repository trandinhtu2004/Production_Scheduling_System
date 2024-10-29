<%-- 
    Document   : update
    Created on : Oct 23, 2024, 5:29:21 PM
    Author     : Admin
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="dal.*" %>
<%@page import="model.*" %>
<%@page import="java.lang.*" %>
<%@page import="java.util.*" %>

<!DOCTYPE html>
<html>
    <head>
    <title>Update Plan</title>
</head>
<body>
    <h2>Update Plan: ${plan.name}</h2>
    <p>Start Date: ${plan.start}</p>
    <p>End Date: ${plan.end}</p>
    <p>Department: ${plan.dept.name}</p>
    <h3>Products</h3>
    
    <p>List of products: ${requestScope.plan.headers.size()}</p>
    
    <table border="1">
        <tr> <th>Product Name</th>
            <th>Quantity</th>
            <th>Estimated Effort</th>
        </tr> 
        
        <%
            //ham <c:foreach> can't list.
            ProductionPlan p = (ProductionPlan) request.getAttribute("plan");
            ArrayList<ProductionPlanHeader> list = (ArrayList<ProductionPlanHeader>)request.getAttribute("header");
            for(int i = 0; i<list.size();i++){
            ProductionPlanHeader plh = list.get(i);
        %>
            <tr>
                <td><%= plh.getProduct().getName() %></td>
                <td><%= plh.getQuantity() %></td> 
                <td><%= plh.getEstimatedeffort() %></td>
            </tr>
        <%}%>
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
<!--       
    </table>

    <input type="submit" value="Update" />
</form>

    </body>
</html>
