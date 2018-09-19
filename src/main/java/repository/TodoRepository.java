package repository;

import dto.Todo;
import enums.Status;
import exception.ServiceException;

import javax.sql.rowset.serial.SerialException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;


public class TodoRepository {

    private List<Todo> todos = new ArrayList<>();
    private String url = "jdbc:mysql://localhost:3306/todos";
    private String user = "todouser";
    private String password = "todopass";
    private static Logger logger = Logger.getLogger(TodoRepository.class.getName());

    public void saveTodos(Todo todo) throws ServiceException {
        String query = "INSERT INTO todos (description, status) VALUES (?, ?);";

        try (PreparedStatement statement = DriverManager.getConnection(url, user, password).prepareStatement(query)) {
            statement.setString(1, todo.getDescription());
            statement.setString(2, todo.getStatus().name());
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new ServiceException("Unable to save todo");
        }
    }

    public void updateStatus (Todo todo) throws ServiceException {
        String query = "UPDATE todos SET status = ? WHERE id=?;";

        try (PreparedStatement statement = DriverManager.getConnection(url, user, password).prepareStatement(query)) {
            statement.setString(1, Status.DONE.name());
            statement.setInt(2, todo.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new ServiceException("Unable to update todo");
        }
    }

    public List<Todo> getAll() {
        List<Todo> allTodos = new ArrayList<>();
        String query = "SELECT * FROM todos;";
        try (PreparedStatement statement = DriverManager.getConnection(url, user, password).prepareStatement(query)) {
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                Todo todo = new Todo(results.getString("description"), Status.valueOf(results.getString("status")), results.getInt("id"));
                allTodos.add(todo);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return allTodos;
    }

    public Todo findById(Integer id) {
        String query = "SELECT * FROM todos WHERE id=?;";
        try (PreparedStatement statement = DriverManager.getConnection(url, user, password).prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                return new Todo(results.getString("description"), Status.valueOf(results.getString("status")), results.getInt("id"));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }

    public void remove(String description) {
        todos.removeIf(todo -> areEqual(description, todo.getDescription()));
    }

    public void remove(Integer id) {
        String query = "DELETE FROM todos WHERE id=?;";
        try (PreparedStatement statement = DriverManager.getConnection(url, user, password).prepareStatement(query)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
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