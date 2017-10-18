package com.sheyuan.hybridlibrary.param;

import org.json.JSONObject;

/**
 * Created by moutain on 17-10-12 11:44.
 */

public class HybridParamAjax extends BaseHybridParam{

    public ACTION tagName = ACTION.GET;
    public String url;
    public JSONObject param; // this param is json data
    public enum ACTION {
        GET("get"), POST("post"),;
        private String mValue;

        ACTION(String value) {
            mValue = value;
        }

        //根据传入的参数来确定Action的类型
        public static ACTION findByAbbr(String value) {
            for (ACTION currEnum : ACTION.values()) {
                if (currEnum.mValue.equals(value)) {
                    return currEnum;
                }
            }
            return GET;
        }
    }
}
