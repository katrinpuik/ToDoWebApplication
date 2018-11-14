package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.Todo;
import service.TodoService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(name = "servlets.TodoServlet", urlPatterns = {"todos/todo"}, loadOnStartup = 1)
public class TodoServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(UpdateServlet.class.getName());

    private TodoService service = new TodoService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer idOfTodoToEdit = Integer.parseInt(request.getParameter("id"));

        Todo todoToEdit = service.findById(idOfTodoToEdit);

        // ObjectMapperit kasutades teha objektist json string
        String todoToEditAsString = new ObjectMapper().writeValueAsString(todoToEdit);

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(todoToEditAsString);
        response.getWriter().flush();
        response.getWriter().close();


    }
}
