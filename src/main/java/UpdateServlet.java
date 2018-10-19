import com.fasterxml.jackson.databind.ObjectMapper;
import dto.TodoUpdateRequest;
import service.TodoService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "UpdateServlet", urlPatterns = {"todos/update"}, loadOnStartup = 1)
public class UpdateServlet extends HttpServlet {
    private TodoService service = new TodoService();
    private static Logger logger = Logger.getLogger(UpdateServlet.class.getName());

    MingiClass mingiClass = new MingiClass();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //loeb body'st info välja stringina
        String json = readJsonStringFromRequest(request);
        //teeb stringist uut Todo ojekti
        TodoUpdateRequest todoUpdateRequest = new ObjectMapper().readValue(json, TodoUpdateRequest.class);

        if(todoUpdateRequest.id == null) {
            logger.warning("Id is missing");
            return;
        }

        if(todoUpdateRequest.description != null) {
            service.updateDescription(todoUpdateRequest.description, todoUpdateRequest.id);
        }
        if (todoUpdateRequest.date != null) {
            service.updateDate(todoUpdateRequest.date, todoUpdateRequest.id);
        }

        System.out.println("Request id: " + todoUpdateRequest.id + " ja description: " + todoUpdateRequest.description);
    }

    private String readJsonStringFromRequest(HttpServletRequest request) {
        final StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            if (reader == null) {
                logger.log(Level.SEVERE, "Request body could not be read because it's empty.");
            }
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (final Exception e) {
            logger.log(Level.SEVERE, "Could not obtain the same request body from the http request", e);
        }
        return builder.toString();
    }

    public String minuMeetod() {
        return mingiClass.teeMidagi();
    }

    public class MingiClass {
        public String teeMidagi() {
            return "onu";
        }

        public void teeMidagiVeel() {

        }
    }
}
