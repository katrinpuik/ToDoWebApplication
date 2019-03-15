package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.NewTodoRequest;
import service.TodoService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Logger;


@WebServlet(name = "servlets.NewTodoServlet", urlPatterns = {"/new"}, loadOnStartup = 1)
public class NewTodoServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(UpdateServlet.class.getName());
    private TodoService service = new TodoService();
    private ServletBodyReader reader = new ServletBodyReader();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = reader.readJsonStringFromRequest(request);
        NewTodoRequest newTodoRequest = new ObjectMapper().readValue(json, NewTodoRequest.class);

        if (newTodoRequest.getDescription() != null && !newTodoRequest.getDescription().equals("")) {
            LocalDate dateNow = java.time.LocalDate.now();
            service.addNew(newTodoRequest.getTitle(), newTodoRequest.getDescription(), newTodoRequest.getDueDate(), dateNow.toString());
        } else {
            logger.warning("New todo's description is missing");
        }
    }
}