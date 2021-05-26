package nz.ac.wgtn.swen301.a3.server;

import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LogsServlet extends HttpServlet {

    static{Persistency.setDB(Persistency.newLogObjects()); }
    private static List<JSONObject> logObjects = Persistency.getLogs();

    private static List<String> levels =
            Arrays.asList("ALL", "TRACE", "DEBUG","INFO", "WARN", "ERROR", "FATAL", "OFF");

    public LogsServlet(){

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        //Get parameters
        String limit = req.getParameter("limit");
        String level = req.getParameter("level");
        //Check for bad inputs
        if(limit == null || level == null){
            res.sendError(HttpServletResponse.SC_BAD_REQUEST); //Code 400
            return;
        }

        //Get the list of logObjects above the minimum level
        List<JSONObject> sortedLogObjects = new ArrayList<>();

        int minimumLevel = levels.indexOf(level.toUpperCase());
        for(JSONObject logObject : logObjects){
            if(levels.indexOf(logObject.get("level").toString().toUpperCase()) >= minimumLevel){
                sortedLogObjects.add(logObject);
            }
        }


        int limitInt = Integer.parseInt(limit);
        if(sortedLogObjects.size() < limitInt){
            limitInt = sortedLogObjects.size();
        }

        //Output
        res.setContentType("application/json");
        PrintWriter pw = res.getWriter();
        for(int i = 0; i < limitInt; i++) pw.println(sortedLogObjects.get(i).toString());
//        for(int i = 0; i < limitInt; i++) pw.println(sortedLogObjects.get(i));
        pw.close();
        res.setStatus(HttpServletResponse.SC_OK); //Code 200
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
        //Get JSONObject from request
        BufferedReader reader = req.getReader();
        String line;
        String output = "";
        while((line = reader.readLine()) != null){
            output += line + "\n";
        }
        //Check for bad input
        if(output.equals("")){
            res.sendError(HttpServletResponse.SC_BAD_REQUEST); //Code 400
            return;
        }
        JSONObject object = new JSONObject(output);

        //Check for empty JSONObject
        if(object.toString().equals("{}")){
            res.sendError(HttpServletResponse.SC_BAD_REQUEST); //Code 400
            return;
        }

        //Check if code with id already exists
        for(JSONObject object1 : logObjects){
            if(object1.get("id").equals(object.get("id"))){
                res.sendError(HttpServletResponse.SC_CONFLICT); //Code 409
                return;
            }
        }
        logObjects.add(object);

        //Post logEvent String to http response
        res.setStatus(HttpServletResponse.SC_CREATED); //Code 201
    }

    public void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException{
        //Clear JSONObjects from List
        logObjects.clear();
        res.setStatus(HttpServletResponse.SC_OK); //Code 200

    }

    public List<JSONObject> getLogObjects(){
        return this.logObjects;
    }
}
