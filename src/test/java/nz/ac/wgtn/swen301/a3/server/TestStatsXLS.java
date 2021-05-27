package nz.ac.wgtn.swen301.a3.server;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

public class TestStatsXLS {

    @Test
    public void testStatsXLS1() throws IOException {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
//        LogsServlet servlet = new LogsServlet();
        StatsXLSServlet xlsServlet = new StatsXLSServlet();

        xlsServlet.doGet(req, res);
    }
}
