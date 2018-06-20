package service;

import dto.ToDo;
import enums.Status;
import exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;


class ToDoServiceTest {

    private ToDoService service;

    @BeforeEach
    void setUp() {
        service = new ToDoService();
    }

    @Test
    void saveAndGetAllToDos() throws ServiceException {
        ToDo toDo1 = new ToDo("toDo1");
        service.save(toDo1);
        ToDo toDo2 = new ToDo("toDo2");
        service.save(toDo2);

        List<ToDo> allToDos = service.getAll();

        assertEquals(asList(toDo1, toDo2), allToDos);
    }

    @Test
    void saveAndAddIds() throws ServiceException {
        ToDo toDo1 = new ToDo("toDo1");
        service.save(toDo1);
        ToDo toDo2 = new ToDo("toDo2");
        service.save(toDo2);

        assertTrue(toDo1.getId() < toDo2.getId());
    }

    @Test
    void updateAndSaveToDo() throws ServiceException {
        ToDo toDo1 = new ToDo("toDo1");
        service.save(toDo1);
        ToDo toDo2 = new ToDo("toDo2");
        service.save(toDo2);
        Integer idBeforeDescriptionUpdate = toDo2.getId();
        ToDo toDo3 = new ToDo("toDo3");
        service.save(toDo3);
        toDo2.setDescription("new description");
        service.save(toDo2);
        Integer idAfterDescriptionUpdate = toDo2.getId();

        assertEquals("new description", toDo2.getDescription());
        assertEquals(idBeforeDescriptionUpdate, idAfterDescriptionUpdate);
    }

    @Test
    void findById() throws ServiceException {
        ToDo toDo1 = new ToDo("toDo1");
        service.save(toDo1);
        ToDo toDo2 = new ToDo("toDo2");
        service.save(toDo2);

        ToDo toDoFoundById = service.findById(toDo1.getId());

        assertEquals(toDo1, toDoFoundById);
    }

    @Test
    void findByIdResultNull() throws ServiceException {
        ToDo todo = new ToDo("toDo1");
        service.save(todo);

        ToDo toDoFoundById = service.findById(todo.getId() + 1);

        assertNull(toDoFoundById);
    }

    @Test
    void removeById() throws ServiceException {
        ToDo toDo1 = new ToDo("toDo1");
        service.save(toDo1);
        ToDo toDo2 = new ToDo("toDo2");
        service.save(toDo2);
        ToDo toDo3 = new ToDo("toDo3");
        service.save(toDo3);

        service.remove(toDo2.getId());

        assertEquals(asList(toDo1, toDo3), service.getAll());
    }

    @Test
    void saveToDoWithDifferentDescription() throws ServiceException {
        ToDo toDo = new ToDo("toDo1");
        service.save(toDo);
        toDo.setDescription("new description");
        service.save(toDo);

        List<ToDo> allToDos = service.getAll();
        ToDo updatedToDo = allToDos.get(0);

        assertEquals(1, allToDos.size());
        assertEquals("new description", updatedToDo.getDescription());
    }

    @Test
    void removeByDescription () throws ServiceException {
        ToDo toDo1 = new ToDo("toDo1");
        service.save(toDo1);
        ToDo toDo2 = new ToDo(null);
        service.save(toDo2);
        ToDo toDo3 = new ToDo("todo3");
        service.save(toDo3);

        service.remove("todo3");

        assertEquals(asList(toDo1, toDo2), service.getAll());
    }


    @Test
    void findByExactDescription() throws ServiceException {
        ToDo toDo1 = new ToDo("toDo1");
        service.save(toDo1);
        ToDo toDo2 = new ToDo("toDo2");
        service.save(toDo2);
        ToDo toDo3 = new ToDo("toDo3");
        service.save(toDo3);

        assertEquals(singletonList(toDo2), service.findByDescription("toDo2"));
    }

    @Test
    void findByExactDescriptionUpperCase() throws ServiceException{
        ToDo toDo1 = new ToDo("toDo1");
        service.save(toDo1);
        ToDo toDo2 = new ToDo("todo2");
        service.save(toDo2);
        ToDo toDo3 = new ToDo("todo3");
        service.save(toDo3);

        assertEquals(singletonList(toDo2), service.findByDescription("TODO2"));
    }

    @Test
    void findByDescriptionSomeLetters() throws ServiceException{
        ToDo toDo1 = new ToDo("toDo1");
        service.save(toDo1);
        ToDo toDo2 = new ToDo("todo2");
        service.save(toDo2);
        ToDo toDo3 = new ToDo("todo3");
        service.save(toDo3);

        assertEquals(asList(toDo1, toDo2, toDo3), service.findByDescription("tod"));
    }

    @Test
    void findByStatusDone() throws ServiceException {
        service.save(createToDo(Status.DISCARDED));
        ToDo toDo = createToDo(Status.DONE);
        service.save(toDo);
        service.save(createToDo(null));
        service.save(createToDo(Status.NOT_DONE));

        List<ToDo> toDosWithStatusDone = service.findByStatus(Status.DONE);

        assertEquals(1, toDosWithStatusDone.size());
        assertEquals(singletonList(toDo), toDosWithStatusDone);
    }

    @Test
    void findByStatusNotDone() throws ServiceException {
        service.save(createToDo(Status.DISCARDED));
        ToDo toDo = createToDo(Status.NOT_DONE);
        service.save(toDo);
        service.save(createToDo(Status.DONE));
        service.save(createToDo(null));

        List<ToDo> toDosWithStatusNotDone = service.findByStatus(Status.NOT_DONE);

        assertEquals(1, toDosWithStatusNotDone.size());
        assertEquals(singletonList(toDo), toDosWithStatusNotDone);
    }


    @Test
    void findByStatusDiscarded() throws ServiceException {
        service.save(createToDo(Status.NOT_DONE));
        ToDo toDo = createToDo(Status.DISCARDED);
        service.save(toDo);
        service.save(createToDo(null));
        service.save(createToDo(Status.DONE));

        List<ToDo> toDosWithStatusDiscarded = service.findByStatus(Status.DISCARDED);

        assertEquals(1, toDosWithStatusDiscarded.size());
        assertEquals(singletonList(toDo), toDosWithStatusDiscarded);
    }

    @Test
    void findIfStatusIsNull() throws ServiceException {
        service.save(createToDo(Status.DISCARDED));
        ToDo toDo = createToDo(null);
        service.save(toDo);

        List<ToDo> toDosWithStatusNull = service.findByStatus(null);

        assertEquals(1, toDosWithStatusNull.size());
        assertEquals(singletonList(toDo), toDosWithStatusNull);
    }

    private ToDo createToDo(Status status) {
        ToDo toDo = new ToDo("toDo");
        toDo.setStatus(status == null ? null : status.name());
        return toDo;
    }
}