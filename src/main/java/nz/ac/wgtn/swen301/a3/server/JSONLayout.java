package nz.ac.wgtn.swen301.a3.server;

import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;
import org.json.JSONObject;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.List;

public class JSONLayout extends Layout
{
    public static void main( String[] args ) {

    }

    @Override
    public String format(LoggingEvent loggingEvent) {
        //Create JSON object
        JSONObject jsonObj = new JSONObject();

        try {
            String logger = loggingEvent.getLoggerName();
            Level level = loggingEvent.getLevel();
            long time = loggingEvent.getTimeStamp();
            String thread = loggingEvent.getThreadName();
            String message = loggingEvent.getRenderedMessage();


            jsonObj.put("message", message);
            jsonObj.put("starttime", time);
            jsonObj.put("thread", thread);
            jsonObj.put("logger", logger);
            jsonObj.put("level", level);
        }catch(Exception e){
            e.printStackTrace();
        }

        return jsonObj.toString();
    }

    @Override
    public boolean ignoresThrowable() {
        return false;
    }

    @Override
    public void activateOptions() {

    }

    public String defaultFormatting(LoggingEvent event){
        PatternLayout layout = new PatternLayout();
        return layout.format(event);
    }

    private String eventsToString(List<LoggingEvent> logEvents){
        String output = "[";
        for(int i = 0; i < logEvents.size(); i++){
            if(logEvents.get(i) != null) output += eventToString(logEvents.get(i));
            if(i != logEvents.size()-1) output += ",";
        }
        output += "\n]";
        return output;
    }

    private String eventToString(LoggingEvent logEvent){
        return "\n\t{\n" +
                "\t\t\"logger\":\"" + logEvent.getLoggerName() + "\",\n" +
                "\t\t\"level\":\"" + logEvent.getLevel() + "\",\n" +
                "\t\t\"starttime\":\"" + logEvent.getTimeStamp() + "\",\n" +
                "\t\t\"thread\":\"" + logEvent.getThreadName() + "\",\n" +
                "\t\t\"message\":\"" + logEvent.getRenderedMessage() + "\"\n" +
                "\t}";
    }
}