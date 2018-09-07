import dto.Todo;
import exception.ServiceException;
import service.TodoMapper;
import service.TodoService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileNotFoundException;
import java.util.List;

import static helper.ReadingAndWritingToFileHelper.initStringsFromFile;

@WebListener
public class ContextListener implements ServletContextListener {

    private TodoMapper mapper = new TodoMapper();

    public static TodoService service = new TodoService();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            List<Todo> todos = mapper.deserialize(initStringsFromFile());
            todos.forEach(toDo -> {
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