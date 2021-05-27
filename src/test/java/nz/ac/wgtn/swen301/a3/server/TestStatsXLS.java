package nz.ac.wgtn.swen301.a3.server;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class TestStatsXLS {

    @Test
    public void testStatsXLSValidFormat() throws IOException {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        StatsXLSServlet xlsServlet = new StatsXLSServlet();

        xlsServlet.doGet(req, res);

    }

    @Test
    public void testStatsXLSContentType() throws IOException {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        StatsXLSServlet xlsServlet = new StatsXLSServlet();

        xlsServlet.doGet(req, res);
        assertEquals(res.getContentType(), "application/vnd.ms-excel");
    }

    @Test
    public void testStatsXLSCorrectContent() throws IOException {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        StatsXLSServlet xlsServlet = new StatsXLSServlet();

        xlsServlet.doGet(req, res);
        String[][] data = getDataFromSheet();
        res.getOutputStream();

    }

    private String[][] getDataFromSheet() throws IOException {

    }
}
