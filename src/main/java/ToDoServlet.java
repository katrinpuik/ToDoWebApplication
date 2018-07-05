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

import static java.lang.String.valueOf;

@WebServlet(name = "ToDoServlet", urlPatterns = {"todos"}, loadOnStartup = 1)
public class ToDoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        String statusFromRequest = request.getParameter("status");
        String toDotoDoneFromRequest = request.getParameter("done");
        String toDotoDeleteFromRequest = request.getParameter("delete");

        // muuda todo staatust
        if (toDotoDoneFromRequest != null) {
            ToDo toDotoChangeStatus = ContextListener.service.findById(Integer.parseInt(toDotoDoneFromRequest));
            toDotoChangeStatus.setStatus("DONE");
            try {
                ContextListener.service.save(toDotoChangeStatus);
            } catch (ServiceException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        if (toDotoDeleteFromRequest != null) {
            ContextListener.service.remove(Integer.parseInt(toDotoDeleteFromRequest));
        }



        request.setAttribute("statusList", createStatusList(statusFromRequest));

// joonista todosid

        request.setAttribute("toDos", createTodoList(statusFromRequest));
        try {
            request.getRequestDispatcher("listAll.jsp").forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<ToDo> createTodoList(String status) {
        List<ToDo> toDos;
        if (status != null) {
            toDos = ContextListener.service.findByStatus(Status.valueOf(status));
        } else {
            toDos = ContextListener.service.getAll();
        }
        return toDos;
    }

    private List<StatusForDropdown> createStatusList(String status) {
        List<StatusForDropdown> result = new ArrayList<>();
        result.add(new StatusForDropdown(Status.DONE, "Tehtud", checkIfSelected(status, Status.DONE)));
        result.add(new StatusForDropdown(Status.NOT_DONE, "Tegemata", checkIfSelected(status, Status.NOT_DONE)));
        result.add(new StatusForDropdown(Status.DISCARDED, "Unustatud", checkIfSelected(status, Status.DISCARDED)));
        return result;
    }

    private boolean checkIfSelected(String statusFromRequest, Status status) {
        return statusFromRequest.equals(status.toString());
    }

}
