<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%-- Import the JSTL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- import the custom tag for AdminHeader 
<%@ taglib prefix="cs3220" uri="admin-header.tld"%> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Yellow Moon Inn: Manage Orders</title>
        <link rel="stylesheet" href="<c:url value='/app.css' />">
        <link href="<c:url value='https://fonts.googleapis.com/css?family=Ceviche+One|Cinzel:900' />" rel="stylesheet"> 
    </head>
    <body>
        <header>
            <img src="<c:url value='../../images/yellow-moon.jpg' />" width=150px height=150px class="header_image">
            The Yellow Moon Inn
        </header>

        <%-- <cs3220:header /> --%>

        <nav>
            <a href="<c:url value='/jdbc/menu' />" class="navigation">Buy Liquor</a>
            <a href="<c:url value='/jdbc/shopping-cart' />" class="navigation">Your Tab</a>
            <a href="<c:url value='/jdbc/orders' />" class="navigation">Purchasing History</a>
            <a href="<c:url value='/jdbc/admin/foods' />" class="navigation">The Stockroom</a>
            <a href="<c:url value='/jdbc/admin/foods/create' />" class="navigation">The Brewery</a>
            <a href="<c:url value='/jdbc/admin/orders' />" class="navigation">The Chancery</a>
        </nav>

        <main>
            <h2> Welcome, Innkeeper!</h2>
            
            <p> Who would have thought this crowd was so alcohol-hungry? The orders are flooding in, please help manage them!</p>

            <h3> Manage Liquor Orders Here </h3>
            
            <table class="center horizontal">
                <thead>
                    <th>Order Info</th>
                    <th>Liquor Requests</th>
                    <th>Status</th>
                    <th>Edit?</th>
                    <th>Delete</th>
                </thead>
                <tbody>
                    <c:if test="${orders.size() == 0}">
                        <tr>
                            <td colspan="5"><p>There are currently no requsts for liquor.</p></td>
                        </tr>
                    </c:if>
                    <c:forEach items="${orders}" var="order">
                        <tr>
                            <td>${order.getCustomerName()}<br>
                                ${order.getOrderTime()}
                            </td>
                            
                            <td>
                                <table class="center">
                                    <c:forEach items="${order.getItems()}" var="item">
                                        <tr>
                                            <td> ${item.getQuantity()} x </td>
                                            <td> <img src="<c:url value='../../${item.imgURL}' />" class="tiny"> </td>
                                            <td> ${item.getName()} </td>
                                        </tr>
                                    </c:forEach> 
                                </table>
                            </td>

                            <td>${order.getStatus()}</td>
                            <td><a href="<c:url value='orders/edit?id=${order.getId()}' />" class="button">Edit Status</a>
                                </td>
                            <td><a href="<c:url value='orders/delete?id=${order.getId()}' />" class="button">Delete</a>
                                </td>
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
