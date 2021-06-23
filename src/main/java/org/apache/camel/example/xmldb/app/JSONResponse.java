package org.apache.camel.example.xmldb.app;

import java.util.Map;
import java.util.HashMap;

import org.json.JSONObject;

public class JSONResponse {

    public JSONResponse() { super(); }
    
    public static String generateResponse(boolean success, Map<String, String> data, String message) {

        JSONObject jsonResponse = new JSONObject();

        jsonResponse.put("success", success ? "1" : "0");

        if (!data.isEmpty()) {
            JSONObject dataObject = new JSONObject();
            for (Map.Entry<String, String> entry : data.entrySet()) {
                dataObject.put(entry.getKey(), entry.getValue());
            }    

            jsonResponse.put("data", dataObject);
        }
        
        if (message != null) {
            jsonResponse.put("message", message);
        }
        
        return jsonResponse.toString();
    }

}