package com.sheyuan.hybridlibrary.action;

import android.content.Intent;
import android.os.Bundle;

import com.sheyuan.hybridlibrary.core.HybridConfig;
import com.sheyuan.hybridlibrary.core.HybridConstant;
import com.sheyuan.hybridlibrary.param.HybridParamAnimation;
import com.sheyuan.hybridlibrary.param.HybridParamForward;
import com.sheyuan.hybridlibrary.ui.HybridWebViewActivity;
import com.sheyuan.hybridlibrary.utils.ActivityUtil;
import com.tencent.smtt.sdk.WebView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vane on 16/6/2.
 */

public class HybridActionForward extends HybridAction {

    private List<String> actionName;

    @Override
    public void onAction(WebView webView, String params, String jsmethod) {
        HybridParamForward hybridParam = mGson.fromJson(params, HybridParamForward.class);
        switch (hybridParam.getType()) {
            case NATIVE:
                //隐式意图添加器
                addIntentInfo();
                toNativePage(hybridParam,webView);
                break;
            case H5:
                if (HybridParamAnimation.NONE.equals(hybridParam.getAnimate())) {
                    webView.loadUrl(hybridParam.getTopage());
                } else {
                    Bundle bundle = new Bundle();
                    // TODO: 17-10-18 这个参数是暂时没有用到，用到的时候可以开启
//                    bundle.putString(HybridConstant.INTENT_EXTRA_KEY_TOPAGE, hybridParam.topage);
                    bundle.putSerializable(HybridConstant.INTENT_EXTRA_KEY_ANIMATION, hybridParam.getAnimate());
                    bundle.putBoolean(HybridConstant.INTENT_EXTRA_KEY_HASNAVGATION, hybridParam.isHasnavgation());
                    ActivityUtil.toSimpleActivity(webView.getContext(), HybridWebViewActivity.class, hybridParam.getAnimate(), bundle);
                }
                break;
        }
    }

    private void toNativePage(HybridParamForward hybridParam, WebView webView) {
        if (!actionName.contains(hybridParam.getTopage())) {
//            ToastUtil.show("暂未开通，敬请期待!");
            // TODO: 17-10-18 如果目标页面不存在就直接跳转到默认页面
            return;
        }
        //创建隐示意图去原生页面
        Intent intent = new Intent(HybridConfig.ACTIONPRE + hybridParam.getTopage());
        intent.putExtra("url", hybridParam.getBackurl());
        // TODO: 17-10-18 code暂时使用不到，如需要可随时开启
//        intent.putExtra("code", HybridConstant.HybridCode);
        ActivityUtil.toActivity(webView.getContext(), intent, hybridParam.getAnimate());
    }

    /**
     * 创建一个新的隐式意图之前先把action在集合重注册一下，
     * 避免H5调用不存在的Native页面时出现Crash。
     */
    public void addIntentInfo() {
        actionName = new ArrayList<>();
        actionName.add("sale"); //销售页签
        actionName.add("actieve"); //活动管理页面
        actionName.add("dynamic");//动态页签
        actionName.add("set"); //设置页面
        actionName.add("location"); //创建农庄页面，地图
        actionName.add("createFarm"); //创建农庄页面，地图
        actionName.add("debuglocation");  //加地图
        actionName.add("identification"); //实名认证页面
        actionName.add("edit");   //编辑农庄页面
        actionName.add("notification"); //消息页面
        actionName.add("bill"); //账单页面
        actionName.add("contact"); //联系客服页面
        actionName.add("retail"); //商品管理页面
        actionName.add("search"); //搜索页面
        actionName.add("account"); //庄主资料
        actionName.add("tohome"); //跳转首页
        actionName.add("tocenter"); //跳转首页
        actionName.add("publishTextImage");//跳转到图文
        actionName.add("dynamicDetail");//跳转到动态详情
        actionName.add("login");//跳转到登录页
//        actionName.add("wholesale");//跳转批发管理
    }
}
