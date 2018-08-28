import dto.StatusForDropdown;
import dto.ToDo;
import enums.Status;
import exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ToDoServlet", urlPatterns = {"todos"}, loadOnStartup = 1)
public class ToDoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        Status statusFromRequestAsEnum = createValidStatus(request.getParameter("status"));
        String descriptionOfToDoToFindFromRequest = request.getParameter("description");

// joonista todosid

        request.setAttribute("statusList", createStatusList(statusFromRequestAsEnum));

        request.setAttribute("toDos", createTodoList(statusFromRequestAsEnum, descriptionOfToDoToFindFromRequest));

        request.setAttribute("query", descriptionOfToDoToFindFromRequest);

        try {
            request.getRequestDispatcher("listAll.jsp").forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idOfToDotoDoneFromRequest = request.getParameter("done");
        if (idOfToDotoDoneFromRequest != null) {
            ToDo toDotoChangeStatus = ContextListener.service.findById(Integer.parseInt(idOfToDotoDoneFromRequest));
            toDotoChangeStatus.setStatus("DONE");
            try {
                ContextListener.service.save(toDotoChangeStatus);
            } catch (ServiceException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String idOfToDotoDeleteFromRequest = request.getParameter("delete");
        if (idOfToDotoDeleteFromRequest != null) {
            ContextListener.service.remove(Integer.parseInt(idOfToDotoDeleteFromRequest));
        }
    }

    private List<ToDo> createTodoList(Status status, String description) {
        List<ToDo> toDos;
        if (status != null && description != null) {
            toDos = ContextListener.service.findByStatusAndDescription(status, description);
        } else if (status != null) {
            toDos = ContextListener.service.findByStatus(status);
        } else if (description != null) {
            toDos = ContextListener.service.findByDescription(description);
        } else {
            toDos = ContextListener.service.getAll();
        }
        return toDos;
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