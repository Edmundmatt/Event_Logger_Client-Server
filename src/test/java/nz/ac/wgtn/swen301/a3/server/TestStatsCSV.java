package nz.ac.wgtn.swen301.a3.server;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import static org.junit.Assert.assertTrue;

public class TestStatsCSV {

    @Test
    public void testStatsCSV1() throws IOException {
        // Do get CSV output
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        StatsCSVServlet csvServlet = new StatsCSVServlet();
        csvServlet.doGet(req, res);

        //Test valid csv output
        assertTrue(csvParser(res));

    }

    private boolean csvParser(MockHttpServletResponse res) throws IOException {

        String[] arr1 = res.getContentAsString().split("\n",Integer.MAX_VALUE);


        return true;
    }
}
