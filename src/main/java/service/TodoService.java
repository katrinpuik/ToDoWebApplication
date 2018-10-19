package service;

import dto.Todo;
import enums.Status;
import exception.ServiceException;
import repository.TodoRepository;

import java.util.List;

public class TodoService {

    private TodoRepository repository = new TodoRepository();

    public void save(Todo todo) throws ServiceException {
        repository.saveTodos(todo);
    }

    public void updateStatus(Integer id) throws ServiceException {
        repository.updateStatusToDone(id);
    }
    public void updateDate(String date, Integer id) throws ServiceException {
        repository.updateDate(date, id);
    }

    public void updateDescription(String newDescription, Integer id) throws ServiceException {
        repository.updateDescription(newDescription,id);
    }

    public List<Todo> getAll() throws ServiceException {
        return repository.getAll();
    }

    public void remove (Integer id) throws ServiceException {
        repository.remove(id);
    }

    public List<Todo> findByDescription(String description) throws ServiceException {
        return repository.findByDescription(description);
    }

    public List<Todo> findByStatus(Status status) throws ServiceException {
        return repository.findByStatus(status);
    }

    public List<Todo> findByStatusAndDescription (Status status, String description) throws ServiceException {
        return repository.findByStatusAndDescription(status, description);
    }

    public Todo findById (Integer id) throws ServiceException {
        return repository.findById(id);
    }
}