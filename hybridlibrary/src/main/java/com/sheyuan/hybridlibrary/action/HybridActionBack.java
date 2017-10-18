package com.sheyuan.hybridlibrary.action;
import com.sheyuan.baselibrary.rxbus2.RxBus;
import com.sheyuan.hybridlibrary.param.HybridParamBack;
import com.tencent.smtt.sdk.WebView;

public class HybridActionBack extends HybridAction {

    @Override
    public void onAction(WebView webView, String params, String jsmethod) {
        RxBus.getDefault().post(new HybridParamBack());
    }
}
