package com.sheyuan.hybridlibrary.action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sheyuan.hybridlibrary.param.HybridParamAnimation;
import com.sheyuan.hybridlibrary.param.HybridParamType;

/**
 * Created by moutain on 17-9-20 09:51.
 */

public class HybridAction {
    public static Gson mGson;
    static {
        mGson = new GsonBuilder()
                .registerTypeAdapter(HybridParamAnimation.class,new HybridParamAnimation.TypeDeserializer())
                .registerTypeAdapter(HybridParamType.class,new HybridParamType.TypeDeserializer())
                .create();
    }
}
