<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<html>
    <%@ include file="header.jsp" %>
    <body>
        <div class="container">
            <h2 align="center">TODOS</h2>
            <div class="searchRow">
                <div class="form-group">
                    <input type="text" class="form-control" id="descriptionSearched" placeholder="Search" value="${query}">
                </div>
                <div class="dropdown">
                    <select class="btn btn-secondary dropdown-toggle" type="button" id="selectStatus"  onchange="searchTodos()">
                        <option value="All">ALL</option>
                            <c:forEach items="${statusList}" var="status">
                                <option <c:if test="${status.isSelected()}">selected</c:if>
                                    value="${status.getValue()}">${status.getName()}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="addNew">
                    <a class="btn btn-outline-secondary" href="todos/new" role="button">Insert new todo</a>
                </div>
            </div>
            <div>
                <table class="table table-striped">
                <thead class="table-secondary">
                    <tr>
                        <th scope="col">Todo</th>
                        <th scope="col">Status</th>
                        <th scope="col">Due date</th>
                        <th scope="col">Time left</th>
                        <th scope="col">Mark as done</th>
                        <th scope="col">Delete</th>
                    </tr>
                </thead>
                <c:forEach items="${todos}" var="todo">
                    <tr class="todoRow" data-id="${todo.getId()}">
                        <td>
                        <textarea class="description" rows="2" cols="30">${todo.getDescription()}</textarea>
                        </td>
                        <td>${todo.getStatus()}</td>
                        <td>
                            <input type="date" class="dueDate" name="date" value="${todo.getDueDate()}">
                        </td>
                        <td class="timeLeft"> </td>
                        <td>
                             <c:if test="${todo.isCompletable()}">
                                <button type="button" class="btn btn-primary toDone">Done</button>
                             </c:if>
                        </td>
                        <td>
                            <button type="button" class="btn btn-primary toDelete">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
                </table>
            </div>
        </div class="container">
        <%@ include file="footer.jsp" %>
    </body>
</html>


