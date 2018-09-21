package service;

import dto.Todo;
import enums.Status;
import exception.ServiceException;
import repository.TodoRepository;

import java.util.List;

public class TodoService {

    TodoRepository repository = new TodoRepository();

    public void save(Todo todo) throws ServiceException {
        repository.saveTodos(todo);
    }

    public void updateStatus(Todo todo) throws ServiceException {
        repository.updateStatus(todo);
    }

    public List<Todo> getAll() {
        return repository.getAll();
    }

    public void remove (Integer id) {
        repository.remove(id);
    }

    public List<Todo> findByDescription(String description) {
        return repository.findByDescription(description);
    }

    public List<Todo> findByStatus(Status status) {
        return repository.findByStatus(status);
    }

    public List<Todo> findByStatusAndDescription (Status status, String description) {
        return repository.findByStatusAndDescription(status, description);
    }

    public Todo findById (Integer id) {
        return repository.findById(id);
    }


}