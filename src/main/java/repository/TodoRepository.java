package repository;

import dto.Todo;
import enums.Status;
import exception.ServiceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;


public class TodoRepository {

    private List<Todo> todos = new ArrayList<>();
    private AtomicInteger id = new AtomicInteger(1);

    public void saveOrUpdateAndSaveTodos(Todo todo) throws ServiceException {

        String url = "jdbc:mysql://localhost:3306/todos";
        String user = "todouser";
        String password = "todopass";

        String query = "INSERT INTO todos (description, status) VALUES (?, ?);";

        try (PreparedStatement statement = DriverManager.getConnection(url, user, password).prepareStatement(query)) {
            statement.setString(1, todo.getDescription());
            statement.setString(2, valueOf(todo.getStatus()));
            statement.executeUpdate();
        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(TodoRepository.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }


//        if (todo.getId() != null) {
//            if (findById(todo.getId()) == null) {
//                throw new ServiceException("No todo with such id");
//            } else remove(todo.getId());
//        } else {
//            todo.setId(id.getAndIncrement());
//        }
//        todos.add(todo);
    }

    public Todo findById(Integer id) {
        return todos.stream()
                .filter(toDo -> Objects.equals(toDo.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public List<Todo> getAll() {
        return todos;
    }

    public void remove(String description) {
        todos.removeIf(todo -> areEqual(description, todo.getDescription()));
    }

    public void remove(Integer id) {
        todos.removeIf(todo -> Objects.equals(id, todo.getId()));
    }

    public List<Todo> findByDescription(String description) {
        return todos.stream()
                .filter(todo -> areEqual(description, todo.getDescription()))
                .collect(Collectors.toList());
    }

    public List<Todo> findByStatus(Status status) {
        return todos.stream()
                .filter(todo -> areEqual(status, todo.getStatus()))
                .collect(Collectors.toList());
    }

    public List<Todo> findByStatusAndDescription(Status status, String description) {
        return findByStatus(status).stream()
                .filter(todo -> areEqual(description, todo.getDescription()))
                .collect(Collectors.toList());
    }

    private boolean areEqual(Status status1, Status status2) {
        return status2 == null && status1 == null
                || status1 != null && status2 != null
                && status2.equals(status1);
    }

    private boolean areEqual(String value1, String value2) {
        return value1 == null && value2 == null
                || (value1 != null && value2 != null
                && haveSameLowerCaseValue(value1, value2));
    }

    private boolean haveSameLowerCaseValue(String value1, String value2) {
        return value2.toLowerCase().contains(value1.toLowerCase());
    }
}