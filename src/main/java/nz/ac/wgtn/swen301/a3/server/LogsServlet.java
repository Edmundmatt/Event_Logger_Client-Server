package nz.ac.wgtn.swen301.a3.server;


import org.apache.htt p.HttpException;
import org.apache.log4j.spi.LoggingEvent;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class LogsServlet extends HttpServlet {

    private ArrayList<LoggingEvent> events;

    public LogsServlet(){
        Persistency p = new Persistency();
        events = p.newEvents();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String limit = req.getParameter("limit");
        String level = req.getParameter("level");
        res.setContentType("text/plain");
        PrintWriter out = res.getWriter();

        events.sort();
        out.close();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{

    }

    public void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException{

    }


}
