package nz.ac.wgtn.swen301.a3.server;

import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LogsServlet extends HttpServlet {

    private static List<JSONObject> logObjects = Persistency.getLogs();

    public LogsServlet(){
        logObjects = Persistency.getLogs();
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

        //Comparator for log times
//        Comparator<JSONObject> comparator = new Comparator<JSONObject>() {
//            @Override
//            public int compare(JSONObject o1, JSONObject o2) {
//                return Long.parseLong(o1.get("starttime")).compareTo(o2.get("starttime"));
//            }
//        };


        //Get Logs 'limit' number events of the level type
        List<JSONObject> sortedLogObjects = logObjects.stream()
                .filter((object -> object.get("level") == level))
                .collect(Collectors.toList());
        Collections.reverse(sortedLogObjects);

        int limitInt = Integer.parseInt(limit);
        if(sortedLogObjects.size() < limitInt){
            limitInt = sortedLogObjects.size();
        }

        //Output
        res.setContentType("application/json");
        PrintWriter pw = res.getWriter();
        for(int i = 0; i < limitInt; i++) pw.println(sortedLogObjects.get(i).toString());

        pw.close();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
        //Get JSONObject from request
        BufferedReader reader = req.getReader();
        String line;
        String output = "";
        while((line = reader.readLine()) != null){
            output += line + "\n";
        }
        JSONObject object = new JSONObject(output);
        //Check for bad input
        if(object == null){
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
}
