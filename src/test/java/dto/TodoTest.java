package dto;

import enums.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TodoTest {

    @Test
    void createTodoWithDescriptionStatus() {
        Todo todoFirst = new Todo("todoFirst", Status.NOT_DONE);
        Todo todoSecond = new Todo("todoSecond", Status.DISCARDED);

        assertEquals("todoFirst", todoFirst.getDescription());
        assertEquals("todoSecond", todoSecond.getDescription());
        assertEquals(Status.NOT_DONE, todoFirst.getStatus() );
        assertEquals(Status.DISCARDED, todoSecond.getStatus());
    }

    @Test
    void createTodoWithDescriptionStatusId (){
        Todo todoFirst = new Todo("todoFirst", Status.NOT_DONE, 1);
        Todo todoSecond = new Todo("todoSecond", Status.DISCARDED, 2);

        assertEquals("todoFirst", todoFirst.getDescription());
        assertEquals("todoSecond", todoSecond.getDescription());
        assertEquals(Status.NOT_DONE, todoFirst.getStatus() );
        assertEquals(Status.DISCARDED, todoSecond.getStatus());
        assertEquals(1, todoFirst.getId().intValue());
    }

    @Test
    void setStatusDone() {
        Todo todo = new Todo("todo", Status.NOT_DONE);
        todo.setStatus("DONE");

        assertEquals(Status.DONE, todo.getStatus());
    }

    @Test
    void setStatusDiscarded() {
        Todo todo = new Todo("todo", Status.NOT_DONE);
        todo.setStatus("NOT_DONE");

        assertEquals(Status.NOT_DONE, todo.getStatus());
    }

    @Test
    void descriptionNull() {
        Todo todo = new Todo(null, Status.NOT_DONE);

        assertNull(todo.getDescription());
    }

    @Test
    void setNewDescription() {
        Todo todo = new Todo("description", Status.NOT_DONE);
        todo.setDescription("new description");

        assertEquals("new description", todo.getDescription());
    }

    @Test
    void isCompletable(){
        Todo todo = new Todo("description", Status.NOT_DONE);

        assertTrue(todo.isCompletable());
    }

}