package nz.ac.wgtn.swen301.a3.server;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class TestStatsHTML {

    @Test
    public void testStatsServletValidHTML() throws IOException {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        StatsServlet servlet = new StatsServlet();
        servlet.doGet(req, res);

        // Parse to validate parsing
        Document doc = Jsoup.parse(res.getContentAsString());
        Document validatedDoc = Jsoup.connect("http://validator.w3.org/nu")
                .data("fragment", doc.html())
                .data("st", "1")
                .post();
        // Assert that no errors are found in the parsing of the html
        assertTrue(validatedDoc.select("li.msg_err").isEmpty());

    }

    @Test
    public void testStatsServletContentType() throws IOException {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        StatsServlet servlet = new StatsServlet();
        servlet.doGet(req, res);

        assertEquals(res.getContentType(), "text/html");

    }

    @Test
    public void testStatsServletDataCorrectness() throws IOException {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        StatsServlet servlet = new StatsServlet();
        servlet.doGet(req, res);
    }

    private void htmlParser(MockHttpServletResponse res) throws IOException{
        Document doc = Jsoup.parse(res.getContentAsString());

    }
}
