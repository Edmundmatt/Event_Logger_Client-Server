package nz.ac.wgtn.swen301.a3.server;

import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LogsServlet extends HttpServlet {

    private static List<JSONObject> logObjects;

    public LogsServlet(){
        logObjects = Persistency.getLogs();
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

        //Get Logs 'limit' number events of the level type
        List<JSONObject> sortedLogObjects = logObjects.stream()
                .filter((object -> object.get("level") == level))
//                .sorted(Comparator.comparing())
                .collect(Collectors.toList());
        //Sort JSONObjects by time

        sortedLogObjects.subList(0, Integer.parseInt(limit) - 1);

        //Output to html
        res.setContentType("text/plain");
        PrintWriter pw = res.getWriter();
        pw.println("<html>");
        pw.println("<head>");
        pw.println("<title>Logs</title>");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("<p>[</p>");
        for(JSONObject object : sortedLogObjects){
            pw.println("<p style=\"margin-left: 40px\">{</p>");

            //Check the JSONObject tags are correct

            pw.println("<p style=\"margin-left: 80px\">");
//            pw.println("\"id\": \""+ object.get("id") +"\",<br>");                       //id
            pw.println("\"message\": \""+ object.get("message") +"\",<br>");             //message
            pw.println("\"timestamp\": \""+ object.get("starttime") +"\",<br>");         //timestamp
            pw.println("\"thread\": \""+ object.get("thread") +"\",<br>");               //thread
            pw.println("\"logger\": \""+ object.get("logger") +"\",<br>");               //logger
            pw.println("\"level\": \""+ object.get("level") +"\",<br>");                 //level
//            pw.println("\"errorDetails\": \""+ object.get("errorDetails") +"\"<br>");   //error details
            pw.println("</p>");

            pw.println("<p style=\"margin-left: 40px\">}</p>");
        }
        pw.println("<p>]</p>");

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
        logObjects.add(object);

        //Post logEvent String to http response

        res.setStatus(HttpServletResponse.SC_CREATED);
    }

    public void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException{
        //Clear JSONObjects from List
        logObjects.clear();
        res.setStatus(HttpServletResponse.SC_OK);

    }
}
