package com.sheyuan.hybridlibrary.action;

import com.sheyuan.baselibrary.rxbus2.RxBus;
import com.sheyuan.hybridlibrary.param.HybridParamKeyValue;
import com.tencent.smtt.sdk.WebView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xiaoma on 2016/12/9.
 */

public class HybridActionKeyValue extends HybridAction {
    @Override
    public void onAction(WebView webView, String params, String jsmethod) {
        HybridParamKeyValue paramKeyValue = new HybridParamKeyValue();
        try {
            JSONObject object = new JSONObject(params);
            paramKeyValue.setMethod(object.getString("method"));
            paramKeyValue.setXkey(object.getString("xkey"));
            paramKeyValue.setData(object.getJSONObject("data"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        paramKeyValue.setCallback(jsmethod);
        RxBus.getDefault().post(paramKeyValue);

    }
}
