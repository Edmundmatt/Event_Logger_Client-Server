package nz.ac.wgtn.swen301.a3.server;

import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StatsCSVServlet extends HttpServlet {

    private static List<JSONObject> logObjects = Persistency.getLogs();

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        //Stats builder build table

        res.setContentType("text/csv; charset=UTF-8");

    }
}
