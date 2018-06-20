package service;

import exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoValidatorTest {
    private ToDoValidator validator;

    @BeforeEach
    void setUp() {
        validator = new ToDoValidator();
    }

    @Test
    void isValidDescription() throws ServiceException {
        assertTrue(validator.isValidDescription("Go ahead"));
    }

    @Test
    void throwsExceptionIfDescriptionIsEmpty() {
        Throwable exception = assertThrows(ServiceException.class, () -> validator.isValidDescription(""));
        assertEquals("Invalid description", exception.getMessage());
    }

    @Test
    void throwsExceptionIfDescriptionIsNull() {
        Throwable exception = assertThrows(ServiceException.class, () -> validator.isValidDescription(null));
        assertEquals("Invalid description", exception.getMessage());
    }

    @Test
    void isValidStatusIfStatusNull() throws ServiceException {
        assertTrue(validator.isValidStatus(null));
    }

    @Test
    void isValidStatusIfStatusIsEmpty() {
            Throwable exception = assertThrows(ServiceException.class, () -> validator.isValidStatus(" "));
            assertEquals("Invalid status", exception.getMessage());
    }

    @Test
    void isValidStatusDone() throws ServiceException {
        assertTrue(validator.isValidStatus("DONE"));
    }

    @Test
    void isValidStatusNotDone() throws ServiceException {
        assertTrue(validator.isValidStatus("NOT_DONE"));
    }

    @Test
    void isValidStatusNotUpperCaseInput() {
        Throwable exception = assertThrows(ServiceException.class, () -> validator.isValidStatus("not_done"));
        assertEquals("Invalid status", exception.getMessage());
    }


    @Test
    void isValidStatusDiscarded() throws ServiceException {
        assertTrue(validator.isValidStatus("DISCARDED"));
    }

    @Test
    void throwsExceptionIfInvalidInput() {
        Throwable exception = assertThrows(ServiceException.class, () -> validator.isValidStatus("kjhsdg"));
        assertEquals("Invalid status", exception.getMessage());
    }
}
