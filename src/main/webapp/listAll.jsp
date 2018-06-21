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
        <c:forEach items="${toDos}" var="item">
            <tr>
                <td>${item}</td>
            </tr>
        </c:forEach>
        </table>
    </body>
</html>