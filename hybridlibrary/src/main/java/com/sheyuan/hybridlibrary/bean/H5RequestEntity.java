package com.sheyuan.hybridlibrary.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by moutain on 17-10-18 16:27.
 */

public class H5RequestEntity {
    private String callback;
    private String data;
    public H5RequestEntity(String callback, String data) {
        this.callback = callback;
        this.data = data;
    }
    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("callback", callback);
            jsonObject.put("data", data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
