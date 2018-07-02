<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <%@ include file="header.jsp" %>
    <body>
        <div class="container">
        <br>
        <br>
        <br>
            <div>
                <h2 align="center">All your not done todos</h2>
                <br>
                <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Todo</th>
                        <th scope="col">Mark status as done</th>
                    </tr>
                </thead>
                <c:forEach items="${toDos}" var="toDo">
                    <tr>
                        <td>${toDo.getDescription()}</td>
                        <td>
                            <form method="post">
                                <button type="submit">DONE</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </table>
            </div>
        </div class="container">
        <%@ include file="footer.jsp" %>
    </body>
</html>
