package servlets;

import dto.Todo;
import enums.Status;
import service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "servlets.NewTodoServlet", urlPatterns = {"todos/new"}, loadOnStartup = 1)
public class NewTodoServlet extends HttpServlet {
    TodoService service = new TodoService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/newTodo.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String newDescription = request.getParameter("newTodo");
        if (newDescription != null && !newDescription.equals("")) {
            service.save(new Todo(newDescription, Status.NOT_DONE));
            response.sendRedirect("/");
        } else {
            response.sendRedirect("/todos/new");
        }
    }
}