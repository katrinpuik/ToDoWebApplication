package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.TodoUpdateRequest;
import service.TodoService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(name = "servlets.UpdateServlet", urlPatterns = {"/update"}, loadOnStartup = 1)
public class UpdateServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(UpdateServlet.class.getName());

    private TodoService service = new TodoService();
    private ServletBodyReader reader = new ServletBodyReader();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //loeb body'st info välja stringina
        String json = reader.readJsonStringFromRequest(request);

        //teeb stringist uue Todo ojekti
        TodoUpdateRequest todoUpdateRequest = new ObjectMapper().readValue(json, TodoUpdateRequest.class);

        if (todoUpdateRequest.getId() == null) {
            logger.warning("Id is missing");
            return;
        }

        if (todoUpdateRequest.getDescription() != null) {
            service.updateDescription(todoUpdateRequest.getDescription(), todoUpdateRequest.getId());
        }

        if (todoUpdateRequest.getDueDate() != null) {
            service.updateDueDate(todoUpdateRequest.getDueDate(), todoUpdateRequest.getId());
        }

        if (todoUpdateRequest.getTitle() !=null) {
            service.updateTitle(todoUpdateRequest.getTitle(), todoUpdateRequest.getId());
        }

    }
}
