<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<html>
    <%@ include file="header.jsp" %>
    <body>
        <div class="wrapper">

            <nav id="sidebar">
                <div class="userInfo" >
                    <div>
                        <p class="usersPicture"> kasutaja pilt </p>
                    </div>
                    <p>Kasutaja Kasutaja</p>
                </div>


                 <ul class="list-unstyled options">
                        <li class="active">
                            <a href="#"> All my todos </a>
                        </li>
                        <li>
                            <a href="#groupsSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">My groups</a>
                            <ul class="collapse list-unstyled" id="groupsSubmenu">
                                <li>
                                    <a href="#">Group 1</a>
                                </li>
                                <li>
                                    <a href="#">Group 2</a>
                                </li>
                            </ul>
                        </li>
                    </ul>


                <div>
                </div>
            </nav>



            <div class="container">
                <h2 align="center">TODOS</h2>
                <div class="searchRow">
                    <div class="form-group">
                        <input type="text" class="form-control" id="descriptionSearched" placeholder="Search" value="${query}">
                    </div>
                    <div class="dropdown">
                        <select class="btn btn-secondary dropdown-toggle" type="button" id="selectStatus" onchange="searchTodos()">
                            <option value="All">ALL</option>
                                <c:forEach items="${statusList}" var="status">
                                    <option <c:if test="${status.isSelected()}">selected</c:if>
                                        value="${status.getValue()}">${status.getName()}
                                    </option>
                                </c:forEach>
                        </select>
                    </div>
                    <div>
                        <button type="button"
                            class="btn btn-info btn-lg"
                            data-toggle="modal" data-target="#addNewModal">
                            <i class="fa fa-plus-square"> </i>
                        </button>
                    </div>
                </div>
                <div class="cardContainer">
                    <c:forEach items="${todos}" var="todo">
                        <div class="todoCard card horizontal border-dark mb-3"
                                    data-id="${todo.getId()}"
                                    data-status="${todo.getStatus()}"
                                    data-duedate="${todo.getDueDate()}">
                            <div class="statusColor"> </div>
                            <div class="card-body">
                                <p> ${todo.getDescription()} </p>
                                <p class="dateField"> DATE </p>
                                <div class="todoCardOverlay">
                                    <button type="button" class="toDelete btn btn-primary">Delete</button>
                                        <c:if test="${todo.isCompletable()}">
                                            <button type="button" class="toDone btn btn-primary">Done</button>
                                        </c:if>
                                    <button type="button"
                                        class="edit btn btn-info btn-lg"
                                        data-toggle="modal" data-target="#editModal">
                                        <i class="fa fa-edit" aria-hidden="true"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

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
                            </div>
                        </div>
                    </div>
                </div>

                <div id=addNewModal class="modal fade" role="dialog">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title">Add new todo</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
                                <textarea class="descriptionInAddNewModal"></textarea>
                                <input type="date" class="dueDateInAddNewModal" name="date">
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="saveInAddNewModal btn btn-primary">Save</button>
                            </div>
                        </div>
                    </div>
                </div>

            </div class="container">
        </div>

        <%@ include file="footer.jsp" %>
    </body>
</html>
