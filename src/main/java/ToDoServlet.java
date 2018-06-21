//package src.main.java;

import dto.ToDo;
import service.ToDoMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static helper.ReadingAndWritingToFileHelper.initStringsFromFile;

@WebServlet(name = "ToDoServlet", urlPatterns = {"toDos"}, loadOnStartup = 1)
public class ToDoServlet extends HttpServlet {
   private ToDoMapper mapper = new ToDoMapper();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            List<ToDo> toDos = mapper.deserialize(initStringsFromFile());
            request.setAttribute("toDos", toDos);
            request.getRequestDispatcher("listAll.jsp").forward(request, response);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
