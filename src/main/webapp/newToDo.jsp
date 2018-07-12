<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <%@ include file="header.jsp" %>
    <body>
        <div class="container">
            <br>
            <br>
            <br>
            <form id="submitNewTodo" method="post" action="/todos/new">
                Insert new todo:<br>
                <input type="text" id="newTodo" name="newTodo"></input>
                <br>
                    <div id="errorMessage" hidden style="color: red">Description is required</div>
                <button type="button" id="submitButton" onclick="checkIfDescriptionIsNotEmpty()">Submit</button>
            </form>
        </div>
        <%@ include file="footer.jsp" %>
    </body>
</html>


