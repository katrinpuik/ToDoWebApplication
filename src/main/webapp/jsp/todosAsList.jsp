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
                            <c:choose>

                                <c:when test="${todo.isExpanded()}">
                                    <div class="card-bodyEditView">
                                        <div class="cardBodyEditViewMainRow">
                                            <input class="form-control form-control-sm titleInEditView" type="text" value ="${todo.getTitle()}">
                                            <c:choose>
                                                <c:when test="${todo.isDone()}">
                                                    <p class="dateField"> Date done: ${todo.getDoneDate()}</p>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="date" class="dateField" name="date" value="${todo.getDueDate()}">
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="cardBodyEditViewDescriptionArea">
                                            <textarea class="form-control descriptionInEditView" rows="5" type="text" placeholder="Description">${todo.getDescription()}</textarea>
                                        </div>
                                        <div class="cardBodyEditViewBottomRow">
                                            <input class="form-control form-control-sm labels" type="text" placeholder="Add labels">
                                            <p class="created">Created: ${todo.getCreationDate()} </p>
                                            <p class="modified"> date </p>
                                        </div>
                                        <div class="closeButtonArea">

                                        <a class="btn btn-primary closeExpandedAndSaveData" href="${todo.getSimpleUrl()}" role="button">Close</a>
                                        </div>
                                    </div>
                                </c:when>

                                <c:otherwise>
                                    <a href="${todo.getExpandedUrl()}">
                                        <div class="card-body" >
                                            <div class="cardBodyMainRow">
                                                <p class="title"> ${todo.getTitle()} </p>
                                                <c:choose>
                                                    <c:when test="${todo.isDone()}">
                                                        <p class="doneDate">Date done: ${todo.getDoneDate()}</p>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <p class="dateField"></p>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                            <p class="mainViewLabels"> labels </p>
                                            <div class="todoCardOverlay">
                                                <button type="button" class="toDelete btn btn-primary">Delete</button>
                                                <c:if test="${todo.isCompletable()}">
                                                    <button type="button" class="toDone btn btn-primary" onclick="event.stopPropagation()">Done</button>
                                                </c:if>
                                            </div>
                                        </div>
                                    </a>
                                </c:otherwise>

                            </c:choose>

                        </div>
                    </c:forEach>
                </div>

                <div id="addNewModal" class="modal fade" role="dialog">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content modal-lg">
                            <div class="modal-header">
                                <h4 class="modal-title">Add new todo</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
                                <div class="titleAndDueDateAreaInAddNewModal">
                                    <input class="form-control form-control-sm titleInAddNewModal" type="text" placeholder="Title">
                                    <p id="errorMessage" hidden> Add title!</p>
                                    <input type="date" class="dueDateInAddNewModal" name="date">
                                </div>
                                <textarea class="form-control descriptionInAddNewModal" rows="5" type="text" placeholder="Description"></textarea>
                                <input class="form-control form-control-sm labelsInAddNewModal" type="text" placeholder="Labels">
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

