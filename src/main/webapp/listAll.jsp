<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <%@ include file="header.jsp" %>
    <body>
        <div class="container">
            <br>
            <h2 align="center">All your todos</h2>
            <br>
            <table class="table">
              <thead>
                <tr>
                    <th scope="col">Todo</th>
                    <th scope="col">Status</th>
                </tr>
              </thead>
            <c:forEach items="${toDos}" var="toDo">
                <tr>
                    <td>${toDo.getDescription()}</td>
                    <td>${toDo.getStatus()}</td>
                </tr>
            </c:forEach>
            </table>
        <div class="container">
        <%@ include file="footer.jsp" %>
    </body>
</html>
