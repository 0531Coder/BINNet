package com.sheyuan.hybridlibrary.action;
import com.sheyuan.baselibrary.rxbus2.RxBus;
import com.sheyuan.hybridlibrary.param.HybridParamUpdateHeader;
import com.tencent.smtt.sdk.WebView;

/**
 * Created by vane on 16/6/6.
 */

public class HybridActionUpdateHeader extends HybridAction  {

    @Override
    public void onAction(WebView webView, String params, String jsmethod) {
        HybridParamUpdateHeader hybridParam = mGson.fromJson(params, HybridParamUpdateHeader.class);
        hybridParam.setId(webView.hashCode());
        RxBus.getDefault().post(hybridParam);
    }

}
