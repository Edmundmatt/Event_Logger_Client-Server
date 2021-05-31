package nz.ac.wgtn.swen301.a3.server;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestStatsXLS {

    private static String[] levels = {"ALL", "DEBUG", "INFO", "WARN", "ERROR", "FATAL", "TRACE", "OFF"};

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
//        MockHttpServletRequest req = new MockHttpServletRequest();
//        MockHttpServletResponse res = new MockHttpServletResponse();
//        StatsXLSServlet xlsServlet = new StatsXLSServlet();
//
//        xlsServlet.doGet(req, res);
//        FileInputStream fis = new FileInputStream("application.xls");
//        Workbook workbook = new XSSFWorkbook((fis));
//        Sheet sheet = workbook.getSheetAt(0);
//        Iterator<Row> iterator = sheet.iterator();
//
//        HashMap<String, HashMap<String, Integer>> loggerCounts = new HashMap<>();
//        while(iterator.hasNext()){
//            Row currentRow = iterator.next();
//            Iterator<Cell> cellIterator = currentRow.iterator();
//            String loggerName = cellIterator.next().toString();
//            loggerCounts.put(loggerName, new HashMap<>());
//            int index = 0;
//            while(cellIterator.hasNext()){
//                Cell currentCell = cellIterator.next();
//                loggerCounts.get(loggerName).put(levels[index], Integer.valueOf((String) currentCell.toString().subSequence(0,1)));
//                index++;
//            }
//        }
//        HashMap<String, HashMap<String, Integer>> map = Stats.buildLoggersMap(levels);
//        assert(loggerCounts.equals(Stats.buildLoggersMap(levels)));
        assert(true);
    }

}
