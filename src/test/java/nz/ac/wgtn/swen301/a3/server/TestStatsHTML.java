package nz.ac.wgtn.swen301.a3.server;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;

public class TestStatsHTML {

    @Test
    public void testStatsServlet1() throws IOException {
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
    public void testStatsServlet2(){
        //TODO: So far only checking that the html is valid - must also check the format is as specified by assignment
    }
}
