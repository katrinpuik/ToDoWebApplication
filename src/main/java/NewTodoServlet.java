import dto.Todo;
import exception.ServiceException;
import service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "NewTodoServlet", urlPatterns = {"todos/new"}, loadOnStartup = 1)
public class NewTodoServlet extends HttpServlet {
    TodoService service = new TodoService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/newTodo.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newDescription = request.getParameter("newTodo");
        if (newDescription != null && !newDescription.equals("")) {
            try {
                service.save(new Todo(newDescription));
            } catch (ServiceException e) {
                throw new RuntimeException(e.getMessage());
            }
            response.sendRedirect("/todos");
        } else {
            response.sendRedirect("/todos/new");
        }
    }
}