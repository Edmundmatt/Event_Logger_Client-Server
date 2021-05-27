package nz.ac.wgtn.swen301.a3.server;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

public class StatsXLSServlet extends HttpServlet {

    static{Persistency.setDB(Persistency.newLogObjects()); }
    private static List<JSONObject> logObjects = Persistency.getLogs();

    public StatsXLSServlet(){

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        String[] levels = {"ALL", "DEBUG", "INFO", "WARN", "ERROR", "FATAL", "TRACE", "OFF"};

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("stats");

        buildHeader(levels, sheet);
        HashMap<String, HashMap<String, Integer>> loggers = buildLoggersMap(levels);
        buildBody(loggers, sheet);

        res.setContentType("application/vnd.ms-excel");


        OutputStream out = res.getOutputStream();
        try{
            workbook.write(out);
        }catch(IOException io){
            System.err.println("Excel write error");
        }
        workbook.close();

        //Output response code 200
        res.setStatus(HttpServletResponse.SC_OK);

    }

    private void buildHeader(String[] levels, XSSFSheet sheet){
        //Create header
        Row firstRow = sheet.createRow(0);
        Cell cell = firstRow.createCell(0);
        cell.setCellValue("logger");
        for(int i = 0; i < levels.length; i++){
            cell = firstRow.createCell(i + 1);
            cell.setCellValue(levels[i]);
        }
    }

    private HashMap<String, HashMap<String, Integer>> buildLoggersMap(String[] levels){
        //Do count
        //Create a map of different logs
        HashMap<String, HashMap<String, Integer>> loggers = new HashMap<>();
        for(JSONObject logObject: logObjects){
            String loggerName = logObject.get("logger").toString();
            //Add logger to map if not already present
            if(!loggers.keySet().contains(loggerName)){
                //Initialise each count for logger
                loggers.put(loggerName, new HashMap<>());
                for(int i = 0; i < levels.length; i++){
                    loggers.get(loggerName).put(levels[i], 0);
                }
            }
            //Next increment counts for each logger
            String level = logObject.get("level").toString();
            HashMap<String, Integer> counts = loggers.get(loggerName);
            for(String key : counts.keySet()){
                if(key.equals(level)) counts.put(key, counts.get(key)+1);
            }

        }
        return loggers;
    }

    private void buildBody(HashMap<String, HashMap<String, Integer>> loggers, XSSFSheet sheet){
        //Build table body
        //This assumes the keyset is in order
        int rowIndex = 0;
        for(String loggerName : loggers.keySet()){
            rowIndex++;
            Row row = sheet.createRow(rowIndex);
            HashMap<String, Integer> counts = loggers.get(loggerName);
            Cell cell = row.createCell(0);
            cell.setCellValue(loggerName);
            int cellIndex = 0;
            for(String level : counts.keySet()){
                cellIndex++;
                cell = row.createCell(cellIndex);
                cell.setCellValue(counts.get(level));
            }
        }
    }
}

