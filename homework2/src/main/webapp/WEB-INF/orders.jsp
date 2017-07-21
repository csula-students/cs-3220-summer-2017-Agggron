<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%-- Import the JSTL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Yellow Moon Inn: Inventory</title>
        <link rel="stylesheet" href="<c:url value='../app.css' />">
        <link href="<c:url value='https://fonts.googleapis.com/css?family=Ceviche+One|Cinzel:900' />" rel="stylesheet"> 
    </head>
    <body>
        <header>
            <img src="<c:url value='../images/yellow-moon.jpg' />" width=150px height=150px class="header_image">
            The Yellow Moon Inn
        </header>

        <nav>
            <a href="<c:url value='/menu' />" class="navigation">Buy Liquor</a>
            <a href="<c:url value='/shopping-cart' />" class="navigation">Your Tab</a>
            <a href="<c:url value='/orders' />" class="navigation">Purchasing History</a>
            <a href="<c:url value='/admin/foods' />" class="navigation">The Stockroom</a>
            <a href="<c:url value='/admin/foods/create' />" class="navigation">The Brewery</a>
            <a href="<c:url value='/admin/orders' />" class="navigation">The Chancery</a>
        </nav>

        <main>
            <h2>Your Purchases</h2>

            <p>Requested a particular brew in the past? Keep track of what purchases you have made at the Yellow Moon Inn here!</p>
            
            <table class="center horizontal">
                <thead>
                    <th>Liquor Requests</th>
                    <th>Order Info</th>
                    <th>Status</th>
                </thead>
                <tbody>
                    <c:if test="${orders.size() == 0}">
                        <tr>
                            <td colspan="3"><p>You have made no orders of liquor! Drink some more, you teetotaler!</p></td>
                        </tr>
                    </c:if>
                    <c:forEach items="${orders}" var="order">
                        <tr>
                            <td><ul>
                                <c:forEach items="${order.getItems()}" var="item">
                                    <li>${item.getQuantity()} x ${item.getName()} 
                                </c:forEach> 
                                </td>
                            <td>${order.getCustomerName()}<br>
                                ${order.getOrderTime()}</td>
                            <td>${order.getStatus()}</td>
                        </tr>
                    </c:forEach> 
                </tbody>
            </table>

        </main>

        <footer>
            © 2017 Aaron Tang, Inc., All Rights Reserved.
        </footer>
    </body>
</html>
