package com.sheyuan.hybridlibrary.action;
import com.sheyuan.baselibrary.rxbus2.RxBus;
import com.sheyuan.hybridlibrary.param.HybridParamUserInfo;
import com.tencent.smtt.sdk.WebView;

/**
 * Created by SheYuanWang on 2016/12/1.
 */

public class HybridActionUserInfo extends HybridAction {
    @Override
    public void onAction(WebView webView, String params, String jsmethod) {
        HybridParamUserInfo userInfo = new HybridParamUserInfo();
        userInfo.setCallback(jsmethod);
        RxBus.getDefault().post(userInfo);
    }
}
