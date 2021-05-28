package nz.ac.wgtn.swen301.a3.server;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class Stats {

    static{Persistency.setDB(Persistency.newLogObjects()); }
    private static List<JSONObject> logObjects = Persistency.getLogs();
    public Stats(){

    }

    public static HashMap<String, HashMap<String, Integer>> buildLoggersMap(String[] levels){
        //Do count
        //Create a map of different logs
        HashMap<String, HashMap<String, Integer>> loggers = new HashMap<>();
        for(JSONObject logObject: logObjects){
            String loggerName = logObject.get("logger").toString();
            //Add logger to map if not already present
            if(!loggers.containsKey(loggerName)){
                //Initialise each count for logger
                loggers.put(loggerName, new HashMap<>());
                for(int i = 0; i < levels.length; i++){
                    loggers.get(loggerName).put(levels[i], 0);
                }
            }
            //Next increment counts for each logger
            String level = logObject.get("level").toString();
            HashMap<String, Integer> counts = loggers.get(loggerName);
            for(String key : counts.keySet()){
                if(key.equals(level)) counts.put(key, counts.get(key)+1);
            }

        }
        return loggers;
    }

}
