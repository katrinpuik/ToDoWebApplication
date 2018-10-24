package servlets;

import dto.Todo;
import enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import service.TodoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class NewTodoServletTest {

    @InjectMocks
    private NewTodoServlet servlet;

    @Mock
    private TodoService service;

    @Captor
    private ArgumentCaptor<Todo> todoArgumentCaptor;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void doGet() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/newTodo.jsp")).thenReturn(requestDispatcher);

        servlet.doGet(request, response);

        verify(requestDispatcher).forward(request,response);
    }

    @Test
    void doPostSavesNewTodoAndSendsRedirect() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("newTodo")).thenReturn("new");

        servlet.doPost(request,response);

        verify(service).save(todoArgumentCaptor.capture());
        Todo todo = todoArgumentCaptor.getValue();
        assertEquals("new", todo.getDescription());
        assertEquals(Status.NOT_DONE, todo.getStatus());
        verify(response).sendRedirect("/todos");
    }

    @Test
    void doPostNewTodoDescriptionIsEmptyString() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("newTodo")).thenReturn("");

        servlet.doPost(request,response);

        verify(response).sendRedirect("/todos/new");
    }

    @Test
    void doPostTodoDescriptionIsNull() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("newTodo")).thenReturn(null);

        servlet.doPost(request,response);

        verify(response).sendRedirect("/todos/new");
    }
}