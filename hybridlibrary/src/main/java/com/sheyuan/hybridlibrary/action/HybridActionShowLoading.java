package com.sheyuan.hybridlibrary.action;
import com.sheyuan.baselibrary.rxbus2.RxBus;
import com.sheyuan.hybridlibrary.param.HybridParamShowLoading;
import com.tencent.smtt.sdk.WebView;

/**
 * Created by vane on 16/6/6.
 */

public class HybridActionShowLoading extends HybridAction {

    @Override
    public void onAction(WebView webView, String params, String jsmethod) {
        HybridParamShowLoading hybridParam = mGson.fromJson(params, HybridParamShowLoading.class);
//        EventBus.getDefault().post(hybridParam);
        RxBus.getDefault().post(hybridParam);
    }
}
