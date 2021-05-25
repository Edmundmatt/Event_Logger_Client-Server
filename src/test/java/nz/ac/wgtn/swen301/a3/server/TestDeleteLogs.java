package nz.ac.wgtn.swen301.a3.server;

import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

public class TestDeleteLogs {

    @Test
    public void testDelete1() throws IOException {
        //Check LogsServlet.logObjects is cleared
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        LogsServlet servlet = new LogsServlet();
        assert(!servlet.getLogObjects().isEmpty());
        servlet.doDelete(req, res);
        assert(servlet.getLogObjects().isEmpty());
    }

    @Test
    public void testDelete2() throws IOException {
        //Check HttpServletResponse
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        LogsServlet servlet = new LogsServlet();
        servlet.doDelete(req, res);
        assertEquals(200, res.getStatus());
    }
}
