import dto.ToDo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ToDoServlet", urlPatterns = {"toDos"}, loadOnStartup = 1)
public class ToDoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            List<ToDo> toDos = ContextListener.service.getAll();
            request.setAttribute("toDos", toDos);
            request.getRequestDispatcher("listAll.jsp").forward(request, response);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        if (name == null) name = "World";
        request.setAttribute("user", name);
        request.getRequestDispatcher("response.jsp").forward(request, response);


    }

}
