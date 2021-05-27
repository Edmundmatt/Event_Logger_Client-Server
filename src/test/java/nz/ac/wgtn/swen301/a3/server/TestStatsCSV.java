package nz.ac.wgtn.swen301.a3.server;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

public class TestStatsCSV {

    @Test
    public void testStatsCSV1() throws IOException {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
//        LogsServlet servlet = new LogsServlet();
        StatsCSVServlet csvServlet = new StatsCSVServlet();

        csvServlet.doGet(req, res);

    }
}
