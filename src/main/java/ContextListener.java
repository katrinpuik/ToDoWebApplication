import dto.ToDo;
import exception.ServiceException;
import service.ToDoMapper;
import service.ToDoService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileNotFoundException;
import java.util.List;

import static helper.ReadingAndWritingToFileHelper.initStringsFromFile;

@WebListener
public class ContextListener implements ServletContextListener {

    private ToDoMapper mapper = new ToDoMapper();

    public static ToDoService service = new ToDoService();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            List<ToDo> toDos = mapper.deserialize(initStringsFromFile());
            toDos.forEach(toDo -> {
                try {
                    service.save(toDo);
                } catch (ServiceException e) {
                    throw new RuntimeException(e.getMessage());
                }
            });
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Shutting down!");
    }
}