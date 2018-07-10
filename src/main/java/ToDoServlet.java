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

import static enums.Status.*;

@WebServlet(name = "ToDoServlet", urlPatterns = {"todos"}, loadOnStartup = 1)
public class ToDoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        String statusFromRequestAsString = request.getParameter("status");
        Status statusFromRequestAsEnum = createValidStatus(statusFromRequestAsString);

        String idOfToDotoDoneFromRequest = request.getParameter("done");
        String idOfToDotoDeleteFromRequest = request.getParameter("delete");
        String descriptionOfToDoToFindFromRequest = request.getParameter("search");

        // muuda todo staatust
        if (idOfToDotoDoneFromRequest != null) {
            ToDo toDotoChangeStatus = ContextListener.service.findById(Integer.parseInt(idOfToDotoDoneFromRequest));
            toDotoChangeStatus.setStatus("DONE");
            try {
                ContextListener.service.save(toDotoChangeStatus);
            } catch (ServiceException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        if (idOfToDotoDeleteFromRequest != null) {
            ContextListener.service.remove(Integer.parseInt(idOfToDotoDeleteFromRequest));
        }



// joonista todosid

        // kui ei ole sobiv staatus sisestatud, siis n'ita k]iki todosid

        request.setAttribute("statusList", createStatusList(statusFromRequestAsEnum));

        request.setAttribute("toDos", createTodoList(statusFromRequestAsEnum, descriptionOfToDoToFindFromRequest));

        try {
            request.getRequestDispatcher("listAll.jsp").forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<ToDo> createTodoList(Status status, String description) {
        List<ToDo> toDos;
        if (status !=null && description !=null){
            toDos = ContextListener.service.findByStatusAndDescription(status,description);
        }
        else if (status != null) {
            toDos = ContextListener.service.findByStatus(status);
        }
        else if (description != null) {
            toDos = ContextListener.service.findByDescription(description);
        } else {
            toDos = ContextListener.service.getAll();
        }
        return toDos;
    }


    private List<StatusForDropdown> createStatusList(Status status) {
        List<StatusForDropdown> result = new ArrayList<>();
        result.add(new StatusForDropdown(DONE, "Tehtud", checkIfSelected(status, DONE)));
        result.add(new StatusForDropdown(NOT_DONE, "Tegemata", checkIfSelected(status, NOT_DONE)));
        result.add(new StatusForDropdown(DISCARDED, "Polegi vaja teha", checkIfSelected(status, DISCARDED)));
        return result;
    }

    private boolean checkIfSelected(Status status, Status statusInOptions) {
        return status == statusInOptions;
    }

//vaja kontrollida
    private Status createValidStatus(String statusFromRequest) {
        for (Status status : values()) {
            if (status.toString().equals(statusFromRequest)) {
                return valueOf(statusFromRequest);
            }
        }
        return null;
    }
}