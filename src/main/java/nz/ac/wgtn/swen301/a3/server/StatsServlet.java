package nz.ac.wgtn.swen301.a3.server;

import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

public class StatsServlet extends HttpServlet {

    static{Persistency.setDB(Persistency.newLogObjects()); }
    private static List<JSONObject> logObjects = Persistency.getLogs();

    public StatsServlet(){

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        String[] levels = {"ALL", "DEBUG", "INFO", "WARN", "ERROR", "FATAL", "TRACE", "OFF"};

        String header = buildHeader(levels);
        HashMap<String, HashMap<String, Integer>> loggers = buildLoggersMap(levels);
        String body = buildBody(loggers);

        String output = header + body;

        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        pw.println("<html>");
        pw.println("<table>");
        pw.println(header);
        pw.println(body);
        pw.println("</table>");
        pw.flush();
        pw.close();

        //Output response code 200
        res.setStatus(HttpServletResponse.SC_OK);

    }

    private String buildHeader(String[] levels){
        //Create header
        String header = "<tr>";
        header += "<th>logger</th>";
        for(int i = 0; i < levels.length; i++){
            header += "<th>" + levels[i] + "</th>";
        }
        header += "</tr>";
        return header;
    }

    private HashMap<String, HashMap<String, Integer>> buildLoggersMap(String[] levels){
        //Do count
        //Create a map of different logs
        HashMap<String, HashMap<String, Integer>> loggers = new HashMap<>();
        for(JSONObject logObject: logObjects){
            String loggerName = logObject.get("logger").toString();
            //Add logger to map if not already present
            if(!loggers.containsKey(loggerName)){
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
            String line = "<tr>";
            line += "<th>" + loggerName + "</th>";
            for(String level : counts.keySet()){
                line += "<th>" + counts.get(level) + "</th>";
            }
            line += "</tr>";
            body += line;
        }
        return body;
    }
}
