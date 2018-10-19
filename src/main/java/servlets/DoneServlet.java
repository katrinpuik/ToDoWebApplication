package servlets;

import service.TodoService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Integer.parseInt;

@WebServlet(name = "servlets.DoneServlet", urlPatterns = {"todos/done"}, loadOnStartup = 1)
public class DoneServlet extends HttpServlet {
    private TodoService service = new TodoService();

    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        Integer idOfTodoToDoneFromRequest = parseInt(request.getParameter("id"));
        service.updateStatus(idOfTodoToDoneFromRequest);
    }
}
