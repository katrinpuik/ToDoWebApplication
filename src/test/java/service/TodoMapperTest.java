package service;

import dto.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static enums.Status.DONE;
import static enums.Status.NOT_DONE;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TodoMapperTest {

    private TodoMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new TodoMapper();
    }

    @Test
    void deserialize() {
        List<String> controlList = new ArrayList<>();
        controlList.add("Read a book,DONE");
        controlList.add("Take a break,NOT_DONE");

        List<Todo> todos = mapper.deserialize(controlList);

        assertEquals(2, todos.size());
        assertEquals("Read a book", todos.get(0).getDescription());
        assertEquals(DONE, todos.get(0).getStatus());
        assertEquals("Take a break", todos.get(1).getDescription());
        assertEquals(NOT_DONE, todos.get(1).getStatus());
    }

    @Test
    void deserializeIfStatusIsNull() {
        List<String> controlList = new ArrayList<>();
        controlList.add("Read a book,");

        List<Todo> todos = mapper.deserialize(controlList);

        assertEquals("Read a book", todos.get(0).getDescription());
        assertNull(todos.get(0).getStatus());
    }

    @Test
    void serialize() {
        List<Todo> controlList = new ArrayList<>();

        Todo todo1 = new Todo("Read a book");
        todo1.setStatus(DONE.name());
        controlList.add(todo1);

        Todo todo2 = new Todo("Take a break");
        todo2.setStatus(NOT_DONE.name());
        controlList.add(todo2);

        List<String> todosAsStrings = mapper.serialize(controlList);

        assertEquals(2, todosAsStrings.size());
        assertEquals("Read a book,DONE", todosAsStrings.get(0));
        assertEquals("Take a break,NOT_DONE", todosAsStrings.get(1));
    }

    @Test
    void serialiseIfStatusIsNull() {
        Todo todo = new Todo("Read a book");
        todo.setStatus(null);

        List<String> todosAsStrings = mapper.serialize(singletonList(todo));

        assertEquals("Read a book,", todosAsStrings.get(0));
    }
}
