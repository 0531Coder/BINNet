package com.sheyuan.hybridlibrary.core;

/**
 * Created by moutain on 17-9-20 14:47.
 */

public interface HybridConstant {
    //forward
    String INTENT_EXTRA_KEY_ANIMATION = "intent_extra_key_animation";
    String INTENT_EXTRA_KEY_TOTAB = "intent_extra_key_TOTAB";
    String INTENT_EXTRA_KEY_HASNAVGATION = "intent_extra_key_hasnavgation";
    String GET_PARAM = "param";
    String GET_CALLBACK = "callback";
    /**
     * 混合开发APP版本，如果增加新的协议的话，导致之前的版本不兼容，需要升级版本号
     */
    String HYBRID_VERSION = "1-0-1";
    //原生组件支持
    String UI_TOAST = "toast";
    String UI_SHARE = "share";
    String UI_PICS = "imgupload";
    String UI_DIALOG = "dialog";
    String UI_TEL = "tel";
    String UI_close = "close";
    String UI_SCREEN_CUT = "screenCut";
    //k-v键值对
    String KV_GET = "get";
    String KV_PUT = "put";
    String KV_CLEAR = "clear";
}
