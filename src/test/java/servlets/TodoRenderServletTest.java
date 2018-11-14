package servlets;

import dto.StatusForDropdown;
import dto.Todo;
import enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import service.TodoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class TodoRenderServletTest {
    @Spy
    @InjectMocks
    private TodoRenderServlet servlet;

    @Mock
    private TodoService service;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void doGetAddsStatusListToRequestAndForwardsToJsp() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        List<StatusForDropdown> listForTest = singletonList(new StatusForDropdown(Status.DONE, "DONE", true));

        when(request.getParameter("status")).thenReturn("DONE");
        when(servlet.createTodoList(any(), any())).thenReturn(null);
        when(servlet.createStatusList(Status.DONE)).thenReturn(listForTest);
        when(request.getRequestDispatcher("/jsp/listAll.jsp")).thenReturn(requestDispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute(eq("statusList"), same(listForTest));
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void doGetAddsTodoListToRequestAndForwardsJsp() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        List<Todo> listForTest = singletonList(new Todo("test", Status.DONE));

        when(request.getParameter("descriptionSearched")).thenReturn("test");
        when(request.getParameter("status")).thenReturn("DONE");
        when(servlet.createStatusList(any())).thenReturn(null);
        when(servlet.createTodoList(Status.DONE, "test")).thenReturn(listForTest);
        when(request.getRequestDispatcher("/jsp/listAll.jsp")).thenReturn(requestDispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute(eq("todos"), same(listForTest));
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void doGetAddsDescriptionQueryToRequestAndForwardsJsp() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("descriptionSearched")).thenReturn("test");
        when(servlet.createTodoList(any(), any())).thenReturn(null);
        when(servlet.createStatusList(any())).thenReturn(null);
        when(request.getRequestDispatcher("/jsp/listAll.jsp")).thenReturn(requestDispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute("query", "test");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void createTodoListWithStatusAndDescription() {
        List<Todo> expectedTodoList = singletonList(new Todo("test", Status.DONE));
        when(service.findByStatusAndDescription(Status.DONE, "test")).
                thenReturn(expectedTodoList);

        List<Todo> result = servlet.createTodoList(Status.DONE, "test");

        verify(service).findByStatusAndDescription(Status.DONE, "test");
        assertSame(expectedTodoList, result);
    }

    @Test
    void createTodoListWithStatus() {
        List<Todo> expectedTodoList = singletonList(new Todo("test", Status.DONE));
        when(service.findByStatus(Status.DONE)).thenReturn(expectedTodoList);

        List<Todo> result = servlet.createTodoList(Status.DONE, null);

        verify(service).findByStatus(Status.DONE);
        assertSame(expectedTodoList, result);
    }

    @Test
    void createTodoListWithDescription() {
        List<Todo> expectedTodoList = singletonList(new Todo("test", Status.DONE));
        when(service.findByDescription("test")).thenReturn(expectedTodoList);

        List<Todo> result = servlet.createTodoList(null, "test");

        verify(service).findByDescription("test");
        assertSame(expectedTodoList, result);
    }

    @Test
    void createTodoListWithoutStatusNorDescription() {
        List<Todo> expectedTodoList = singletonList(new Todo("test", Status.DONE));
        when(service.getAll()).thenReturn(expectedTodoList);

        List<Todo> result = servlet.createTodoList(null, null);

        verify(service).getAll();
        assertSame(expectedTodoList, result);
    }

    @Test
    void createStatusList() {
        Status inputStatus = Status.DONE;
        List<StatusForDropdown> expectedList = asList(
                new StatusForDropdown(Status.DONE, "DONE", true),
                new StatusForDropdown(Status.NOT_DONE, "NOT_DONE", false),
                new StatusForDropdown(Status.DISCARDED, "DISCARDED", false));

        when(servlet.checkIfSelected(inputStatus, Status.DONE)).thenReturn(true);
        when(servlet.checkIfSelected(inputStatus, Status.NOT_DONE)).thenReturn(false);
        when(servlet.checkIfSelected(inputStatus, Status.DISCARDED)).thenReturn(false);

        List<StatusForDropdown> result = servlet.createStatusList(inputStatus);

        assertEquals(expectedList, result);
    }

    @Test
    void checkIfSelectedTrue() {
        boolean isSelected = servlet.checkIfSelected(Status.DONE, Status.DONE);

        assertTrue(isSelected);
    }

    @Test
    void checkIfSelectedFalse() {
        boolean isSelected = servlet.checkIfSelected(Status.DONE, Status.NOT_DONE);

        assertFalse(isSelected);
    }

    @Test
    void createValidStatusValid(){
        assertEquals(Status.DONE, servlet.createValidStatus("DONE"));
    }

    @Test
    void createValidStatusValidWhenLowercase(){
        assertEquals(Status.DONE, servlet.createValidStatus("done"));
    }

    @Test
    void createValidStatusValidMixedLowerAndUpperCases(){
        assertEquals(Status.DONE, servlet.createValidStatus("doNE"));
    }

    @Test
    void createValidStatusWhenNull(){
        assertNull(servlet.createValidStatus(null));
    }

    @Test
    void createValidStatusInvalid() {
        assertNull(servlet.createValidStatus("test"));
    }
}