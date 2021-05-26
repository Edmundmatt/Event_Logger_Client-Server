package nz.ac.wgtn.swen301.a3.server;


import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;

public class Persistency {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static List<JSONObject> DB = new ArrayList<>();
    private static final int NUM_OF_EVENTS = 50;

    public static List<JSONObject> newLogObjects(){
        List<JSONObject> logObjects = new ArrayList<>();
        for(int i = 0; i < NUM_OF_EVENTS; i++){
            logObjects.add(randomLogObject(i + 1));
        }
        //Randomise event order for test
//        Collections.shuffle(logObjects);
        return logObjects;
    }

    public static void setDB(List<JSONObject> list){
        DB = list;
    }

    public static List<JSONObject> getLogs(){
        return DB;
    }

    private static JSONObject randomLogObject(int count){
        Random r = new Random();
        String[] levels = {"ALL", "TRACE", "DEBUG" , "INFO" , "WARN", "ERROR", "FATAL"};
        String level = levels[r.nextInt(levels.length)];

        JSONObject jsonObj = new JSONObject();
        //Create unique ID`
        String id = UUID.randomUUID().toString();
        jsonObj.put("id", id);
        jsonObj.put("logger", "logger " + count);
        jsonObj.put("level", level);
        jsonObj.put("starttime", sdf.format(new Timestamp(System.currentTimeMillis())));
        jsonObj.put("thread", "main");
        jsonObj.put("message", "Message: Level - " + level);
        jsonObj.put("errorDetails", "string");

        return jsonObj;
    }

    public JSONObject testObject(){

        JSONObject jsonObj = new JSONObject();
        //Create unique ID`
        String id = UUID.randomUUID().toString();
        jsonObj.put("id", id);
        jsonObj.put("logger", "a3TestLogger");
        jsonObj.put("level", "INFO");
        jsonObj.put("starttime", sdf.format(new Timestamp(System.currentTimeMillis())));
        jsonObj.put("thread", "main");
        jsonObj.put("message", "Message: Level - " + "INFO");
        jsonObj.put("errorDetails", "string");

        return jsonObj;
    }

}
