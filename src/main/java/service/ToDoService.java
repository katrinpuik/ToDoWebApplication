package service;

import dto.ToDo;
import enums.Status;
import exception.ServiceException;
import repository.ToDoRepository;

import java.util.List;

public class ToDoService {

    private ToDoRepository repository = new ToDoRepository();

    public void save(ToDo todo) throws ServiceException {
        repository.saveOrUpdateAndSaveToDos(todo);
    }

    public List<ToDo> getAll() {
        return repository.getAll();
    }

    public void remove(String description) {
        repository.remove(description);
    }

    public void remove (Integer id) {
        repository.remove(id);
    }

    public List<ToDo> findByDescription(String description) {
        return repository.findByDescription(description);
    }

    public List<ToDo> findByStatus(Status status) {
        return repository.findByStatus(status);
    }

    public ToDo findById (Integer id) {
        return repository.findById(id);
    }


}