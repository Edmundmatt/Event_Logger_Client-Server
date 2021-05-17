package nz.ac.wgtn.swen301.a3.server;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Persistency {
    private static Logger LOGGER = Logger.getLogger("org.apache.log4j");
    private static ArrayList<LoggingEvent> DB = new ArrayList<>();
    private static final int NUM_OF_EVENTS = 50;

    public ArrayList<LoggingEvent> newEvents(){
        ArrayList<LoggingEvent> events = new ArrayList<>();
        for(int i = 0; i < NUM_OF_EVENTS; i++){
            events.add(randomEvent());
        }
        //Randomise event order for test
        Collections.shuffle(events);
        return events;
    }

    public ArrayList<LoggingEvent> getLogs(){
        return this.DB;
    }

    private static LoggingEvent randomEvent(){
        Random r = new Random();
        Level[] levels = {Level.TRACE, Level.DEBUG, Level.INFO, Level.WARN, Level.ERROR, Level.FATAL};
        Level level = levels[r.nextInt(levels.length)];
        LoggingEvent event = new LoggingEvent("org.apache.logging.log4j", LOGGER, System.currentTimeMillis(),
                level, "Message: Level - " + level, null);

        return event;
    }

}
