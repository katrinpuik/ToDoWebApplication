import service.TodoService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Integer.parseInt;

@WebServlet(name = "DeleteServlet", urlPatterns = {"todos/delete"}, loadOnStartup = 1)
public class DeleteServlet extends HttpServlet {
    private TodoService service = new TodoService();

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        Integer idOfTodoToDeleteFromRequest = parseInt(request.getParameter("id"));
        service.remove(idOfTodoToDeleteFromRequest);
    }
}

