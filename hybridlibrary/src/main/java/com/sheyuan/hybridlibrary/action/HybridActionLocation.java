package com.sheyuan.hybridlibrary.action;

import com.sheyuan.baselibrary.rxbus2.RxBus;
import com.sheyuan.hybridlibrary.param.HybridParamLocation;
import com.tencent.smtt.sdk.WebView;


/**
 * Created by SheYuanWang on 2016/12/1.
 */

public class HybridActionLocation extends HybridAction {
    @Override
    public void onAction(WebView webView, String params, String jsmethod) {
        HybridParamLocation hybridParam = new HybridParamLocation();
        hybridParam.setCallback(jsmethod);
        RxBus.getDefault().post(hybridParam);
    }
}
