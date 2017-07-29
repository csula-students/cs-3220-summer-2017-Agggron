<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%-- Import the JSTL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- import the custom tag for AdminHeader --%>
<%@ taglib prefix="cs3220" uri="/WEB-INF/admin-header.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Yellow Moon Inn: Manage Orders</title>
        <link rel="stylesheet" href="<c:url value='/app.css' />">
        <link href="<c:url value='https://fonts.googleapis.com/css?family=Ceviche+One|Cinzel:900' />" rel="stylesheet"> 
    </head>
    <body>

        <cs3220:header title="Order Statuses" />

        <nav>
            <a href="<c:url value='../admin/foods' />" class="navigation">The Lobby</a>
            <a href="<c:url value='../admin/foods' />" class="navigation">Buy Items</a>
            <a href="<c:url value='../admin/foods' />" class="navigation">Your Tab</a>
            <a href="<c:url value='../admin/foods' />" class="navigation">The Stockroom</a>
            <a href="<c:url value='../admin/foods/create' />" class="navigation">The Brewery</a>
            <a href="<c:url value='../admin/orders' />" class="navigation">The Chancery</a>
        </nav>

        <main>
            <h2>Welcome to the Chancery!</h2>
            
            <p>Who would have thought this crowd was so alcohol-hungry? The orders are flooding in, please help manage them!</p>
            
            
            <table class="center horizontal">
                <thead>
                    <th class="six-column">Order Info</th>
                    <th class="six-column">Customer Info</th>
                    <th class="six-column">Order Time</th>
                    <th class="six-column">Status</th>
                    <th class="six-column">Edit?</th>
                    <th class="six-column">Delete?</th>
                </thead>
                <tbody>
                    <c:if test="${orders.size() == 0}">
                        <tr>
                            <td colspan="6"><p>There are no orders!</p></td>
                        </tr>
                    </c:if>
                    <c:forEach items="${orders}" var="order">

                        <tr>
                            <td><p>Order Info Here</p>
                                </td>
                            <td>${order.getCustomerName()}</td>
                            <td>${order.getOrderTime()}</td>
                            <td>${order.getStatus()}</td>
                            <td><a href="<c:url value='../admin/orders/edit?id=${order.getId()}' />" class="button">Edit Status</a>
                                </td>
                            <td><a href="<c:url value='../admin/orders/delete?id=${order.getId()}' />" class="button">Delete</a>
                                </td>
                        </li>
                    </c:forEach> 
                </tbody>
            </table>

        </main>

        <footer>
            © 2017 Aaron Tang, Inc., All Rights Reserved.
        </footer>
    </body>
</html>
