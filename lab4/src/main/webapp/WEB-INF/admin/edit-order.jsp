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
        <title>Yellow Moon Inn: Edit Order</title>
        <link rel="stylesheet" href="<c:url value='/app.css' />">
        <link href="<c:url value='https://fonts.googleapis.com/css?family=Ceviche+One|Cinzel:900' />" rel="stylesheet"> 
    </head>
    <body>

        <cs3220:header title="Edit Order" />

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
            
            <p>Editing Order for:</p>

            <p>${orderToEdit.getCustomerName()} at ${orderToEdit.getOrderTime()}</p>
            
            <form method="post">
                Order Status:<br>
                <select name="new_status">
                    <option value="IN_QUEUE">In Queue</option>
                    <option value="IN_PROGRESS">In Progress</option>
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
