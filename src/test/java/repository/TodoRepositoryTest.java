package repository;

import dto.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import static enums.Status.DONE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class TodoRepositoryTest {

    @InjectMocks
    private TodoRepository repository;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void createTodoFromResult() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        Date date = new Date(123);

        when(resultSet.getString("description")).thenReturn("test");
        when(resultSet.getString("status")).thenReturn("DONE");
        when(resultSet.getInt("id")).thenReturn(123);
        when(resultSet.getDate("dueDate")).thenReturn(date);

        Todo todo = repository.createTodoFromResult(resultSet);

        assertEquals("test", todo.getDescription());
        assertEquals(DONE, todo.getStatus());
        assertEquals(123, todo.getId().intValue());
        assertEquals(date, todo.getDueDate());
    }
}
