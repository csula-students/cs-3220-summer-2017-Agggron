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
            <a href="<c:url value='../admin/foods' />" class="navigation">The Lobby</a>
            <a href="<c:url value='../admin/foods' />" class="navigation">Buy Items</a>
            <a href="<c:url value='../admin/foods' />" class="navigation">Your Tab</a>
            <a href="<c:url value='../admin/foods' />" class="navigation">The Stockroom</a>
            <a href="<c:url value='../admin/foods/create' />" class="navigation">The Brewery</a>
            <a href="<c:url value='../admin/foods' />" class="navigation">The Chancery</a>
        </nav>

        <main>
            <h2>Welcome to the Yellow Moon Inn!</h2>
            
            <p>"You and your adventuring companions have spent all day slogging through the Dungeon, killing monsters and taking their stuff. Now you're back in town, healed up, cleaned up, and ready to party at the Yellow Moon Inn."</p>
            
            <p> Before your next quest, come check out what the Yellow Moon Inn has to offer! Browse our liquors, or manage what your party has been ordering!</p>
            
            <table class="center horizontal">
                <thead>
                    <th>Item</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Edit?</th>
                    <th>Delete?</th>
                </thead>
                <tbody>
                    <c:forEach items="${inventory}" var="item">
                        <tr>
                            <td><p class="title">${item.getName()}</p>
                                <img src="<c:url value='${item.imgURL}' />" class="small">
                                </td>
                            <td>${item.getDescription()}</td>
                            <td>${item.getPrice()}</td>
                            <td><a href="<c:url value='../admin/foods/edit?id=${item.getId()}' />" class="button">Edit</a>
                                </td>
                            <td><a href="<c:url value='../admin/foods/delete?id=${item.getId()}' />" class="button">Delete</a>
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
