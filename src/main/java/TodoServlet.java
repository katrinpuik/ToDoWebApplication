import dto.StatusForDropdown;
import dto.Todo;
import enums.Status;
import service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@WebServlet(name = "TodoServlet", urlPatterns = {"todos"}, loadOnStartup = 1)
public class TodoServlet extends HttpServlet {

    private TodoService service = new TodoService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        Status statusFromRequestAsEnum = createValidStatus(request.getParameter("status"));
        String descriptionOfTodoToFindFromRequest = request.getParameter("description");

        request.setAttribute("statusList", createStatusList(statusFromRequestAsEnum));

        request.setAttribute("todos", createTodoList(statusFromRequestAsEnum, descriptionOfTodoToFindFromRequest));

        request.setAttribute("query", descriptionOfTodoToFindFromRequest);

        try {
            request.getRequestDispatcher("listAll.jsp").forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        Integer id = parseInt(request.getParameter("id"));
        String date = request.getParameter("date");
            service.updateDate(date, id);
    }

    private List<Todo> createTodoList(Status status, String description) {
        List<Todo> todos;
        if (status != null && description != null) {
            todos = service.findByStatusAndDescription(status, description);
        } else if (status != null) {
            todos = service.findByStatus(status);
        } else if (description != null) {
            todos = service.findByDescription(description);
        } else {
            todos = service.getAll();
        }
        return todos;
    }

    private List<StatusForDropdown> createStatusList(Status status) {
        List<StatusForDropdown> result = new ArrayList<>();
        for (Status statusInEnums : Status.values()) {
            result.add(new StatusForDropdown(statusInEnums, statusInEnums.toString(), checkIfSelected(status, statusInEnums)));
        }
        return result;
    }

    private boolean checkIfSelected(Status status, Status statusInOptions) {
        return status == statusInOptions;
    }

    private Status createValidStatus(String statusFromRequest) {
        for (Status status : Status.values()) {
            if (status.toString().equals(statusFromRequest)) {
                return Status.valueOf(statusFromRequest);
            }
        }
        return null;
    }
}