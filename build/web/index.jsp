<%-- 
    Document   : index
    Created on : Oct 15, 2024, 10:19:46 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Production Scheduling System</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Link to the external CSS file -->
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <!-- Header section with company info and login button -->
        <header>
            <div class="company-info">
                <p><strong>Production Scheduling System</strong></p>
                <p>Address: Bo cong an Pham Van Dong, Bac Tu Liem, HaNoi, VietNam</p>
                <p>Phone: +84 91 248 9520</p>
                <p>Copyright: Trần Đình Tú</p>
                <p>Version 1.0</p>
            </div>
            <!-- <a href="login.html" class="login-btn">Login</a> -->
            <!-- Kiểm tra nếu không có 'account' trong session (nghĩa là đã logout) -->
            <c:choose>
                <c:when test="${sessionScope.account == null}">
                    <a href="login.html">login</a>
                </c:when>
                <c:otherwise>
                    <p>Hello, ${sessionScope.account.email}!</p>
                    <form action="logout" method="get">
                        <button type="submit">Logout</button>
                    </form>
                </c:otherwise>
            </c:choose>

        </header>

        <!-- Main content-->
        <div>
            <div class="news">
                News:
            </div>
        </div>
        <div>
                <div class="createPlan">
        <a href="plan/create">Add new plan</a>
            </div>
                <div class="listPlan">
        <a href="plan/view">List/Update plan</a>
            </div>
        </div>
    </div>
    <!-- Footer section -->
    <footer>
        <p>Production Scheduling System</p>
    </footer>
</body>
</html>