//package src.main.java;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static helper.ReadingAndWritingToFileHelper.initStringsFromFile;

@WebServlet(name = "ToDoServlet", urlPatterns = {"toDos"}, loadOnStartup = 1)
public class ToDoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            List<String> toDosFromFile = initStringsFromFile();
            request.setAttribute("toDosFromFile", toDosFromFile);
            request.getRequestDispatcher("listOfAllTodos.jsp").forward(request, response);
        } catch (IOException e) {
            System.out.println("Error");
        }


//        try {
//            PrintWriter writer = response.getWriter();
//            List<String> toDosFromFile = initStringsFromFile();
//            toDosFromFile.forEach(writer::print);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
