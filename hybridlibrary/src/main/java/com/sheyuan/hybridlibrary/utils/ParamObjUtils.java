package com.sheyuan.hybridlibrary.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by xiaoma on 2016/12/9.
 */

public class ParamObjUtils {
    
    public static HashMap<String,String> obj2Map(JSONObject obj){
        HashMap<String, String> map = new HashMap<>();
        Iterator it = obj.keys();
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            String value = null;
            try {
                value = obj.get(key) + "";
            } catch (JSONException e) {
                e.printStackTrace();

            }
            map.put(key, value);
        }
        return map;
    }
}
