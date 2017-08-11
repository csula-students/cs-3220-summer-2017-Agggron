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

            <h2>Gamblin' With Dice!</h2>

            <p> Guess what the next d6 roll will be, and earn a point!</p>

            <table class="center roll_dice_table">
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${rolledDiceResult == 'start'}">
                                <img src="<c:url value='../images/dice-game-image.jpg' />" class="center medium">
                            </c:when>
                            <c:otherwise>
                                <img src="<c:url value='../${dice.getImgURL()}' />" class="center medium">
                            </c:otherwise> 
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${rolledDiceResult == 'correct'}">
                                <p> Your guess of ${guess} matched the dice roll of ${dice.getValue()}! You earned a point! </p>
                            </c:when>
                            <c:when test="${rolledDiceResult == 'incorrect'}">
                                <p> Your guess of ${guess} did not match the dice roll of ${dice.getValue()}! Sorry. :( </p>
                            </c:when> 
                        </c:choose>

                        <h3> Current points: ${diceScore} </h3>

                        <form method="POST">
                            <select name="guess">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                                <option value="6">6</option>
                            </select>
                            <input type="submit" name="roll" value="Roll Dice!">
                            <input type="submit" name="reset" value="Reset!">
                        </form>
                    </td>
                </tr>
            </table>

        </main>

        <footer>
            © 2017 Aaron Tang, Inc., All Rights Reserved.
        </footer>
    </body>
</html>
