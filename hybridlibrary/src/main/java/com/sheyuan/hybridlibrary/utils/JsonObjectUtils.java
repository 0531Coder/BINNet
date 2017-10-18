package com.sheyuan.hybridlibrary.utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xiaoma on 2017/1/9.
 */

public class JsonObjectUtils {
    
    public static String getJsonObject(String key,String value){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(key,value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
