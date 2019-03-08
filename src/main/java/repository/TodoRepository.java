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

    public void addNew(String newDescription, String date) throws ServiceException {
        String query = "INSERT INTO todos (description, status, dueDate) VALUES(?,?,?);";

        try (PreparedStatement statement = database.getConnection().prepareStatement(query)) {
            statement.setString(1, newDescription);
            statement.setString(2, Status.NOT_DONE.name());
            statement.setString(3, date);
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new ServiceException("Unable to save new todo");
        }
    }

    public void updateStatusToDone(Integer id, String doneDate) throws ServiceException {
        String query = "UPDATE todos SET status = ?, doneDate = ? WHERE id=?;";

        try (PreparedStatement statement = database.getConnection().prepareStatement(query)) {
            statement.setString(1, Status.DONE.name());
            statement.setString(2, doneDate);
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new ServiceException("Unable to update status to done");
        }
    }

    public void updateDueDate(String date, Integer id) throws ServiceException {
        String query = "UPDATE todos SET dueDate = ? WHERE id=?";

        try (PreparedStatement statement = database.getConnection().prepareStatement(query)) {
            statement.setString(1, date);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new ServiceException("Unable to update date");
        }
    }

    public void updateTitle(String newTitle, Integer id) throws ServiceException{
        String query = "UPDATE todos SET title = ? WHERE id = ?";

        try(PreparedStatement statement = database.getConnection().prepareStatement(query)) {
            statement.setString(1, newTitle);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new ServiceException("Unable to update title");        }
    }


    public void updateDescription(String newDescription, Integer id) throws ServiceException{
        String query = "UPDATE todos SET description = ? WHERE id = ?";

        try(PreparedStatement statement = database.getConnection().prepareStatement(query)) {
            statement.setString(1, newDescription);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new ServiceException("Unable to update description");        }
    }

    public List<Todo> getAll() throws ServiceException {
        String query = "SELECT * FROM todos WHERE status !='" + Status.DISCARDED + "';";
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

    public List<Todo> findByTitle(String title) throws ServiceException {
        String query = "SELECT * FROM todos WHERE title =?;";
        try (PreparedStatement statement = database.getConnection().prepareStatement(query)) {
            statement.setString(1, title);
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

    public List<Todo> findByStatusAndTitle(Status status, String title) throws ServiceException {
        String query = "SELECT * FROM todos WHERE status = ? AND title = ?;";
        try (PreparedStatement statement = database.getConnection().prepareStatement(query)) {
            statement.setString(1, status.name());
            statement.setString(2, title);
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
        return new Todo(
                results.getString("title"),
                Status.valueOf(results.getString("status")),
                results.getInt("id"),
                results.getDate("dueDate"),
                results.getString("description"),
                results.getDate("doneDate"));
    }
}