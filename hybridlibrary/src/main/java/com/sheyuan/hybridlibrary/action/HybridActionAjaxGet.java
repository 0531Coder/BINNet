package com.sheyuan.hybridlibrary.action;

import com.sheyuan.baselibrary.rxbus2.RxBus;
import com.sheyuan.hybridlibrary.param.HybridParamAjax;
import com.tencent.smtt.sdk.WebView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by moutain on 17-10-12 11:41.
 */

public class HybridActionAjaxGet extends HybridAction {
    @Override
    public void onAction(WebView webView, String params, String jsmethod) {
        HybridParamAjax hybridParam = new HybridParamAjax();
        try {
            JSONObject object = new JSONObject(params);
            hybridParam.url = object.getString("url");
            hybridParam.url = object.getString("param");
        }catch (JSONException e){
            e.printStackTrace();
        }
        hybridParam.setCallback(jsmethod);
        RxBus.getDefault().post(hybridParam);
    }
}
