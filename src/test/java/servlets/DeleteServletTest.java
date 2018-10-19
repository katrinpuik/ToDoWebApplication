package servlets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import service.TodoService;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class DeleteServletTest {

    @InjectMocks
    private DeleteServlet servlet;

    @Mock
    private TodoService service;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void doDelete() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("id")).thenReturn("1");

        servlet.doDelete(request,null);

        verify(service).remove(1);
    }

    @Test
    void doDeleteIdEmptyString(){
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("id")).thenReturn("");

        servlet.doDelete(request,null);

        verifyZeroInteractions(service);
    }

    @Test
    void doDeleteIdNull(){
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("id")).thenReturn(null);

        servlet.doDelete(request,null);

        verifyZeroInteractions(service);
    }

}