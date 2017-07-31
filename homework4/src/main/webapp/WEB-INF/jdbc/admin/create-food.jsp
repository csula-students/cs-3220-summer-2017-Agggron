<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%-- Import the JSTL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Yellow Moon Inn: Create Food</title>
        <link rel="stylesheet" href="<c:url value='../../../app.css' />">
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
            
            <p class="italics"> All of these wandering adventurers have slogged through dungeons, looted monsters, and valiantly completed their quests. They want a long day of rest, and it's here, at the Yellow Moon Inn, where they are now ready to party!</p>

            <h3> Brew House Liquor Here </h3>
            
            
            <form method="post">
                Liquor Name:<br>
                <input type="text" name="name" value="Healing Salve"><br>
                Liquor Image URL:<br>
                <input type="text" name="imgURL" value="images/potion6.png"><br>
                Liquor Description:<br>
                <input type="text" name="description" value="Useful for recovering a hearty dose of HP!"><br>
                Liquor Price:<br>
                <input type="number" name="price" value="2.0"><br><br>
                <input type="submit" value="Add Brew to Inventory!">
            </form>

        </main>

        <footer>
            © 2017 Aaron Tang, Inc., All Rights Reserved.
        </footer>
    </body>
</html>
