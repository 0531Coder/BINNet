package com.sheyuan.hybridlibrary.action;
import com.sheyuan.baselibrary.rxbus2.RxBus;
import com.sheyuan.hybridlibrary.param.HybridParamAjax;
import com.tencent.smtt.sdk.WebView;
import org.json.JSONException;
import org.json.JSONObject;

public class HybridActionAjaxPost extends HybridAction {

    @Override
    public void onAction(final WebView webView, String params, final String jsmethod) {
//        HybridParamAjax hybridParam = mGson.fromJson(params, HybridParamAjax.class);
        HybridParamAjax hybridParam = new HybridParamAjax();
        //手动解析url和param
 //       {"url":"http://api.kuai.baidu.com/city/getstartcitys","param":{"a":1,"b":2}}
        try {
            JSONObject object = new JSONObject(params);
            hybridParam.url = object.getString("url");
            hybridParam.param = object.getJSONObject("param");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        hybridParam.tagName = HybridParamAjax.ACTION.POST;
        hybridParam.setCallback(jsmethod);
//        EventBus.getDefault().post(hybridParam);
        RxBus.getDefault().post(hybridParam);
    }
}

