package nz.ac.wgtn.swen301.a3.server;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Persistency {
    private static Logger LOGGER = Logger.getLogger("org.apache.log4j");
    private static List<JSONObject> DB = newLogObjects();
    private static final int NUM_OF_EVENTS = 50;

    public static List<JSONObject> newLogObjects(){
        List<JSONObject> logObjects = new ArrayList<>();
        for(int i = 0; i < NUM_OF_EVENTS; i++){
            logObjects.add(randomLogObject());
        }
        //Randomise event order for test
        Collections.shuffle(logObjects);
        return logObjects;
    }

    public static List<JSONObject> getLogs(){
        return DB;
    }

    private static JSONObject randomLogObject(){
        Random r = new Random();
        Level[] levels = {Level.TRACE, Level.DEBUG, Level.INFO, Level.WARN, Level.ERROR, Level.FATAL};
        Level level = levels[r.nextInt(levels.length)];
        LoggingEvent event = new LoggingEvent("org.apache.logging.log4j", LOGGER, System.currentTimeMillis(),
                level, "Message: Level - " + level, null);

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("logger", event.getLoggerName());
        jsonObj.put("level", event.getLevel());
        jsonObj.put("starttime", event.getTimeStamp());
        jsonObj.put("thread", event.getThreadName());
        jsonObj.put("message", event.getMessage());

        return jsonObj;
    }

}
