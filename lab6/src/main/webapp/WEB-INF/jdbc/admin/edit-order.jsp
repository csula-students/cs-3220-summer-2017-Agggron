<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%-- Import the JSTL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
            <img src="<c:url value='../../../images/yellow-moon.jpg' />" width=150px height=150px class="header_image">
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
            <h2> Welcome, Innkeeper!</h2>
            
            <h3> Editing Order for: </h3>

            <table class="center edit_order_table">
                <th colspan="3"> ${orderToEdit.getCustomerName()} at ${orderToEdit.getOrderTime()} </th>
                <c:forEach items="${orderToEdit.getItems()}" var="item">
                    <tr>
                        <td> ${item.getQuantity()} x </td>
                        <td> <img src="<c:url value='../../../${item.imgURL}' />" class="small"> </td>
                        <td> ${item.getName()} </td>
                    </tr>
                </c:forEach> 
             </table>
            
            <form method="post">
                Order Status:<br>
                <select name="new_status">
                    <option value="IN QUEUE">In Queue</option>
                    <option value="IN PROGRESS">In Progress</option>
                    <option value="COMPLETED">Completed</option>
                </select>
                <input type="submit" value="Change Order Status!">
            </form>


        </main>

        <footer>
            © 2017 Aaron Tang, Inc., All Rights Reserved.
        </footer>
    </body>
</html>
