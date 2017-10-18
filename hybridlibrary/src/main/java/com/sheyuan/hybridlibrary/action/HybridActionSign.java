package com.sheyuan.hybridlibrary.action;

import com.sheyuan.baselibrary.rxbus2.RxBus;
import com.sheyuan.hybridlibrary.param.HybridParamSign;
import com.tencent.smtt.sdk.WebView;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xiaoma on 2016/12/15.
 */

public class HybridActionSign extends HybridAction {

    private JSONObject object;

    @Override
    public void onAction(WebView webView, String params, String jsmethod) {
        HybridParamSign signParam = new HybridParamSign();
        try {
            object = new JSONObject(params);
        } catch (JSONException e) {
            e.printStackTrace();
        }
            signParam.setParam(object);
            signParam.setCallback(jsmethod);
        RxBus.getDefault().post(signParam);
    }
}
