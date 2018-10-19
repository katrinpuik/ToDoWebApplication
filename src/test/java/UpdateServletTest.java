import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class UpdateServletTest {

    @InjectMocks
    private UpdateServlet servlet;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void mockiTest() {
        servlet.mingiClass = mock(UpdateServlet.MingiClass.class);

        when(servlet.mingiClass.teeMidagi()).thenReturn("t2di");

        String result = servlet.minuMeetod();

        assertEquals("t2di", result);
        verify(servlet.mingiClass).teeMidagi();
        verify(servlet.mingiClass, never()).teeMidagiVeel();
    }

    @Test
    void mockiTestIlmaWhenita() {
        servlet.mingiClass = mock(UpdateServlet.MingiClass.class);

        String result = servlet.minuMeetod();

        assertEquals(null, result);
    }

    @Test
    void mockimataTest() {
        String result = servlet.minuMeetod();

        assertEquals("onu", result);
    }
}