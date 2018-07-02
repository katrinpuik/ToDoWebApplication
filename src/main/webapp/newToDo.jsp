<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <%@ include file="header.jsp" %>
<body>
<div class="container">
<br>
<br>
<br>
    <form method="post" action="/todos/new">
        Insert new todo:<br>
        <input type="text" name="newTodo"><br>
        <input type="submit" value="Submit">
    </form>
</div class="container">
<%@ include file="footer.jsp" %>
</body>
</html>