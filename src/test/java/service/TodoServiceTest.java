package service;

import dto.Todo;
import enums.Status;
import exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;


class TodoServiceTest {

    private TodoService service;

    @BeforeEach
    void setUp() {
        service = new TodoService();
    }

    @Test
    void saveAndGetAllTodos() throws ServiceException {
        Todo todo1 = new Todo("todo1");
        service.save(todo1);
        Todo todo2 = new Todo("todo2");
        service.save(todo2);

        List<Todo> allToDos = service.getAll();

        assertEquals(asList(todo1, todo2), allToDos);
    }

    @Test
    void saveAndAddIds() throws ServiceException {
        Todo todo1 = new Todo("todo1");
        service.save(todo1);
        Todo todo2 = new Todo("todo2");
        service.save(todo2);

        assertTrue(todo1.getId() < todo2.getId());
    }

    @Test
    void updateAndSaveToDo() throws ServiceException {
        Todo todo1 = new Todo("todo1");
        service.save(todo1);
        Todo todo2 = new Todo("todo2");
        service.save(todo2);
        Integer idBeforeDescriptionUpdate = todo2.getId();
        Todo todo3 = new Todo("todo3");
        service.save(todo3);
        todo2.setDescription("new description");
        service.save(todo2);
        Integer idAfterDescriptionUpdate = todo2.getId();

        assertEquals("new description", todo2.getDescription());
        assertEquals(idBeforeDescriptionUpdate, idAfterDescriptionUpdate);
    }

    @Test
    void findById() throws ServiceException {
        Todo todo1 = new Todo("todo1");
        service.save(todo1);
        Todo todo2 = new Todo("todo2");
        service.save(todo2);

        Todo todoFoundById = service.findById(todo1.getId());

        assertEquals(todo1, todoFoundById);
    }

    @Test
    void findByIdResultNull() throws ServiceException {
        Todo todo = new Todo("todo1");
        service.save(todo);

        Todo todoFoundById = service.findById(todo.getId() + 1);

        assertNull(todoFoundById);
    }

    @Test
    void removeById() throws ServiceException {
        Todo todo1 = new Todo("todo1");
        service.save(todo1);
        Todo todo2 = new Todo("todo2");
        service.save(todo2);
        Todo todo3 = new Todo("todo3");
        service.save(todo3);

        service.remove(todo2.getId());

        assertEquals(asList(todo1, todo3), service.getAll());
    }

    @Test
    void saveToDoWithDifferentDescription() throws ServiceException {
        Todo todo = new Todo("todo1");
        service.save(todo);
        todo.setDescription("new description");
        service.save(todo);

        List<Todo> allTodos = service.getAll();
        Todo updatedTodo = allTodos.get(0);

        assertEquals(1, allTodos.size());
        assertEquals("new description", updatedTodo.getDescription());
    }

    @Test
    void removeByDescription () throws ServiceException {
        Todo todo1 = new Todo("todo1");
        service.save(todo1);
        Todo todo2 = new Todo(null);
        service.save(todo2);
        Todo todo3 = new Todo("todo3");
        service.save(todo3);

        service.remove("todo3");

        assertEquals(asList(todo1, todo2), service.getAll());
    }

    @Test
    void findByExactDescription() throws ServiceException {
        Todo todo1 = new Todo("todo1");
        service.save(todo1);
        Todo todo2 = new Todo("todo2");
        service.save(todo2);
        Todo todo3 = new Todo("todo3");
        service.save(todo3);

        assertEquals(singletonList(todo2), service.findByDescription("todo2"));
    }

    @Test
    void findByExactDescriptionUpperCase() throws ServiceException{
        Todo todo1 = new Todo("todo1");
        service.save(todo1);
        Todo todo2 = new Todo("todo2");
        service.save(todo2);
        Todo todo3 = new Todo("todo3");
        service.save(todo3);

        assertEquals(singletonList(todo2), service.findByDescription("TODO2"));
    }

    @Test
    void findByDescriptionSomeLetters() throws ServiceException{
        Todo todo1 = new Todo("todo1");
        service.save(todo1);
        Todo todo2 = new Todo("todo2");
        service.save(todo2);
        Todo todo3 = new Todo("todo3");
        service.save(todo3);

        assertEquals(asList(todo1, todo2, todo3), service.findByDescription("tod"));
    }

    @Test
    void findByStatusDone() throws ServiceException {
        service.save(createTodo(Status.DISCARDED));
        Todo todo = createTodo(Status.DONE);
        service.save(todo);
        service.save(createTodo(null));
        service.save(createTodo(Status.NOT_DONE));

        List<Todo> todosWithStatusDone = service.findByStatus(Status.DONE);

        assertEquals(1, todosWithStatusDone.size());
        assertEquals(singletonList(todo), todosWithStatusDone);
    }

    @Test
    void findByStatusNotDone() throws ServiceException {
        service.save(createTodo(Status.DISCARDED));
        Todo todo = createTodo(Status.NOT_DONE);
        service.save(todo);
        service.save(createTodo(Status.DONE));
        service.save(createTodo(null));

        List<Todo> todosWithStatusNotDone = service.findByStatus(Status.NOT_DONE);

        assertEquals(1, todosWithStatusNotDone.size());
        assertEquals(singletonList(todo), todosWithStatusNotDone);
    }

    @Test
    void findByStatusDiscarded() throws ServiceException {
        service.save(createTodo(Status.NOT_DONE));
        Todo todo = createTodo(Status.DISCARDED);
        service.save(todo);
        service.save(createTodo(null));
        service.save(createTodo(Status.DONE));

        List<Todo> todosWithStatusDiscarded = service.findByStatus(Status.DISCARDED);

        assertEquals(1, todosWithStatusDiscarded.size());
        assertEquals(singletonList(todo), todosWithStatusDiscarded);
    }

    @Test
    void findIfStatusIsNull() throws ServiceException {
        service.save(createTodo(Status.DISCARDED));
        Todo todo = createTodo(null);
        service.save(todo);

        List<Todo> todosWithStatusNull = service.findByStatus(null);

        assertEquals(1, todosWithStatusNull.size());
        assertEquals(singletonList(todo), todosWithStatusNull);
    }

    @Test
    void findByStatusAndDescription () throws ServiceException {
        Todo todo1 = new Todo("todo1");
        todo1.setStatus("DONE");
        service.save(todo1);
        Todo todo2 = new Todo("todo2");
        todo2.setStatus("NOT_DONE");
        service.save(todo2);
        Todo todo3 = new Todo("todo3");
        service.save(todo3);

        List<Todo> todosFoundByStatusAndDescription = service.findByStatusAndDescription(Status.NOT_DONE, "todo2");

        assertEquals(1, todosFoundByStatusAndDescription.size());
        assertEquals(singletonList(todo2), todosFoundByStatusAndDescription);
    }

    private Todo createTodo(Status status) {
        Todo todo = new Todo("todo");
        todo.setStatus(status == null ? null : status.name());
        return todo;
    }
}