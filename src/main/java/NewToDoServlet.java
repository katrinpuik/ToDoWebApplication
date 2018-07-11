import dto.ToDo;
import exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "NewToDoServlet", urlPatterns = {"todos/new"}, loadOnStartup = 1)
public class NewToDoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/newToDo.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newDescription = request.getParameter("newTodo");
        if (newDescription != null && !newDescription.equals("")) {
            try {
                ContextListener.service.save(new ToDo(newDescription));
            } catch (ServiceException e) {
                throw new RuntimeException(e.getMessage());
            }
            response.sendRedirect("/todos");
        }
    }
}