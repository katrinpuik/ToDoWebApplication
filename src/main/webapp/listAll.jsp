<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>List of all todos</title>
    </head>
    <body>
        <b>All your todos
        <br>
        <table>
            <tr>
                <th>ToDo</th>
                <th>Status</th>
            </tr>
        <c:forEach items="${toDos}" var="toDo">
            <tr>
                <td>${toDo.getDescription()}</td>
                <td>${toDo.getStatus()}</td>
            </tr>
        </c:forEach>
        </table>
    </body>
</html>