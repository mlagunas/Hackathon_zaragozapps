package com.example.mlagunas.hackaton_app;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by planaspa on 7/11/15.
 */
public class HTTPRequest {

    private String url;
    private String type;
    private String data;
    private String contentType;

    public HTTPRequest(String apiBase, int dataSetId, String Query){
        this.url =  apiBase + "/data/query/" + dataSetId + "/sql";
        this.type = "POST";
        this.data = Query;
        this.contentType = "text/plain";
    }

    public String getJsonRequest() throws JSONException {

        // Build jsonObject
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("url", url);
        jsonObject.accumulate("type", type);
        jsonObject.accumulate("data", data);
        jsonObject.accumulate("contentType", contentType);

        //convert JSONObject to JSON to String
        return jsonObject.toString();
    }

}
