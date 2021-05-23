package nz.ac.wgtn.swen301.a3.server;

import org.json.JSONObject;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestPostLogs {

    @Test
    public void testPostInvalidInput1() throws IOException {
        //Check for null input
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        LogsServlet servlet = new LogsServlet();
        servlet.doPost(req, res);
        assertEquals(400, res.getStatus());
    }

    @Test
    public void testPostInvalidInput2() throws IOException {
        //Check for empty JSONObject
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        JSONObject object = new JSONObject();
        assertEquals(object.toString(), "{}");
        req.setContent(object.toString().getBytes());
        LogsServlet servlet = new LogsServlet();
        servlet.doPost(req, res);
        assertEquals(400, res.getStatus());
    }

    @Test
    public void testPostInValidInput3() throws IOException {
        //Check for if JSONObject already exists in List via Id
        MockHttpServletRequest req1 = new MockHttpServletRequest();
        MockHttpServletResponse res1 = new MockHttpServletResponse();
        MockHttpServletRequest req2 = new MockHttpServletRequest();
        MockHttpServletResponse res2 = new MockHttpServletResponse();

        Persistency p = new Persistency();
        JSONObject object = p.testObject();
        req1.setContent(object.toString().getBytes());
        req2.setContent(object.toString().getBytes());

        LogsServlet servlet = new LogsServlet();
        servlet.doPost(req1, res1);
        servlet.doPost(req2, res2);
        assertEquals(201, res1.getStatus());
        assertEquals(409, res2.getStatus());

    }

    @Test
    public void testPostValidInput1() throws IOException {
        //Check for valid JSONObject response code
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();

        Persistency p = new Persistency();
        JSONObject object = p.testObject();
        req.setContent(object.toString().getBytes());

        LogsServlet servlet = new LogsServlet();
        servlet.doPost(req, res);
        assertEquals(201, res.getStatus());
    }

    @Test
    public void testPostValidInput2() throws IOException {
        //Check for latest JSONObject in LogsServlet.logObjects
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();

        Persistency p = new Persistency();
        JSONObject object = p.testObject();
        req.setContent(object.toString().getBytes());

        LogsServlet servlet = new LogsServlet();
        servlet.doPost(req, res);
        assertEquals(servlet.getLogObjects().get(servlet.getLogObjects().size()-1).toString(), object.toString());
        assertEquals(servlet.getLogObjects().get(servlet.getLogObjects().size()-1).get("id"),object.get("id"));
    }

}
