package com.sheyuan.hybridlibrary.utils;

import com.google.gson.Gson;

/**
 * Created by xiaoma on 2016/12/7.
 */

public class GsonUtils {

    public static String Gson2String(Object obj) {
        if (null == obj) {
            return null;
        }
        Gson gson = new Gson();
        String data = gson.toJson(obj);
        return data;
    }
    
  
}
