package servlets;

import dto.StatusForDropdown;
import dto.Todo;
import dto.TodoRenderObject;
import enums.Status;
import org.apache.http.client.utils.URIBuilder;
import service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "servlets.TodoRenderServlet", urlPatterns = {""}, loadOnStartup = 1)
public class TodoRenderServlet extends HttpServlet {

    private TodoService service = new TodoService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        Status statusFromRequestAsEnum =
                createValidStatus(request.getParameter("status"));

        String descriptionOfTodoToFindFromRequest =
                request.getParameter("descriptionSearched");

        String expandedFromRequestAsString = request.getParameter("expanded");

        request.setAttribute("statusList",
                createStatusList(statusFromRequestAsEnum));

        request.setAttribute("todos",
                createTodoList(statusFromRequestAsEnum,
                        descriptionOfTodoToFindFromRequest,
                        request,
                        expandedFromRequestAsString));

        request.setAttribute("query",
                descriptionOfTodoToFindFromRequest);

        try {
            request.getRequestDispatcher("/jsp/todosAsList.jsp").forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List<TodoRenderObject> createTodoList
            (Status status, String description, HttpServletRequest request, String expanded) {
        List<TodoRenderObject> todoRenderObjects = new ArrayList<>();
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

        for (Todo todo : todos) {
            // http://localhost:8080/?status=All&expanded=1
            // http://localhost:8080/?status=Done&search=blah&expanded=1
            todoRenderObjects.add(TodoRenderObjectMapper.map(
                    todo,
                    getFullExpandedURL(request, todo.getId().toString()),
                    expanded != null && Integer.parseInt(expanded) == todo.getId()));
        }
        return todoRenderObjects;
    }



    private static String getFullExpandedURL(HttpServletRequest request, String id) {
        try {
            URIBuilder uriBuilder = new URIBuilder(request.getRequestURI());
            for (Map.Entry<String, String[]> parameter : request.getParameterMap().entrySet()) {
                uriBuilder.setParameter(parameter.getKey(), parameter.getValue()[0]);
            }
            uriBuilder.setParameter("expanded", id);
            return uriBuilder.build().toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return "";
        }
    }

    List<StatusForDropdown> createStatusList(Status status) {
        List<StatusForDropdown> result = new ArrayList<>();
        for (Status statusInEnums : Status.values()) {
            if (statusInEnums == Status.DISCARDED) continue;
            result.add(new StatusForDropdown
                    (statusInEnums, statusInEnums.toString(),
                            checkIfSelected(status, statusInEnums))
            );
        }
        return result;
    }

    boolean checkIfSelected(Status status, Status statusInOptions) {
        return status == statusInOptions;
    }

    Status createValidStatus(String statusFromRequest) {
        if (statusFromRequest == null) {
            return null;
        }
        for (Status status : Status.values()) {
            if (status.toString().equals(statusFromRequest.toUpperCase())) {
                return status;
            }
        }
        return null;
    }
}