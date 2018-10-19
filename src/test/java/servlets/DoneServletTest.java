package servlets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import service.TodoService;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class DoneServletTest {

    @InjectMocks
    private DoneServlet servlet;

    @Mock
    private TodoService service;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void doPut() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("id")).thenReturn("1");

        servlet.doPut(request,null);

        verify(service).updateStatus(1);
    }

    @Test
    void doPutIdEmptyString(){
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("id")).thenReturn("");

        servlet.doPut(request,null);

        verifyZeroInteractions(service);
    }

    @Test
    void doPutIdNull(){
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("id")).thenReturn(null);

        servlet.doPut(request,null);

        verifyZeroInteractions(service);
    }

}