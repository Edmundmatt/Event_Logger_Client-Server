package nz.ac.wgtn.swen301.a3.server;

import org.apache.log4j.spi.LoggingEvent;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

        System.out.println(events.toString());
        List<LoggingEvent> sortedEvents = events.stream()
                .sorted(Comparator.comparing(LoggingEvent::getTimeStamp))
                .collect(Collectors.toList());
        int limitInt = Integer.parseInt(limit);
        sortedEvents.subList(0, limitInt - 1);

        System.out.println(sortedEvents.toString());
        out.close();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{

    }

    public void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException{

    }
}
