package com.sheyuan.hybridlibrary.ui;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.sheyuan.baselibrary.rxbus2.RxBus;
import com.sheyuan.baselibrary.rxbus2.Subscribe;
import com.sheyuan.baselibrary.rxbus2.ThreadMode;
import com.sheyuan.hybridlibrary.R;
import com.sheyuan.hybridlibrary.bean.H5RequestEntity;
import com.sheyuan.hybridlibrary.core.HybridConstant;
import com.sheyuan.hybridlibrary.param.BaseHybridParam;
import com.sheyuan.hybridlibrary.param.HybridParamAjax;
import com.sheyuan.hybridlibrary.param.HybridParamBack;
import com.sheyuan.hybridlibrary.param.HybridParamCallback;
import com.sheyuan.hybridlibrary.param.HybridParamKeyValue;
import com.sheyuan.hybridlibrary.param.HybridParamLocation;
import com.sheyuan.hybridlibrary.param.HybridParamNativeUI;
import com.sheyuan.hybridlibrary.param.HybridParamShowHeader;
import com.sheyuan.hybridlibrary.param.HybridParamShowLoading;
import com.sheyuan.hybridlibrary.param.HybridParamSign;
import com.sheyuan.hybridlibrary.param.HybridParamUpdateHeader;
import com.sheyuan.hybridlibrary.param.HybridParamUserInfo;
import com.tencent.smtt.sdk.ValueCallback;

/**
 * Created by moutain on 17-9-21 17:24.
 */

public class HybridWebViewActivity extends HybridBaseActivity {

    private QMUITopBar qmuiTopBar;
    private ProgressBar pb_webview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        qmuiTopBar = (QMUITopBar) findViewById(R.id.qmui_topbar);
        pb_webview = (ProgressBar) findViewById(R.id.pb_webview);
        //当前页面是否需要显示导航栏
        boolean hasNavgation = getIntent().getBooleanExtra(HybridConstant.INTENT_EXTRA_KEY_HASNAVGATION, true);
        if (hasNavgation) {
            // TODO: 17-10-12 将qmui导航栏按要求配置

        } else {
            qmuiTopBar.setVisibility(View.GONE);
        }
        //如果获取到的url不为空,就加载这个url
        if (!TextUtils.isEmpty(getUrl())) {
            loadUrl(getUrl());
        }
        //注册rxbus接收通知
        RxBusRegiseter();
    }

    private void RxBusRegiseter() {
        //注册RxBus的订阅者
        if (!RxBus.getDefault().isRegistered(this)) {
            RxBus.getDefault().register(this);
        }
    }
    private void RxBusunregiseter() {
        //解除RxBus的订阅
        if (RxBus.getDefault().isRegistered(this)) {
            RxBus.getDefault().unregister(this);
        }
    }

    //注解的方式执行RxBus的回调函数
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void RxBack(BaseHybridParam object) {
        if (null == object) {
            return;
        }
        if (object instanceof HybridParamBack) {  //返回键处理
            HybridParamBack msg = (HybridParamBack) object;
            handlerBack(msg);
        } else if (object instanceof HybridParamShowLoading) { //是否显示进度条
            HybridParamShowLoading msg = (HybridParamShowLoading) object;
            handlerLoading(msg);
        } else if (object instanceof HybridParamShowHeader) {  //是否显示头部
            HybridParamShowHeader header = (HybridParamShowHeader) object;
            handlerShowHeader(header);
        } else if (object instanceof HybridParamAjax) { //处理网络请求
            HybridParamAjax ajax = (HybridParamAjax) object;
            handlerHybridParamAjax(ajax);
        } else if (object instanceof HybridParamNativeUI) { //调用原生UI处理
            HybridParamNativeUI nativeUI = (HybridParamNativeUI) object;
            handlerNativeUI(nativeUI);
        } else if (object instanceof HybridParamUpdateHeader) { //更新header处理
            HybridParamUpdateHeader header = (HybridParamUpdateHeader) object;
            updateHeader(header);
        } else if (object instanceof HybridParamLocation) {
            HybridParamLocation location = (HybridParamLocation) object;
            handlerLocation(location);
        } else if (object instanceof HybridParamUserInfo) {
            HybridParamUserInfo userInfo = (HybridParamUserInfo) object;
            handlerUser(userInfo);
        } else if (object instanceof HybridParamKeyValue) {//key-value键值对处理
            HybridParamKeyValue keyValue = (HybridParamKeyValue) object;
            handlerKeyValue(keyValue);
        } else if (object instanceof HybridParamSign) {
            HybridParamSign sign = (HybridParamSign) object;
            handlerSign(sign);
        }
    }

    private void handlerSign(HybridParamSign sign) {
        //处理签名参数，暂时先不用
    }

    private void handlerKeyValue(HybridParamKeyValue keyValue) {
        //处理key-value存取
    }

    private void handlerUser(HybridParamUserInfo userInfo) {
        //加密传递用户信息
    }

    private void handlerLocation(HybridParamLocation location) {
        //向前端传递位置信息
    }

    private void updateHeader(HybridParamUpdateHeader header) {
        //导航栏更新
    }

    private void handlerNativeUI(HybridParamNativeUI nativeUI) {
        //调用原生的UI组件
    }

    private void handlerHybridParamAjax(HybridParamAjax ajax) {
        //处理ajax请求
    }

    private void handlerShowHeader(HybridParamShowHeader header) {
        //创建原生的导航栏
    }

    private void handlerLoading(HybridParamShowLoading msg) {
        //显示等待提示框
    }

    private void handlerBack(HybridParamBack msg) {
        //返回键处理
    }

    @Override
    public boolean isDestroyed() {
        return super.isDestroyed();
    }

    public void handleHybridCallback(HybridParamCallback param) {
        if(isDestroyed())return;
        if (param.getCallback() != null) {
            String script = "Hybrid.callback(" + new H5RequestEntity(param.getCallback(), param.getData()).toString() + ")";
            // TODO: 17-10-18 X5内核有对版本的判断，验证只调用evaluateJavascript能否通过
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                webview.evaluateJavascript(script, new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        //不关注返回值,不对返回值进行处理
                    }
                });
            } else {
                webview.loadUrl("javascript:Hybrid.callback(" + new H5RequestEntity(param.getCallback(), param.getData()).toString() + ")");
//                String js = "javascript:(function(){var result= Hybrid.callback(" + new H5RequestEntity(param.callback, param.data).toString() + ");window." + HybridConfig.JSInterface + ".stringByEvaluatingJavaScriptFromString(\"" + param.tagname + "\",result);})()";
//                Log.e("vane","js="+js);
//                mWebView.loadUrl(js);
            }
        } else if ("back".equals(param.getTagname())) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        RxBusunregiseter();

    }

    @Override
    protected void onResume() {
        super.onResume();
        RxBusRegiseter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusunregiseter();
    }
}
