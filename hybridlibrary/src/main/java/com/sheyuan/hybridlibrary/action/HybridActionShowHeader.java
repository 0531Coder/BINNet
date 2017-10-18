package com.sheyuan.hybridlibrary.action;
import com.sheyuan.baselibrary.rxbus2.RxBus;
import com.sheyuan.hybridlibrary.param.HybridParamShowHeader;
import com.tencent.smtt.sdk.WebView;

/**
 * Created by vane on 16/6/6.
 */

public class HybridActionShowHeader extends HybridAction {

    @Override
    public void onAction(WebView webView, String params, String jsmethod) {
        HybridParamShowHeader hybridParam = mGson.fromJson(params, HybridParamShowHeader.class);
//        EventBus.getDefault().post(hybridParam);
        RxBus.getDefault().post(hybridParam);
    }
}
