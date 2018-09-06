package dto;

import enums.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TodoTest {

    @Test
    void testDescription() {
        Todo first = new Todo("todoFirst");
        Todo second = new Todo("todoSecond");

        assertEquals("todoFirst", first.getDescription());
        assertEquals("todoSecond", second.getDescription());
    }

    @Test
    void descriptionNull() {
        Todo todo = new Todo(null);

        assertNull(todo.getDescription());
    }

    @Test
    void setStatusDone() {
        Todo first = new Todo("todoFirst");
        first.setStatus("DONE");

        assertEquals(Status.DONE, first.getStatus());
    }

    @Test
    void setStatusNotDone() {
        Todo first = new Todo("todoFirst");
        first.setStatus("NOT_DONE");

        assertEquals(Status.NOT_DONE, first.getStatus());
    }

    @Test
    void setStatusDiscarded() {
        Todo first = new Todo("todoFirst");
        first.setStatus("DISCARDED");

        assertEquals(Status.DISCARDED, first.getStatus());
    }

    @Test
    void initialStatusIsNull() {
        Todo first = new Todo("todoFirst");

        assertEquals(Status.NOT_DONE, first.getStatus());
    }
}