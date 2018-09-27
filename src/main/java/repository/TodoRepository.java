package repository;

import dto.Todo;
import enums.Status;
import exception.ServiceException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TodoRepository {

    private static Logger logger = Logger.getLogger(TodoRepository.class.getName());
    private Database database = new Database();

    public void saveTodos(Todo todo) throws ServiceException {
        String query = "INSERT INTO todos (description, status) VALUES (?, ?);";

        try (PreparedStatement statement = database.getConnection().prepareStatement(query)) {
            statement.setString(1, todo.getDescription());
            statement.setString(2, todo.getStatus().name());
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new ServiceException("Unable to save todo");
        }
    }

    public void updateStatusToDone(Integer id) throws ServiceException {
        String query = "UPDATE todos SET status = ? WHERE id=?;";

        try (PreparedStatement statement = database.getConnection().prepareStatement(query)) {
            statement.setString(1, Status.DONE.name());
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new ServiceException("Unable to update todo");
        }
    }

    public List<Todo> getAll() throws ServiceException {
        String query = "SELECT * FROM todos;";
        try (PreparedStatement statement = database.getConnection().prepareStatement(query)) {
            ResultSet results = statement.executeQuery();
            return generateTodosFromDatabaseData(results);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new ServiceException("Unable to get todos");
        }
    }

    public Todo findById(Integer id) throws ServiceException {
        String query = "SELECT * FROM todos WHERE id=?;";
        try (PreparedStatement statement = database.getConnection().prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet results = statement.executeQuery();
            return generateTodosFromDatabaseData(results).get(0);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new ServiceException("Unable to find  todo");
        }
    }

    public void remove(Integer id) throws ServiceException {
        String query = "DELETE FROM todos WHERE id=?;";
        try (PreparedStatement statement = database.getConnection().prepareStatement(query)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new ServiceException("Unable to remove todo");
        }
    }

    public List<Todo> findByDescription(String description) throws ServiceException {
        String query = "SELECT * FROM todos WHERE description =?;";
        try (PreparedStatement statement = database.getConnection().prepareStatement(query)) {
            statement.setString(1, description);
            ResultSet results = statement.executeQuery();
            return generateTodosFromDatabaseData(results);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new ServiceException("Unable to find todo");
        }
    }

    public List<Todo> findByStatus(Status status) throws ServiceException {
        String query = "SELECT * FROM todos WHERE status =?;";
        try (PreparedStatement statement = database.getConnection().prepareStatement(query)) {
            statement.setString(1, status.name());
            ResultSet results = statement.executeQuery();
            return generateTodosFromDatabaseData(results);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new ServiceException("Unable to find todo");
        }
    }

    public List<Todo> findByStatusAndDescription(Status status, String description) throws ServiceException {
        String query = "SELECT * FROM todos WHERE status = ? AND description = ?;";
        try (PreparedStatement statement = database.getConnection().prepareStatement(query)) {
            statement.setString(1, status.name());
            statement.setString(2, description);
            ResultSet results = statement.executeQuery();
            return generateTodosFromDatabaseData(results);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new ServiceException("Unable to find todo");
        }
    }

    private List<Todo> generateTodosFromDatabaseData(ResultSet results) throws SQLException {
        List<Todo> todos = new ArrayList<>();
        while (results.next()) {
            todos.add(createTodoFromResult(results));
        }
        return todos;
    }

    Todo createTodoFromResult(ResultSet results) throws SQLException {
        return new Todo(results.getString("description"), Status.valueOf(results.getString("status")), results.getInt("id"));
    }
}