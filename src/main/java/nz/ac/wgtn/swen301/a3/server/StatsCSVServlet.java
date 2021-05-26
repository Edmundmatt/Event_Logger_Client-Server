package nz.ac.wgtn.swen301.a3.server;

import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatsCSVServlet extends HttpServlet {

    private static List<JSONObject> logObjects = Persistency.getLogs();

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        String[] levels = {"ALL", "DEBUG", "INFO", "WARN", "ERROR", "FATAL", "TRACE", "OFF"};
        Map<String, Integer> counts = new HashMap<>();
        String header = "logger";
        for(int i = 0; i < levels.length; i++){
            counts.put(levels[i], 0);
            header += "\t" + levels[i];
        }
        header += "\n";

        //Do count
        for(JSONObject logObject : logObjects){

            String level = logObject.get("level").toString();
            for(String key : counts.keySet()){
                if(key.equals(level)) counts.put(key, counts.get(key)+1);
            }
        }

        //Build table body
        //This assumes the keyset is in order
        String body = "loggerName";
        for(String key : counts.keySet()){
            body += "\t" + counts.get(key);
        }
        body += "\n";

        res.setContentType("text/csv;");
        PrintWriter pw = res.getWriter();
//        res.setHeader(header);
        pw.println(header);
        pw.println(body);

        //Output response code 200
        res.setStatus(HttpServletResponse.SC_OK);

    }
}
