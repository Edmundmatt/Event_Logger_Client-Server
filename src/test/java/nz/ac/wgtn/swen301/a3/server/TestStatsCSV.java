package nz.ac.wgtn.swen301.a3.server;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLOutput;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestStatsCSV {

    @Test
    public void testStatsCSVValidOutput1() throws IOException {
        // Do get CSV output
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        StatsCSVServlet csvServlet = new StatsCSVServlet();
        csvServlet.doGet(req, res);

        csvParser(res);
        assert(true);
    }

    @Test
    public void testContentType() throws IOException {
        // Do get CSV output
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        StatsCSVServlet csvServlet = new StatsCSVServlet();
        csvServlet.doGet(req, res);

        assertEquals(res.getContentType(),"text/csv");
    }

    @Test
    public void testStatsCSVContent() throws IOException {
        // Do get CSV output
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        StatsCSVServlet csvServlet = new StatsCSVServlet();
        csvServlet.doGet(req, res);

        HashMap<String, ArrayList<Integer>> values = csvParser(res);
        for(String logger : values.keySet()){
            System.out.println(logger + ": ");
            System.out.println(values.get(logger).toString());
        }
        // Get actual logger level counts

    }


    private HashMap<String, ArrayList<Integer>> csvParser(MockHttpServletResponse res) throws IOException {
        try {
            HashMap<String, ArrayList<Integer>> values = new HashMap<>();
            String[] arr1 = res.getContentAsString().split("\n", Integer.MAX_VALUE);
            for (int i = 1; i < arr1.length - 1; i++) {
                String[] arr2 = arr1[i].split("\t", Integer.MAX_VALUE);
                ArrayList<Integer> loggerCounts = new ArrayList<>();
                for (int j = 1; j < arr2.length; j++) {
                    loggerCounts.add(Integer.parseInt(arr2[j]));
                }
                values.put(arr2[0], loggerCounts);
            }
            return values;
        }catch(IOException e){
            throw e;
        }
    }
}
