package com.sheyuan.hybridlibrary.param;

import org.json.JSONObject;

/**
 * Created by moutain on 17-10-18 11:21.
 */

public class HybridParamKeyValue extends BaseHybridParam {
    private String method;
    private String xkey;
    private JSONObject data;


    public String getMethod() {
        return method;
    }

    public String getXkey() {
        return xkey;
    }

    public JSONObject getData() {
        return data;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setXkey(String xkey) {
        this.xkey = xkey;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }
}
