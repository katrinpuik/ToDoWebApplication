<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

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
            <div class="row">
                <div class="form-group">
                    <label for="description">Search:</label>
                    <input type="text" class="form-control, col-xs-6" id="description" value="${query}">
                </div>
                <div class="form-group">
                    <select class="form-control, col-xs-2" id="selectStatus">
                        <option value="All">ALL</option>
                        <c:forEach items="${statusList}" var="status">
                            <option <c:if test="${status.isSelected()}">selected</c:if> value="${status.getValue()}">${status.getName()}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <button class="btn" onclick="searchTodos()"><i class="fa fa-search"></i></button>
                </div>
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
                        <td>
                            <a class="btn btn-outline-primary float-left" value=".float-left" href="todos?delete=${toDo.getId()}" role="button">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </table>
            </div>
        </div class="container">
        <%@ include file="footer.jsp" %>
    </body>
</html>
