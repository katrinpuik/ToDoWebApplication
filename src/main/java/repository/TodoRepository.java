package repository;

import dto.Todo;
import enums.Status;
import exception.ServiceException;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TodoRepository {

    private static Logger logger = Logger.getLogger(TodoRepository.class.getName());
    private DatabaseConnection connection = new DatabaseConnection();

    public void saveTodos(Todo todo) throws ServiceException {
        String query = "INSERT INTO todos (description, status) VALUES (?, ?);";

        try (PreparedStatement statement = connection.getConnection().prepareStatement(query)) {
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

        try (PreparedStatement statement = connection.getConnection().prepareStatement(query)) {
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
        try (PreparedStatement statement = connection.getConnection().prepareStatement(query)) {
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
        try (PreparedStatement statement = connection.getConnection().prepareStatement(query)) {
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

    public void remove(Integer id) {
        String query = "DELETE FROM todos WHERE id=?;";
        try (PreparedStatement statement = connection.getConnection().prepareStatement(query)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public List<Todo> findByDescription(String description) {
        List<Todo> todosFoundByDescription = new ArrayList<>();
        String query = "SELECT * FROM todos WHERE description =?;";
        try (PreparedStatement statement = connection.getConnection().prepareStatement(query)) {
            statement.setString(1, description);
            ResultSet results = statement.executeQuery();
            todosFoundByDescription = generateTodosFromDatabaseData(results);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return todosFoundByDescription;
    }

    public List<Todo> findByStatus(Status status) {
        List<Todo> todosFoundByStatus = new ArrayList<>();
        String query = "SELECT * FROM todos WHERE status =?;";
        try (PreparedStatement statement = connection.getConnection().prepareStatement(query)) {
            statement.setString(1, status.name());
            ResultSet results = statement.executeQuery();
            todosFoundByStatus = generateTodosFromDatabaseData(results);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return todosFoundByStatus;
    }

    public List<Todo> findByStatusAndDescription(Status status, String description) {
        List<Todo> todosFoundByStatusAndDescription = new ArrayList<>();
        String query = "SELECT * FROM todos WHERE status = ? AND description = ?;";
        try (PreparedStatement statement = connection.getConnection().prepareStatement(query)) {
            statement.setString(1, status.name());
            statement.setString(2, description);
            ResultSet results = statement.executeQuery();
            todosFoundByStatusAndDescription = generateTodosFromDatabaseData(results);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return todosFoundByStatusAndDescription;
    }

    private List<Todo> generateTodosFromDatabaseData(ResultSet results) throws SQLException {
        List<Todo> todos= new ArrayList<>();
        while (results.next()) {
            todos.add(new Todo(results.getString("description"), Status.valueOf(results.getString("status")), results.getInt("id")));
        }
        return todos;
    }
}