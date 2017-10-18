package com.sheyuan.hybridlibrary.param;

import org.json.JSONObject;

/**
 * Created by moutain on 17-10-18 11:21.
 */

public class HybridParamSign extends BaseHybridParam {
    private JSONObject param;

    public JSONObject getParam() {
        return param;
    }

    public void setParam(JSONObject param) {
        this.param = param;
    }
}
