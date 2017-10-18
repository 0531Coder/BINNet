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

    /**
     *
     * @param webView   处理这个Action的webview
     * @param params    与原生交互携带的参数
     * @param jsmethod  原生与前端交互所执行的回调方法
     */
    public abstract void onAction(WebView webView, String params, String jsmethod);
}
