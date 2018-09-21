package service;

import dto.Todo;
import exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.TodoRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class TodoServiceTest {

    private TodoService service;

    @BeforeEach
    public void setUp() {
        service = new TodoService();
        service.repository = mock(TodoRepository.class);
    }

    @Test
    void save_callsRepositorySave() throws ServiceException {
        Todo todo = mock(Todo.class);

        service.save(todo);

        verify(service.repository).saveTodos(todo);
    }

    @Test
    void updateStatus_callsRepositoryUpdateStatus() throws ServiceException {
        Todo todo = mock(Todo.class);

        service.updateStatus(todo);

        verify(service.repository).updateStatus(todo);
    }

    @Test
    void getAll_returnsListOfTodos() {
        List<Todo> expectedTodos = Collections.singletonList(mock(Todo.class));

        when(service.repository.getAll()).thenReturn(expectedTodos);

        List<Todo> todos = service.getAll();

        assertEquals(expectedTodos, todos);
    }

    @Test
    void remove_callsRepositoryRemove() {
        service.remove(null);

        verify(service.repository).remove(null);
    }

    @Test
    void findByDescription_callsRepositoryFindByDescription() {
        List<Todo> expectedTodos = Collections.singletonList(mock(Todo.class));

        when(service.repository.findByDescription(null)).thenReturn(expectedTodos);

        List<Todo> todos = service.findByDescription(null);

        assertEquals(expectedTodos, todos);
    }

    @Test
    void findByStatus_callsRepositoryFindByStatus() {
        List<Todo> expectedTodos = Collections.singletonList(mock(Todo.class));

        when(service.repository.findByStatus(null)).thenReturn(expectedTodos);

        List<Todo> todos = service.findByStatus(null);

        assertEquals(expectedTodos, todos);
    }

    @Test
    void findByStatusAndDescription_callsRepositoryFindByStatusAndDescription() {
        List<Todo> expectedTodos = Collections.singletonList(mock(Todo.class));

        when(service.repository.findByStatusAndDescription(null, null)).thenReturn(expectedTodos);

        List<Todo> todos = service.findByStatusAndDescription(null, null);

        assertEquals(expectedTodos, todos);
    }

    @Test
    void findById_callsRepositoryFindById() {
        Todo expectedTodo = mock(Todo.class);

        when(service.repository.findById(null)).thenReturn(expectedTodo);

        Todo todo = service.findById(null);

        assertEquals(expectedTodo, todo);
    }
}