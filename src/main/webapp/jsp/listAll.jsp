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
                <table class="todoListContainer table table-striped">
                    <thead class="table-secondary">
                        <tr>
                            <th scope="col">Todo</th>
                            <th scope="col">Status</th>
                            <th scope="col">Due date</th>
                            <th scope="col">Time left</th>
                        </tr>
                    </thead>
                    <tbody class="awesome table">
                        <c:forEach items="${todos}" var="todo">
                            <tr class="todoRow" data-id="${todo.getId()}">
                                <td class="descriptionArea"> ${todo.getDescription()} </td>
                                <td>${todo.getStatus()}</td>
                                <td class ="dueDate">${todo.getDueDate()}</td>
                                <td class="timeLeft"> </td>
                                <td class="todoRowOverlay">
                                    <button type="button" class="toDelete btn btn-primary">Delete</button>
                                    <c:if test="${todo.isCompletable()}">
                                        <button type="button" class="toDone btn btn-primary">Done</button>
                                    </c:if>
                                    <button type="button"
                                        class="edit btn btn-info btn-lg"
                                        data-toggle="modal" data-target="#editModal">
                                        <i class="fa fa-edit" aria-hidden="true"></i>
                                    </button>
                                </td>
                            </tr>
                       </c:forEach>
                    </tbody>
                </table>

                <div id="editModal" class="modal fade" role="dialog">
                  <div class="modal-dialog">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h4 class="modal-title">Edit your todo</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                      </div>
                      <div class="modal-body">
                        <textarea class="descriptionInModal"></textarea>
                        <p class="statusInModal"></p>
                        <input type="date" class="dueDateInModal" name="date">
                      </div>
                      <div class="modal-footer">
                        <button type="button" class="saveInModal btn btn-primary">Save</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                      </div>
                    </div>

                  </div>
                </div>


            </div>





        </div class="container">
        <%@ include file="footer.jsp" %>
    </body>
</html>


