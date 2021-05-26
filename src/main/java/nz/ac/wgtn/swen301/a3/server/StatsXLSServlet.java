package nz.ac.wgtn.swen301.a3.server;

import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

public class StatsXLSServlet extends HttpServlet {

    private static List<JSONObject> logObjects = Persistency.getLogs();

    public StatsXLSServlet(){

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        String[] levels = {"ALL", "DEBUG", "INFO", "WARN", "ERROR", "FATAL", "TRACE", "OFF"};

        String header = buildHeader(levels);
        HashMap<String, HashMap<String, Integer>> loggers = buildLoggersMap(levels);
        String body = buildBody(loggers);

        String output = header + body;

        res.setContentType("application/vnd.ms-excel");
        PrintWriter pw = res.getWriter();
        pw.print(output);
        pw.flush();
        pw.close();

        //Testing
        System.out.println(output);

        //Output response code 200
        res.setStatus(HttpServletResponse.SC_OK);

    }

    private String buildHeader(String[] levels){
        //Create header
        String header = "logger";
        for(int i = 0; i < levels.length; i++){
            header += "\t" + levels[i];
        }
        header += "\n";
        return header;
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

    private String buildBody(HashMap<String, HashMap<String, Integer>> loggers){
        //Build table body
        //This assumes the keyset is in order
        String body = "";
        for(String loggerName : loggers.keySet()){
            HashMap<String, Integer> counts = loggers.get(loggerName);
            String line = loggerName;
            for(String level : counts.keySet()){
                line += "\t" + counts.get(level);
            }
            line += "\n";
            body += line;
        }
        return body;
    }
}

