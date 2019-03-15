package service;

import dto.Todo;
import enums.Status;
import exception.ServiceException;
import repository.TodoRepository;

import java.util.List;

public class TodoService {

    private TodoRepository repository = new TodoRepository();

    public void addNew(String newTitle, String newDescription, String dueDate, String creationDate) {
        repository.addNew(newTitle, newDescription, dueDate, creationDate);
    }

    public void updateStatus(Integer id, String doneDate) throws ServiceException {
        repository.updateStatusToDone(id, doneDate);
    }
    public void updateDueDate(String date, Integer id) throws ServiceException {
        repository.updateDueDate(date, id);
    }

    public void updateDescription(String newDescription, Integer id) throws ServiceException {
        repository.updateDescription(newDescription,id);
    }

    public void updateTitle(String title, Integer id) throws ServiceException {
        repository.updateTitle(title, id);
    }

    public List<Todo> getAll() throws ServiceException {
        return repository.getAll();
    }

    public void remove (Integer id) throws ServiceException {
        repository.remove(id);
    }

    public List<Todo> findByTitle(String title) throws ServiceException {
        return repository.findByTitle(title);
    }

    public List<Todo> findByStatus(Status status) throws ServiceException {
        return repository.findByStatus(status);
    }

    public List<Todo> findByStatusAndTitle (Status status, String title) throws ServiceException {
        return repository.findByStatusAndTitle(status, title);
    }

    public Todo findById (Integer id) throws ServiceException {
        return repository.findById(id);
    }
}