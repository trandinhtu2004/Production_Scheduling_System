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
<%@page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
    <head>
    <title>Update Plan</title>
</head>
<body>
    <script>
        funtion doSelect(Date date){
            window.location = 
        }
    </script>
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
<form action="update" method="GET">
    <table style="border-collapse: collapse; width: 100%;">
    <thead>
        <tr>
            <th colspan="7" style="padding: 8px; text-align: left;">
                <label>Date:</label>
            </th>
        </tr>
        <tr>
            <th style="border: 1px solid #ddd; padding: 8px; text-align: center;" rowspan="2">Product</th>
            <th style="border: 1px solid #ddd; padding: 8px; text-align: center;" colspan="2">K1<br/>(07:00-11:00)</th>
            <th style="border: 1px solid #ddd; padding: 8px; text-align: center;" colspan="2">K2<br/>(14:00-18:00)</th>
            <th style="border: 1px solid #ddd; padding: 8px; text-align: center;" colspan="2">K3<br/>(19:00-23:00)</th>
        </tr>
        <tr>
            <th style="border: 1px solid #ddd; padding: 16px; text-align: center;">Input</th>
            <th style="border: 1px solid #ddd; padding: 8px; text-align: center;">DB</th>
            <th style="border: 1px solid #ddd; padding: 8px; text-align: center;">Input</th>
            <th style="border: 1px solid #ddd; padding: 8px; text-align: center;">DB</th>
            <th style="border: 1px solid #ddd; padding: 8px; text-align: center;">Input</th>
            <th style="border: 1px solid #ddd; padding: 8px; text-align: center;">DB</th>
        </tr>
    </thead>
    <tbody>
        <%
            List<Date> dates = (List<Date>) request.getAttribute("dates");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (Date date : dates) {
        %>
        <tr>
            <td colspan="7" style="padding: 8px; text-align: left;">
                <h3><%= sdf.format(date) %></h3>
            </td>
        </tr>
        <%
            for(int i = 0; i < list.size(); i++){
                ProductionPlanHeader plh = list.get(i);
        %>
        <tr>
            <td style="border: 1px solid #ddd; padding: 8px;"><%= plh.getProduct().getName() %></td>
            <td style="border: 1px solid #ddd; padding: 8px;">
                <input type="number" name="quantity--K1" style="width: 60px;">
            </td>
            <td style="border: 1px solid #ddd; padding: 8px;">0</td>
            <td style="border: 1px solid #ddd; padding: 8px;">
                <input type="number" name="quantity--K2" style="width: 60px;">
            </td>
            <td style="border: 1px solid #ddd; padding: 8px;">0</td>
            <td style="border: 1px solid #ddd; padding: 8px;">
                <input type="number" name="quantity--K3" style="width: 60px;">
            </td>
            <td style="border: 1px solid #ddd; padding: 8px;">0</td>
        </tr>
        <% 
            } // end of inner for loop
        } // end of outer for loop
        %>
    </tbody>
</table>

</form>

    <input type="submit" value="Update" />
</form>

    </body>
</html>
