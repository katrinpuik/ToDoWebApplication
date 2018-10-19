package servlets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import service.TodoService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class UpdateServletTest {

    @InjectMocks
    private UpdateServlet servlet;

    @Mock
    private ServletBodyReader reader;

    @Mock
    private TodoService service;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void doPostUpdateDescription() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(reader.readJsonStringFromRequest(request)).thenReturn("{\"id\":1, \"description\": \"test\"}");

        servlet.doPost(request, null);

        verify(service).updateDescription("test", 1);
    }

    @Test
    void doPostUpdateDate() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(reader.readJsonStringFromRequest(request)).thenReturn("{\"id\":1, \"date\": \"19.10.2018\"}");

        servlet.doPost(request, null);

        verify(service).updateDate("19.10.2018", 1);
    }

    @Test
    void doPostIdMissing() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(reader.readJsonStringFromRequest(request)).thenReturn("{}");

        servlet.doPost(request, null);

        verifyZeroInteractions(service);
    }

    @Test
    void doPostDescriptionAndDateMissing() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(reader.readJsonStringFromRequest(request)).thenReturn("{\"id\":1}");

        servlet.doPost(request, null);

        verifyZeroInteractions(service);
    }
}