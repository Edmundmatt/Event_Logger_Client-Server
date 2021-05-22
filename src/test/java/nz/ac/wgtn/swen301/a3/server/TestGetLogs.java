package nz.ac.wgtn.swen301.a3.server;


import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestGetLogs
{
    @Test
    public void testGet1() throws IOException {
        //Check response for two null request inputs
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        LogsServlet servlet = new LogsServlet();
        servlet.doGet(req, res);
        assertEquals(400,res.getStatus());
    }

    @Test
    public void testGet2() throws IOException {
        //Check response for null limit request input
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        req.setParameter("level", "INFO");
        LogsServlet servlet = new LogsServlet();
        servlet.doGet(req, res);
        assertEquals(400,res.getStatus());
    }

    @Test
    public void testGet3() throws IOException {
        //Check response for null level request input
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        req.setParameter("limit", "5");
        LogsServlet servlet = new LogsServlet();
        servlet.doGet(req, res);
        assertEquals(400,res.getStatus());
    }

}
