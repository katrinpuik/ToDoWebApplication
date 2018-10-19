package servlets;

import com.mysql.jdbc.StringUtils;
import service.TodoService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

@WebServlet(name = "servlets.DeleteServlet", urlPatterns = {"todos/delete"}, loadOnStartup = 1)
public class DeleteServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(UpdateServlet.class.getName());

    private TodoService service = new TodoService();

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        if (StringUtils.isNullOrEmpty(id)){
            logger.warning("Id is missing");
        } else {
            service.remove(parseInt(id));
        }
    }
}

