<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>List of all todos</title>
    </head>
    <body>











            <h2><c:forEach items="${data}" var="item">
                ${item}<br>
                </c:forEach>
             </h2>
    </body>
</html>