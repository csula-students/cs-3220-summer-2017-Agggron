<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%-- Import the JSTL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Yellow Moon Inn: Inventory</title>
        <link rel="stylesheet" href="<c:url value='/app.css' />">
        <link href="<c:url value='https://fonts.googleapis.com/css?family=Ceviche+One|Cinzel:900' />" rel="stylesheet"> 
    </head>
    <body>
        <header>
            <img src="<c:url value='../images/yellow-moon.jpg' />" width=150px height=150px class="header_image">
            The Yellow Moon Inn
        </header>

        <nav>
            <a href="<c:url value='/jdbc/menu' />" class="navigation">Buy Liquor</a>
            <a href="<c:url value='/jdbc/shopping-cart' />" class="navigation">Your Tab</a>
            <a href="<c:url value='/jdbc/orders' />" class="navigation">Purchasing History</a>
            <a href="<c:url value='/jdbc/admin/foods' />" class="navigation">The Stockroom</a>
            <a href="<c:url value='/jdbc/admin/foods/create' />" class="navigation">The Brewery</a>
            <a href="<c:url value='/jdbc/admin/orders' />" class="navigation">The Chancery</a>
        </nav>

        <main>
            <h2>Your Tab</h2>

            <p>See what liquors have caught your fancy so far!</p>
            
            <table class="center horizontal shopping_cart_table">
                <thead>
                    <th>Item</th>
                    <th>Quantity</th>
                    <th>Remove?</th>
                </thead>
                <tbody>
                    <c:if test="${cart.size() == 0}">
                        <tr>
                            <td colspan="3"><p>You have put no liquor on your tab!</p></td>
                        </tr>
                    </c:if>
                    <c:forEach items="${cart}" var="item">
                        <tr>
                            <td><p class="title">${item.getName()}</p>
                                <img src="<c:url value='../${item.imgURL}' />" class="small">
                                </td>
                            <td>${item.getQuantity()}</td>
                            <td><a href="<c:url value='shopping-cart/remove?id=${item.getId()}' />" class="button">Remove From Tab!</a>    </td>
                        </tr>
                    </c:forEach> 
                    <c:if test="${cart.size() > 0}">
                        <tr>
                            <td colspan="3"><a href="<c:url value='shopping-cart/submit-order' />" class="button">Order Everything On Tab!</a><br>
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>

        </main>

        <footer>
            © 2017 Aaron Tang, Inc., All Rights Reserved.
        </footer>
    </body>
</html>
