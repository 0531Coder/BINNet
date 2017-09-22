package com.sheyuan.hybridlibrary.action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sheyuan.hybridlibrary.param.HybridParamAnimation;
import com.sheyuan.hybridlibrary.param.HybridParamType;
import com.tencent.smtt.sdk.WebView;

/**
 * Created by moutain on 17-9-20 09:51.
 */

public abstract class HybridAction {
    public static Gson mGson;
    static {
        mGson = new GsonBuilder()
                .registerTypeAdapter(HybridParamAnimation.class,new HybridParamAnimation.TypeDeserializer())
                .registerTypeAdapter(HybridParamType.class,new HybridParamType.TypeDeserializer())
                .create();
    }

    //统一处理调用原生接口的请求
    public abstract void onAction(WebView webView, String params, String jsmethod);
}
