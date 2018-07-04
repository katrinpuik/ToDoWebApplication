import dto.ToDo;
import enums.Status;
import exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ToDoServlet", urlPatterns = {"todos"}, loadOnStartup = 1)
public class ToDoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            List<ToDo> toDos;
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

            // joonista todosid
            if (statusFromRequest != null) {
                toDos = ContextListener.service.findByStatus(Status.valueOf(statusFromRequest));
                request.setAttribute("toDos", toDos);
                request.getRequestDispatcher("listNotDone.jsp").forward(request, response);
            }
            else {
                toDos = ContextListener.service.getAll();
                request.setAttribute("toDos", toDos);
                request.getRequestDispatcher("listAll.jsp").forward(request, response);
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
