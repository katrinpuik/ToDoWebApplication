<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>`


<html>
    <%@ include file="header.jsp" %>
    <body>
        <div class="container">
        <br>
        <br>
        <br>
            <div>
                <a class="btn btn-outline-primary float-right" value=".float-right" href="todos/new" role="button">Insert new todo</a>
            </div>
            <div>
                <h2 align="center">All your todos</h2>
                <br>
                <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Delete</th>
                        <th scope="col">Todo</th>
                        <th scope="col">Status</th>
                        <th scope="col">Mark status as done</th>
                    </tr>
                </thead>
                <c:forEach items="${toDos}" var="toDo">
                    <tr>
                        <td><a class="btn btn-outline-primary float-left" value=".float-left" href="todos?delete=${toDo.getId()}" role="button">Delete</a></td>
                        <td>${toDo.getDescription()}</td>
                        <td>${toDo.getStatus()}</td>
                        <td>
                            <div>
                                <c:if test="${toDo.isCompletable()}">
                                <a class="btn btn-outline-primary center" value=".center" href="todos?done=${toDo.getId()}" role="button">Done</a>
                                </c:if>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </table>
            </div>
        </div class="container">
        <%@ include file="footer.jsp" %>
    </body>
</html>
