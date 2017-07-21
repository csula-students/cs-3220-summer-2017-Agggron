<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%-- Import the JSTL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value='app.css' />">
</head>
<body>

<c:out value="Hello JSTL" />

<img src="<c:url value='images/yellow-moon.jpg' />" width=150px height=150px>
<c:choose>
    <c:when test="${day == 'tue'}">
        Tuesday
    </c:when>
    <c:when test="${day == 'wed'}">
        <%-- Find day variable and replace day --%>
        <%-- ${"some" == 'wed'} --%>
        <%-- ${false} --%>
        <%-- false --%>
        Wednesday</c:when>
    <c:when test="${day == 'thr'}">
        Thursday
    </c:when>
    <c:otherwise>Friday</c:otherwise>
</c:choose>

<ul>
    <c:forEach items="${numbers}" var="number">
        <li>${number}</li>
    </c:forEach>
</ul>

<ul>
    <c:forEach items="${numbers}" var="number"
          varStatus="status">
        <li>${status.index}: ${number}</li>
    </c:forEach>
</ul>

Current Date: <fmt:formatDate value="${date}" type="time" />


</body>
</html>
