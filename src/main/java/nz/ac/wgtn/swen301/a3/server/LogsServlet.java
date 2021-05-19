package nz.ac.wgtn.swen301.a3.server;

import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LogsServlet extends HttpServlet {

    private ArrayList<JSONObject> events;

    public LogsServlet(){
        Persistency p = new Persistency();
        events = p.newEvents();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        //Get parameters
        String limit = req.getParameter("limit");
        String level = req.getParameter("level");
        //Check for bad inputs
        if(limit == null || level == null){
            res.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        //Get Logs 'limit' number events of the level type - in order by time
        List<JSONObject> sortedEvents = events.stream()
                .filter((object -> object.get("level") == level))
//                .sorted(Comparator.comparing())
                .collect(Collectors.toList());
        sortedEvents.subList(0, Integer.parseInt(limit) - 1);

        //Output to html
        res.setContentType("text/plain");
        PrintWriter pw = res.getWriter();
        pw.println("<html>");
        pw.println("<head>");
        pw.println("<title>Logs</title>");
        pw.println("</head>");
        pw.println("<body>");
        for(JSONObject object : sortedEvents){

        }
        pw.println("<p>"+ "" +"</p>");

        pw.println("</body>");
        pw.println("</html>");

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
            res.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        events.add(object);
        //Post logEvent String to http response


    }

    public void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException{

    }
}
