package com.sheyuan.hybridlibrary.core;

import com.sheyuan.baselibrary.base.BaseApplication;

/**
 * Created by moutain on 17-9-20 16:35.
 */

public interface HybridConfig {
    String HYBRID_RES_PATH = "webapp";
    String HYBRID_IMG_PATH = "imgRes";
    //指定使用原生接口的scheme
    String SCHEME = "hybrid";//原生Activity隐示意图跳转协议
    String ACTIONPRE = BaseApplication.getAppContext().getPackageName()+"hybrid.";//配置是intent-filter中的action的前缀
    String GET_PARAM = "param";
    String GET_CALLBACK = "callback";
    String HYBRID_VERSION = "1.0.0";
    String JSInterface = "HybridJSInterface";
}
