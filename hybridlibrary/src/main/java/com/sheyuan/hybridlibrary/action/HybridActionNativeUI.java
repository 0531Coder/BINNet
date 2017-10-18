package com.sheyuan.hybridlibrary.action;


import com.sheyuan.baselibrary.rxbus2.RxBus;
import com.sheyuan.hybridlibrary.core.HybridConstant;
import com.sheyuan.hybridlibrary.param.HybridParamNativeUI;
import com.tencent.smtt.sdk.WebView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xiaoma on 2016/11/22.
 * native类型可以拓展，可以参考native
 */

public class HybridActionNativeUI extends HybridAction {

    @Override
    public void onAction(WebView webView, String params, String jsmethod) {
        HybridParamNativeUI hybridParam = new HybridParamNativeUI();
       
        try {
            JSONObject object = new JSONObject(params);
            hybridParam.setType(object.getString("type"));
            switch (object.getString("type")){
                case HybridConstant.UI_TOAST:
                    hybridParam.setContent(object.getString("content"));
                    break;
                case HybridConstant.UI_SHARE:
                    hybridParam.setShareinfo(object.getJSONObject("shareinfo"));
                    break;
                case HybridConstant.UI_PICS:
                    hybridParam.setImgNumber(object.getString("imgnum"));
                    break;
                case HybridConstant.UI_DIALOG:
                    hybridParam.setContent(object.getString("content"));
                    hybridParam.setSure(object.getString("sure"));
                    hybridParam.setCancel(object.getString("cancel"));
                    break;
                case HybridConstant.UI_TEL:
                    hybridParam.setPhoneNumber(object.getString("content"));
                    break;
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        hybridParam.setCallback(jsmethod);
        RxBus.getDefault().post(hybridParam);
    }


}
