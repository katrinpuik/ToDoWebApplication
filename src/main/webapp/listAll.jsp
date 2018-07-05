<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
    <%@ include file="header.jsp" %>
    <body>
        <div class="container">
        <br>
        <br>
        <br>
            <h2 align="center">Everything you have to do</h2>
            <br>
            <br>
            <div>
                <a class="btn btn-outline-primary float-right" value=".float-right" href="todos/new" role="button">Insert new todo</a>
            </div>
            <div class="form-group">
                <label for="selectStatus">Find todo by status</label>
                <select class="form-control, col-xs-2" id="selectStatus" onchange="changeStatus()">
                    <option value="ALL TODOS">ALL TODOS</option>
                    <option value="NOT_DONE">NOT DONE</option>
                    <option value="DONE">DONE</option>
                    <option value="DISCARDED">DISCARDED</option>
                </select>
            </div>
            <div>
                <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Todo</th>
                        <th scope="col">Status</th>
                        <th scope="col">Mark status as done</th>
                        <th scope="col">Delete</th>
                    </tr>
                </thead>
                <c:forEach items="${toDos}" var="toDo">
                    <tr>
                        <td>${toDo.getDescription()}</td>
                        <td>${toDo.getStatus()}</td>
                        <td>
                            <div>
                                <c:if test="${toDo.isCompletable()}">
                                <a class="btn btn-outline-primary center" value=".center" href="todos?done=${toDo.getId()}" role="button">Done</a>
                                </c:if>
                            </div>
                        </td>
                        <td><a class="btn btn-outline-primary float-left" value=".float-left" href="todos?delete=${toDo.getId()}" role="button">Delete</a></td>
                    </tr>
                </c:forEach>
                </table>
            </div>
        </div class="container">
        <%@ include file="footer.jsp" %>
    </body>
</html>
