package repository;

import dto.ToDo;
import enums.Status;
import exception.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class ToDoRepository {

    private List<ToDo> toDos = new ArrayList<>();
    private AtomicInteger id = new AtomicInteger(1);

    public void saveOrUpdateAndSaveToDos(ToDo toDo) throws ServiceException {
        if (toDo.getId() != null) {
            if (findById(toDo.getId()) == null) {
                throw new ServiceException("No toDo with such id");
            } else remove(toDo.getId());
        } else {
            toDo.setId(id.getAndIncrement());
        }
        toDos.add(toDo);
    }

    public ToDo findById(Integer id) {
        return toDos.stream()
                .filter(toDo -> Objects.equals(toDo.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public List<ToDo> getAll() {
        return toDos;
    }

    public void remove(String description) {
        toDos.removeIf(toDo -> areEqual(description, toDo.getDescription()));
    }

    public void remove (Integer id) {
        toDos.removeIf(toDo -> Objects.equals(id, toDo.getId()));
    }


    public List<ToDo> findByDescription(String description) {
        return toDos.stream()
                .filter(toDo -> areEqual(description, toDo.getDescription()))
                .collect(Collectors.toList());
    }


    public List<ToDo> findByStatus(Status status) {
        return toDos.stream()
                .filter(toDo -> areEqual(status, toDo.getStatus()))
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
