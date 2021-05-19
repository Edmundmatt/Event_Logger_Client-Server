package nz.ac.wgtn.swen301.a3.server;

import org.json.JSONObject;

public class JSONLogObject {
    String level;
    String logger;
    String startTime;
    String thread;
    String message;

    public JSONLogObject(String input){
        String[] inputs = input.split("\n");
        JSONObject object = new JSONObject(input);
    }
}
